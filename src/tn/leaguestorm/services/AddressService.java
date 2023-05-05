/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.leaguestorm.entities.Address;
import tn.leaguestorm.entities.Location;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author khair
 */
public class AddressService {

  
 public MyConnection ds=MyConnection.getInstance();
 public Connection cnx;
    public Statement stm;
    

    
    public AddressService() {
        cnx= ds.getCnx();

    }

    
   
   public void displayUserAddresses(int userId) throws SQLException{
     PreparedStatement addressStmt = ds.getCnx().prepareStatement("SELECT * FROM address WHERE user_id = ? ");
        addressStmt.setInt(1, userId);
        ResultSet addressResult = addressStmt.executeQuery();

        // Display the orders and their details
        while (addressResult.next()) {
            int addressId = addressResult.getInt("id");
            String name = addressResult.getString("name");
            String address = addressResult.getString("address");
            String postal = addressResult.getString("postal");


            String phoneLiv = addressResult.getString("phone_liv");

            // Display order information
            System.out.println("Name: " + name);
            System.out.println("address: " + address);
            System.out.println("postal: " + postal);
           System.out.println("Phone liv: " + phoneLiv);

        }
   
   }
 
    public void ajouter(Address a) {
     
         String req =
                 "INSERT INTO address"
                 + "(user_id,name,address,postal,phone_liv)"
                 + "VALUES(?,?,?,?,?)";
     try {
         PreparedStatement ps = cnx.prepareStatement(req);
         ps.setInt(1, a.getUserId());
         ps.setString(2, a.getName());
         ps.setString(3, a.getAddress());
         ps.setString(4, a.getPostal());
         ps.setString(5, a.getPhoneLiv());
         ps.executeUpdate();
         System.out.println("Adress Ajoutée !!");
     } catch (SQLException ex) {
         Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
 
    public void modifier(Address a) {
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("UPDATE address SET user_id = ?, name = ?, address = ?, postal = ?, phone_liv = ? WHERE id = ?");
            preparedStatement.setInt(1, a.getUserId());
            preparedStatement.setString(2, a.getName());
            preparedStatement.setString(3, a.getAddress());
            preparedStatement.setString(4, a.getPostal());
            preparedStatement.setString(5, a.getPhoneLiv());
            preparedStatement.setInt(6, a.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    public void supprimer(int id) {
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("DELETE FROM address WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Adresse a  supprim�e avec succes !");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression de l'adresse.");
        }
    }

	 
		 
		public ObservableList<Address> getAll() {
			ObservableList<Address> addresses =  FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM address");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String address1 = resultSet.getString("address");
                String postal = resultSet.getString("postal");
                String phoneLiv = resultSet.getString("phone_liv");

                Address address = new Address(id, userId, name, address1, postal, phoneLiv);
                addresses.add(address);
                System.out.println(address.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    

    

    
   


}