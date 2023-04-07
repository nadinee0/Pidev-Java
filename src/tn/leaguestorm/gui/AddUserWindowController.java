/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class AddUserWindowController implements Initializable {

    ObservableList<String> rolesBoxList = FXCollections.observableArrayList("ORGANISATION","EQUIPE","JOUEUR");
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfFirstName;
    @FXML
    private Button btnValider;
    @FXML 
    private ComboBox rolesBox;
    @FXML
    private Label labelOnSubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rolesBox.setItems(rolesBoxList);
    }    

    @FXML
    private void saveUser(ActionEvent event) throws SQLException {
    String email = tfEmail.getText();
    String password = tfPassword.getText();
    String roles = rolesBox.getValue().toString();
    String firstName = tfFirstName.getText();
    int isVerified = 0;

    // Check if email is valid
    if (!isValidEmail(email)) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Adresse email invalide!");
        alert.setContentText("Veuillez entrez une adresse email valide!");
        alert.showAndWait();
        return;
    }
    
    if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Prénom invalide!");
        alert.setContentText("Veuillez entrer un prénom valide (lettres uniquement)!");
        alert.showAndWait();
        return;    
    }
    
    if (!isValidPasswordFormat(password)) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mot de passe invalide!");
        alert.setContentText("Veuillez entrer un mot de passe valide (1ère lettre en majuscule, lettres et chiffres, et au moins un caractère spécial)!");
        alert.showAndWait();
        return;
    }

    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    User u = new User(email, roles, hashedPassword, isVerified, firstName);
    UserService us = new UserService();
    us.ajouter3(u);

    labelOnSubmit.setText("Utilisateur ajouté avec succès!");
    
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private boolean isValidPasswordFormat(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    
}
