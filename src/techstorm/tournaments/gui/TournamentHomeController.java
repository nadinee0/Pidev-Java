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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

public class TournamentHomeController implements Initializable{

    @FXML
    private Button addButton;

    @FXML 
    private GridPane tournamentGrid;
    
    @FXML
    private TextField homeSearchtf;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load tournaments from data source
        TournamentCRUD tcd = new TournamentCRUD();
        List<Tournament> tournaments = tcd.getAllTournaments();
        updateGridPane(tournaments);        
    }
    
    
    private void updateGridPane(List<Tournament> tournaments) {
        // Clear existing data
        tournamentGrid.getChildren().clear();
        // Populate the GridPane with the tournaments
        int rowIndex = 0; // start at row 1 to skip the header row
        
        Label nameLabel1 =   new Label("Name");            
            tournamentGrid.add(nameLabel1, 0, rowIndex);
            
            Label tidLabel1 = new Label("Tid");                    
            tournamentGrid.add(tidLabel1, 1, rowIndex);

            Label startDateLabel1 = new Label("Start Date");
            tournamentGrid.add(startDateLabel1, 2, rowIndex);

            Label participantsNumberLabel1 = new Label("Participants Number");
            tournamentGrid.add(participantsNumberLabel1, 3, rowIndex);

            Label statusLabe1l = new Label("Status");
            tournamentGrid.add(statusLabe1l, 4, rowIndex);
            
            Label replayIdLabel1 = new Label("Replay ID");
            tournamentGrid.add(replayIdLabel1, 5, rowIndex);
            
           rowIndex = 1; // start at row 1 to skip the header row
            
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
            
            // Create buttons for Delete, Update, and Details
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> handleDeleteButton(tournament));
            tournamentGrid.add(deleteButton, 6, rowIndex);
            
            Button updateButton = new Button("Update");
            updateButton.setOnAction(e -> handleUpdateButton(tournament));
            tournamentGrid.add(updateButton, 7, rowIndex);
            
            Button detailsButton = new Button("Details");
            detailsButton.setOnAction(e -> handleDetailsButton(tournament));
            tournamentGrid.add(detailsButton, 8, rowIndex);

            rowIndex++;
        }
    }
    
    
    // Action handler for the Delete button
    private void handleDeleteButton(Tournament tournament) {
        // Implement the logic to delete the tournament
        System.out.println("Deleting tournament " + tournament.getName());
    }
    
    // Action handler for the Update button
    private void handleUpdateButton(Tournament tournament) {
        // Implement the logic to update the tournament
        System.out.println("Updating tournament " + tournament.getName());
    }
    
    // Action handler for the Details button
    private void handleDetailsButton(Tournament tournament) {
        // Implement the logic to show the details of the tournament
        System.out.println("Showing details of tournament " + tournament.getName());
    }

    @FXML
    void handleAddButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTournament.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    
@FXML
void handleSearchButton(ActionEvent event) {
    String searchText = homeSearchtf.getText();
    TournamentCRUD tcd = new TournamentCRUD();
    List<Tournament> tournaments = tcd.searchTournaments(searchText);
    updateGridPane(tournaments);
}

}

