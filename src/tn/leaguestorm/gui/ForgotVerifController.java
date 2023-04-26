/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

/**
 *
 * @author Bellalouna Iheb
 */
public class ForgotVerifController implements Initializable{

    @FXML
    private TextField tfCode;
    @FXML
    private Button btnConfirm;
    @FXML
    private Hyperlink lnkCancel;
    @FXML
    private ImageView exitImg;
    
    private Alert alert;
    
    String phone = CurrentUser.phoneProcedures;
    UserService us = new UserService();

    @FXML
    private void handleConfirmAction(ActionEvent event) throws SQLException, IOException {
        
        String code = tfCode.getText();
        if (!us.isCode(phone,code)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect code!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Correct code!");
            alert.showAndWait();
            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ForgotConfirm.fxml", "New Password");
        }
            
    }

    @FXML
    private void handleCancelLinkAction(ActionEvent event) {
        try {
            FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in");
        } catch (IOException ex) {
            Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void handleExitImgAction(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
    }
    
}
