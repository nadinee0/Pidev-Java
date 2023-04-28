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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.BadgeService;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class ModifyUserPopupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> countriesBoxList = FXCollections.observableArrayList(getCountryList());

    User selectedUser = CurrentUser.getSelecUser();

    private Alert alert;

    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private ImageView profilePictureIMGV;
    @FXML
    private Button btnApply;
    @FXML
    private Hyperlink cancelHPRL;
    @FXML
    private TextField tfPhone;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ComboBox countryBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (selectedUser != null) {
            ObservableList<String> countryList = getCountryList();
            countryBox.setItems(countriesBoxList);
            countryBox.getSelectionModel().select(selectedUser.getCountry());
            String badgeImagePath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + selectedUser.getProfilePictureName();
            Image badgeImage = new Image("file:" + badgeImagePath);
            this.profilePictureIMGV.setImage(badgeImage);
            tfFirstName.setText(selectedUser.getFirstName());
            tfLastName.setText(String.valueOf(selectedUser.getLastName()));
            tfPhone.setText(selectedUser.getPhoneNumber());
            birthDatePicker.setValue(selectedUser.getBirthDate());
        } else {
            System.out.println("is null");
        }
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

    @FXML
    private void choosePictureAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String baseName = FilenameUtils.getBaseName(selectedFile.getName());
            String randomPrefix = UUID.randomUUID().toString();
            String fileName = baseName + "-" + randomPrefix.substring(0, 8) + randomPrefix.substring(9, 13) + randomPrefix.substring(14, 18) + randomPrefix.substring(19, 23) + selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            Path destinationPath = Paths.get("C:/leagueStorm/src/tn/leaguestorm/miscs/user/", fileName);
            try {
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(selectedFile.toURI().toString());
                this.profilePictureIMGV.setImage(image);
                selectedUser.setProfilePictureName(fileName);
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
        String phoneNumber = tfPhone.getText();
        LocalDate birthDate = birthDatePicker.getValue();

        selectedUser.setFirstName(firstName);
        selectedUser.setLastName(lastName);
        selectedUser.setCountry(country);
        selectedUser.setPhoneNumber(phoneNumber);
        selectedUser.setBirthDate(birthDate);

        UserService us = new UserService();
        try {
            us.modifier(selectedUser);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
