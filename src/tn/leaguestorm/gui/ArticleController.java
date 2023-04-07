/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ArticleController implements Initializable {

    @FXML
    private TextField tfTitle;
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
    private RadioButton rbRent;
    @FXML
    private RadioButton rbSale;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, Integer> IDColumn;
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
    @FXML
    private ObservableList<Article> articles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ServiceArticle sa = new ServiceArticle();
 
        try {
            articles = FXCollections.observableArrayList(sa.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
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
            } else {
                tfTitle.setText("");
            }
        });
    }    

    @FXML
    private void saveArticle(ActionEvent event) {
    }

    @FXML
    private void updateArticle(ActionEvent event) {
    }

    @FXML
    private void deleteArticle(ActionEvent event) {
    }
    
}
