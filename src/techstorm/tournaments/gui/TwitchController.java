/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author qiyanu
 */
public class TwitchController implements Initializable {

    @FXML
    private WebView webView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.youtube.com/embed/pgz-qd9LFjo?autoplay=1&fs=1");
    }
}

