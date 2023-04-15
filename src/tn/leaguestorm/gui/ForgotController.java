/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.leaguestorm.utils.FXMLUtils;

/**
 * FXML Controller class
 *
 * @author Bellalouna Iheb
 */
public class ForgotController implements Initializable {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private ImageView exitImg;
    @FXML
    private Button btnConfirm;
    @FXML
    private Hyperlink lnkCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
