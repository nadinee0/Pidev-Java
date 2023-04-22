/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.ValidationException;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ArticleController implements Initializable {

    Article a = new Article();
    @FXML
    private TextField tfTitle;
    private TextField tfImage;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private ListView<Article> articleList;

    private ObservableList<Article> articles;

    @FXML
    private Button btnImport;


    @FXML
    private ComboBox<String> cbCategory;
    @FXML
    private ComboBox<String> cbSubcategory;
   
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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ImageView imageView;
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Article a = new Article();
// TODO
        ServiceArticle sa = new ServiceArticle();
      //  cbType.setItems(FXCollections.observableArrayList("For Rent", "For Sale"));
       try {
          articles = FXCollections.observableArrayList(sa.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<String> categoryNames = sa.getAllCategoryNames();
            cbCategory.getItems().addAll(categoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Show the combo box when it is clicked
        cbCategory.setOnMouseClicked(event -> {
            cbCategory.show();
        });
       /* try {
            List<String> SubcategoryNames = sa.getAllSubCategoryNames();
            cbSubcategory.getItems().addAll(SubcategoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }*
        // Show the combo box when it is clicked
        cbSubcategory.setOnMouseClicked(event -> {
            cbSubcategory.show();
        });*/

           // Retrieve the article data using a separate SQL function
           
            articleList.setItems(articles);
            
            
  /*     catch (SQLException e) {
            // Handle any SQL exceptions that occur
            e.printStackTrace();
        }

*/
        
        
        /*    String req = "Select * from article";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        Article a = new Article(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
        //   categories.add(c);
        //}
        articleList.getItems().add(a);
        }*/
        // Personnalisation de la ListView pour afficher les catégories
        /*  articleList.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() {
        @Override
        public ListCell<Article> call(ListView<Article> listView) {
        return new ListCell<Article>() {
        @Override
        protected void updateItem(Article article, boolean empty) {
        super.updateItem(article, empty);
        if (article == null || empty) {
        setText(null);
        } else {
        // Customize the appearance of the cell here
        setText(article.getTitre() + " - " + article.getPrix());
        setGraphic(new ImageView(article.getImage()));
        }
        }
        };
        }
        });*/
 /*  articleList.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() {
        @Override
        public ListCell<Article> call(ListView<Article> listView) {
        return new ListCell<Article>() {
        private ImageView imageView = new ImageView();
        
        @Override
        protected void updateItem(Article article, boolean empty) {
        super.updateItem(article, empty);
        if (article == null || empty) {
        setText(null);
        setGraphic(null);
        } else {
        try {
        // Set the text of the cell
        setText(article.getTitre() + " - " + article.getPrix());
        
        // Set the image of the cell
        Blob blob = rs.getBlob("image"); // Get the binary data from the "image" column as a Blob object
        byte[] imageData = blob.getBytes(1, (int) blob.length()); // Convert the Blob object to a byte array
        Image image = new Image(new ByteArrayInputStream(imageData));
        
        //   byte[] imageData = article.getImage(); // Get the image data from the article
        if (imageData != null) {
        try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
        Image image = new Image(inputStream) {
        };
        imageView.setImage(image);
        setGraphic(imageView);
        } catch (IOException e) {
        e.printStackTrace();
        }
        } else {
        setGraphic(null);
        }
        } catch (SQLException ex) {
        Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        };
        }
        });*/// Retrieve the article data from the database
        /*    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getCnx();
            String sql = "SELECT article.id, article.titre, article.description, article.prix, article.stock, article.type, article.image, category.nom AS category_name, sub_category.nom_sub_category AS subcategory_name FROM article JOIN category ON article.category_id = category.id JOIN sub_category ON article.sub_category_id = sub_category.id WHERE article.id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, a.getId());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("titre");
                String description = rs.getString("description");
                float price = rs.getFloat("prix");
                int stock = rs.getInt("stock");
                String type = rs.getString("type");
                byte[] imageData = rs.getBytes("image");
                String imageS = new String(imageData, StandardCharsets.UTF_8);
                String categoryName = rs.getString("category_name");
                String subcategoryName = rs.getString("subcategory_name");
                
                // Convert the image data to an Image object
                Image image = null;
                if (imageData != null) {
                    image = new Image(new ByteArrayInputStream(imageData));
                }
                
                // Create the Category and SubCategory objects
                Category category = new Category(categoryName);
                SubCategory subCategory = new SubCategory(category, subcategoryName);
                
                // Create the Article object and add it to the list
                Article article = new Article(id, title, imageS, price, description, stock, category, type, subCategory);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        // Display the article data in the UI
        articles.forEach(article -> {
            // Create a new ListView cell for each article
            ListCell<Article> cell = new ListCell<Article>() {
                @Override
                protected void updateItem(Article item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty || item == null) {
                        // If the cell is empty, clear the content
                        setText(null);
                        setGraphic(null);
                    } else {
                        // If the cell contains an article, display its title, category name, subcategory name, and image (if available)
                        setText(item.getTitre() + " - " + item.getCategory().getNom() + " - " + item.getSubcategory().getNomSubCategory());
                        
                        if (item.getImage() != null) {
                            ImageView imageView = new ImageView(item.getImage());
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            setGraphic(imageView);
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            };
            
            // Add the cell to the ListView
            //   articleList.getItems().add(cell);
        });
        /*    articleList.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() {
        @Override
        public ListCell<Article> call(ListView<Article> listView) {
        return new ArticleListCell();
        }
        });*/
 /* categoryColumn.setCellValueFactory(new Callback<ListColumn.CellDataFeatures<SubCategory, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<SubCategory, String> cellData) {
        String categoryName = cellData.getValue().getCategory().getNom();
        return new SimpleStringProperty(categoryName);
        }
        });
        
        
        // Load the data into the table
        List<Article> articles;
        try {
        articles = sa.getAll();
        articleList.getItems().setAll(articles);
        } catch (SQLException ex) {
        Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }  */
        articleList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tfTitle.setText(newSelection.getTitre());
//                tfImage.setText(newSelection.getImage());
                taDescription.setText(newSelection.getDescription());
                //cbType.setValue(newSelection.getType());
                tfStock.setText(String.valueOf(newSelection.getStock()));
                tfPrice.setText(String.valueOf(newSelection.getPrix()));
                cbSubcategory.setValue(newSelection.getSubcategory().getNomSubCategory());
                cbCategory.setValue(newSelection.getCategory().getNom());

            } else {
                tfTitle.setText("");
                taDescription.setText("");
                //cbType.setValue(null);
                tfStock.setText("");
                tfPrice.setText("");
                cbSubcategory.setValue(null);
                cbCategory.setValue(null);

            }
        });
    }

    @FXML
    private void saveArticle(ActionEvent event) throws SQLException, IOException {
        ServiceArticle sa = new ServiceArticle();

        String title = tfTitle.getText();
      //  String image = tfImg.getText();
        String description = taDescription.getText();
        String priceS = /*Float.valueOf(tfPrice.getText()); */ tfPrice.getText();
        float price = Float.valueOf(tfPrice.getText());
        //String type = cbType.getValue();
        String stockS = tfStock.getText();
        Integer stock = Integer.valueOf(tfStock.getText());

        String category = cbCategory.getValue();
        int categoryId = sa.getCategoryIDByName(category);
        String subcategory = cbSubcategory.getValue();
        int subcategoryId = sa.getSubCategoryIDByName(subcategory);

     //   Article a = new Article(title, price, description, stock, categoryId subcategoryId);

        try {
            if (title.isEmpty() && description.isEmpty() && priceS.isEmpty()  && stockS.isEmpty() && category.isEmpty() && subcategory.isEmpty()) {
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
            String req1 = "INSERT INTO `article` (`titre`, `image`,`prix`,`description`,`stock`,`category_id`,`sub_category_id`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st1 = ds.getCnx().prepareStatement(req1);
            st1.setString(1, a.getTitre());
            st1.setString(2, a.getImage());
            st1.setFloat(3, a.getPrix());
            st1.setString(4, a.getDescription());
            st1.setInt(5, a.getStock());
            st1.setInt(6, categoryId);
            st1.setInt(7, subcategoryId);
            st1.executeUpdate();
          //  Import(event);
            //  sa.ajouter2(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully Added !");
            alert.showAndWait();

            articleList.refresh();
            tfTitle.setText("");
          //  tfImg.setText("");
            taDescription.setText("");
            tfPrice.setText("");
//            cbType.setValue("");
            tfStock.setText("");
            cbCategory.setValue(null);
            cbSubcategory.setValue(null);
        }

    }

    @FXML
    private void updateArticle(ActionEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();

        String title = tfTitle.getText();
//        String image = tfImage.getText();
        String description = taDescription.getText();
        Float price = Float.valueOf(tfPrice.getText());
       // String type = cbType.getValue();
        Integer stock = Integer.valueOf(tfStock.getText());
        String newCategory = cbCategory.getValue();
        int categoryId = sa.getCategoryIDByName(newCategory);
        String newSubCategory = cbSubcategory.getValue();
        int subcategoryId = sa.getSubCategoryIDByName(newSubCategory);

        Article a = articleList.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select an Article to update !");
            alert.showAndWait();
            return;
        }

        sa.updateArticle(a.getId(), title, price, description, stock, newCategory,  newSubCategory);
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully updated !");
            alert.showAndWait();
            
        articleList.refresh();
        tfTitle.setText("");
       // tfImage.setText("");
        taDescription.setText("");
        tfPrice.setText("");
       // cbType.setValue("");
        tfStock.setText("");
        cbCategory.setValue(null);
        cbSubcategory.setValue(null);
    }

    @FXML
    private void deleteArticle(ActionEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();

        Article a = articleList.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select an Article to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Article");
        alert.setContentText("Are you sure you want to delete the selected article : " + a.getTitre() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            sa.deleteArticle(a);
            articles.remove(a);
            //   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Article Successfully Deleted !");
            alert.showAndWait();
            articleList.refresh();
            tfTitle.setText("");
//            tfImage.setText("");
            taDescription.setText("");
            tfPrice.setText("");
        //    cbType.setValue("");
            tfStock.setText("");
        }

    }

    private MyConnection ds = MyConnection.getInstance();

    @FXML
    private void Import(ActionEvent event) throws IOException {
FileChooser fileChooser = new FileChooser();
File selectedFile = fileChooser.showOpenDialog(null);
if (selectedFile != null) {
    // User has selected a file, do something with it
    Path sourcePath = selectedFile.toPath();
    Path targetPath = Paths.get("C:/Users/Nadine/Pidev/public/img"); // replace with the desired path to save the image
    try {
        Files.copy(sourcePath, targetPath.resolve(selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);
    } catch (IOException e) {
        // handle the exception
    }
}




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
    private void handleClicks(ActionEvent event) {
    }
    

}
