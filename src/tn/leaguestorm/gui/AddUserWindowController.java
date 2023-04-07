/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    @FXML
    ObservableList<String> rolesBoxList = FXCollections.observableArrayList("ORGANISATION","EQUIPE");
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
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User u = new User(email, roles, hashedPassword, isVerified, firstName);
        UserService us = new UserService();
        us.ajouter3(u);
    }
    
}
