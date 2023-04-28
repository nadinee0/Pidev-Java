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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Pagination;
import tn.leaguestorm.services.Pdf2;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class OrcaController implements Initializable {

    @FXML
    private Pagination pagination;

    private final int ITEMS_PER_PAGE = 1; // nombre d'organismes à afficher par page
    private ObservableList<Organism> observableOrganisms;
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
    private Button Add;

    private PreparedStatement ps;
    private Statement st;
    private ResultSet resultat;
    private Alert altert;
    Pdf2 oo = new Pdf2();

    @FXML
    private TextField searchField;

    @FXML
    private ListView<Organism> organismListView;
    @FXML
    private Button pd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceOrganism so = new ServiceOrganism();
        List<Organism> organisms = so.getAll();
        ObservableList<Organism> observableOrganisms = FXCollections.observableArrayList(organisms);

       // Create a filtered list to hold the filtered organisms
FilteredList<Organism> filteredOrganisms = new FilteredList<>(observableOrganisms, p -> true);

// Set the filter predicate whenever the search field's text changes
searchField.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredOrganisms.setPredicate(organism -> {
        if (newValue == null || newValue.isEmpty()) {
            // If the search field is empty, show all organisms
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        // Check if the organism's commercial name contains the search criteria
        return organism.getNom_commercial().toLowerCase().contains(lowerCaseFilter);
    });
});

// Create a sorted list to hold the sorted organisms
SortedList<Organism> sortedOrganisms = new SortedList<>(filteredOrganisms);

// Set the sorted and filtered organisms as the list view items
organismListView.setItems(sortedOrganisms);

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

    
    // Create a Comparator to sort organisms by commercial name
    Comparator<Organism> organismComparator = Comparator.comparing(Organism::getNom_commercial);

    // Sort the list of organisms by commercial name
    ObservableList<Organism> observableOrganism = FXCollections.observableArrayList(organisms);
    observableOrganism = observableOrganism.stream()
                                             .sorted(organismComparator)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList));

    // Set the sorted organisms as the list view items
    organismListView.setItems(observableOrganism);

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
    pd.setOnAction(event -> {
    try {
        Pdf2 pdfGenerator = new Pdf2();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "report_" + timestamp + ".pdf";
        String filePath = "C:\\Users\\Dell\\Documents\\pdf\\" + fileName;
        String nomAbonn = "Crypto.com";
        String type = "ISI";
        String description = "Crypto.com fournit une plateforme permettant aux utilisateurs d'échanger des cryptomonnaies, de rassembler des NFT (Non-Fungible Token) et de visualiser les mesures d'échange telles que le volume et les mouvements de prix.";
        String date = "2023-04-28";
        pdfGenerator.generatePDF(filePath, nomAbonn, type, description, date);
    } catch (Exception e) {
        e.printStackTrace();
    }
});








        // Add a listener to the ListView to update the labels with the selected organism's properties
        organismListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // set text of data_nom label to the selected organism's name
                Ncommercial.setText(newSelection.getNom_commercial());

                // set text of other labels to the selected organism's properties
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

       /* // Create the pagination control
        Pagination pagination = new Pagination((observableOrganism.size() / 10 + 1), 0);
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * 10;
            int toIndex = Math.min(fromIndex + 10, observableOrganism.size());
            SortedList<Organism> currentOrganisms = new SortedList<>(FXCollections.observableArrayList(filteredOrganisms.subList(fromIndex, toIndex)));
            organismListView.setItems(currentOrganisms);
            return organismListView;
        });
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPrefWidth(observableOrganism.size() > 10 ? 360 : 0);
        pagination.setVisible(observableOrganism.size() > 10);

        // Add the pagination control to the UI
    */
}
    @FXML
    private void ajouter(ActionEvent event) throws SQLException, Exception {

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
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }

        Organism o = new Organism(nomc, nomj, date, ph, emai, img, morei);
        ServiceOrganism or = new ServiceOrganism();
        or.ajouter(o);

        int rowsAffected = 0;
        // prepare the SQL statement to insert the new organism
        try ( // create a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techstorm_3a39", "root", "")) {
            // prepare the SQL statement to insert the new organism
            String req = "INSERT INTO `Organisme` (`nom_commercial`, `nom_juridique`,`date_de_fondation`, `phone_organization`, `email_organization`, `image`, `more`) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(req)) {
                statement.setString(1, nomc);
                statement.setString(2, nomj);
                statement.setDate(3, date);
                statement.setInt(4, ph);
                statement.setString(5, emai);
                statement.setString(6, img);
                statement.setString(7, morei);
                // execute the SQL statement
                rowsAffected = statement.executeUpdate();
                // close the statement and the connection
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
        if (rowsAffected == 1) {
            Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
            successAlert.setTitle("good");
            successAlert.setContentText("L'organisme a été ajouté avec succès");
            successAlert.showAndWait();
        } else {
            throw new Exception("Failed to add the organism to the database.");
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
        Organism selectedOrganism = organismListView.getSelectionModel().getSelectedItem();
        if (selectedOrganism == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une équipe à supprimer.");
            alert.showAndWait();
        } else {
            ServiceOrganism serviceOrganism = new ServiceOrganism();
            serviceOrganism.supprimer(selectedOrganism.getId());
            organismListView.getItems().remove(selectedOrganism);
            Ncommercial.setText("");
            Njuridique.setText("");
            phone.setText("");
            email.setText("");
            ImageOrg.setText("");
            more.setText("");
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws Exception {
        String nomc = Ncommercial.getText().trim();
        String nomj = Njuridique.getText().trim();
        LocalDate localDate = dati.getValue();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.valueOf(localDate);
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
            or.modifier(o);

            int rowsAffected = 0;

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techstorm_3a39", "root", "")) {
                String req = "UPDATE `Organisme` SET `nom_commercial` = ?, `nom_juridique` = ?, `date_de_fondation` = ?, `phone_organization` = ?, `email_organization` = ?, `image` = ?, `more` = ? WHERE `id_organism` = ?";
                try (PreparedStatement statement = connection.prepareStatement(req)) {
                    statement.setString(1, o.getNom_commercial());
                    statement.setString(2, o.getNom_juridique());
                    statement.setDate(3, (Date) o.getDate_de_fondation());
                    statement.setInt(4, o.getPhone_organisation());
                    statement.setString(5, o.getEmail_organisation());
                    statement.setString(6, o.getImage());
                    statement.setString(7, o.getMore());

                    // execute the SQL statement
                    rowsAffected = statement.executeUpdate();
                    // close the statement and connection
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (rowsAffected == 1) {
                Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("The team has been added successfully.");
                successAlert.showAndWait();
            } else {
                System.out.println("Failed to update organism.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @FXML
    private void pdf(ActionEvent event) {
        try {
            Pdf2 pdfGenerator = new Pdf2();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String fileName = "report_" + timestamp + ".pdf";
            String filePath = "C:\\Users\\Dell\\Documents\\pdf" + fileName;
            String nomAbonn = "John Doe";
            String type = "Type";
            String description = "Description";
            String date = "2023-04-27";
            pdfGenerator.generatePDF(filePath, nomAbonn, type, description, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
