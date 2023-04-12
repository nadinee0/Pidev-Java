/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
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
    private TableColumn<SubCategory, String> SubnomColumn;
    @FXML
    private TableColumn<SubCategory, String> catgColumn;
    @FXML
    private TableView<SubCategory> subcategoryTable;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<String> cbCatg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSubcategory ss = new ServiceSubcategory();

        try {
            List<String> categoryNames = ss.getAllCategoryNames();
            cbCatg.getItems().addAll(categoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Show the combo box when it is clicked
        cbCatg.setOnMouseClicked(event -> {
            cbCatg.show();
        });
        /*     cbCatg.setOnAction(event -> {
                    try {
                        List<String> categoryNames = ss.getAllCategoryNames();
                        cbCatg.getItems().setAll(categoryNames);
                    } catch (SQLException ex) {
                        Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    });
         */
        SubnomColumn.setCellValueFactory(new PropertyValueFactory<>("nomSubCategory"));
        catgColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubCategory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubCategory, String> cellData) {
                String categoryName = cellData.getValue().getCategory().getNom();
                return new SimpleStringProperty(categoryName);
            }
        });

// Load the data into the table
        List<SubCategory> subCategories;
        try {
            subCategories = ss.getAll();
            subcategoryTable.getItems().setAll(subCategories);
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        ServiceSubcategory ss = new ServiceSubcategory();

        String nomSubCategory = tfnom.getText();
        String category = cbCatg.getValue();
       // int categoryId = ss.getCategoryIDByName(category); // implement this method to retrieve the ID of the category from the database based on its name

        SubCategory s = new SubCategory(category, nomSubCategory);
        try {
            if (nomSubCategory.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;
            }

            if (nomSubCategory.length() < 3 || nomSubCategory.length() > 15) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("SubCategory Name must be between 3 and 15 !");
                alert.showAndWait();
                return;
            }
            // Vérifier si le nom de catégorie commence par une majuscule
            if (!nomSubCategory.matches("^[A-Z].*")) {
                System.out.println("Le nom de catégorie est invalide !");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Name");
                alert.setContentText("SubCategory Name Must Start With UpperCase !!");
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

        ss.ajouter2(s);
        subcategoryTable.refresh();
        tfnom.setText("");
        cbCatg.setValue(null); // Reset the selected category in the combo box

    }

    @FXML
    private void UpdateSubCategory(ActionEvent event) throws SQLException {
        String nomSubCategory = tfnom.getText();
        String category = cbCatg.getValue();
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
        //  s.setCategory(category);
        ss.modifier(s);
        subcategoryTable.refresh();
        tfnom.setText("");
        cbCatg.setAccessibleText("");

    }

    @FXML
    private void DeleteSubCategory(ActionEvent event) throws SQLException {
        ServiceSubcategory ss = new ServiceSubcategory();

        SubCategory s = subcategoryTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a subcategory to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete SubCategory");
        alert.setContentText("Are you sure you want to delete the selected subcategory :"+s.getNomSubCategory()+" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            ss.deleteSubCategory(s);
       //     subcategories.remove(s);
            subcategoryTable.refresh();
            tfnom.setText("");

        }
    }

    @FXML
    private void subcategories(SortEvent<SubCategory> event) {
        /*  ServiceSubcategory ss = new  ServiceSubcategory();
        List<SubCategory> subcategories = ss.getAll();
        System.out.println("All subcategories:");
        for (SubCategory s : subcategories) {
            System.out.println(s.getNomSubCategory() + " - " + s.getCategory().getNom());
        }*/
    }

}
