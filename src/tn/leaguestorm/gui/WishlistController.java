/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.tests.Front;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class WishlistController implements Initializable {
private ListView<Article> wishlistListView;
    @FXML
    private ImageView imageView;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PriceLabel;
    private VBox chosenCard;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<Article> wishlistItems = FXCollections.observableArrayList(wishlistListView.getItems());
wishlistListView.setItems(wishlistItems);
    }    
    public void setArticleDetails(Article article) {
    // Set the article details in the wishlist page
    NameLabel.setText(article.getTitre());
    PriceLabel.setText(Front.CURRENCY + article.getPrix());
    Image image = new Image(getClass().getResourceAsStream(article.getImage()));
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(200);
    imageView.setFitHeight(200);
    
    chosenCard.getChildren().addAll(imageView, NameLabel, PriceLabel);
}


}
