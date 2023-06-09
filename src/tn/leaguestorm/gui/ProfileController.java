<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
=======
package tn.leaguestorm.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
<<<<<<< HEAD
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void shop(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Shop.fxml"));
            Parent root = loader.load(); // load the new FXML file
            Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
            Node sourceNode = (Node) event.getSource(); // get the source node of the current event
            Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
            Stage stage = (Stage) currentScene.getWindow(); // get the current stage
            stage.setScene(scene); // set the new scene as the content of the stage
    }
    
=======
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.MyConnection;

public class ProfileController implements Initializable {
    private MyConnection cnx = MyConnection.getInstance();


    @FXML
    private Label lblEmail;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblBirthDate;
    
    @FXML
    private Pane pnlOverview;

    User currentUser = CurrentUser.getUser();
    @FXML
    private FlowPane badgesPane;
    @FXML
    private Label scoreLB;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int score = 0;
        if (currentUser != null) {
            lblEmail.setText(currentUser.getEmail());
            lblCountry.setText(currentUser.getCountry());
            LocalDate birthDate = currentUser.getBirthDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedBirthDate = birthDate.format(formatter);
            lblBirthDate.setText(formattedBirthDate);
        } else {
            System.out.println("No such user found!");
        }
        score = userCards();
        scoreLB.setText(String.valueOf(score));
    }
    
    public int userCards() {
        badgesPane.getChildren().clear();
        badgesPane.setAlignment(Pos.CENTER);
        UserService us = new UserService();
        ObservableList<Badge> badgeList = FXCollections.observableArrayList();
        badgeList = us.displayBadgesForUser();
        int score = 0;
        for (Badge b : badgeList) {
            score += b.getValeur();
            VBox card = new VBox();
            card.setAlignment(Pos.CENTER);
            card.setPrefSize(75, 75);
            badgesPane.setHgap(2);
            badgesPane.setVgap(2);
            card.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            ImageView imageView;
            try {
                Image image = new Image(new FileInputStream("C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\badge\\" + b.getBadgeFileName()));
                imageView = new ImageView(image);
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

            badgesPane.getChildren().add(card);
            badgesPane.setMargin(card, new Insets(5, 5, 5, 5));
        }
        return score;
    }
    
    
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b
}
