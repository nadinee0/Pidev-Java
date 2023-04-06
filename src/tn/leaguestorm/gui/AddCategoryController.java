 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import static java.awt.PageAttributes.MediaType.C;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.services.ServiceCategory;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class AddCategoryController implements Initializable {

     @FXML
    private TextField tfNom;
     @FXML
    private TextField tfImage;
     @FXML
        private Button btnAdd;   
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, String> nameColumn;
    @FXML
    private TableColumn<Category, String> imageColumn;
    @FXML
    private ObservableList<Category> categories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // categoryDao = DaoFactory.getCategoryDao();
       ServiceCategory sc = new ServiceCategory();
         try {
             categories = FXCollections.observableArrayList(sc.getAll());
         } catch (SQLException ex) {
             Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
         }
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
    categoryTable.setItems(categories);
    categoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            tfNom.setText(newSelection.getNom());
        } else {
            tfNom.setText("");
        }
    });
    }    
     @FXML
     private void saveCategory(ActionEvent event) throws SQLException{
       String nom = tfNom.getText();
       String image = tfImage.getText();
       
       if (nom.isEmpty() && image.isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty Field");
        alert.setContentText("Please fill the fields !!");
        alert.showAndWait();
        return;
    }
       
       Category c = new Category(nom, image);
       ServiceCategory sc = new ServiceCategory();
       sc.ajouter(c);   
       categoryTable.refresh();
     tfNom.setText("");
     tfImage.setText("");

     }

     @FXML
     private void UpdateCategory(ActionEvent event) throws SQLException{
    
         String nom = tfNom.getText();
       String image = tfImage.getText();
       ServiceCategory sc = new ServiceCategory();
   
         Category c = categoryTable.getSelectionModel().getSelectedItem();
    if (c == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setContentText("Please select a category to update.");
        alert.showAndWait();
        return;
    }
   
    ///String nom = tfNom.getText().trim();
    if (nom.isEmpty() && image.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty Field");
        alert.setContentText("Please enter a name.");
        alert.showAndWait();
        return;
    }
 
     c.setNom(nom);
     c.setImg(image);
     sc.modifier(c);  
     categoryTable.refresh();
     tfNom.setText("");
     tfImage.setText("");
 
     }

 @FXML
     private void DeleteCategory(ActionEvent event) throws SQLException{    
    
         ServiceCategory sc = new ServiceCategory(); 
       
        Category c = categoryTable.getSelectionModel().getSelectedItem();
    if (c == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setContentText("Please select a category to delete.");
        alert.showAndWait();
        return;
    }
    sc.deleteCategory(c);
    categories.remove(c);
    tfNom.setText("");
       
     }

    @FXML
    private void categories(SortEvent<Category> event) {
    }
}
