/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui2;

import tn.leaguestorm.entities.Team;
import tn.leaguestorm.services.ServiceTeam;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class StatisticsController implements Initializable {


    @FXML
    private ImageView GoBackBtn;
    @FXML
    private PieChart StatsChart;
    
    ServiceTeam rs = new ServiceTeam();
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            displayStatistics();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
public void displayStatistics() throws SQLException {
    // Récupérer toutes les équipes de la base de données
    List<Team> teams = rs.getAll();
    // Regrouper les équipes par taux d'évaluation
    Map<Float, Long> teamParRate = teams.stream()
            .collect(Collectors.groupingBy(Team::getRate_team, Collectors.counting()));

    // Ajouter le nombre total de chaque Rate_team
    Map<Float, Integer> nombreTotalParRate = new HashMap<>();
    for (Map.Entry<Float, Long> entry : teamParRate.entrySet()) {
        Float rate = entry.getKey();
        Long count = entry.getValue();
        int total = count.intValue();
        nombreTotalParRate.put(rate, total);
    }

    // Conversion de teamParRate map en une liste d'objets PieChart.Data
    List<PieChart.Data> pieChartData = teamParRate.entrySet().stream()
            .map(entry -> new PieChart.Data(entry.getKey().toString(), entry.getValue()))
            .collect(Collectors.toList());

    // Définissez les éléments de données dans le PieChart
    StatsChart.setData(FXCollections.observableArrayList(pieChartData));

    // Configurer l'animation pour les données piechart
    StatsChart.getData().forEach(data ->
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(200), data.getNode());
                st.setToX(1.1);
                st.setToY(1.1);
                st.play();
            })
    );
    StatsChart.getData().forEach(data ->
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(200), data.getNode());
                st.setToX(1.0);
                st.setToY(1.0);
                st.play();
            })
    );

    // Configurer l'interactivité pour les données piechart
    StatsChart.getData().forEach(data ->
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                // Obtenir la valeur actuelle de l'élément data
                long currentValue = (long) data.getPieValue();

                // Obtenir le nombre total pour ce taux d'évaluation
                Float rate = Float.parseFloat(data.getName());
                int total = nombreTotalParRate.getOrDefault(rate, 0);

                // Afficher un message avec la valeur de data et le nombre total
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Statistique Team(s)");
                alert.setHeaderText("Taux d'évaluation : " + data.getName());
                alert.setContentText("Nombre d'équipe(s) avec ce taux exactement : " + total);
                alert.showAndWait();
            })
    );
}
}