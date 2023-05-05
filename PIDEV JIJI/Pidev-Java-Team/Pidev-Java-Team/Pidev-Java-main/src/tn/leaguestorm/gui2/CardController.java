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
import javax.smartcardio.Card;
import tn.leaguestorm.entities.Organism;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CardController implements Initializable {

    private TextField tfTITRE;
    private TextArea tfDESC;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfTeam;
    @FXML
    private TextField tfRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
}
    public void afficherCard(Organism card) {
    tfTITRE.setText(card.getNom_commercial());
    tfDESC.setText(card.getNom_juridique() + "\n\n" + card.getMore());
    
}


    }    
    

