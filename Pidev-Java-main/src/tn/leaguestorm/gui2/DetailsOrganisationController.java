/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.leaguestorm.entities.Organism;
import tn.leaguestorm.services.ServiceOrganism;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class DetailsOrganisationController implements Initializable {

    @FXML
    private Label nomcc;
    @FXML
    private Label tel;
    @FXML
    private Label nomjj;
    @FXML
    private Label gm;
    @FXML
    private ImageView pic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
        // TODO
    
public void setNomcc(String nomcc) {
    this.nomcc.setText(nomcc);
}

public void setTel(String tel) {
    this.tel.setText(tel);
}

public void setNomjj(String nomjj) {
    this.nomjj.setText(nomjj);
}

public void setGm(String gm) {
    this.gm.setText(gm);
}

public void setPic(String pic) {
    this.pic.setImage(new Image(pic));}


    
}
