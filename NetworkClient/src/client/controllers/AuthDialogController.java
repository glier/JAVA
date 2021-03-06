package client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import client.NetworkChatClient;
import client.models.Network;

public class AuthDialogController {
    private @FXML TextField loginField;
    private @FXML PasswordField passwordField;
    private @FXML Button authButton;

    private Network network;
    private NetworkChatClient clientApp;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            NetworkChatClient.showNetworkError("Username and password should be not empty!", "Auth error");
            return;
        }

        network.sendAuthCommand(login, password);
    }

    public void authOk() {
        Platform.runLater(() -> clientApp.openChat());

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setClientApp(NetworkChatClient clientApp) {
        this.clientApp = clientApp;
    }
}
