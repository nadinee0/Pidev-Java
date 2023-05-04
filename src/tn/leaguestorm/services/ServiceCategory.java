/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.DriverManager;
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
import javafx.scene.control.Alert;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Nadine
 */
public class ServiceCategory implements IService<Category> {

    private MyConnection ds = MyConnection.getInstance();

    @Override
    public void ajouter(Category c) throws SQLException {
        String req = "INSERT INTO `category` (`nom`, `img`) VALUES ('" + c.getNom() + "', '" + c.getImg() + "')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void ajouter2(Category c) throws SQLException {
        String req = "SELECT id FROM `category` WHERE nom=?";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, c.getNom());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // Le nom de catégorie existe déjà
            System.out.println("This Category Already Exists ! ");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Exsiting Category ");
            alert.setContentText(c.getNom() + " Category Already Exists !!");
            alert.showAndWait();
            return;
        } else {
            String req1 = "INSERT INTO `category` (`nom`, `img`) VALUES (?,?)";
            PreparedStatement insertst = ds.getCnx().prepareStatement(req1);
            insertst.setString(1, c.getNom());
            insertst.setString(2, c.getImg());
            insertst.executeUpdate();
        }
    }

    @Override
    public void modifier(Category c) throws SQLException {
        String req = "UPDATE `category` SET `nom` = '" + c.getNom() + "', `img` = '" + c.getImg() + "' WHERE `category`.`id` = " + c.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

public void updateCategory(Category c) throws SQLException {
    String req = "UPDATE category SET nom = ?, img = ? WHERE id = ?";
    PreparedStatement st = ds.getCnx().prepareStatement(req);
    st.setString(1, c.getNom());
    st.setString(2, c.getImg());
    st.setInt(3, c.getId());
    st.executeUpdate();
}


    /*   public void updateCategory(int CategoryId, String newSubCategoryName, String img) throws SQLException {
    String query = "UPDATE category SET nom= ?, img = ? WHERE id = ?";
    PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
    preparedStatement.setString(1, newSubCategoryName);
    preparedStatement.setInt(2, getCategoryIDByName(newCategory));
    preparedStatement.setInt(3, CategoryId);
    preparedStatement.executeUpdate();
}*/
    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `category` WHERE id =" + id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void deleteCategory(Category category) throws SQLException {
        String sql = "DELETE FROM category WHERE nom= ?";
        PreparedStatement statement = ds.getCnx().prepareStatement(sql);
        statement.setString(1, category.getNom());
        statement.executeUpdate();
    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> list = new ArrayList<>();

        String req = "Select * from category";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Category c = new Category(rs.getInt("id"), rs.getString(2), rs.getString(3));
            list.add(c);
        }

        return list;
    }

}
