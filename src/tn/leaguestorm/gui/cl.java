/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

/**
 *
 * @author Nadine
 */import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class cl extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Set up the layout
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hbox);
        borderPane.setRight(vbox);
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        
        // Create and add components to the layout
        ImageView imageView = new ImageView(new Image("tn/leaguestorm/img/4f35d081925e67da0859b188f125fc43.png"));
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        gridPane.add(imageView, 0, 0, 1, 5);
        
        Label titleLabel = new Label("Article Title");
        titleLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 1, 0);
        
        Label priceLabel = new Label("$99.99");
        priceLabel.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");
        gridPane.add(priceLabel, 1, 1);
        
        TextArea descriptionTextArea = new TextArea("This is the description of the article. It can be multiple lines long and include any relevant information about the product.");
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setWrapText(true);
        descriptionTextArea.setStyle("-fx-font-size: 14pt;");
        gridPane.add(descriptionTextArea, 1, 2);
        
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 18pt;");
        hbox.getChildren().add(addToCartButton);
        
        Button addToWishlistButton = new Button("Add to Wishlist");
        addToWishlistButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 18pt;");
        hbox.getChildren().add(addToWishlistButton);
        
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 18pt;");
        hbox.getChildren().add(backButton);
        
        Scene scene = new Scene(borderPane, 800, 600);
        
        primaryStage.setTitle("Article Details Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}


