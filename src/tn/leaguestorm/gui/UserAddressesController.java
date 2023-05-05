/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.AddressService;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author khair
 */
public class UserAddressesController {
    @FXML
    private VBox addressList;
     private MyConnection ds = MyConnection.getInstance();


    private AddressService as = new AddressService();  // assume you have an AddressDAO class with the displayUserAddresses method

  
    public void displayAddressesForUser(int userId) {
        try {
            PreparedStatement addressStmt = ds.getCnx().prepareStatement("SELECT * FROM address WHERE user_id = ?");
            addressStmt.setInt(1, userId);
            ResultSet addressResult = addressStmt.executeQuery();

            // Clear the existing addresses in the address list
            addressList.getChildren().clear();

            // Display the addresses for the user
            while (addressResult.next()) {
                int addressId = addressResult.getInt("id");
                String name = addressResult.getString("name");
                String address = addressResult.getString("address");
                String postal = addressResult.getString("postal");
                String phoneLiv = addressResult.getString("phone_liv");

                // Create a label with the address information
                Label addressLabel = new Label(name + "\n" + address + "\n" + postal + "\n" + phoneLiv);
                addressList.getChildren().add(addressLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

