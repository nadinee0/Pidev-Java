/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class UpdateArticleController implements Initializable {

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
    private TextField tfTitle;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private ComboBox<?> cbCategory;
    @FXML
    private ComboBox<?> cbSubcategory;
    @FXML
    private Button btnUpdate;
    @FXML
    private ComboBox<?> cbType;
    @FXML
    private Button btnImport;
    @FXML
    private Label LabelImage;
    @FXML
    private ListView<?> articleList;
    @FXML
    private TextField tfImg;
    @FXML
    private VBox pnItems;

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
    private void category(ActionEvent event) {
    }

    @FXML
    private void subcategory(ActionEvent event) {
    }

    @FXML
    private void article(ActionEvent event) {
    }

    @FXML
    private void updateArticle(ActionEvent event) {
    }

    @FXML
    private void Import(ActionEvent event) {
    }
    
}
