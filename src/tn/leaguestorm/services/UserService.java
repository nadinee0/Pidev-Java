/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;


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
public class UserService implements UService<User> {
    private MyConnection cnx = MyConnection.getInstance();

    @Override
    public void ajouter(User u) throws SQLException{
            String req = "INSERT INTO `user` (`email`, `first_name`, `last_name`, `phone_number`) VALUES ('" + u.getEmail()+ "', '" + u.getFirstName()+ "', '" + u.getLastName()+ "', '" + u.getPhoneNumber()+ "')";
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("User created !");
    }
    
    public void ajouter2(User u) {
        try {
            String req = "INSERT INTO `user` (`email`, `password`) VALUES (?,?)";
            PreparedStatement ps = cnx.getCnx().prepareStatement(req);
            ps.setString(2, u.getEmail());
            ps.setString(1, u.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException{
            String req = "DELETE FROM `User` WHERE id = " + id;
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
    }

    @Override
    public void modifier(User u) throws SQLException{
            String req = "UPDATE `user` SET `nom` = '" + u.getLastName()+ "', `prenom` = '" + u.getFirstName()+ "' WHERE `user`.`id` = " + u.getId();
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("user updated !");
    }

    @Override
    public List<User> getAll() throws SQLException{
        List<User> list = new ArrayList<>();
            String req = "Select * from user";
            Statement st = cnx.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                User u = new User(rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("country"), rs.getInt("phone_number"));
                list.add(u);
            }
        return list;
    }

}