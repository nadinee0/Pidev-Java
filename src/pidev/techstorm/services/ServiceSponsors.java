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
import pidev.techstorm.entities.Sponsors;
import pidev.techstorm.utils.DataSource;

/**
 *
 * @author USER
 */
public class ServiceSponsors implements IService<Sponsors>{
    private DataSource ds = DataSource.getInstance();

    @Override
    public void ajouter(Sponsors e) throws SQLException {
       String req = "INSERT INTO `events` (`num_tel`, `name`,`email`,`img`,`url`) VALUES ('"+e.getNum_tel()+"', '"+e.getName()+"', '"+e.getEmail()+"', '"+e.getImg()+"', '"+e.getUrl()+"')";
         Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    public void ajouter2(Sponsors e) throws SQLException{
        String req = "INSERT INTO `sponsors` (`num_tel`, `name`,`email`,`img`,`url`) VALUES (?,?,?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setInt(1, e.getNum_tel());
        st.setString(2, e.getName());
        st.setString(3, e.getEmail());
        st.setString(4, e.getImg());
        st.setString(5, e.getUrl());
        
        st.executeUpdate();
    }

    @Override
    public void modifier(Sponsors p) throws SQLException {
        String req = "UPDATE `sponsors` SET `num_tel` = '"+p.getNum_tel()+"', `name` = '"+p.getName()+"', `email` = '"+p.getEmail()+"', `img` = '"+p.getImg()+"', `url` = '"+p.getUrl()+"' WHERE `sponsors`.`id` = "+p.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `sponsors` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Sponsors> getAll() throws SQLException {
        List<Sponsors> list = new ArrayList<>();
        
        String req = "Select * from sponsors";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Sponsors p = new Sponsors(rs.getInt("id"), rs.getInt(5), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7));
            list.add(p);
        }
        
        return list;
    }
    
}
