/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
    private ChoiceBox<Integer> Wins;
    @FXML
    private ChoiceBox<Integer> Losses;
    @FXML
    private TextField Rate;
    private ImageView Logo;
    private ColorPicker colorPicker;
    @FXML  
    private Button Add;
    @FXML
    private ImageView logo;
    @FXML
    private Label nom;
    @FXML
    private ColorPicker color;
    @FXML
    private DatePicker dato;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Wins.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Losses.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @FXML
    private void saveTeam(ActionEvent event) {
        String nom = Nom.getText().trim();
        String description = Description.getText().trim();
        int wins = Wins.getValue();
        int losses = Losses.getValue();
        String color = colorPicker.getValue().toString().trim();
        String logo = Logo.getImage().toString();
        Date date = java.sql.Date.valueOf(dato.getValue());

        float rate;
        
        try {
            rate = Float.parseFloat(Rate.getText().trim());
            if (rate <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("Please enter a positive floating number for Rate.");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter a valid floating number for Rate.");
            alert.showAndWait();
            return;
        }

        
            if (nom.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText("Please enter a name for the team.");
        alert.showAndWait();
        return;
        }

        if (description.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText("Please enter a description for the team.");
        alert.showAndWait();
        return;
        }

        /*if (wins == 0 && losses == 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText("Please enter at least one win or one loss for the team.");
        alert.showAndWait();
        return;
        }*/

        if (color.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText("Please select a color for the team.");
        alert.showAndWait();
        return;
        }

        if (logo.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText("Please select a logo for the team.");
        alert.showAndWait();
        return;
        }

        // Check name length
            if (nom.length() < 2 || nom.length() > 32) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Name length should be between 2 and 32 characters.");
            alert.showAndWait();
            return;
        }

        // Check description length
        if (description.length() < 10 || description.length() > 1000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Description length should be between 10 and 1000 characters.");
            alert.showAndWait();
            return;
        }

        // Check wins, losses and rate are positive
        if (wins < 0 || losses < 0 || rate < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Wins, losses and rate should be positive.");
            alert.showAndWait();
            return;
        }
        //Check creation date
            LocalDate creationDate = dato.getValue();
        if (creationDate.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Creation date should be before current date.");
            alert.showAndWait();
            return;
        }



           Team t = new Team(nom, description, wins, losses, rate, color, logo, date); 
            
            ServiceTeam ta = new ServiceTeam();
            ta.ajouter2(t);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Team Added");
            alert.setContentText("Team " + nom + " has been added successfully.");
            alert.showAndWait();

            // clear input fields
            Nom.setText("");
            Description.setText("");
            Wins.setValue(0);
            Losses.setValue(0);
            Rate.setText("");
            colorPicker.setValue(Color.WHITE);
            Logo.setImage(null);
            }



        
        /*
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
        }*/
    }


