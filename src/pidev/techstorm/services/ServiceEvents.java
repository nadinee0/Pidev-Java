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
import java.util.stream.Collectors;
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
    
    public List<Events> rechercherEvents(String title) throws SQLException {
String req = "SELECT * FROM events WHERE title LIKE ? OR id LIKE ?";
PreparedStatement pst = ds.getCnx().prepareStatement(req);
pst.setString(1, "%" + title + "%");
pst.setString(2, "%" + title + "%");
List<Events> events = new ArrayList<>();
ResultSet rs = pst.executeQuery();

while(rs.next()){
    Events t = new Events(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("image"), rs.getString("location"), rs.getString("url"));
    events.add(t);
}

return events;
}
    
    public List<Events> Search1(String t) throws SQLException {

        List<Events> list1 = new ArrayList<>();
        List<Events> list2 = getAll();
        list1 = (list2.stream().filter(c -> c.getTitle().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
}
