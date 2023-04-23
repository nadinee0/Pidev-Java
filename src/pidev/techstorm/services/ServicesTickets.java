/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.techstorm.entities.Tickets;
import pidev.techstorm.utils.DataSource;

/**
 *
 * @author USER
 */
public class ServicesTickets implements IService<Tickets>{
    private Connection cnx;
    Statement st;
     private DataSource ds = DataSource.getInstance();

    @Override
    public void ajouter(Tickets e) throws SQLException {
       String req = "INSERT INTO `tickets` (`prix`, `quantite`,`description`,`type`) VALUES ('"+e.getPrix()+"', '"+e.getQuantite()+"', '"+e.getDescription()+"', '"+e.getType()+"')";
         Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    public void ajouter2(Tickets e) throws SQLException{
        String req = "INSERT INTO `tickets` (`prix`, `quantite`,`description`,`type`) VALUES (?,?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setDouble(1, e.getPrix());
        st.setInt(2, e.getQuantite());
        st.setString(3, e.getDescription());
        st.setString(4, e.getType());
        /*st.setEvents(5, e.getEvents());*/
        
        st.executeUpdate();
    }

    @Override
    public void modifier(Tickets p) throws SQLException {
        String req = "UPDATE `tickets` SET `prix` = '"+p.getPrix()+"', `quantite` = '"+p.getQuantite()+"', `description` = '"+p.getDescription()+"', `type` = '"+p.getType()+"' WHERE `tickets`.`id` = "+p.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `tickets` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Tickets> getAll() throws SQLException {
        List<Tickets> list = new ArrayList<>();
        
        String req = "Select * from tickets";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Tickets p = new Tickets(rs.getInt("id"), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            list.add(p);
        }
        
        return list;
    }
    public List<Tickets> rechercherTickets(String type) throws SQLException {
String req = "SELECT * FROM tickets WHERE type LIKE ? OR id LIKE ?";
PreparedStatement pst = ds.getCnx().prepareStatement(req);
pst.setString(1, "%" + type + "%");
pst.setString(2, "%" + type + "%");
List<Tickets> tickets = new ArrayList<>();
ResultSet rs = pst.executeQuery();

while(rs.next()){
    Tickets t = new Tickets(rs.getInt("id"), rs.getInt("prix"), rs.getInt("quantite"), rs.getString("description"), rs.getString("type"));
    tickets.add(t);
}

return tickets;
}

    
//    public List<Tickets> rechercherTickets4(String type) throws SQLException {
//    
//    String req = "SELECT * FROM tickets WHERE type LIKE ? OR id LIKE ? ";
//    Statement st = ds.getCnx().createStatement();
//        //ensemble de resultat
//        
//   ResultSet rs = st.executeQuery(req);
//    rs.setString(1, "%" + type + "%");
//    rs.setString(2, "%" + type + "%");
//    List<Tickets> tickets = new ArrayList<>();
//    while(rs.next()){
//            Tickets p = new Tickets(rs.getInt("id"), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
//            tickets.add(p);
//        }
//    return tickets;
//}
    
}
