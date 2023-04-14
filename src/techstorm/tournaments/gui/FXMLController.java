/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;

/**
 * FXML Controller class
 *
 * @author qiyanu
 */
public class FXMLController implements Initializable {

    @FXML
    private TableView<Tournament> tournamentTableView;
    @FXML
    private TableColumn<Tournament, Integer> tidColumn;
    @FXML
    private TableColumn<Tournament, String> nameColumn;
    @FXML
    private TableColumn<Tournament, String> startDateColumn;
    @FXML
    private TableColumn<Tournament, Integer> participantsNumberColumn;
    @FXML
    private TableColumn<Tournament, String> statusColumn;

    private ObservableList<Tournament> tournamentList;

    private TournamentCRUD tournamentService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tournamentService = new TournamentCRUD();

        tournamentList = FXCollections.observableArrayList();

        // Set up the cell value factories for the table columns
        tidColumn.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        participantsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("participantsNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set the data source for the table view
        tournamentTableView.setItems(tournamentList);
    }

    @FXML
    private void Display(MouseEvent event) {
        // Retrieve the list of tournaments from the data source
        List<Tournament> tournaments = tournamentService.getAllTournaments();

        // Populate the observable list with the retrieved tournaments
        tournamentList.clear();
        tournamentList.addAll(tournaments);
    }
    
}
