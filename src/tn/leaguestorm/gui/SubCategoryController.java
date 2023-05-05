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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceSubcategory;
import tn.leaguestorm.utils.MyConnection;

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
    private VBox pnItems;

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
                cbCatg.setValue(newSelection.getCategory().getNom());

            } else {
                tfnom.setText("");
                cbCatg.setValue(null); // Reset the selected category in the combo box
            }
        });
    }
    private MyConnection ds = MyConnection.getInstance();

    @FXML
    private void AddSubCategory(ActionEvent event) throws SQLException {
        ServiceSubcategory ss = new ServiceSubcategory();

        String nomSubCategory = tfnom.getText();
        String category = cbCatg.getValue();
        int categoryId = ss.getCategoryIDByName(category);

        SubCategory s = new SubCategory(categoryId, nomSubCategory);
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
        String req = "SELECT id FROM `sub_category` WHERE nom_sub_category=?";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, s.getNomSubCategory());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // The subcategory name already exists
            System.out.println("This SubCategory Already Exists ! ");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Exsiting SubCategory ");
            alert.setContentText(s.getNomSubCategory() + " SubCategory Already Exists !!");
            alert.showAndWait();
            return;
        } else {
            String req1 = "INSERT INTO `sub_category` (`nom_sub_category`,`category_id`) VALUES (?,?)";
            PreparedStatement st1 = ds.getCnx().prepareStatement(req1);
            st1.setString(1, s.getNomSubCategory());
            st1.setInt(2, categoryId);
            st1.executeUpdate();
            // ss.ajouter2(s);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("SubCategory Successfully Added !");
            alert.showAndWait();
            subcategoryTable.refresh();
            tfnom.setText("");
            cbCatg.setValue(null); // Reset the selected category in the combo box

        }
    }

    @FXML
    private void UpdateSubCategory(ActionEvent event) throws SQLException {

        ServiceSubcategory ss = new ServiceSubcategory();

        SubCategory subCategory = subcategoryTable.getSelectionModel().getSelectedItem();
        if (subCategory == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a subcategory in the table.");
            alert.showAndWait();
            return;
        }

        String newSubCategoryName = tfnom.getText();
        String newCategory = cbCatg.getValue();
        int categoryId = ss.getCategoryIDByName(newCategory);

        try {
            if (newSubCategoryName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setContentText("Please fill the fields !!");
                alert.showAndWait();
                return;
            }

            if (newSubCategoryName.length() < 3 || newSubCategoryName.length() > 15) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(" Invalid Length");
                alert.setContentText("SubCategory Name must be between 3 and 15 !");
                alert.showAndWait();
                return;
            }

            // Vérifier si le nom de catégorie commence par une majuscule
            if (!newSubCategoryName.matches("^[A-Z].*")) {
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

        ss.updateSubCategory(subCategory.getId(), newSubCategoryName, newCategory);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("SUCCESS");
                alert.setContentText("SubCategory Successfully Updated !");
                alert.showAndWait();
        subcategoryTable.refresh();
        tfnom.setText("");
        cbCatg.setValue(null);
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
        alert.setContentText("Are you sure you want to delete the selected subcategory :" + s.getNomSubCategory() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            ss.deleteSubCategory(s);
              //  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("SUCCESS");
                alert.setContentText("SubCategory Successfully Deleted !");
                alert.showAndWait();
            //     subcategories.remove(s);
            subcategoryTable.refresh();
            tfnom.setText("");
            cbCatg.setValue(null);

        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        /*  ServiceSubcategory ss = new  ServiceSubcategory();
        List<SubCategory> subcategories = ss.getAll();
        System.out.println("All subcategories:");
        for (SubCategory s : subcategories) {
            System.out.println(s.getNomSubCategory() + " - " + s.getCategory().getNom());
        }*/
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
    private void subcategories(SortEvent<SubCategory> event) {
    }

}
