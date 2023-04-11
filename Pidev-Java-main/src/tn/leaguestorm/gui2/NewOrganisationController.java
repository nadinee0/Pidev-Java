/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static sun.security.krb5.KrbException.errorMessage;
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
    private DatePicker dati;
    @FXML
    private DatePicker date;
    @FXML
    private Button aaddorga;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
    private void addOrganism(ActionEvent event) {
        String nomc = Ncommercial.getText().trim();
        String nomj = Njuridique.getText().trim();
        LocalDate localDate = dati.getValue();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = (Date) Date.from(instant);
        String phoneStr = phone.getText();
        int ph = Integer.parseInt(phoneStr);
        String emai = email.getText().trim();
        String img = ImageOrg.getImage() != null ? ImageOrg.getImage().toString() : null;
        String morei = more.getText().trim();
         boolean isValid = false;
        
    
    // Validate nom commercial
    if (nomc.length() < 2 || nomc.length() > 50 || !nomc.matches("[a-zA-Z]+")) {
      
        // Show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Nom commercial doit contenir entre 2 et 50 lettres et pas de chiffres ou de caractères spéciaux.");
        alert.showAndWait();
    }
    
    // Validate nom juridique
    if (nomj.length() < 2 || nomj.length() > 50 || !nomj.matches("[a-zA-Z]+")) {
        isValid = false;
        // Show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Nom juridique doit contenir entre 2 et 50 lettres et pas de chiffres ou de caractères spéciaux.");
        alert.showAndWait();
    }
    
    // Validate phone number
    

if (!phoneStr.matches("[2|5|7|9][0-9]\\d{6}")) {
    isValid = false;
    // Show an error message
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("Numéro de 8 chiffres téléphone Oooredoo ou Telecom ou Orange ou FAX");
    alert.showAndWait();
} else {
    try {
        ph = Integer.parseInt(phoneStr);
    } catch (NumberFormatException e) {
        isValid = false;
        // Show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Numéro de téléphone doit être un nombre entier.");
        alert.showAndWait();
    }
}

    
    // Validate email
    if (!emai.matches("^(?=.{1,256}$)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        isValid = false;
        // Show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Adresse email doit être de la forme 'nom@domaine.com'.");
        alert.showAndWait();
    }
    // Validate date de fondation
        if (localDate == null || localDate.isAfter(LocalDate.now()) || localDate.isBefore(LocalDate.now().minusMonths(1))) {
           
            // Show an error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Date de fondation doit être avant la date d'aujourd'hui et d'au plus un mois.");
            alert.showAndWait();
        } else {
            Organism o = new Organism(nomc, nomj, date, ph, emai, img, morei);
            ServiceOrganism or = new ServiceOrganism();
            or.ajouter(o);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("good");
            alert.setHeaderText(null);
            alert.setContentText("L'organisme a été ajouté avec succès");
            alert.showAndWait();
        }
    

                // Validate more
            if (morei.length() < 10 || !morei.matches("[a-zA-Z]+")) {
                isValid = false;
                // Show an error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Description doit contenir au moins 10 lettres et pas de chiffres ou de caractères spéciaux.");
                alert.showAndWait();
            }

            // Validate image
            if (img.isEmpty()) {
                isValid = false;
                // Show an error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir une image.");
                alert.showAndWait();
            }

            if (isValid) {



            Organism o = new Organism(nomc, nomj, date, ph, emai, img, morei);

                ServiceOrganism or = new ServiceOrganism();
                or.ajouter(o);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("good");
                alert.setHeaderText(null);
                alert.setContentText("L'organisme a été ajouté avec succès");
                alert.showAndWait();
                // clear fields
                Ncommercial.clear();
                Njuridique.clear();
                dati.setValue(null);
                phone.clear();
                email.clear();
                ImageOrg.setImage(null);
                more.clear();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite.");
                alert.showAndWait();
            }}

    @FXML
    private void AddOrganism(ActionEvent event) {
    }
}

  

/*
/**

Validate the input fields.

@return true if the input is valid
/
private boolean validateInput() {
String errorMessage = "";

// validate nom commercial
String nomc = Ncommercial.getText().trim();
if (nomc == null || nomc.length() < 2 || nomc.length() > 50 || !nomc.matches("[a-zA-Z]+")) {
errorMessage += "Nom commercial invalide! (2-50 caractères, pas de chiffres ni de caractères spéciaux)\n";
}

// validate nom juridique
String nomj = Njuridique.getText().trim();
if (nomj == null || nomj.length() < 2 || nomj.length() > 50 || !nomj.matches("[a-zA-Z]+")) {
errorMessage += "Nom juridique invalide! (2-50 caractères, pas de chiffres ni de caractères spéciaux)\n";
}

// validate phone number
String phoneNum = phone.getText().trim();
if (phoneNum == null || !phoneNum.matches("[2|5|7|9][0-9]{7}")) {
errorMessage += "Numéro de téléphone invalide! (8 chiffres exactement, commence par 2, 5, 7 ou 9)\n";
}

// validate email
String emailStr = email.getText().trim();
if (emailStr == null || !emailStr.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}")) {
errorMessage += "Adresse e-mail invalide!\n";
}

// validate date
LocalDate localDate = dati.getValue();
if (localDate == null || localDate.isAfter(LocalDate.now()) || localDate.isBefore(LocalDate.now().minusMonths(1))) {
errorMessage += "Date de fondation invalide! (antérieure d'au moins un mois)\n";
}

// validate more
String moreStr = more.getText().trim();
if (moreStr == null || moreStr.length() < 10 || !moreStr.matches("[a-zA-Z]+")) {
errorMessage += "Plus d'informations invalide! (minimum 10 caractères, pas de chiffres ni de caractères spéciaux)\n";
}

// return true if there are no errors
if (errorMessage.length() == 0) {
return true;
} else {
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Erreur de saisie");
alert.setHeaderText(null);
alert.setContentText(errorMessage);
alert.showAndWait();
return false;*/







     
   

