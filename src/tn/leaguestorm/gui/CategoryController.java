/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import static java.awt.PageAttributes.MediaType.C;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.services.ServiceCategory;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class CategoryController implements Initializable {

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

    private ObservableList<Category> categories = FXCollections.observableArrayList();
    @FXML
    private ListView<Category> categoryList;

    /**
     * Initializes the controller class.
     */
    private MyConnection ds = MyConnection.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO 

            ServiceCategory sc = new ServiceCategory();

            /*   List<Category>  category  = sc.getAll();
            for(Category cat: category){
                categoryTable.getItems().add(cat.getNom()+"                     "+cat.getImg());
            }*/
            String req = "Select * from category";
            Statement st = ds.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getString(2), rs.getString(3));
                //   categories.add(c);
                //}
                categoryList.getItems().add(c);
            }
            // Personnalisation de la ListView pour afficher les catégories
            categoryList.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
                @Override
                public ListCell<Category> call(ListView<Category> listView) {
                    return new CategoryListCell();
                }
            });
            categoryList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Update the labels with the data from the selected Category object
                    tfNom.setText(newSelection.getNom());
                    tfImage.setText(newSelection.getImg());
                } else {
                    // Clear the labels if no row is selected
                    tfNom.setText("");
                    tfImage.setText("");
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveCategory(ActionEvent event) throws SQLException {
        String nom = tfNom.getText();
        String image = tfImage.getText();
        try {
            // code that might throw an exception
            if (nom.isEmpty() && image.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;

            }
// Vérifier si le nom de catégorie commence par une majuscule
            if (!nom.matches("^[A-Z].*")) {
                System.out.println("Le nom de catégorie est invalide !");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Name");
                alert.setContentText("Category Name Must Start With Majuscule !!");
                alert.showAndWait();
                return;
            }

            if (nom.length() < 2 || nom.length() > 10) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("Category Name must be between 2 and 10  !!");
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

        Category c = new Category(nom, image);
        ServiceCategory sc = new ServiceCategory();
        sc.ajouter2(c);
        categoryList.refresh();
        tfNom.setText("");
        tfImage.setText("");

    }

    @FXML
    private void UpdateCategory(ActionEvent event) throws SQLException {

        String nom = tfNom.getText();
        String image = tfImage.getText();
        ServiceCategory sc = new ServiceCategory();

        //    Category c = categoryList.getSelectionModel().getSelectedItem();
        ObservableList<Category> selectedItems = categoryList.getSelectionModel().getSelectedItems();
        /*
        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a category to update !");
            alert.showAndWait();
            return;
        }
/*
        ///String nom = tfNom.getText().trim();
        if (nom.isEmpty() && image.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Field");
            alert.setContentText("Please fill the fields !");
            alert.showAndWait();
            return;
        }

        c.setNom(nom);
        c.setImg(image);
        sc.modifier(c);
        categoryList.refresh();
        tfNom.setText("");
        tfImage.setText("");

         */
        for (Category item : selectedItems) {
            item.setNom(nom); // Update the name of the item
            item.setImg(image);  // Update the image of the item
            sc.update(item);

        }

// Refresh the ListView to reflect the changes
        categoryList.refresh();
    }

    @FXML
    private void DeleteCategory(ActionEvent event) throws SQLException {

        ServiceCategory sc = new ServiceCategory();

        // Get the selected category from the ListView
        Category selectedCategory = categoryList.getSelectionModel().getSelectedItem();
        Category c = new Category();
        if (selectedCategory == null) {
            // Show a warning message if no category is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a category to delete.");
            alert.showAndWait();
        } else {
            // Show a confirmation dialog to confirm the deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Category");
            alert.setContentText("Are you sure you want to delete the selected category :" + "  " + selectedCategory.getNom() + " " + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the selected category from the database
                sc.deleteCategory(selectedCategory);

                // Remove the selected category from the ListView
                categories.remove(selectedCategory);
                //  categories.clear();
                categoryList.refresh();

                // Clear the text fields
                tfNom.clear();
                tfImage.clear();
            }
        }
    }

    @FXML
    private void categories(ListView.EditEvent<Category> event) {
    }

}
