/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.leaguestorm.entities.Organism;

import tn.leaguestorm.services.ServiceOrganism;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class NewOrganisationController implements Initializable {


    @FXML
    private TextField Ncommercial;
    @FXML
    private TextField Njuridique;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private ImageView ImageOrg;
    @FXML
    private TextArea more;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
private void Add(ActionEvent event) {
    String nomc = Ncommercial.getText();
    String nomj = Njuridique.getText(); 
    int ph = Integer.parseInt(phone.getText()); 
    String emai = email.getText();
    String img = ImageOrg.getImage().toString();
    String Morei = more.getText();
    Organism o = new Organism(nomc, nomj, ph, emai, img, Morei);
    ServiceOrganism or = new ServiceOrganism();
    or.ajouter(o);


        
FXMLLoader loader =
    new FXMLLoader(getClass().getResource("DetailsOrganisation.fxml"));
        try {
            Parent root= loader.load();
                 DetailsOrganisationController doc = loader.getController();
                doc.setNomcc(""+ o.getNom_commercial());
                doc.setNomjj(""+ o.getNom_juridique());
                doc.setTel(""+ o.getPhone_organisation());
                doc.setGm(""+ o.getEmail_organisation());
                doc.setPic(""+ o.getImage());
                Ncommercial.getScene().setRoot(root);
             
        } catch (IOException ex) {
            System.out.println("Error:" +ex.getMessage() );
        }}}        
   

