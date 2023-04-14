/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import tn.leaguestorm.utils.FXMLUtils;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class SignupController implements Initializable {

    ObservableList<String> countriesBoxList = FXCollections.observableArrayList(getCountryList());

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirmPassword;
    @FXML
    private TextField tfFirstName;
    @FXML
    private ComboBox countryBox;
    @FXML
    private Button btnRegister;
    @FXML
    private ImageView exitImg;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfPhone;
    @FXML
    private DatePicker birthDatePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> countryList = getCountryList();
        countryBox.setItems(countriesBoxList);
        countryBox.setPromptText("Select a country");
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
        FXCollections.sort(countryList);
        return countryList;
    }

    @FXML
    private void saveUser(ActionEvent event) throws SQLException, IOException {

        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPassword.getText();
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String country = (String) countryBox.getSelectionModel().getSelectedItem();
        String phoneNumber = tfPhone.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        int isVerified = 0;
        String roles = "ORGANISATION";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty()
                || lastName.isEmpty() || country == null || phoneNumber.isEmpty() || birthDate == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("All fields are required");
            alert.showAndWait();
            return;
        } else {

            if (!email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Email");
                alert.setContentText("Please enter a valid Email : example@email.com");
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

            if (!password.equals(confirmPassword)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setContentText("Passwords do not match");
                alert.showAndWait();
                return;
            }

            if (!password.matches("^(?=.*[0-9])(?=.*[A-Z]).+$")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Password");
                alert.setContentText("Password must start with an uppercase letter and include at least one number.");
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

        //int phoneNumber = Integer.parseInt(phoneNumberStr);
        User u = new User(email, roles, hashedPassword, isVerified, firstName, lastName, country, phoneNumber, birthDate);
        UserService us = new UserService();
        if (!us.ajouter3(u)) {
            return;
        }

        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in", null);
    }

    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleSigninLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in", null);
    }

    public static boolean atLeast12YearsOld(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();
        return age >= 12;
    }

}
