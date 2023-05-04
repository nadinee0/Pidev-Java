/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

/**
 *
 * @author Bellalouna Iheb
 */
public class SideMenuController implements Initializable {

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

    User currentUser = CurrentUser.getUser();
    Circle circle = new Circle(90, 90, 90);

    @FXML
    private void handleProfileLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Profile.fxml", "Profile");
    }

    @FXML
    private void handleChangePasswordLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ChangePassword.fxml", "Change Password");
    }

    @FXML
    private void handleProfileUpdateLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ProfileUpdate.fxml", "Edit Profile");
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        CurrentUser.endSession();
        try {
            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in");
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (currentUser != null) {
            lblFullName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

            String userProfilePicPath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + currentUser.getProfilePictureName();
            Image userProfilePic = new Image("file:" + userProfilePicPath);
            double radius = circle.getRadius();
            double diameter = radius * 2;
            this.userProfilePic.setImage(userProfilePic);

            this.userProfilePic.setFitWidth(diameter);
            this.userProfilePic.setFitHeight(diameter);
            this.userProfilePic.setClip(circle);

        } else {
            System.out.println("No such user found!");
        }
    }

}
