/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.leaguestorm.entities.Address;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.AddressService;
import tn.leaguestorm.services.UserService;

/**
 * FXML Controller class
 *
 * @author khair
 */
public class AddAddressController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    UserService us = new UserService();
    List<User> user = us.getAllWithId();
    private int userId=-1;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(User u : user){
            userField.getItems().add(u.getEmail());
            valuesMap.put(u.getEmail(),u.getId());
        }
        
        userField.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = userField.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            userId = SelectedValue;
        });
    }    
    
    
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$"); // regular expression for 10-digit phone number
    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("^\\d{4}$"); // regular expression for 4-digit postal code
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField postalField;
    
    @FXML
    private TextField phoneLivField;
    
    @FXML
    private ComboBox<String> userField;
    
    @FXML
    private AnchorPane addAdressPane;

    

    @FXML
    private Button btnAddAdress;

    
    
    
    private void ajouterAdresse(){
        AddressService as = new AddressService();
        
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phoneLiv = phoneLivField.getText();
        int userid = userId;
        
        Address newAddress = new Address(userid, name, address, postal, phoneLiv);
        
        
        as.ajouter(newAddress);
        
        
    }
    
    @FXML
    private void handleAddButton(ActionEvent event) {
        if(event.getSource() == btnAddAdress){
            if (!validatePostalCode(postalField.getText())) {
                showErrorAlert("Postal code must be a 4-digit number.");
                return;
            }

            if (!validatePhoneNumber(phoneLivField.getText())) {
                showErrorAlert("Phone number must be a 10-digit number.");
                return;
            }

            ajouterAdresse();
            showSuccessAlert("Address added successfully.");
        }
    }
    
    private boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
    
    private boolean validatePostalCode(String postalCode) {
        return POSTAL_CODE_PATTERN.matcher(postalCode).matches();
    }
    
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
