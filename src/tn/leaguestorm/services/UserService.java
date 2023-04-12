/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bellalouna Iheb
 */
public class UserService implements IService<User> {
    
    private MyConnection cnx = MyConnection.getInstance();

    @Override
    public void ajouter(User u) throws SQLException{
            String req = "INSERT INTO `user` (`email`, `first_name`, `last_name`, `phone_number`) VALUES ('" + u.getEmail()+ "', '" + u.getFirstName()+ "', '" + u.getLastName()+ "', '" + u.getPhoneNumber()+ "')";
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }
    
    public void ajouter2(User u) throws SQLException{
            String req = "INSERT INTO `user` (`email`, `password`) VALUES (?,?)";
            PreparedStatement ps = cnx.getCnx().prepareStatement(req);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.executeUpdate();
    }

    public void ajouter3(User u) throws SQLException{
            
            String req = "INSERT INTO `user` (`email`, `roles`, `password`, `is_verified`, `first_name`, `last_name`, `country`, `phone_number`) VALUES ('" + u.getEmail()+ "', '" + u.getRoles()+ "', '" + u.getPassword()+ "', '" + u.getIsVerified()+ "', '" + u.getFirstName().substring(0, 1).toUpperCase() + u.getFirstName().substring(1)+ "', '" + u.getLastName().substring(0, 1).toUpperCase() + u.getLastName().substring(1)+ "', '" + u.getCountry()+ "', '" + u.getPhoneNumber()+ "')";
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }
    
    
    @Override
    public void supprimer(int id) throws SQLException{
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }

    @Override
    public void modifier(User u) throws SQLException{
            String req = "UPDATE `user` SET `nom` = '" + u.getLastName()+ "', `prenom` = '" + u.getFirstName()+ "' WHERE `user`.`id` = " + u.getId();
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }

    @Override
    public List<User> getAll() throws SQLException{
        List<User> list = new ArrayList<>();
            String req = "Select * from user";
            Statement st = cnx.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                User u = new User(rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("phone_number"));
                list.add(u);
            }
        return list;
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}