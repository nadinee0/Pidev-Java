/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class CategoryFrontController implements Initializable {
    private MyConnection ds = MyConnection.getInstance();
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    try {
       
        // Retrieve category data from the database and create the grid view
        PreparedStatement stmt = ds.getCnx().prepareStatement("SELECT * FROM category");
        ResultSet rs = stmt.executeQuery();
        
        int col = 0;
        int row = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nom");
            String imagePath = rs.getString("img");
            
            File file = new File(imagePath);
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            
            Label nameLabel = new Label(name);
            nameLabel.setFont(new Font("Arial", 18));
            
            StackPane pane = new StackPane();
            pane.getChildren().addAll(imageView, nameLabel);
            pane.setOnMouseClicked(event -> {
                try {
                    showArticles(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
            grid.add(pane, col, row);
            
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

/*    
private void handleProductClick(int productId, String productName, String Description, /*String category, String price, String imagePath) {
        // Update the UI with the selected product details
        NameLabel.setText(productName);
        PriceLabel.setText(price);
        //  DescriptionLabel.setText(description);
        Img.setImage(new Image(new File(imagePath).toURI().toString()));
        Img.setStyle("-fx-background-color: transparent");
        DescriptionLabel.setText(Description);
        // CategoryLabel.setText(category);
        Article article = new Article(productId, productName, Description, Float.parseFloat(price), imagePath);
        wishlist.add(article);
    }
*/
public void showArticles(int categoryId) throws SQLException {
    String query = "SELECT * FROM article WHERE category_id = ?";
    PreparedStatement stmt = ds.getCnx().prepareStatement(query);
    stmt.setInt(1, categoryId);
    ResultSet rs = stmt.executeQuery();

    // Create a new GridPane to display the articles
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    int rowIndex = 0;

    // Iterate through the result set and add each article to the GridPane
    while (rs.next()) {
        // Create a new Label for the article title
        Label articleTitle = new Label(rs.getString("titre"));
        articleTitle.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        grid.add(articleTitle, 0, rowIndex);

        // Create a new Label for the article description
        Label articleDescription = new Label(rs.getString("description"));
        articleDescription.setWrapText(true);
        grid.add(articleDescription, 0, rowIndex + 1);

        // Create a new ImageView for the article image
        String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + rs.getString("image"); // Replace this with your own file path and column name
        Image image = new Image(new File(imagePath).toURI().toString());

        ImageView articleImage = new ImageView(image);
        articleImage.setFitWidth(200); // Set the width to 200 pixels
        articleImage.setFitHeight(200); // Set the height to 200 pixels
        grid.add(articleImage, 1, rowIndex, 1, 2);

        // Create a new Label for the article price
        Label articlePrice = new Label(rs.getString("prix"));
        articlePrice.setStyle("-fx-font-size: 14pt;");
        grid.add(articlePrice, 2, rowIndex + 1);

        // Add a mouse click event handler to the GridPane
        int articleId = rs.getInt("id"); // Get the article ID from the result set
        grid.setOnMouseClicked(e -> {
            // Pass the article ID to the method that will handle the click event
           // handleArticleClick(articleId);
        });

        // Increment the row index
        rowIndex += 2;
    }

    // Create a new Scene and add the GridPane to it
    Scene scene = new Scene(grid, 800, 600);

    // Create a new Stage and set its title and Scene
    Stage stage = new Stage();
    stage.setTitle("Articles");
    stage.setScene(scene);
    stage.show();
}

    
   
public void showArticleContent(String title) throws SQLException {
   /*  PreparedStatement stmt = ds.getCnx().prepareStatement("SELECT * FROM article WHERE titre = ?");
    stmt.setString(1, title);
    ResultSet rs = stmt.executeQuery();

    String content = "";
    while (rs.next()) {
        content = rs.getString("prix");
    }

    TextArea textArea = new TextArea(content);
    textArea.setWrapText(true);
    textArea.setEditable(false);

    VBox vbox = new VBox(textArea);
    Scene scene = new Scene(vbox, 400, 400);

    Stage stage = new Stage();
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
    
    
StackPane pane = new StackPane();
pane.getChildren().addAll(imageView, nameLabel);
pane.setOnMouseClicked(event -> {
    try {
        showArticles(category.getId());
    } catch (SQLException e) {
        e.printStackTrace();
    }
});

grid.add(pane, col, row);

col++;

if (col == 3) {
    col = 0;
    row++;
}

}*/

}}
