/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ArticleDetailsController implements Initializable {

    @FXML
    private Label titleLabel;

    private ImageView imageView;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label categoryLabel;

    private String imagePath;
    @FXML
    private ImageView img;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setArticleDetails(String title, String price, String description, /*String category, */String imagePath) {
    titleLabel.setText(title);
    priceLabel.setText(String.valueOf(price));
    descriptionLabel.setText(description);
   // categoryLabel.setText(category.toString());
 /*  this.imagePath = imagePath;
    imageView.setImage(new Image(new File(imagePath).toURI().toString()));*/
   /* Image image = new Image(getClass().getResourceAsStream("C:/Users/Nadine/Pidev/public/upload/" + imagePath));
imageView.setImage(image);*/
            //Image image = new Image(new File(imagePath).toURI().toString());

}

    @FXML
    private void goBack(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Shop.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

  public void setTitre(String titre){
        titleLabel.setText(titre);
    }
    
    public void setPrix(String prix){
        priceLabel.setText(prix);
    }
    
 public void setDescription(String description){
        descriptionLabel.setText(description);
    }
    
    public void setCategory(String category){
        categoryLabel.setText(category);
    }
 public void setImage(Image image){
 image = new Image(getClass().getResourceAsStream("C:/Users/Nadine/Pidev/public/upload/" + imagePath));
imageView.setImage(image);
        
            }
}

