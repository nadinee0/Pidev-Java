/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class AddArticleController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnSubCategory;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private ComboBox<String> cbCategory;
    @FXML
    private ComboBox<String> cbSubcategory;
    @FXML
    private Button btnAdd;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private Button btnImport;
    @FXML
    private Label LabelImage;
    @FXML
    private TextField tfImg;
    @FXML
    private VBox pnItems;
  @FXML
    private ListView<Article> articleList;

    private ObservableList<Article> articles;
        private MyConnection ds = MyConnection.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

   
        @FXML
    private void category(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Category.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

    @FXML
    private void subcategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/SubCategory.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }

    @FXML
    private void article(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Article.fxml"));
        Parent root = loader.load(); // load the new FXML file
        Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
        Node sourceNode = (Node) event.getSource(); // get the source node of the current event
        Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
        Stage stage = (Stage) currentScene.getWindow(); // get the current stage
        stage.setScene(scene); // set the new scene as the content of the stage
    }
    @FXML
    private void saveArticle(ActionEvent event) throws SQLException {
   ServiceArticle sa = new ServiceArticle();

        String title = tfTitle.getText();
        String image = tfImg.getText();
        String description = taDescription.getText();
        String priceS = /*Float.valueOf(tfPrice.getText()); */ tfPrice.getText();
        float price = Float.valueOf(tfPrice.getText());
        String type = cbType.getValue();
        String stockS = tfStock.getText();
        Integer stock = Integer.valueOf(tfStock.getText());

        String category = cbCategory.getValue();
        int categoryId = sa.getCategoryIDByName(category);
        String subcategory = cbSubcategory.getValue();
        int subcategoryId = sa.getSubCategoryIDByName(subcategory);

        Article a = new Article(title, price, description, stock, categoryId, type, subcategoryId);

        try {
            if (title.isEmpty() && description.isEmpty() && priceS.isEmpty() && type.isEmpty() && stockS.isEmpty() && category.isEmpty() && subcategory.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;
            }

            if (title.length() < 2 || title.length() > 20) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("title must must be between 2 and 50 characters !!");
                alert.showAndWait();
                return;
            }
            if (!description.matches("[a-zA-Z0-9\\s'.,-]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Input");
                alert.setContentText("Article description can only contain letters, digits, spaces, apostrophes, commas, periods, and hyphens !");
                alert.showAndWait();
                return;
            }

            if (description.length() < 10 || description.length() > 100) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("Description must be between 10 and 100  !!");
                alert.showAndWait();
                return;
            }

            //  float price = Float.parseFloat(priceS);
            if (price < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Price can't be negative");
                alert.setContentText("Please fill the field with positive value !!");
                alert.showAndWait();
                return;
            }
            if (!priceS.matches("[0-9]+(\\.[0-9]+)?")) { // Check if the string contains only numbers and optional decimal point
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Price must contains only numbers ");
                alert.setContentText("Please fill the field with Numbers !!");
                alert.showAndWait();
                return;
            }

            //  int stock = Integer.parseInt(stockS);
            if (stock < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Stock can't be negative");
                alert.setContentText("Stock must be greater than zero!");
                alert.showAndWait();
                return;
            }
            if (!stockS.matches("\\d+")) { // Check if the string contains only digits
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stock must contain only whole numbers");
                alert.setContentText("Please fill the field with whole numbers only!");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }

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
          //  Import(event);
            //  sa.ajouter2(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully Added !");
            alert.showAndWait();

            articleList.refresh();
            tfTitle.setText("");
            tfImg.setText("");
            taDescription.setText("");
            tfPrice.setText("");
            cbType.setValue(null);
            tfStock.setText("");
            cbCategory.setValue(null);
            cbSubcategory.setValue(null);
        }

    
    }

    @FXML
    private void Import(ActionEvent event) {
    }
    
}
