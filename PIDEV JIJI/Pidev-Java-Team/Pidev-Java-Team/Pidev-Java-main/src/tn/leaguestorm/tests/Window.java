/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class Window extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
           Parent root = FXMLLoader.load(getClass().getResource("/tn/leaguestorm/gui2/Orca.fxml"));

            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Organisme");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}