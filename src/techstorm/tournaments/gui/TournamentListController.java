package techstorm.tournaments.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

public class TournamentListController implements Initializable {
    @FXML private GridPane tournamentGridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load tournaments from data source
        TournamentCRUD tcd = new TournamentCRUD();
        List<Tournament> tournaments = tcd.getAllTournaments();

        // Populate the GridPane with the tournaments
        int rowIndex = 1; // start at row 1 to skip the header row
        for (Tournament tournament : tournaments) {
            Label nameLabel = new Label(tournament.getName());
            tournamentGridPane.add(nameLabel, 0, rowIndex);

            Label tidLabel = new Label("Tid: " + tournament.getTid());
            tournamentGridPane.add(tidLabel, 1, rowIndex);

            Label startDateLabel = new Label("Start Date: " + tournament.getStartDate());
            tournamentGridPane.add(startDateLabel, 2, rowIndex);

            Label participantsNumberLabel = new Label("Participants Number: " + tournament.getParticipantsNumber());
            tournamentGridPane.add(participantsNumberLabel, 3, rowIndex);

            Label statusLabel = new Label("Status: " + tournament.getStatus());
            tournamentGridPane.add(statusLabel, 4, rowIndex);

            rowIndex++;
        }
    }
}
