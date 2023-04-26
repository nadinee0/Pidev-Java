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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.tests.MainClass;
import tn.leaguestorm.utils.MyConnection;

public class ShopController implements Initializable {

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
    @FXML
    private VBox chosenCard;

    @FXML
    private Button btndetails;
    private Article selectedArticle;
    @FXML
    private Label DescriptionLabel;
    Article ar;
    @FXML
    private Button wishlist;

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

            Label Description = new Label(resultSet.getString("description"));
            //     grid.add(Description, colIndex, rowIndex + 1);

            // Create a new Label for the product name
            Label productName = new Label(resultSet.getString("titre"));
            grid.add(productName, colIndex, rowIndex + 1);

            // Create a new Label for the product price
            Label price = new Label(resultSet.getString("prix"));
            grid.add(price, colIndex, rowIndex + 2);

            // Create a new ImageView for the product image
            String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + resultSet.getString("image"); // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());

            ImageView imageView = new ImageView(image);
            // imageView.setBlendMode(BlendMode.MULTIPLY);

            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels

            // Add a mouse click event handler to the ImageView
            int productId = resultSet.getInt("id"); // Get the product ID from the result set
            imageView.setOnMouseClicked(e -> {
                // Pass the product ID to the method that will handle the click event
                handleProductClick(productId, productName.getText(), Description.getText(), price.getText(), imagePath);
            });

            // Add the ImageView to the GridPane
            grid.add(imageView, colIndex, rowIndex + 3);

            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 4;
            }
        }
    }

    private void handleProductClick(int productId, String productName, String Description, String price, String imagePath) {
        // Update the UI with the selected product details
        NameLabel.setText(productName);
        PriceLabel.setText(price);
        //  DescriptionLabel.setText(description);
        Img.setImage(new Image(new File(imagePath).toURI().toString()));
        Img.setStyle("-fx-background-color: transparent");
        DescriptionLabel.setText(Description);
        /*     
   btndetails.setOnAction(event -> {
    try {
        // Pass the product details to the controller of the new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleDetails.fxml"));
        Parent root = loader.load();
        ArticleDetailsController controller = loader.getController();
        controller.setArticleDetails(productName, price, imagePath); 

        // Create a new window and display the new scene
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
});*/
    }

    /*
    private void setChosenArticle(Article article) {
        NameLabel.setText(article.getTitre());
        PriceLabel.setText(Front.CURRENCY + article.getPrix());
        Image image = new Image(getClass().getResourceAsStream(article.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setBlendMode(BlendMode.MULTIPLY);

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        chosenCard.setStyle("-fx-background-color: #" + article.getColor() + ";\n"
                + "    -fx-background-radius: 30;");
        chosenCard.getChildren().clear();
        chosenCard.getChildren().addAll(imageView, NameLabel, PriceLabel);
    }*/
    private void setChosenArticle(Article article) {
        selectedArticle = article;
        NameLabel.setText(selectedArticle.getTitre());
        PriceLabel.setText(Front.CURRENCY + selectedArticle.getPrix());
        Image image = new Image(getClass().getResourceAsStream(selectedArticle.getImage()));
        ImageView imageView = new ImageView(image);
        //  imageView.setBlendMode(BlendMode.MULTIPLY);

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        chosenCard.setStyle("-fx-background-color: #" + selectedArticle.getColor() + ";\n"
                + "    -fx-background-radius: 30;");
        chosenCard.getChildren().clear();
        chosenCard.getChildren().addAll(imageView, NameLabel, PriceLabel);

        handleProductClick(selectedArticle.getId(), selectedArticle.getTitre(), selectedArticle.getDescription(), Float.toString(selectedArticle.getPrix()), selectedArticle.getImage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateGridPane();
            if (articles.size() > 0) {
                setChosenArticle(articles.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Article article) {
                        setChosenArticle(article);
                    }
                };
            }

            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < articles.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("tn/leaguestorm/gui/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(articles.get(i), myListener);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row);

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

    private String getImageNameFromDatabase(String articleName) {
        String imageName = null;

        try {
            PreparedStatement pstmt = ds.getCnx().prepareStatement("SELECT image FROM article WHERE titre = ?");
            pstmt.setString(1, articleName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                imageName = rs.getString("image");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageName;
    }

    @FXML
    private void details(ActionEvent event) throws SQLException, IOException {
        String query = "SELECT * FROM article";

        // Execute the query and retrieve the result set
        Statement statement = ds.getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleDetails.fxml"));
        Parent root = loader.load();
        NameLabel.getScene().setRoot(root);

        ArticleDetailsController apc = loader.getController();
        apc.setTitre(NameLabel.getText());
        apc.setPrix(PriceLabel.getText());
        apc.setDescription(DescriptionLabel.getText());

        Image image = new Image(getClass().getResourceAsStream(selectedArticle.getImage()));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        apc.setImage(image);
        /* String productName = NameLabel.getText(); // Replace with your actual code to get the product name
        String imageName = getImageNameFromDatabase(NameLabel.getText());

        String imagePath = "C:/Users/Nadine/Pidev/public/upload/";
        Image image = new Image(new File(imagePath).toURI().toString());
        apc.setImage(image);

         */

    }

}
