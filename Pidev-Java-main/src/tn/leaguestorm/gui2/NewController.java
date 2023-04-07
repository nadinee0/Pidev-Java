/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import tn.leaguestorm.entities.Team;
import tn.leaguestorm.services.ServiceTeam;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class NewController implements Initializable {

    @FXML
    private TextField Nom;  
    @FXML
    private TextArea Description;
    @FXML
    private ChoiceBox<String> Wins;
    @FXML
    private ChoiceBox<String> Losses;
    @FXML
    private TextField Rate;
    @FXML
    private ImageView Logo;
    @FXML
    private ColorPicker colorPicker;
    @FXML  
    private Button Add;
    @FXML
    private ImageView logo;
    @FXML
    private Label nom;
    @FXML
    private ColorPicker color;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveTeam(ActionEvent event) {
        String nom = Nom.getText();
        String description = Description.getText();
        int wins = Integer.parseInt(Wins.getValue());
        int losses = Integer.parseInt(Losses.getValue());
        float rate = Float.parseFloat(Rate.getText());
        String color = colorPicker.getValue().toString();
        String logo = Logo.getImage().toString();
        Team t = new Team(nom, description, wins, losses, rate, color, logo);
        ServiceTeam ta = new ServiceTeam();
        ta.ajouter(t);
        
        
         FXMLLoader loader =
                 new FXMLLoader(getClass().getResource("DetailsTeam.fxml"));
        try {
            Parent root= loader.load();
             DetailsTeamController dtc = loader.getController();
             dtc.setNom(""+ t.getNom_team());
             dtc.setDescription(""+ t.getDescription_team());
             dtc.setWins(""+ t.getWins_team());
             dtc.setLosses(""+ t.getLosses_team());
             dtc.setRate(""+ t.getRate_team() );
             dtc.setLogo(new Image(t.getLogo_team()));


             
            
             
            Nom.getScene().setRoot(root);
             
        } catch (IOException ex) {
            System.out.println("Error:" +ex.getMessage() );
        }
    }
}

