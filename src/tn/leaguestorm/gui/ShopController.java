/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import tn.leaguestorm.tests.Front;
import tn.leaguestorm.tests.MyListener;
import tn.leaguestorm.entities.Article;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.tests.MainClass;
import tn.leaguestorm.utils.MyConnection;

public class ShopController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    private Label fruitNameLable;

    private Label fruitPriceLabel;

    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Article> articles = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private ImageView Img;
    Article a = new Article();
    private MyConnection ds = MyConnection.getInstance();
    ServiceArticle sa = new ServiceArticle();

    public void populateGridPane() throws SQLException, FileNotFoundException {

        String query = "SELECT * FROM article";

// Execute the query and retrieve the result set
        Statement statement = ds.getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // Define the column and row indexes for the GridPane
        int colIndex = 0;
        int rowIndex = 0;

// Iterate through the result set and add each product to the GridPane
        while (resultSet.next()) {
            // Create a new Label for the product name
            Label productName = new Label(resultSet.getString("titre"));
            grid.add(productName, colIndex, rowIndex);

String imagePath = "C:/Users/Nadine/Pidev/public/uploads/" + resultSet.getString("image") ; // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels
            // Create a new ImageView for the product image
            /*  ImageView productImage = new ImageView(new Image(resultSet.getString("image")));
             */ grid.add(imageView, colIndex, rowIndex + 1);

              Label price = new Label(resultSet.getString("prix"));
            grid.add(price, colIndex, rowIndex + 1);
            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 2;
            }
        }
    }

      private void setChosenArticle(Article article) {
        fruitNameLable.setText(article.getTitre());
        fruitPriceLabel.setText(Front.CURRENCY + article.getPrix());
        image = new Image(getClass().getResourceAsStream(article.getImage()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + article.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }
      

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /*      try {
            populateGridPane();
            if (articles.size() > 0) {
                setChosenArticle(articles.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Article article ) {
                        setChosenArticle(article);
                    }
                };
            }
            
            int column = 0;
            int row = 1;
            try {
            for (int i = 0; i < articles.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("gui/item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
              ItemController itemController = fxmlLoader.getController();
                  itemController.setData(articles.get(i),myListener);
            
            if (column == 3) {
            column = 0;
            row++;
            }

            grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            
            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            GridPane.setMargin(anchorPane, new Insets(10));
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */ try {
            // Call the method to populate the GridPane with the products from the database
            populateGridPane();
        } catch (SQLException e) {
            e.printStackTrace();
} catch (FileNotFoundException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }}

