/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.techstorm.entities.Events;
import pidev.techstorm.utils.DataSource;

/**
 *
 * @author USER
 */
public class ServiceEvents implements IService<Events> {
    
     private DataSource ds = DataSource.getInstance();
    
    public void ajouter(Events e) throws SQLException{
        String req = "INSERT INTO `events` (`title`, `description`,`image`,`location`,`url`) VALUES ('"+e.getTitle()+"', '"+e.getDescription()+"', '"+e.getImage()+"', '"+e.getLocation()+"', '"+e.getUrl()+"')";
         Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    public void ajouter2(Events e) throws SQLException{
        String req = "INSERT INTO `events` (`title`, `description`,`image`,`location`,`url`) VALUES (?,?,?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, e.getTitle());
        st.setString(2, e.getDescription());
        st.setString(5, e.getImage());
        st.setString(6, e.getLocation());
        st.setString(7, e.getUrl());
        
        st.executeUpdate();
    }

    @Override
    public void modifier(Events p) throws SQLException {
        String req = "UPDATE `events` SET `title` = '"+p.getTitle()+"', `description` = '"+p.getDescription()+"', `image` = '"+p.getImage()+"', `location` = '"+p.getLocation()+"', `url` = '"+p.getUrl()+"' WHERE `events`.`id` = "+p.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `events` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Events> getAll() throws SQLException {
         List<Events> list = new ArrayList<>();
        
        String req = "Select * from events";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Events p = new Events(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(7), rs.getString(8));
            list.add(p);
        }
        
        return list;
    }
    
}
