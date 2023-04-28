/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tn.leaguestorm.entities.Team;
import tn.leaguestorm.services.ServiceTeam;
import tn.leaguestorm.utils.MyConnection;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tn.leaguestorm.services.Pdf2;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.MediaPlayer;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.MediaPlayer;

import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TewtController implements Initializable {

    @FXML
    private Label data_nom;
    @FXML
    private Label r_nom;
    @FXML
    private Label lom;
    @FXML
    private Label l_nom;
    @FXML
    private Label w_nom;
    @FXML
    private Label co_nom;
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
    @FXML
    private DatePicker dato;
    @FXML
    private TextField Logo;
    @FXML
    private ColorPicker color;
    @FXML
    private Button Add;
    @FXML
    private Label lo_nom;
    @FXML
    private Button Clear;
    @FXML
    private Button Delete;
    @FXML
    private Button Update;
    @FXML
    private ListView<Team> tableview_crud;

    private PreparedStatement ps;
    private Statement st;
    private ResultSet resultat;
    private Alert altert;
    Pdf2 oo = new Pdf2();
    @FXML
    private Label Des;
    @FXML
    private Pagination pagination;

     



    public class TeamListCell extends ListCell<Team> {

        @Override
        protected void updateItem(Team team, boolean empty) {
            super.updateItem(team, empty);

            if (empty || team == null) {
                setText(null);
            } else {
                setText(team.getNom_team());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Wins.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Losses.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Set the cell factory for the ListView
        tableview_crud.setCellFactory(param -> new TeamListCell());

        Add.setOnAction(event -> {
            try {
                ajouter(event);
            } catch (Exception ex) {
                Logger.getLogger(TewtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Clear.setOnAction(event -> nettoyer(event));
        Delete.setOnAction(event -> supprimer(event));
        Update.setOnAction(event -> modifier(event));

        ServiceTeam st = new ServiceTeam();
        List<Team> teams = null;

        try {
            teams = st.getAll();
            tableview_crud.setItems(FXCollections.observableList(teams));
        } catch (SQLException ex) {
            Logger.getLogger(TewtController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableview_crud.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // set text of data_nom label to the selected team's name
                Nom.setText(newSelection.getNom_team());
                Description.setText(newSelection.getDescription_team());
                lom.setText(String.valueOf(newSelection.getWins_team()));
                l_nom.setText(String.valueOf(newSelection.getLosses_team()));
                r_nom.setText(String.valueOf(newSelection.getRate_team()));
                Logo.setText(String.valueOf(newSelection.getLogo_team()));
                co_nom.setText(String.valueOf(newSelection.getColor()));
            } else {
                // clear text of all labels if no team is selected
                Nom.setText(null);
                Description.setText(null);
                Wins.setValue(null);
                Losses.setValue(null);
                Rate.setText(null);
                co_nom.setText(null);
                Des.setText(null);
            }
        });

    }

    @FXML
    private void ajouter(ActionEvent event) throws Exception {

        String nom = Nom.getText().trim();
        String description = Description.getText().trim();
        int wins = Wins.getValue();
        int losses = Losses.getValue();
        float rate = Float.parseFloat(Rate.getText().trim());
        String colorValue = color.getValue().toString().trim();
        String logo = Logo.getText();
        Date date = java.sql.Date.valueOf(dato.getValue());

        try {
            // Validate input fields
            if (nom.isEmpty()) {
                throw new IllegalArgumentException("Please enter a name for the team.");
            }

            if (description.isEmpty()) {
                throw new IllegalArgumentException("Please enter a description for the team.");
            }

            if (color.getValue() == null) {
                throw new Exception("Please select a color for the team.");
            }

            if (logo.isEmpty()) {
                throw new IllegalArgumentException("Please select a logo for the team.");
            }

            if (nom.length() < 2 || nom.length() > 32) {
                throw new IllegalArgumentException("Name length should be between 2 and 32 characters.");
            }

            if (description.length() < 10 || description.length() > 1000) {
                throw new IllegalArgumentException("Description length should be between 10 and 1000 characters.");
            }

            if (wins < 0 || losses < 0 || rate < 0 || rate > 1.0f) {
                throw new IllegalArgumentException("Wins, losses and rate should be positive and rate should be between 0 and 1.");
            }

            LocalDate creationDate = dato.getValue();
            if (creationDate == null) {
                throw new NullPointerException("Please select a creation date.");
            }

            if (creationDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Creation date should be before current date.");
            }

            if (logo == null) {
           
                throw new IllegalArgumentException("Please select a logo for the team.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }

        Team t = new Team(nom, description, wins, losses, rate, colorValue, logo, date);
        ServiceTeam st = new ServiceTeam();
        st.ajouter2(t);

        int rowsAffected = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techstorm_3a39", "root", "")) {
            String req = "INSERT INTO `team` (`nom_team`, `Organisme_id`, `description_team`, `wins_team`, `losses_team`, `rate_team`, `color`, `logo_team`, `date_de_creation_team`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setString(1, t.getNom_team());
            statement.setInt(2, 1);
            statement.setString(3, t.getDescription_team());
            statement.setInt(4, t.getWins_team());
            statement.setInt(5, t.getLosses_team());
            statement.setFloat(6, t.getRate_team());
            statement.setString(7, t.getColor());
            statement.setString(8, t.getLogo_team());
            statement.setDate(9, new java.sql.Date(t.getDate_de_creation_team().getTime()));

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Database Error");
            errorAlert.setContentText("An error occurred while connecting to the database: " + e.getMessage());
            errorAlert.showAndWait();
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("An error occurred while adding the team: " + e.getMessage());
            errorAlert.showAndWait();
        }

        if (rowsAffected == 1) {
            Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
            successAlert.setTitle("Success");
            successAlert.setContentText("The team has been added successfully.");
            successAlert.showAndWait();
        } else {
            throw new Exception("Failed to add the team to the database.");
        }
    }

    @FXML
    private void nettoyer(ActionEvent event) {
        try {
            // Clear the input fields
            Nom.clear();
            Description.clear();
            Wins.getSelectionModel().select(0);
            Losses.getSelectionModel().select(0);
            Rate.clear();
            color.setValue(Color.WHITE);
            Logo.clear();
            dato.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("An error occurred while trying to clear the input fields.");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Team selectedTeam = tableview_crud.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une équipe à supprimer.");
            alert.showAndWait();
        } else {
            ServiceTeam st = new ServiceTeam();
            st.supprimer(selectedTeam.getId());
            tableview_crud.getItems().remove(selectedTeam);
            nettoyer(event);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        Team selectedTeam = tableview_crud.getSelectionModel().getSelectedItem();
        if (selectedTeam == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une équipe à modifier.");
            alert.showAndWait();
        } else {
            try {
                String nom = Nom.getText();
                String description = Description.getText();
                int wins = Wins.getValue();
                int losses = Losses.getValue();
                double rate = Double.parseDouble(Rate.getText());
                LocalDate date = dato.getValue();
                String logo = Logo.getText();
                String color = this.color.getValue().toString();

                if (nom.isEmpty() || description.isEmpty() || logo.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Avertissement");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir tous les champs.");
                    alert.showAndWait();
                } else {
                    ServiceTeam st = new ServiceTeam();
                    Team team = new Team(nom, description, wins, losses, rate, date, logo, color);
                    st.modifier(team);
                    updateTableView(selectedTeam, team);
                    nettoyer(event);
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir une valeur numérique pour le taux de victoire.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void pdfabonn(ActionEvent event) {

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choix de l'action");
            alert.setHeaderText("Que voulez-vous faire ?");
            alert.setContentText("Voulez-vous passer à l'application ou choisir une musique ?");

            ButtonType buttonTypeApp = new ButtonType("Aller à l'application");
            ButtonType buttonTypeMusic = new ButtonType("Choisir une musique");

            alert.getButtonTypes().setAll(buttonTypeApp, buttonTypeMusic);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeApp) {
                // Faire quelque chose pour passer à l'application
            } else if (result.get() == buttonTypeMusic) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Sélectionner un fichier audio");
                File defaultDirectory = new File("C:\\Users\\Dell\\Desktop\\MUSIC");
                fileChooser.setInitialDirectory(defaultDirectory);
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers audio (*.mp3, *.wav, *.flac)", "*.mp3", "*.wav", "*.flac");
                fileChooser.getExtensionFilters().add(extFilter);
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    javafx.scene.media.Media javafxMedia = new javafx.scene.media.Media(selectedFile.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(javafxMedia);
                    mediaPlayer.setAutoPlay(true);
                } else {
                    System.out.println("Aucun fichier audio sélectionné.");
                }
            }
            String filename = "C:\\xampp\\htdocs\\fichierExcelJava\\dataabonn.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("nom Abonnement");
            rowhead.createCell((short) 1).setCellValue("type d'abonnement");
            rowhead.createCell((short) 2).setCellValue("description ");
            ServiceTeam st = new ServiceTeam();

            List<Team> teams = st.getAll();
            for (int i = 0; i < teams.size(); i++) {
                HSSFRow row = sheet.createRow((short) i);
                row.createCell((short) 0).setCellValue(teams.get(i).getNom_team());
                row.createCell((short) 1).setCellValue(teams.get(i).getDescription_team());
                row.createCell((short) 2).setCellValue(teams.get(i).getColor());
//row.createCell((short) 3).setCellValue((abonnements.get(i).getDate()));
                i++;
            }
            int i = 1;
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            File file = new File(filename);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void updateTableView(Team oldTeam, Team newTeam) {
        int index = tableview_crud.getItems().indexOf(oldTeam);
        tableview_crud.getItems().set(index, newTeam);
    }

}
