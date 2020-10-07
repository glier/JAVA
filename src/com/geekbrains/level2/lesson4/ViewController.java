package com.geekbrains.level2.lesson4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Text userName;

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(Main.USERS_TEST_DATA));
        sendButton.setOnAction(event -> sendMessage());
        textField.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        chatHistory.appendText(userName.getText());
        chatHistory.appendText(":  ");
        chatHistory.appendText(textField.getText());
        chatHistory.appendText(System.lineSeparator());
        textField.clear();
    }

}
