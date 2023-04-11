/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tn.leaguestorm.entities.Article;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import tn.leaguestorm.entities.Location;
import tn.leaguestorm.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author Nadine
 */
public class ServiceLocation implements IService<Location>{

          private MyConnection ds = MyConnection.getInstance();

    @Override
    public void ajouter(Location l) throws SQLException {
        String req = "INSERT INTO `location` (`date_d`,`date_f`,`statut`,`frais_retard`,`date_retour`) VALUES ('"+l.getDateD()+"','"+l.getDateF()+"','"+l.getStatut()+"','"+l.getFraisRetard()+"','"+l.getDate_retour()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }
      public void ajouter2(Location l) throws SQLException{
        String req = "INSERT INTO `location` (`date_d`,`date_f`,`statut`,`frais_retard`,`date_retour`) VALUES (?,?,?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setDate(1, new java.sql.Date(l.getDateD().getTime()));
        st.setDate(2, new java.sql.Date(l.getDateF().getTime()));
        st.setString(3, l.getStatut());
        st.setFloat(4, l.getFraisRetard());
        st.setDate(5, new java.sql.Date(l.getDate_retour().getTime()));
                
        st.executeUpdate();
    } 
    @Override
    public void modifier(Location l) throws SQLException {
    String req = "UPDATE `location` SET `date_d` = '"+l.getDateD()+"', `date_f` = '"+l.getDateF()+"', `statut` = '"+l.getStatut()+"', `frais_retard` = '"+l.getFraisRetard()+"', `date_retour` = '"+l.getDate_retour()+"' WHERE `location`.`id` = "+l.getId(); 
    Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `location` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

    @Override
    public List<Location> getAll() throws SQLException {
    List<Location> list = new ArrayList<>();
        
        String req = "Select * from location";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Location l = new Location(rs.getInt("id"), rs.getDate(2),rs.getDate(3),rs.getString(5),rs.getFloat(6),rs.getDate(7));
            list.add(l);
        }
        
        return list;    
    }
    
    private String formatDate(Date dateexpiration) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateexpiration);
    }
}

