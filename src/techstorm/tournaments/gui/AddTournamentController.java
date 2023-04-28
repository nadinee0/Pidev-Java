package techstorm.tournaments.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

public class AddTournamentController implements Initializable {

    @FXML
    private Label addTitle;

    @FXML
    private Label addNameLabel;

    @FXML
    private TextField addNametf;

    @FXML
    private DatePicker addDatedp;

    @FXML
    private Label addDateLabel;

    @FXML
    private Label addPNLabel;

    @FXML
    private Label addStatusLabel;

    @FXML
    private Label addReplayLabel;

    @FXML
    private TextField addReplaytf;

    @FXML
    private ComboBox<String> addPNcb;

    @FXML
    private ComboBox<String> addStatuscb;

    @FXML
    private Button addCancelbtn;

    @FXML
    private Button addConfirmbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addPNcb.getItems().addAll("16", "32", "64");
        addStatuscb.getItems().addAll("Upcoming", "In Progress", "Completed");
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        // get a reference to the button's stage
        Stage stage = (Stage) addCancelbtn.getScene().getWindow();
        // close the window
        stage.close();
    }


    @FXML
    private void handleConfirmAction(ActionEvent event) {
        // create a new tournament object with the data from the form
        String name = addNametf.getText();
        LocalDate date = addDatedp.getValue();
        int participantsNumber = Integer.parseInt(addPNcb.getValue());
        String status = addStatuscb.getValue();
        String replayID = addReplaytf.getText();
        Tournament newTournament = new Tournament(name, date.toString(), participantsNumber, status, replayID);
        TournamentCRUD tcd = new TournamentCRUD();
        tcd.addTournament(newTournament);
        // get a reference to the button's stage
        Stage stage = (Stage) addConfirmbtn.getScene().getWindow();
        // close the window
        stage.close();
    }
}
