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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.leaguestorm.entities.Team;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CardteamController implements Initializable {

    @FXML
    private TextField tfTeam;
    @FXML
    private TextField tfRate;
    @FXML
    private TextArea tfDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void afficherCard(Team card) {
    tfTeam.setText(card.getNom_team());
    tfDescription.setText(card.getDescription_team());
    tfRate.setText( Float.toString(card.getRate_team()));

   
}
}
