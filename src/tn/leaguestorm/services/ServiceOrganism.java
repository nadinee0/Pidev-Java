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
import tn.leaguestorm.entities.Organism;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author dell
 */
public class ServiceOrganism implements IService<Organism> {
    
    private MyConnection ds;

    public ServiceOrganism() {
        ds = MyConnection.getInstance();
    }

    @Override
    public void ajouter(Organism o) throws SQLException{
        String req = "INSERT INTO `Organism` (`nom commercial`, `nom juridique`, `Phone`, `email`, `image`, `more`) VALUES ('"+o.getNom_commercial()+"', '"+o.getNom_juridique()+"','"+o.getPhone_organisation()+"','"+o.getEmail_organisation()+"','"+o.getImage()+"','"+o.getMore()+"')"; 
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
    public void ajouter2(Organism o) throws SQLException {
        String req = "INSERT INTO `Organism` (`nom commercial`, `nom juridique`, `Phone`, `email`, `image`, `more`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(2, o.getNom_commercial());
        st.setString(3, o.getNom_juridique());
        st.setInt(4, o.getPhone_organisation());
        st.setString(5, o.getEmail_organisation());
        st.setString(6, o.getImage());
        st.setString(7, o.getMore());
   
        st.executeUpdate();
    }

    @Override
    public void modifier(Organism o) throws SQLException{
        String req = "UPDATE `Organism` SET `nom commercial` = '"+o.getNom_commercial()+"', `nom juridique` = '"+o.getNom_juridique()+"', `Phone`= '"+o.getPhone_organisation()+"', `email` = '"+o.getEmail_organisation()+"', `image` = '"+o.getImage()+"', `more` ='"+o.getMore()+"' WHERE `Organism`.`id` = "+o.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `Organism` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Organism> getAll() throws SQLException{
        List<Organism> list = new ArrayList<>();
    
        String req = "SELECT * FROM Organism";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Organism o = new Organism(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
            list.add(o);
        }
    
        return list;
    }
}
