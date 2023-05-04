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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class HomeShopController implements Initializable {

    @FXML
    private Button btnshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void shop(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Shop.fxml"));
            Parent root = loader.load(); // load the new FXML file
            Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
            Node sourceNode = (Node) event.getSource(); // get the source node of the current event
            Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
            Stage stage = (Stage) currentScene.getWindow(); // get the current stage
            stage.setScene(scene); // set the new scene as the content of the stage
    }
    
}
