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
public class ServiceArticle implements IService<Article> {

    private MyConnection ds = MyConnection.getInstance();

    @Override
    public void ajouter(Article a) throws SQLException {
        String req = "INSERT INTO `article` (`titre`, `image`, `prix`,`description`,`stock`,`type`) VALUES ('" + a.getTitre() + "', '" + a.getImage() + "', '" + a.getPrix() + "', '" + a.getDescription() + "', '" + a.getStock() + "', '" + a.getType() + "')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void ajouter2(Article a) throws SQLException {

        int categoryId = getCategoryIDByName(a.getCategory().getNom());
        int subcategoryId = getSubCategoryIDByName(a.getSubcategory().getNomSubCategory());

        String req = "SELECT id FROM `article` WHERE titre=?";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, a.getTitre());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // The article name already exists
            System.out.println("This Article Already Exists ! ");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Exsiting Article ");
            alert.setContentText(a.getTitre() + " Article Already Exists !!");
            alert.showAndWait();
            return;
        } else {
            String req1 = "INSERT INTO `article` (`titre`, `image`,`prix`,`description`,`stock`,`category_id`,`type`,`sub_category_id`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st1 = ds.getCnx().prepareStatement(req1);
            st1.setString(1, a.getTitre());
            st1.setString(2, a.getImage());
            st1.setFloat(3, a.getPrix());
            st1.setString(4, a.getDescription());
            st1.setInt(5, a.getStock());
            st1.setInt(6, categoryId);
            st1.setString(7, a.getType());
            st1.setInt(8, subcategoryId);
            st1.executeUpdate();
        }
    }

    @Override
    public void modifier(Article a) throws SQLException {
        String req = "UPDATE `article` SET `titre` = '" + a.getTitre() + "', `image` = '" + a.getImage() + "', `prix` = '" + a.getPrix() + "', `description` = '" + a.getDescription() + "', `stock` = '" + a.getStock() + "', `type` = '" + a.getType() + "' WHERE `article`.`id` = " + a.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void updateArticle(int articleId, String title, String image, float price, String description, int stock, String newCategory, String type, String newSubCategory) throws SQLException {
        String query = "UPDATE sub_category SET nom_sub_category = ?, category_id = ? WHERE id = ?";
        PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, image);
        preparedStatement.setFloat(3, price);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, stock);
        preparedStatement.setInt(6, getCategoryIDByName(newCategory));
        preparedStatement.setString(7, type);
        preparedStatement.setInt(8, getSubCategoryIDByName(newSubCategory));
        preparedStatement.setInt(9, articleId);
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `article` WHERE id =" + id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void deleteArticle(Article article) throws SQLException {
        String sql = "DELETE FROM article WHERE id = ?";
        PreparedStatement statement = ds.getCnx().prepareStatement(sql);
        statement.setInt(1, article.getId());
        statement.executeUpdate();
    }

    public List<String> retrieveData() throws SQLException {
        List<String> articleData = new ArrayList<>();

        // Construct an SQL query to retrieve article data from the database
        String query = "SELECT article.titre AS article_name, article.image AS article_image,article.description AS article_descrip, category.nom AS category_name "
                + "FROM article "
                + "JOIN category ON article.category_id = category.id";
        PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Loop through the result set and add each row of data to the list
        while (resultSet.next()) {
            String articleName = resultSet.getString("article_name");
            String articleImg= resultSet.getString("article_image");
            String articleDescrip = resultSet.getString("article_descrip");
            String categoryName = resultSet.getString("category_name");
            String articleInfo = articleName +articleImg + articleDescrip+ " (Category: " + categoryName + ")";
            articleData.add(articleInfo);
        }

        return articleData;
    }

    @Override
    public List<Article> getAll() throws SQLException {

        //  List<Article> list = new ArrayList<>();

        /* String req = "Select * from article";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Article a = new Article(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getString(8));
            list.add(a);
        }

        return list;*/
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();

        conn = ds.getCnx();
        String sql = ("SELECT article.id, article.titre, article.description, article.image, article.type, article.prix,article.stock, category.nom AS category_name, sub_category.nom_sub_category AS subcategory_name FROM article JOIN category ON article.category_id = category.id JOIN sub_category ON article.sub_category_id = sub_category.id");
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int stock = rs.getInt("stock");
            String title = rs.getString("titre");
            String description = rs.getString("description");
            String image = rs.getString("image");
            String type = rs.getString("type");
            float price = rs.getFloat("prix");
            String categoryName = rs.getString("category_name");
            String subCategoryName = rs.getString("subcategory_name");

            Category category = new Category(categoryName);
            SubCategory subCategory = new SubCategory(category, subCategoryName);

            Article article = new Article(id, title, image, price, description, stock, category, type, subCategory);

            articles.add(article);
        }

        return articles;
    }

    public List<String> getAllCategoryNames() throws SQLException {
        /* Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;*/
        List<String> categoryNames = new ArrayList<>();

        // conn = ds.getCnx();
        String sql = "SELECT nom FROM category";
        //   stmt = conn.prepareStatement(sql);
        PreparedStatement statement = ds.getCnx().prepareStatement(sql);

        // rs = statement.executeQuery();
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String categoryName = rs.getString("nom");
            categoryNames.add(categoryName);
        }

        return categoryNames;

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

    public void insertArticle(String articleName, String categoryName) throws SQLException {
        // First, retrieve the category ID based on the category name
        int categoryID = getCategoryIDByName(categoryName);

        // Insert the article into the database
        String query = "INSERT INTO article (nom, category_id) VALUES (?, ?)";
        PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
        preparedStatement.setString(1, articleName);
        preparedStatement.setInt(2, categoryID);
        preparedStatement.executeUpdate();
    }

    public List<String> getAllSubCategoryNames() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> categoryNames = new ArrayList<>();

        conn = ds.getCnx();
        String sql = "SELECT nom_sub_category FROM sub_category";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String SubcategoryName = rs.getString("nom_sub_category");
            categoryNames.add(SubcategoryName);
        }

        return categoryNames;

    }

    public int getSubCategoryIDByName(String SubcategoryName) throws SQLException {
        String query = "SELECT id FROM sub_category WHERE nom_sub_category = ?";
        PreparedStatement preparedStatement = ds.getCnx().prepareStatement(query);
        preparedStatement.setString(1, SubcategoryName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            throw new SQLException("No category found with name " + SubcategoryName);
        }
    }
    

    
}
