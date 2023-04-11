/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    public void ajouter(Organism o) {
        try {
        String req = "INSERT INTO `Organisme` (`nom_commercial`, `nom_juridique`,`date_de_fondation`, `phone_organization`, `email_organization`, `image`, `more`) VALUES ('"+o.getNom_commercial()+"', '"+o.getNom_juridique()+"', '"+o.getDate_de_fondation()+"','"+o.getPhone_organisation()+"','"+o.getEmail_organisation()+"','"+o.getImage()+"','"+o.getMore()+"')"; 
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
        System.out.println("Organisation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ajouter2(Organism o) {
        try {
        String req = "INSERT INTO `Organisme` (`nom_commercial`, `nom_juridique`,`date_de_fondation`, `phone_organization`, `email_organization`, `image`, `more`) VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, o.getNom_commercial());
        st.setString(2, o.getNom_juridique());
        st.setDate(3, (Date) o.getDate_de_fondation());
        st.setInt(4, o.getPhone_organisation());
        st.setString(5, o.getEmail_organisation());
        st.setString(6, o.getImage());
        st.setString(7, o.getMore());
   
        st.executeUpdate();
        System.out.println("Organism Ajouté !");
        }
        catch  (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 @Override
public void modifier(Organism o) {
    String sql = "UPDATE Organisme SET nom_commercial=?, nom_juridique=?, date_de_fondation=?, phone_organization=?, email_organization=?, image=?, more=? WHERE id=?";
    try {                        
        PreparedStatement ps = ds.getCnx().prepareStatement(sql);
        ps.setString(1, o.getNom_commercial());
        ps.setString(2, o.getNom_juridique());
        ps.setDate(3, (Date) o.getDate_de_fondation());
        ps.setInt(4, o.getPhone_organisation());
        ps.setString(5, o.getEmail_organisation());
        ps.setString(6, o.getImage());
        ps.setString(7, o.getMore());
        ps.setInt(8, o.getId());
        ps.executeUpdate();
        System.out.println("Organism Modifié !");
        System.out.println(o);
    } catch(SQLException ex) {
        System.err.println(ex.getMessage());
    }
}



    @Override
    public void supprimer(int id) {
        try {
        String req = "DELETE FROM `Organisme` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
         System.out.println("Organisation Supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Organism> getAll(){
        List<Organism> list = new ArrayList<>();
        try{
        String req = "SELECT * FROM Organisme";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Organism o = new Organism(rs.getInt("id"), rs.getString(2), rs.getString(3),rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getString(6), rs.getString(7));
            list.add(o);}
        } catch (SQLException ex){
        
        System.out.println(ex.getMessage());
        }
        
    
        return list;
    }
    private String formatDate(Date Date_de_fondation) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(Date_de_fondation);
    }
}
