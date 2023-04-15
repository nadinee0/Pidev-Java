/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Bellalouna Iheb
 */
public class ChangePasswordController {

    @FXML
    private ImageView userProfilePic;
    @FXML
    private Label lblFullName;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPassword;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    private ResultSet result;
    
    @FXML
    private PasswordField tfCurrentPassword;
    @FXML
    private PasswordField tfNewPassword;
    @FXML
    private PasswordField tfConfirmNewPassword;
    @FXML
    private Button btnApply;
    
    private Alert alert;

    private User user;

    public void setUser(User user) {
        this.user = user;
        System.out.println(user.getId());
    }

//    private int userID = initData(user);
    

    @FXML
    private void applyPasswordChangeAction(ActionEvent event) {

        String currentPassword = tfCurrentPassword.getText();
        String newPassword = tfNewPassword.getText();
        String confirmNewPassword = tfConfirmNewPassword.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
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
        if (!checkCurrentPassword(currentPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Current password is incorrect.");
            alert.showAndWait();
            return;
        }

        boolean success = updateUserPassword(newPassword);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password changed successfully.");
            alert.showAndWait();

            tfCurrentPassword.clear();
            tfNewPassword.clear();
            tfConfirmNewPassword.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error occurred while changing password.");
            alert.showAndWait();
        }
    }

    private boolean checkCurrentPassword(String password) {
        String hashedPassword = "SELECT password FROM user WHERE id = id";
        if (BCrypt.checkpw(password, hashedPassword));
        return true;
    }

    private boolean updateUserPassword(String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(13));
        UserService us = new UserService();
        try {
            us.updatePassword(hashedPassword);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return true;
    }

    
    public int initData(User user) {
        int id = user.getId();
        return id;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    @FXML
    private void handleProfileUpdateLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ProfileUpdate.fxml", "Edit Profile");
    }

}
