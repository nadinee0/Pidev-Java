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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.utils.CurrentUser;

/**
 *
 * @author Bellalouna Iheb
 */
public class UserService implements IService<User> {
    
    User currentUser = CurrentUser.getUser();

    private MyConnection cnx = MyConnection.getInstance();

    @Override
    public void ajouter(User u) throws SQLException {
        String req = "INSERT INTO `user` (`email`, `first_name`, `last_name`, `phone_number`) VALUES ('" + u.getEmail() + "', '" + u.getFirstName() + "', '" + u.getLastName() + "', '" + u.getPhoneNumber() + "')";
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void ajouter2(User u) throws SQLException {
        String req = "INSERT INTO `user` (`email`, `password`) VALUES (?,?)";
        PreparedStatement ps = cnx.getCnx().prepareStatement(req);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
        ps.executeUpdate();
    }

    public void updatePassword(String password, int id) throws SQLException {
        String req = "UPDATE `user` SET `password` = '" + password + "' WHERE `user`.`id` =" + id;
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void updateForgottenPassword(String password, String phone) throws SQLException {
        String req = "UPDATE `user` SET `password` = '" + password + "' WHERE `user`.`phone_number` =" + phone;
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        PreparedStatement statement = cnx.getCnx().prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }

    public boolean isCode(String phone, String code) throws SQLException {
        String query = "SELECT auth_code FROM user WHERE phone_number = ? AND auth_code = ?";
        PreparedStatement statement = cnx.getCnx().prepareStatement(query);
        statement.setString(1, phone);
        statement.setString(2, code);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public boolean ajouter3(User u) throws SQLException {
        if (isEmailExists(u.getEmail())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Email already exists");
            alert.setContentText("The email address you entered is already in use. Please enter a different email address.");
            alert.showAndWait();
            return false;
        }
        String req = "INSERT INTO `user` (`email`, `roles`, `password`, `is_verified`, `first_name`, `last_name`, `birth_date`, `country`, `profile_picture_name`, `phone_number`) VALUES ('" + u.getEmail() + "', '" + u.getRole() + "', '" + u.getPassword() + "', '" + u.getIsVerified() + "', '" + u.getFirstName().substring(0, 1).toUpperCase() + u.getFirstName().substring(1) + "', '" + u.getLastName().substring(0, 1).toUpperCase() + u.getLastName().substring(1) + "', '" + u.getBirthDate() + "', '" + u.getCountry() + "', '" + u.getProfilePictureName() + "', '" + u.getPhoneNumber() + "')";
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Created!");
        alert.setContentText("User created successfully");
        alert.showAndWait();
        return true;
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `user` WHERE id = " + id;
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(User u) throws SQLException {
        String req = "UPDATE `user` SET `first_name` = '" + u.getLastName() + "', `last_name` = '" + u.getFirstName() + "', `birth_date` = '" + u.getBirthDate() + "', `country` = '" + u.getCountry() + "', `phone_number` = '" + u.getPhoneNumber() + "', `profile_picture_name` = '" + u.getProfilePictureName() + "' WHERE `id` = " + u.getId();
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void insCode(String number, String authcode) throws SQLException {
        String req = "UPDATE `user` SET `auth_code` = '" + authcode + "' WHERE `phone_number` = " + number;
        Statement st = cnx.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        String req = "Select * from user";
        Statement st = cnx.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User(rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone_number"));
            list.add(u);
        }
        return list;
    }

    public ObservableList<Badge> displayBadgesForUser() {
        ObservableList<Badge> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT b.id, b.logo, b.valeur, b.badge_file_name, b.description FROM badge b INNER JOIN badge_user ub ON b.id = ub.badge_id WHERE ub.user_id = ?";
        PreparedStatement ps = cnx.getCnx().prepareStatement(req);
        ps.setInt(1, currentUser.getId());
        ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Badge b = new Badge();
                b.setId(rs.getInt("id"));
                b.setLogo(rs.getString("logo"));
                b.setValeur(rs.getInt("valeur"));
                b.setBadgeFileName(rs.getString("badge_file_name"));
                b.setDescription(rs.getString("description"));
                
                myList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    

    public ObservableList<User> displayUsers() {
        ObservableList<User> myList = FXCollections.observableArrayList();

        try {
            String req = "Select * from user";
            Statement st = cnx.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setBirthDate(LocalDate.parse(rs.getString("birth_date")));
                u.setCountry(rs.getString("country"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setProfilePictureName(rs.getString("profile_picture_name"));
                u.setBanned(rs.getBoolean("banned"));

                myList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void banUser(User user) {
        try {
            String req = "UPDATE user SET banned = true WHERE id = ?";
            PreparedStatement ps = cnx.getCnx().prepareStatement(req);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void unbanUser(User user) {
        try {
            String req = "UPDATE user SET banned = false WHERE id = ?";
            PreparedStatement ps = cnx.getCnx().prepareStatement(req);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isBanned(String email) throws SQLException {
        String query = "SELECT banned FROM user WHERE email = ?";
        PreparedStatement statement = cnx.getCnx().prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getBoolean("banned");
        } else {
            return false;
        }
    }

}
