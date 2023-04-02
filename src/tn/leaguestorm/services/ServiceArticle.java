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
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Nadine
 */
public class ServiceArticle  implements IService<Article> {

        private MyConnection ds = MyConnection.getInstance();
    
    @Override
    public void ajouter(Article a) throws SQLException {
        String req = "INSERT INTO `article` (`titre`, `image`, `prix`,`description`,`stock`,`type`) VALUES ('"+a.getTitre()+"', '"+a.getImage()+"', '"+a.getPrix()+"', '"+a.getDescription()+"', '"+a.getStock()+"', '"+a.getType()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

       public void ajouter2(Article a) throws SQLException{
        String req = "INSERT INTO `article` (`titre`, `image`, `prix`,`description`,`stock`,`type`) VALUES (?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, a.getTitre());
        st.setString(2, a.getImage());
        st.setFloat(3, a.getPrix());
        st.setString(4, a.getDescription());
        st.setInt(5, a.getStock());
        st.setString(6, a.getType());
        
        st.executeUpdate();
    } 
       
    @Override
    public void modifier(Article a) throws SQLException {
        String req = "UPDATE `article` SET `titre` = '"+a.getTitre()+"', `image` = '"+a.getImage()+"', `prix` = '"+a.getPrix()+"', `description` = '"+a.getDescription()+"', `stock` = '"+a.getStock()+"', `type` = '"+a.getType()+"' WHERE `article`.`id` = "+a.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `article` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);   
    }

    @Override
    public List<Article> getAll() throws SQLException {
        
        List<Article> list = new ArrayList<>();
        
        String req = "Select * from article";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Article a = new Article(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getString(8));
            list.add(a);
        }
        
        return list;   
    }
    
}
