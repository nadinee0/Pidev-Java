/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.ByteArrayInputStream;
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

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
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
    private MyConnection ds = MyConnection.getInstance();
    @FXML
    private Label NameLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private ImageView Img;

    /**
     * Initializes the controller class.
     */
  /*  private List<Article> getData() throws SQLException {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();

 /*       
// Préparer la requête SQL
PreparedStatement stmt = ds.getCnx().prepareStatement("SELECT * FROM article");

// Exécuter la requête et récupérer le résultat
ResultSet rs = stmt.executeQuery();

// Parcourir le résultat et ajouter chaque objet à une liste
while (rs.next()) {
    Article ar = new Article();
    ar.setTitre(rs.getString("titre"));
    ar.setPrix((float) rs.getDouble("prix"));
    ar.setImage("image");
    //ar.setColor(rs.getString("color"));
    articles.add(ar);
}
// Créer une liste de couleurs
List<String> colors = new ArrayList<>();
colors.add("A7745B");
colors.add("6A7324");
colors.add("E6B0AA");
colors.add("F7DC6F");
colors.add("34495E");
colors.add("5F060E");
colors.add("F16C31");
colors.add("291D36");
colors.add("FB5D03");
colors.add("80080C");
colors.add("22371D");
colors.add("FFB605");
colors.add("5F060E");
colors.add("E7C00F");

// Créer un objet Random pour générer des couleurs aléatoires
Random random = new Random();

// Parcourir la liste de fruits et attribuer des couleurs aléatoires
for (Article ar : articles) {
    String color = colors.get(random.nextInt(colors.size()));
    ar.setColor(color);
}

// Fermer la connexion à la base de données
//rs.close();
//stmt.close();
//conn.close();
        return articles;
 */
    /*     article = new Article();
        article.setTitre("Peach");
        article.setPrix(1.50f);
        article.setImage("/img/ic_cart.png");
        article.setColor("F16C31");
        articles.add(article);
        return articles;*/
    //}

    private void setChosenFruit(Article article) {
        fruitNameLable.setText(article.getTitre());
        fruitPriceLabel.setText(Front.CURRENCY + article.getPrix());
        image = new Image(getClass().getResourceAsStream(article.getImage()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + article.getColor() + ";\n"
                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            /*    try {
            articles.addAll(getData());
            } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (articles.size() > 0) {
            setChosenFruit(articles.get(0));
            myListener = new MyListener() {
            @Override
            public void onClickListener(Article article) {
            setChosenFruit(article);
            }
            };
            }
            int column = 0;
            int row = 1;
            try {
            for (int i = 0; i < articles.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            ItemController itemController = fxmlLoader.getController();
            itemController.setData(articles.get(i), myListener);
            
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
            }*/
            // Retrieve the data for all articles
            String sql = "SELECT * FROM article";
            PreparedStatement pstmt = ds.getCnx().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
// Create a list of Java objects to hold the data for all articles
List<Article> articles = new ArrayList<>();

// Populate the list of Java objects with the data from the ResultSet
while (rs.next()) {
    Article article = new Article();
    article.setTitre(rs.getString("titre"));
    article.setPrix(rs.getFloat("prix"));
   //== article.setImage(rs.getString("image"));
    articles.add(article);
}

// Create a GridPane to display the articles
//GridPane gridPane = new GridPane();
grid.setHgap(10);
grid.setVgap(10);

// Loop through the list of Java objects to create JavaFX UI controls for each article
int row = 0;
for (Article article : articles) {
    Label titleLabel = new Label("Title:");
    Label priceLabel = new Label("Price:");
    Label imageLabel = new Label("Image:");

    TextField titleField = new TextField(article.getTitre());
   // TextField contentArea = new TextField(article.getPrix());
    //ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(article.getImage())));
// Assume that the image string is stored in the variable 'imageString'
/*byte[] imageBytes = Base64.getDecoder().decode(article.getImage());
Image image = new Image(new ByteArrayInputStream(imageBytes));
ImageView imageView = new ImageView(image);*/

    // Add the JavaFX UI controls to the GridPane
    grid.add(titleLabel, 0, row);
    grid.add(titleField, 1, row);
  //  grid.add(priceLabel, 0, row+1);
  //  grid.add(contentArea, 1, row+1);
   // grid.add(imageLabel, 0, row+2);
  //  grid.add(imageView, 1, row+2);

    row += 3;
}       } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
