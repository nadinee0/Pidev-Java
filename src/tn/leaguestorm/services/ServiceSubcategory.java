/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.Connection;
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
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.utils.MyConnection;

/**
 *
 * @author Nadine
 */
public class ServiceSubcategory implements IService<SubCategory> {

    private MyConnection ds = MyConnection.getInstance();

    @Override
    public void ajouter(SubCategory s) throws SQLException {
        String req = "INSERT INTO `sub_category` (`nom_sub_category`,`category_id`) VALUES ('" + s.getNomSubCategory() + "','" + s.getCategory() + "')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
public void ajouter2(SubCategory s) throws SQLException {
    String req = "SELECT id FROM `sub_category` WHERE nom_sub_category=?";
    PreparedStatement st = ds.getCnx().prepareStatement(req);
    st.setString(1, s.getNomSubCategory());
    ResultSet rs = st.executeQuery();
    if (rs.next()) {
        // The subcategory name already exists
        System.out.println("This SubCategory Already Exists ! ");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(" Exsiting SubCategory ");
        alert.setContentText(s.getNomSubCategory() + " SubCategory Already Exists !!");
        alert.showAndWait();
        return;
    } else {
        //int categoryId = getCategoryIDByName(s.getCategory().getNom());
        String req1 = "INSERT INTO `sub_category` (`nom_sub_category`) VALUES (?)";
        PreparedStatement st1 = ds.getCnx().prepareStatement(req1);
        st1.setString(1, s.getNomSubCategory());
     //   st1.setInt(2, categoryId);
        st1.executeUpdate();
    }
}

public int getCategoryIDByName(String categoryName) throws SQLException {
    String query = "SELECT id FROM category WHERE nom = ?";
    PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
    preparedStatement.setString(1, categoryName);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
        return resultSet.getInt("id");
    } else {
        throw new SQLException("No category found with name " + categoryName);
    }
}



    @Override
    public void modifier(SubCategory s) throws SQLException {
        String req = "UPDATE `sub_category` SET `nom_sub_category` = '" + s.getNomSubCategory() + "' WHERE `sub_category`.`id` = " + s.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
   /* public void updateSubCategory(SubCategory subCategory) throws SQLException {
    Connection connection = ds.getInstance().getCnx();
    PreparedStatement ps = connection.prepareStatement("UPDATE sub_category SET category_id = ?, nom_sub_category = ? WHERE id = ?");
    ps.setInt(1, subCategory.getCategoryId());
    ps.setString(2, subCategory.getNomSubCategory());
    ps.setInt(3, subCategory.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
}*/
    public void updateSubCategory(int subCategoryId, String newSubCategoryName, String newCategory) throws SQLException {
    String query = "UPDATE sub_category SET nom_sub_category = ?, category_id = ? WHERE id = ?";
    PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
    preparedStatement.setString(1, newSubCategoryName);
    preparedStatement.setInt(2, getCategoryIDByName(newCategory));
    preparedStatement.setInt(3, subCategoryId);
    preparedStatement.executeUpdate();
}



    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `sub_category` WHERE id =" + id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void deleteSubCategory(SubCategory subcategory) throws SQLException {
        String sql = "DELETE FROM sub_category WHERE nom_sub_category = ?";
        PreparedStatement statement = ds.getCnx().prepareStatement(sql);
        statement.setString(1, subcategory.getNomSubCategory());
        statement.executeUpdate();
    }
        

    private Category loadCategoryById(int categoryId) {
        Category category = null;
        try {
            String sql = "SELECT * FROM category WHERE id=?";
            PreparedStatement statement = ds.getCnx().prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("nom_sub_category"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<SubCategory> getAll() throws SQLException {
        /*  List<SubCategory> list = new ArrayList<>();
        String req = "SELECT s.id, s.nom_sub_category, c.nom AS category_name FROM sub_category s JOIN category c ON s.category_id = c.id";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nomSubCategory = rs.getString("nom_sub_category");
            String categoryName = rs.getString("category_name");
            Category category = new Category(categoryName);
            SubCategory sc = new SubCategory(id, category, nomSubCategory);
            list.add(sc);
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<SubCategory> subCategories = new ArrayList<>();

        conn = ds.getCnx();
        String sql = "SELECT sub_category.id, sub_category.nom_sub_category, category.id AS category_id, category.nom AS category_name FROM sub_category JOIN category ON sub_category.category_id = category.id";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nom_sub_category");
            int categoryId = rs.getInt("category_id");
            String categoryName = rs.getString("category_name");
            Category category = new Category(categoryId, categoryName);
            SubCategory subCategory = new SubCategory(id, name, category);
            subCategories.add(subCategory);

        }
        return subCategories;
    }

    public List<String> getAllCategoryNames() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> categoryNames = new ArrayList<>();

        conn = ds.getCnx();
        String sql = "SELECT nom FROM category";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String categoryName = rs.getString("nom");
            categoryNames.add(categoryName);
        }

        return categoryNames;

    }

}
