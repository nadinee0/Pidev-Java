/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class BackController implements Initializable {

    @FXML
    private Button btnOverview;
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
    private VBox pnItems;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnSubCategory;
    @FXML
    private Button btnArticle;

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
    
}
