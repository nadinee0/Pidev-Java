/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.leaguestorm.entities.Organism;
import tn.leaguestorm.entities.Team;
import tn.leaguestorm.services.ServiceOrganism;
import tn.leaguestorm.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class OrcaController implements Initializable {

    @FXML
    private TextField Ncommercial;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField Njuridique;
    @FXML
    private TextArea more;
    @FXML
    private DatePicker dati;
    @FXML
    private TextField ImageOrg;
    @FXML
    private Button aaddorga;
    @FXML
    private Button clear;
    @FXML
    private Button supp;
    @FXML
    private Button Update;
    @FXML
    private ListView<?> listview_crud;
    private Button Add;

    private PreparedStatement ps;
    private Statement st;
    private ResultSet resultat;
    private Alert altert;

    @FXML
    private ListView<Organism> organismListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceOrganism so = new ServiceOrganism();
        List<Organism> organisms = null;

        organisms = so.getAll();
        organismListView.setItems(FXCollections.observableList(organisms));

        // Set the cell factory for the ListView to display the organism's name
        organismListView.setCellFactory(param -> new ListCell<Organism>() {
            @Override
            protected void updateItem(Organism organism, boolean empty) {
                super.updateItem(organism, empty);

                if (empty || organism == null || organism.getNom_commercial() == null) {
                    setText(null);
                } else {
                    setText(organism.getNom_commercial());
                }
            }
        });

        // Add a listener to the ListView to update the labels with the selected organism's properties
        organismListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // set text of data_nom label to the selected organism's name
                Ncommercial.setText(newSelection.getNom_commercial());
                Njuridique.setText(newSelection.getNom_juridique());
                phone.setText(String.valueOf(newSelection.getPhone_organisation()));
                email.setText(newSelection.getEmail_organisation());
                ImageOrg.setText(newSelection.getImage());
                more.setText(newSelection.getMore());
            } else {
                // clear text of all labels if no organism is selected
                Ncommercial.setText("");
                Njuridique.setText("");
                phone.setText("");
                email.setText("");
                ImageOrg.setText("");
                more.setText("");
            }
        });
    }

    @FXML
    private void ajouter(ActionEvent event) {

        String nomc = Ncommercial.getText().trim();
        String nomj = Njuridique.getText().trim();
        LocalDate localDate = dati.getValue();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = (Date) Date.from(instant);
        String phoneStr = phone.getText();

        int ph = 0;
        String emai = email.getText().trim();
        String img = ImageOrg.getText();
        String morei = more.getText().trim();

        try {
            if (nomc.isEmpty()) {
                throw new Exception("Le nom commercial ne doit pas être vide.");
            } else if (nomc.length() < 2 || nomc.length() > 50 || !nomc.matches("[a-zA-Z]+")) {
                throw new Exception("Nom commercial doit contenir entre 2 et 50 lettres et pas de chiffres ou de caractères spéciaux.");
            }

            if (nomj.isEmpty()) {
                throw new Exception("Le nom juridique ne doit pas être vide.");
            } else if (nomj.length() < 2 || nomj.length() > 50 || !nomj.matches("[a-zA-Z]+")) {
                throw new Exception("Nom juridique doit contenir entre 2 et 50 lettres et pas de chiffres ou de caractères spéciaux.");
            }

            if (!phoneStr.matches("[2|5|7|9][0-9]\\d{6}")) {
                throw new Exception("Numéro de 8 chiffres téléphone Oooredoo ou Telecom ou Orange ou FAX.");
            } else {
                ph = Integer.parseInt(phoneStr);
            }

            if (emai.isEmpty()) {
                throw new Exception("L'adresse email ne doit pas être vide.");
            }
            if (!emai.matches("^(?=.{1,256}$)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new Exception("Adresse email doit être de la forme 'nom@domaine.com'.");
            }

            if (img.isEmpty()) {
                throw new Exception("Veuillez choisir une image.");
            }

            if (morei.isEmpty()) {
                throw new Exception("La description ne doit pas être vide.");
            }

            Organism o = new Organism(nomc, nomj, date, ph, emai, img, morei);
            ServiceOrganism or = new ServiceOrganism();
            or.ajouter(o);

            int rowsAffected;
            // prepare the SQL statement to insert the new organism
            try ( // create a connection to the database
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techstorm_3a39", "root", "")) {
                // prepare the SQL statement to insert the new organism
                String req = "INSERT INTO `Organisme` (`nom_commercial`, `nom_juridique`,`date_de_fondation`, `phone_organization`, `email_organization`, `image`, `more`) VALUES (?, ?, ?, ?, ?, ?,?)";
                try (PreparedStatement statement = connection.prepareStatement(req)) {
                    statement.setString(1, nomc);
                    statement.setString(2, nomj);
                    statement.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
                    statement.setInt(4, ph);
                    statement.setString(5, emai);
                    statement.setString(6, img);
                    statement.setString(7, morei);
                    // execute the SQL statement
                    rowsAffected = statement.executeUpdate();
                    // close the statement and the connection
                }
            }

            if (rowsAffected == 1) {
                Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
                successAlert.setTitle("good");
                successAlert.setContentText("L'organisme a été ajouté avec succès");
                successAlert.showAndWait();
            } else {
                throw new Exception("Failed to add the organism to the database.");
            }
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Database Error");
            errorAlert.setContentText("An error occurred while connecting to the database: " + e.getMessage());
            errorAlert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("An error occurred while adding the organism: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void nettoyer(ActionEvent event) {
        Ncommercial.setText("");
        Njuridique.setText("");
        dati.setValue(null);
        phone.setText("");
        email.setText("");
        ImageOrg.setText("");
        more.setText("");
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

}
