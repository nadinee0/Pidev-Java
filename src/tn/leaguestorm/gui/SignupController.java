/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

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
        int phoneNumber = Integer.parseInt(tfPhone.getText());
        int isVerified = 0;

        if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid First Name");
            alert.setContentText("Please enter a valid First Name");
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

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));

        User u = new User(email, hashedPassword, isVerified, firstName, lastName, country, phoneNumber);
        UserService us = new UserService();
        us.ajouter3(u);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Created!");
        alert.setContentText("User created successfully");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleSigninLinkAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
