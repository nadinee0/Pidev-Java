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
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Nadine
 */
public class ServiceSubcategory implements IService<SubCategory>{

          private MyConnection ds = MyConnection.getInstance();
    
    @Override
    public void ajouter(SubCategory s) throws SQLException {
        String req = "INSERT INTO `sub_category` (`nom_sub_category`) VALUES ('"+s.getNomSubCategory()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

       public void ajouter2(SubCategory s) throws SQLException{
        String req = "INSERT INTO `sub_category` (`nom_sub_category`) VALUES (?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, s.getNomSubCategory());
      
        st.executeUpdate();
    } 
    
    @Override
    public void modifier(SubCategory s) throws SQLException {
String req = "UPDATE `sub_category` SET `nom_sub_category` = '"+s.getNomSubCategory()+"' WHERE `sub_category`.`id` = "+s.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

    
    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `sub_category` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);     }
    
        public void deleteSubCategory(SubCategory subcategory) throws SQLException {
    String sql = "DELETE FROM sub_category WHERE id = ?";
         PreparedStatement statement = ds.getCnx().prepareStatement(sql);
        statement.setInt(1, subcategory.getId());
        statement.executeUpdate();  
}

    @Override
    public List<SubCategory> getAll() throws SQLException {
 List<SubCategory> list = new ArrayList<>();
        
       String req = "SELECT s.id, s.nom_sub_category, s.category_id, c.nom AS category_name FROM sub_category s JOIN category c ON s.category_id = c.id";
    Statement st = ds.getCnx().createStatement();
    ResultSet rs = st.executeQuery(req);
    while (rs.next()) {
        int id = rs.getInt("id");
        String nomSubCategory = rs.getString("nom_sub_category");
        String categoryName = rs.getString("category_name");
        Category category = new Category(categoryName);
        SubCategory sc = new SubCategory(id, category, nomSubCategory);
        list.add(sc);
        }
        
        return list;  
    }
  
    
}
