package client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import client.NetworkChatClient;
import client.models.Network;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ViewController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    private Network network;

    private String selectedRecipient;

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(NetworkChatClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> sendMessage());
        textField.setOnAction(event -> sendMessage());

        usersList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = usersList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                usersList.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell ;
        });
    }

    private void sendMessage() {
        String message = textField.getText();
        appendMessage("Я: " + message);
        textField.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(message, selectedRecipient);
            }
            else {
                network.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Failed to send message";
            NetworkChatClient.showNetworkError(e.getMessage(), errorMessage);
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
    }

    public void showError(String title, String message) {
        NetworkChatClient.showNetworkError(message, title);
    }

    public void updateUsers(List<String> users) {
        usersList.setItems(FXCollections.observableList(users));
    }
}