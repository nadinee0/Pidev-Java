/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class DetailsTeamController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Rate;
    @FXML
    private ChoiceBox<String> Losses;
    @FXML
    private ChoiceBox<String> Wins;
   
    @FXML
    private ImageView Logo;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setNom(String message){
        this.nom.setText(message);
    }
    public void setDescription(String message){
        this.Description.setText(message);
    }
   
    public void setWins(String message){
        this.Wins.setValue(message);
    }
    public void setLosses(String message){
        this.Losses.setValue(message);
    }
    public void setRate(String message){
        this.Rate.setText(message);
    }
    public void setLogo(Image image){
        this.Logo.setImage(image);
    }
    
   
}






