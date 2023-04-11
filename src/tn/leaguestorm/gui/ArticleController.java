/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    @FXML
    private TextField tfImage;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private ComboBox<SubCategory> subcategory;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> titleColumn;
    @FXML
    private TableColumn<Article, ?> imageColumn;
    @FXML
    private TableColumn<Article, String> descriptionColumn;
    @FXML
    private TableColumn<Article, Float> priceColumn;
    @FXML
    private TableColumn<Article, String> typeColumn;
    @FXML
    private TableColumn<Article, Integer> stockColumn;
    @FXML
    private TableColumn<Article, Category> categoryColumn;
    @FXML
    private TableColumn<Article, SubCategory> subcategoryColumn;
    private ObservableList<Article> articles;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private Button btnImport;
    @FXML
    private Label LabelImage;

    @FXML
    ImageView imagep = null;
     @FXML
    private FontAwesomeIconView photo1;
    private String i ;
    byte [] image = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceArticle sa = new ServiceArticle();
        cbType.setItems(FXCollections.observableArrayList("For Rent", "For Sale"));
        try {
            articles = FXCollections.observableArrayList(sa.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subcategoryColumn.setCellValueFactory(new PropertyValueFactory<>("subcategory"));
        articleTable.setItems(articles);
        articleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tfTitle.setText(newSelection.getTitre());
                tfImage.setText(newSelection.getImage());
                taDescription.setText(newSelection.getDescription());
                cbType.setValue(newSelection.getType());
                //  tfStock.setText(newSelection.getStock());
                //   tfPrice.setText(newSelection.getPrix());

            } else {
                tfTitle.setText("");
            }
        });
    }

    @FXML
    private void saveArticle(ActionEvent event) throws SQLException {
        String title = tfTitle.getText();
        String image = tfImage.getText();
        String description = taDescription.getText();
        String priceS = /*Float.valueOf(tfPrice.getText());*/ tfPrice.getText();
        String type = cbType.getValue();
        String stockS = tfStock.getText();
        // Integer stock = Integer.valueOf(tfStock.getText());
        try {
            if (title.isEmpty() && image.isEmpty() && description.isEmpty() && priceS.isEmpty() && type.isEmpty() && stockS.isEmpty() /*&& category.isEmpty() && subcategory.isEmpty()*/) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;
            }
/*
            if (articles.contains(title)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Exsiting Title ");
                alert.setContentText("Title Already Exists !!");
                alert.showAndWait();
                return;
            }
*/

            if (title.length() < 2) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("title must contain at least 2 caracters !!");
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

                    float price = Float.parseFloat(priceS);
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
            
            int stock = Integer.parseInt(stockS);
            if (stock < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Stock can't be negative");
                alert.setContentText("Please fill the field with positive value !!");
                alert.showAndWait();
                return;
            }
            if (!stockS.matches("[0-9]+(\\.[0-9]+)?")) { // Check if the string contains only numbers and optional decimal point
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Stock must contains only numbers ");
                alert.setContentText("Please fill the field with Numbers !!");
                alert.showAndWait();
                return;
            }
     

            Article a = new Article(title, image, price, description, stock, type);
            ServiceArticle sa = new ServiceArticle();
            sa.ajouter(a);
            articleTable.refresh();
            tfTitle.setText("");
            tfImage.setText("");
            taDescription.setText("");
            tfPrice.setText("");
            cbType.setValue("");
            tfStock.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updateArticle(ActionEvent event) throws SQLException {
        String title = tfTitle.getText();
        String image = tfImage.getText();
        String description = taDescription.getText();
        Float price = Float.valueOf(tfPrice.getText());
        String type = cbType.getValue();
        Integer stock = Integer.valueOf(tfStock.getText());

        ServiceArticle sa = new ServiceArticle();

        Article a = articleTable.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select an Article to update !");
            alert.showAndWait();
            return;
        }

        /* ///String nom = tfNom.getText().trim();
        if (title.isEmpty() && image.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Field");
            alert.setContentText("Please enter a name !");
            alert.showAndWait();
            return;
        }*/
        a.setTitre(title);
        a.setImage(image);
        a.setDescription(description);
        a.setPrix(price);
        a.setStock(stock);
        a.setType(type);
        /*a.setCategory(category);
        a.setSubcategory(subcategory);*/

        sa.modifier(a);
        articleTable.refresh();
        tfTitle.setText("");
        tfImage.setText("");
        taDescription.setText("");
        tfPrice.setText("");
        cbType.setValue("");
        tfStock.setText("");
    }

    @FXML
    private void deleteArticle(ActionEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();

        Article a = articleTable.getSelectionModel().getSelectedItem();
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
        alert.setContentText("Are you sure you want to delete the selected article?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            sa.deleteArticle(a);
            articles.remove(a);
            articleTable.refresh();
            tfTitle.setText("");
            tfImage.setText("");
            taDescription.setText("");
            tfPrice.setText("");
            cbType.setValue("");
            tfStock.setText("");
        }

    }
/*            private MyConnection ds = MyConnection.getInstance();*/

    @FXML
    private void Import(ActionEvent event) {
   /*   Article a = null;
        // Create a file chooser
    JFileChooser fileChooser = new JFileChooser();

    // Show the file chooser dialog
    int result = fileChooser.showOpenDialog(null);

    // If the user selects a file, set the image attribute of the Article instance with the file path
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String imagePath = selectedFile.getAbsolutePath();
        a.setImage(imagePath);
    }

 String sql = "INSERT INTO article (image) VALUES (?)";   
 PreparedStatement pstmt = ds.getCnx().prepareStatement(sql);
    pstmt.setString(1, a.getImage());
    pstmt.executeUpdate();*/
  FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");
        fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File f = fc.showOpenDialog(null);
       /* String DBPath = "C:\\\\\\\\xampp\\\\\\\\htdocs\\\\\\\\Version-Integre\\\\\\\\public\\\\\\\\uploads\\\\\\\\"+f.getName();
        i=f.getName();
        a.setImage(i);
        System.out.println(a.getImage());
        if (f != null){
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image1 = SwingFXUtils.toFXImage(bufferedImage,null);
        ImageIO.write(bufferedImage, "jpg", new File(DBPath));
        imagep.setImage(image1);
        FileInputStream fin =new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte [1024];
        for (int readNum ;(readNum= fin.read(buf)) != -1 ;){
            bos.write(buf,0,readNum);
         image = bos.toByteArray();}
        } */}

}
