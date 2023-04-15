/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
public class ProfileUpdateController implements Initializable{
    private Alert alert;
    
    @FXML
    private ImageView userProfilePic;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private Button btnApply;
    @FXML
    private DatePicker birthDatePicker;
    
    User currentUser = CurrentUser.getUser();
    Circle circle = new Circle(90, 90, 80);
    
    
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
    private void applyAction(ActionEvent event) {
        
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        //String country = (String) countryBox.getSelectionModel().getSelectedItem();
        String phoneNumber = tfPhoneNumber.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()|| birthDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("All fields are required");
            alert.showAndWait();
            return;
        }
        if (!firstName.matches("[a-zA-Z]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid First Name");
                alert.setContentText("Please enter a valid First Name");
                alert.showAndWait();
                return;
            }

            if (!lastName.matches("[a-zA-Z]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid First Name");
                alert.setContentText("Please enter a valid Last Name");
                alert.showAndWait();
                return;
            }


            if (!phoneNumber.matches("^(\\+|00)[0-9\\-\\s]+$")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Phone");
                alert.setContentText("Phone Number format is invalid!");
                alert.showAndWait();
                return;
            }

            if (!atLeast12YearsOld(birthDate)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Date of Birth");
                alert.setContentText("You must be at least 12 years old to use this service.");
                alert.showAndWait();
                return;
            }
    }
    
    public static boolean atLeast12YearsOld(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();
        return age >= 12;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfFirstName.setText(currentUser.getFirstName());
        tfLastName.setText(currentUser.getLastName());
        tfPhoneNumber.setText(currentUser.getPhoneNumber());
        birthDatePicker.setValue(currentUser.getBirthDate());
        
        String userProfilePicPath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + currentUser.getProfilePictureName();
        Image userProfilePic = new Image("file:" + userProfilePicPath);
        this.userProfilePic.setImage(userProfilePic);
        this.userProfilePic.setClip(circle);
    }
}

