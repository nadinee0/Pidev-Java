/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class ForgotConfirmController implements Initializable {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirmPassword;
    @FXML
    private ImageView exitImg;
    @FXML
    private Button btnApply;
    
    private Alert alert;
    
    String phone = CurrentUser.phoneProcedures;
    UserService us = new UserService();
    @FXML
    private Hyperlink showHideLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showHideLink.setOnMousePressed(event -> {
            tfPassword.setPromptText(tfPassword.getText());
            tfPassword.setText("");
            tfConfirmPassword.setPromptText(tfConfirmPassword.getText());
            tfConfirmPassword.setText("");
            showHideLink.setText("Hide");
        });

        showHideLink.setOnMouseReleased(event -> {
            tfPassword.setText(tfPassword.getPromptText());
            tfPassword.setPromptText("");
            tfConfirmPassword.setText(tfConfirmPassword.getPromptText());
            tfConfirmPassword.setPromptText("");
            showHideLink.setText("Show");
        });
    }    


    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void savePassword(ActionEvent event) throws IOException {
        String newPassword = tfPassword.getText();
        String confirmNewPassword = tfConfirmPassword.getText();
        
        if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        if (!newPassword.equals(confirmNewPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("New password and confirm new password do not match.");
            alert.showAndWait();
            return;
        }
        if (!newPassword.matches("^(?=.*[0-9])(?=.*[A-Z]).+$")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Password");
                alert.setContentText("Password must start with an uppercase letter and include at least one number.");
                alert.showAndWait();
                return;
            }
        
        boolean success = updateUserForgottenPassword(newPassword);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password changed successfully.");
            alert.showAndWait();

            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error occurred while changing password.");
            alert.showAndWait();
        }
    }
    
    private boolean updateUserForgottenPassword(String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(13));
        UserService us = new UserService();
        try {
            us.updateForgottenPassword(hashedPassword, phone);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return true;
    }
    
}
