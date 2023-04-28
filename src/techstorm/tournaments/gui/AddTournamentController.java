package techstorm.tournaments.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

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
        addPNcb.setValue("16");
        addStatuscb.getItems().addAll("Upcoming", "In Progress", "Completed");
        addStatuscb.setValue("Upcoming");
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
        // validate name field
        String name = addNametf.getText();
        if (name.trim().isEmpty()) {
            showErrorDialog("Name field cannot be empty.");
            return;
        }

        // validate date field
        LocalDate date = addDatedp.getValue();
        if (date == null || date.isBefore(LocalDate.now())) {
            showErrorDialog("Invalid date. Date must be in the future.");
            return;
        }

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

    private void showErrorDialog(String message) {
        // create a new alert dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // get the owner window
        Window owner = addConfirmbtn.getScene().getWindow();

        // show the dialog
        alert.initOwner(owner);
        alert.showAndWait();
    }
}
