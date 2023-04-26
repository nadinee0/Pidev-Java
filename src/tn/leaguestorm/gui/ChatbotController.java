/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ChatbotController implements Initializable {
 @FXML
    private VBox chatBox;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    // Create a new Bot object
    private Bot bot = new Bot(BotConfiguration.builder()
            .name("product_bot")
            .build());

    // Create a new Chat object using the Bot instance
    private Chat chat = new Chat(bot);

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        sendButton.setOnAction(this::onSendButtonClicked);
        inputField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                onSendButtonClicked(null);
            }
        });
    }

    private void onSendButtonClicked(ActionEvent event) {
        String userInput = inputField.getText().trim();
        inputField.clear();

        if (!userInput.isEmpty()) {
            // Append user's message to chat history
            appendMessage("You", userInput);

            // Ask the chatbot to respond to the user's message
            String botResponse = chat.multisentenceRespond(userInput);

            // Check if the chatbot's response is related to products or the shop
            if (botResponse.contains("product")) {
                appendMessage("Bot", "Our store offers a wide range of products, including electronics, clothing, and home goods. Is there anything specific you're looking for?");
            } else if (botResponse.contains("shop")) {
                appendMessage("Bot", "Our store is located at 123 Main Street. We're open from 9am to 9pm Monday through Saturday, and from 10am to 6pm on Sundays.");
            } else {  // Append the chatbot's response to the chat history
            appendMessage("Bot", botResponse);
        }
    }
}

private void appendMessage(String sender, String message) {
    // Append the message to the chat history
    chatArea.appendText(String.format("%s: %s%n", sender, message));
}
    /**
     * Initializes the controller class.
     */

}
