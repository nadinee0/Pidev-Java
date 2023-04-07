/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceSubcategory;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class SubCategoryController implements Initializable {

    @FXML
    private TextField tfnom;
    private ObservableList<SubCategory> subcategories;
  
    @FXML
    private TableColumn<SubCategory, Integer> IDColumn;
    @FXML
    private TableColumn<SubCategory, String> SubnomColumn;
   @FXML
    private TableColumn<SubCategory, Category> catgColumn;
      @FXML
    private TableView<SubCategory> subcategoryTable;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<Category> cbCatg;

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               ServiceSubcategory ss = new ServiceSubcategory();
 
        try {
            subcategories = FXCollections.observableArrayList(ss.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      //  IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        SubnomColumn.setCellValueFactory(new PropertyValueFactory<>("nomSubCategory"));
        //catgColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subcategoryTable.setItems(subcategories);
        subcategoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tfnom.setText(newSelection.getNomSubCategory());
            } else {
                tfnom.setText("");
            }
        });
    }    

    @FXML
    private void AddSubCategory(ActionEvent event) throws SQLException {
         String nomSubCategory = tfnom.getText();
         //Category category =cbCatg.getValue();
        SubCategory s = new SubCategory(/*category,*/nomSubCategory);
        ServiceSubcategory ss = new ServiceSubcategory();
        if (nomSubCategory.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Field");
            alert.setContentText("Please fill the fields !!");
            alert.showAndWait();
            return;
        }

      
        ss.ajouter(s);
        subcategoryTable.refresh();
        tfnom.setText("");
    //    cbCatg.set .setText("");
    }

    @FXML
    private void UpdateSubCategory(ActionEvent event) throws SQLException {
         String nomSubCategory = tfnom.getText();
         Category category =cbCatg.getValue();
        ServiceSubcategory ss = new ServiceSubcategory();

       SubCategory s = subcategoryTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a subcategory to update !");
            alert.showAndWait();
            return;
        }

        ///String nom = tfNom.getText().trim();
        if (nomSubCategory.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Field");
            alert.setContentText("Please enter a name !");
            alert.showAndWait();
            return;
        }

        s.setNomSubCategory(nomSubCategory);
        s.setCategory(category);
        ss.modifier(s);
        subcategoryTable.refresh();
        tfnom.setText("");
        //cbCatg.setText("");

    }

    @FXML
    private void DeleteSubCategory(ActionEvent event) {
     /*  ServiceSubcategory ss = new ServiceSubcategory();

        SubCategory s = subcategoryTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a category to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Category");
        alert.setContentText("Are you sure you want to delete the selected category?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

           // ss.deleteCategory(s);
            subcategories.remove(s);
            subcategoryTable.refresh();
            tfnom.setText("");

        }*/
    }


  @FXML
    private void subcategories(SortEvent<SubCategory> event) {
    }
    
}
