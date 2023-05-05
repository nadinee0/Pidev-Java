/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

/**
 *
 * @author Bellalouna Iheb
 */
public class ProfileUpdateController implements Initializable {

    private Alert alert;

    ObservableList<String> countriesBoxList = FXCollections.observableArrayList(getCountryList());
    
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
    @FXML
    private ComboBox countryBox;

    @FXML
    private void choosePictureAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String baseName = FilenameUtils.getBaseName(selectedFile.getName());
            String randomPrefix = UUID.randomUUID().toString();
            String fileName = baseName + "-" + randomPrefix.substring(0, 8) + randomPrefix.substring(9, 13) + randomPrefix.substring(14, 18) + randomPrefix.substring(19, 23) + selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            Path destinationPath = Paths.get("C:/leagueStorm/src/tn/leaguestorm/miscs/user/", fileName);
            try {
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(selectedFile.toURI().toString());
                this.userProfilePic.setImage(image);
                currentUser.setProfilePictureName(fileName);
            } catch (IOException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File copied to " + destinationPath);
        } else {
            System.out.println("No file selected.");
        }
    }

    @FXML
    private void applyAction(ActionEvent event) {

        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String country = (String) countryBox.getSelectionModel().getSelectedItem();
        String phoneNumber = tfPhoneNumber.getText();
        LocalDate birthDate = birthDatePicker.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || birthDate == null) {
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

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setBirthDate(birthDate);
        currentUser.setCountry(country);

        UserService us = new UserService();
        try {
            us.modifier(currentUser);
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();
            try {
                FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Home.fxml", "Profile");
            } catch (IOException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean atLeast12YearsOld(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();
        return age >= 12;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> countryList = getCountryList();
        countryBox.setItems(countriesBoxList); 
        countryBox.getSelectionModel().select(currentUser.getCountry());
        tfFirstName.setText(currentUser.getFirstName());
        tfLastName.setText(currentUser.getLastName());
        tfPhoneNumber.setText(currentUser.getPhoneNumber());
        birthDatePicker.setValue(currentUser.getBirthDate());

        String userProfilePicPath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + currentUser.getProfilePictureName();
        Image userProfilePic = new Image("file:" + userProfilePicPath);
        this.userProfilePic.setImage(userProfilePic);
    }
    
    private ObservableList<String> getCountryList() {
        Locale[] locales = Locale.getAvailableLocales();
        ObservableList<String> countryList = FXCollections.observableArrayList();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (!country.isEmpty() && !countryList.contains(country)) {
                countryList.add(country);
            }
        }
        countryList.add("Palestine");
        FXCollections.sort(countryList);
        return countryList;
    }
}
