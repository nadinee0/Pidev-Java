package techstorm.tournaments.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Window;

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
    private Button addSavebtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addPNcb.getItems().addAll("16", "32", "64");
        addStatuscb.getItems().addAll("Upcoming", "In Progress", "Completed");
    }

    @FXML
    private void handleAddCancel() {
        // Get the current scene and window
        Scene scene = addCancelbtn.getScene();
        Window window = scene.getWindow();

        // Close the window
        window.hide();
}


    @FXML
    private void handleAddSave() {
        // TODO: Handle save button action
    }
}
