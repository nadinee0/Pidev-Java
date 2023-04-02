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
import java.util.ArrayList;
import java.util.List;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Nadine
 */
public class ServiceCategory implements IService<Category>{

        private MyConnection ds = MyConnection.getInstance();
    
    @Override
    public void ajouter(Category c) throws SQLException {
 String req = "INSERT INTO `category` (`nom`, `img`) VALUES ('"+c.getNom()+"', '"+c.getImg()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

       public void ajouter2(Category c) throws SQLException{
        String req = "INSERT INTO `category` (`nom`, `img`) VALUES (?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, c.getNom());
        st.setString(2, c.getImg());
       
        st.executeUpdate();
    } 
    
    @Override
    public void modifier(Category c) throws SQLException {
  String req = "UPDATE `category` SET `nom` = '"+c.getNom()+"', `img` = '"+c.getImg()+"' WHERE `category`.`id` = "+c.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req); 
    }

    @Override
    public void supprimer(int id) throws SQLException {
     String req = "DELETE FROM `category` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req); 
    }

    @Override
    public List<Category> getAll() throws SQLException {
    List<Category> list = new ArrayList<>();
        
        String req = "Select * from category";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Category c = new Category(rs.getInt("id"), rs.getString(2), rs.getString(3));
            list.add(c);
        }
        
        return list;   
    }
    
    
    
}
