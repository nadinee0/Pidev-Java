/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.utils;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.gui.HomeController;

/**
 *
 * @author Bellalouna Iheb
 */
public class FXMLUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLUtils.class.getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    

//    public static void changeScene(ActionEvent event, String fxmlFile, String title, User user) {
//        Parent root = null;
//        if (user != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(MyConnection.class.getResource(fxmlFile));
//                root = loader.load();
//                HomeController hc = loader.getController();
//                hc.initData(user);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                root = FXMLLoader.load(MyConnection.class.getResource(fxmlFile));
//                System.out.println(fxmlFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setTitle(title);
//        stage.setScene(new Scene(root));
//    }
}

