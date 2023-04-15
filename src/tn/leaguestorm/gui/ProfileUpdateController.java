/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.CurrentUser;

/**
 *
 * @author Bellalouna Iheb
 */
public class ProfileUpdateController {
    private Alert alert;
    
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
    @FXML
    private PasswordField tfFirstName;
    @FXML
    private PasswordField tfLastName;
    @FXML
    private PasswordField tfPhoneNumber;
    
    User currentUser = CurrentUser.getUser();
    
    private void handleBtnSelectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());

        if (selectedFile != null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Selected file: " + selectedFile.getAbsolutePath());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void handleChangePasswordLinkAction(ActionEvent event) {
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
    }
}

