/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pidev.techstorm.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart pieChart;
    Connection connexion;
     Statement stm;
       private Stage stage;
      private Scene scene;
      private Parent root;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            afficherPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
     public StatsController() {
        connexion = DataSource.getInstance().getCnx();
    }  
      public void afficherPie() throws SQLException{
        int infticketsCount=0 ;
        int VIPticketsCount=0 ;
        int StandardticketsCount=0 ;
         String req = "SELECT COUNT(*) AS count FROM tickets WHERE type='VIP' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           infticketsCount = rst.getInt(1);
        }
         PieChart.Data qtr1= new PieChart.Data("TYPE VIP", +infticketsCount) ;
         String req1 = "SELECT COUNT(*) AS count FROM tickets WHERE type='VIP' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst1 = stm.executeQuery(req1);

        while (rst1.next()) {
         VIPticketsCount = rst1.getInt(1);
        }
         PieChart.Data qtr2= new PieChart.Data("TYPE Standard", +VIPticketsCount) ;
          String req2 = "SELECT COUNT(*) AS count FROM tickets WHERE type='Standard' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst2 = stm.executeQuery(req2);
         
         pieChart.getData().addAll(qtr1,qtr2) ;
       
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AjouterEvents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
