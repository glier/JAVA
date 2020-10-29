package client;


import client.models.MessageHistory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import client.controllers.AuthDialogController;
import client.controllers.ViewController;
import client.models.Network;

import java.io.*;
import java.util.List;


public class NetworkChatClient extends Application {

    public static final List<String> USERS_TEST_DATA = List.of("Oleg", "Alexey", "Peter");
    private static final String MSG_HIST_TEMPLATE = "_msg_hist.txt";

    private Stage primaryStage;
    private Stage authDialogStage;
    private Network network;
    private ViewController viewController;
    private AuthDialogController authController;
    private MessageHistory messageHistory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        network = new Network();
        if (!network.connect()) {
            showNetworkError("", "Failed to connect to server");
            return;
        }

        openAuthDialog(primaryStage);
        createChatDialog(primaryStage);
        network.waitMessages(viewController, authController);
    }

    private void createChatDialog(Stage primaryStage) throws java.io.IOException {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(NetworkChatClient.class.getResource("views/mainView.fxml"));

        Parent root = mainLoader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        viewController = mainLoader.getController();
        viewController.setNetwork(network);

        primaryStage.setOnCloseRequest(event -> network.close());
    }

    private void openAuthDialog(Stage primaryStage) throws java.io.IOException {
        FXMLLoader authLoader = new FXMLLoader();

        authLoader.setLocation(NetworkChatClient.class.getResource("views/authDialog.fxml"));
        Parent authDialogPanel = authLoader.load();
        authDialogStage = new Stage();

        authDialogStage.setTitle("Аутентификая чата");
        authDialogStage.initModality(Modality.WINDOW_MODAL);
        authDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(authDialogPanel);
        authDialogStage.setScene(scene);
        authDialogStage.show();


        authController = authLoader.getController();
        authController.setNetwork(network);
        authController.setClientApp(this);
    }

    public static void showNetworkError(String errorDetails, String errorTitle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Network Error");
        alert.setHeaderText(errorTitle);
        alert.setContentText(errorDetails);
        alert.showAndWait();
    }

    public void openChat() {
        authDialogStage.close();
        primaryStage.show();
        primaryStage.setTitle(network.getUsername());
        messageHistory = readMessageHistoryFromFile(getFilePath("", network.getUsername()));
        viewController.setMessageHistory(messageHistory);
    }

    @Override
    public void stop() {
        writeMessageHistoryToFile(messageHistory, getFilePath("", network.getUsername()));
    }

    private MessageHistory readMessageHistoryFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (MessageHistory) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("History file not exists. Will be created new file.");
            return new MessageHistory();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new MessageHistory();
    }

    private void writeMessageHistoryToFile(MessageHistory messageHistory, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(messageHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath(String filePath, String filePrefix) {
        return filePath + filePrefix + MSG_HIST_TEMPLATE;
    }
}