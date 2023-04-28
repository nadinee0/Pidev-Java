package techstorm.tournaments.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

public class TournamentHomeController implements Initializable{

    @FXML
    private Button addButton;

    @FXML private GridPane tournamentGrid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load tournaments from data source
        TournamentCRUD tcd = new TournamentCRUD();
        List<Tournament> tournaments = tcd.getAllTournaments();

        // Populate the GridPane with the tournaments
        int rowIndex = 1; // start at row 1 to skip the header row
        for (Tournament tournament : tournaments) {
            Label nameLabel =   new Label(tournament.getName());            
            tournamentGrid.add(nameLabel, 0, rowIndex);
            
            Label tidLabel = new Label(String.valueOf(tournament.getTid()));                    
            tournamentGrid.add(tidLabel, 1, rowIndex);

            Label startDateLabel = new Label(tournament.getStartDate());
            tournamentGrid.add(startDateLabel, 2, rowIndex);

            Label participantsNumberLabel = new Label(String.valueOf(tournament.getParticipantsNumber()));
            tournamentGrid.add(participantsNumberLabel, 3, rowIndex);

            Label statusLabel = new Label(tournament.getStatus());
            tournamentGrid.add(statusLabel, 4, rowIndex);
            
            Label replayIdLabel = new Label(tournament.getReplayID());
            tournamentGrid.add(replayIdLabel, 5, rowIndex);

            rowIndex++;
        }
    }
    @FXML
    void handleAddButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTournament.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
