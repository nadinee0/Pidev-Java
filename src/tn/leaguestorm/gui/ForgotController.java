package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.SQLException;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

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
    private static final String TWILIO_PHONE_NUMBER = "+16812216467";

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
    private void handleConfirmAction(ActionEvent event) throws IOException, SQLException {

        String number = tfNumber.getText();
        int code = (int) (Math.random() * 900000) + 100000;
        String formattedCode = String.format("%06d", code);
        String accountSid = "ACa8be271efbf32879031ed087f2139862";
        String authToken = "01f6b8564931d389597a71168533229f";
        Twilio.init(accountSid, authToken);

        UserService us = new UserService();

        Message message = Message.creator(new PhoneNumber(number), new PhoneNumber(TWILIO_PHONE_NUMBER),
                "Security code: " + formattedCode).create();

        if (message.getStatus() == Message.Status.FAILED) {
    alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Message");
    alert.setHeaderText(null);
    alert.setContentText("Message failed with error: " + message.getErrorCode());
    alert.showAndWait();
    //System.out.println("Message failed with error: " + message.getErrorMessage());
} else {
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
}
    }

}
