/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.leaguestorm.utils.FXMLUtils;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import java.sql.SQLException;
import java.util.Random;
import tn.leaguestorm.services.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.leaguestorm.utils.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class ForgotController implements Initializable {

    @FXML
    private ImageView exitImg;
    @FXML
    private Button btnConfirm;
    @FXML
    private Hyperlink lnkCancel;
    @FXML
    private TextField tfNumber;

    private Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleCancelLinkAction(ActionEvent event) {
        try {
            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in");
        } catch (IOException ex) {
            Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleConfirmAction(ActionEvent event) throws IOException {

        String number = tfNumber.getText();
        Random rand = new Random();
        int code = rand.nextInt(999999) + 1;
        String formattedCode = String.format("%06d", code);

        VonageClient client = VonageClient.builder().apiKey("c50c8a9e").apiSecret("h7aN72rvM10CD4Gl").build();
        TextMessage message = new TextMessage("LeagueStorm",
                number,
                "Security code: " + formattedCode
        );

        UserService us = new UserService();
        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Message sent successfully");
            alert.showAndWait();
            //System.out.println("Message sent successfully.");
            try {
                us.insCode(number, formattedCode);
            } catch (SQLException ex) {
                Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            CurrentUser.phoneProcedures = number;
            
            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ForgotVerif.fxml", "Forgot");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Message failed with error: " + response.getMessages().get(0).getErrorText());
            alert.showAndWait();
            //System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }

    }

}
