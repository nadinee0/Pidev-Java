/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.techstorm.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatsController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<?, ?> barChart;
    
    java.sql.Connection connexion = DataSource.getInstance().getCnx();

    
//    private Connection connexion;
//    private PreparedStatement prepare;
//    private ResultSet result;
    
//    private void Connection connectDb() throws ClassNotFoundException, SQLException {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit","root","");
//        }catch( ClassNotFoundException |SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
    public void chart(){
        String chartSql = "SELECT type, SUM(prix) AS total FROM tickets GROUP BY type ORDER BY type ASC LIMIT 4";
        connexion = DataSource.getInstance().getCnx();
        try{
            XYChart.Series chartData = new XYChart.Series();
            PreparedStatement k=connexion.prepareStatement(chartSql);
            ResultSet rs=k.executeQuery(chartSql);
            while(rs.next()){
                chartData.getData().add(new XYChart.Data(rs.getString(1),rs.getInt(2)));
            }
            barChart.getData().add(chartData);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chart();
     } 
}
