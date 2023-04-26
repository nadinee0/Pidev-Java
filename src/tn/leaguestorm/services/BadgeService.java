/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Bellalouna Iheb
 */
public class BadgeService implements IService<Badge> {
    
    private MyConnection cnx = MyConnection.getInstance();

    @Override
    public void ajouter(Badge b) throws SQLException {
        String req = "INSERT INTO `badge` (`valeur`, `logo`, `badgeFileName`, `description`) VALUES ('" + b.getValeur()+ "', '" + b.getLogo()+ "', '" + b.getBadgeFileName()+ "', '" + b.getDescription()+ "')";
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }

    @Override
    public void modifier(Badge b) throws SQLException {
        String req = "UPDATE `badge` SET `valeur` = '" + b.getValeur()+ "', `logo` = '" + b.getValeur()+ "', `badgeFileName` = '" + b.getBadgeFileName()+ "', `description` = '" + b.getDescription()+ "' WHERE `badge`.`id` = " + b.getId();
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException {
            String req = "DELETE FROM `badge` WHERE id = " + id;
            Statement st = cnx.getCnx().createStatement();
            st.executeUpdate(req);
    }

    @Override
    public List<Badge> getAll() throws SQLException {
        
        List<Badge> list = new ArrayList<>();
            String req = "Select * from badge";
            Statement st = cnx.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Badge b = new Badge(rs.getInt("valeur"), rs.getString("logo"), rs.getString("badgeFileName"), rs.getString("description"));
                list.add(b);
            }
        return list;
    }
    
    public ObservableList<Badge> displayBadges() {
        ObservableList<Badge> myList = FXCollections.observableArrayList();

        try {
            String req = "Select * from badge";
            Statement st = cnx.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Badge b = new Badge();
                b.setValeur(rs.getInt("valeur"));
                b.setLogo(rs.getString("logo"));
                b.setDescription(rs.getString("description"));
                b.setBadgeFileName(rs.getString("badge_file_name"));
                myList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
}
