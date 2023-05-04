/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;
import tn.leaguestorm.entities.User;
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
    private TextField searchField;
    @FXML
    private Button searchButton;
    private List<Article> wishlist = new ArrayList<>();
    @FXML
    private Button btnwishlist;
    @FXML
    private Button btnWishlist;

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
            Label category = new Label(resultSet.getString("category_id"));

            // Create a new Label for the product name
            Label productName = new Label(resultSet.getString("titre"));
            productName.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold;");
            grid.add(productName, colIndex, rowIndex);

            // Create a new ImageView for the product image
            String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + resultSet.getString("image"); // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());

            ImageView imageView = new ImageView(image);
            // imageView.setBlendMode(BlendMode.MULTIPLY);

            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels

            // Add the ImageView to the GridPane
            grid.add(imageView, colIndex, rowIndex + 1);

            // Create a new Label for the product price
            Label price = new Label(resultSet.getString("prix"));
            grid.add(price, colIndex, rowIndex + 2);

            // Add a mouse click event handler to the ImageView
            int productId = resultSet.getInt("id"); // Get the product ID from the result set
            imageView.setOnMouseClicked(e -> {
                // Pass the product ID to the method that will handle the click event
                handleProductClick(productId, productName.getText(), Description.getText(),/* category.getText(),*/ price.getText(), imagePath);
            });

            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 4;
            }
        }
    }

    private void handleProductClick(int productId, String productName, String Description, /*String category,*/ String price, String imagePath) {
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

        handleProductClick(selectedArticle.getId(), selectedArticle.getTitre(), selectedArticle.getDescription(),/*selectedArticle.getCategory().toString(), */ Float.toString(selectedArticle.getPrix()), selectedArticle.getImage());
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
        // apc.setCategory(CategoryLabel.getText());

// Retrieve the image name from the database for the selected article
        String imageName = getImageNameFromDatabase(NameLabel.getText());

// Create a file path using the image name and the path of the directory where the images are stored
        String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + imageName;

// Create an Image object from the file path
        Image image = new Image(new File(imagePath).toURI().toString());

// Pass the Image object to the ArticleDetailsController and set it as the image of the article
        apc.setImage(image);

    }

    @FXML
    private void handleSearch(ActionEvent event) {
// Get the search term from the text field
        String searchTerm = searchField.getText().toLowerCase();

// Filter the list of articles to include only those that contain the search character in the title
        List<Article> filteredArticles = articles.stream()
                .filter(article -> article.getTitre().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

// Clear the existing items in the GridPane
        grid.getChildren().clear();

// Repopulate the GridPane with the filtered articles
        int colIndex = 0;
        int rowIndex = 0;
        for (Article article : filteredArticles) {
            // Create the UI elements for the article
            Label productName = new Label(article.getTitre());
            Label price = new Label(Float.toString(article.getPrix()));
            ImageView imageView = new ImageView(new Image(new File(article.getImage()).toURI().toString()));

            // Add the UI elements to the GridPane
            grid.add(productName, colIndex, rowIndex + 1);
            grid.add(price, colIndex, rowIndex + 2);
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

    public void removeArticleFromWishlist(/*int userId,*/int articleId) throws SQLException {
        // Define the SQL query to remove the article from the user's wishlist
        String query = "DELETE FROM user_article WHERE user_id = ? AND article_id = ?";
        int userId = 23;
        // Prepare the SQL statement with the query and the parameter values
        PreparedStatement statement = ds.getCnx().prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, articleId);

        // Execute the SQL statement
        int rowsDeleted = statement.executeUpdate();

        // Check if the article was successfully removed from the user's wishlist
        if (rowsDeleted > 0) {
            System.out.println("Article removed from wishlist.");
        } else {
            System.out.println("Failed to remove article from wishlist.");
        }
    }

    @FXML
    private void onWishlistButtonClicked(ActionEvent event) throws SQLException, FileNotFoundException {
        String query = "SELECT a.id, a.titre, a.description, a.prix, a.image FROM article a "
                + "INNER JOIN user_article w ON a.id = w.article_id "
                + "WHERE w.user_id = 23";

        // Execute the query and retrieve the result set
        Statement statement = ds.getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Clear the GridPane before adding new elements
        grid.getChildren().clear();

        // Define the column and row indexes for the GridPane
        int colIndex = 0;
        int rowIndex = 0;

        // Iterate through the result set and add each article to the GridPane
        while (resultSet.next()) {
            // Create a new Label for the article name
            Label nameLabel = new Label(resultSet.getString("titre"));
            grid.add(nameLabel, colIndex, rowIndex);

            // Create a new ImageView for the article image
            String imagePath = "C:/Users/Nadine/Pidev/public/upload/" + resultSet.getString("image"); // Replace this with your own file path and column name
            Image image = new Image(new File(imagePath).toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200); // Set the width to 200 pixels
            imageView.setFitHeight(200); // Set the height to 200 pixels
            grid.add(imageView, colIndex, rowIndex + 1);

            // Create a new Label for the article price
            Label priceLabel = new Label(resultSet.getString("prix"));
            grid.add(priceLabel, colIndex, rowIndex + 2);

            // Create a new Button to remove the article from the wishlist
            Button removeButton = new Button("Remove");
            int articleId = resultSet.getInt("id");
            removeButton.setOnAction(e -> {
                try {
                    // Remove the selected article from the wishlist
                    removeArticleFromWishlist(articleId);

                    // Remove the corresponding elements from the GridPane
                    grid.getChildren().removeAll(nameLabel, imageView, priceLabel, removeButton);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Add error handling here
                }
            });
            grid.add(removeButton, colIndex, rowIndex + 3);

            // Increment the column index
            colIndex++;

            // Move to the next row if the current row is full
            if (colIndex == 3) {
                colIndex = 0;
                rowIndex += 4;
            }
        }

        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Shop.fxml"));
                Parent root = loader.load(); // load the new FXML file
                Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
                Node sourceNode = (Node) event.getSource(); // get the source node of the current event
                Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
                Stage stage = (Stage) currentScene.getWindow(); // get the current stage
                stage.setScene(scene); // set the new scene as the content of the stage
            } catch (IOException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(returnButton, colIndex + 5, rowIndex + 5);

    }

    @FXML
    private void addToWishlist(ActionEvent event) {

        for (Article article : wishlist) {
            int articleId = article.getId();
            btnWishlist.setOnAction(e -> {
                // Get the current user
                // User user = getCurrentUser();
                int userId = 23;
                // Add the selected article to the wishlist database for the current user
                try {

                    PreparedStatement stmt = ds.getCnx().prepareStatement("INSERT INTO user_article (article_id, user_id) VALUES (?, ?)");
                    stmt.setInt(1, articleId);
                    stmt.setInt(2, userId);
                    stmt.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("The selected article has been added to your wishlist.");
                    alert.showAndWait();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Home.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage

    }

    @FXML
    private void help(ActionEvent event) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "Chatbot");
        pb.start();

    }

}
