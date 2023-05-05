/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.services.BadgeService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class BackBadgeController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    
    @FXML
    private FlowPane badgePane;
    private ObservableList<Badge> badges;
    
    
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    

    public void badgeCards() {
        badgePane.getChildren().clear();
        BadgeService bs = new BadgeService();
        ObservableList<Badge> badgeList = FXCollections.observableArrayList();
        badgeList = bs.displayBadges();

        for (Badge b : badgeList) {
            VBox card = new VBox();
            card.setPrefSize(250, 250);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream("C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\badge\\" + b.getBadgeFileName())));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label logoLabel = new Label(b.getLogo());
            Label valeurLabel = new Label(Integer.toString(b.getValeur()));
            Label descriptionLabel = new Label(b.getDescription());
            
            logoLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            logoLabel.setAlignment(Pos.CENTER);
            logoLabel.setStyle("-fx-text-fill: gray;");

            valeurLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            valeurLabel.setAlignment(Pos.CENTER);
            valeurLabel.setStyle("-fx-text-fill: gray;");

            descriptionLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            descriptionLabel.setAlignment(Pos.CENTER);
            descriptionLabel.setStyle("-fx-text-fill: gray;");

            card.getChildren().add(logoLabel);
            card.getChildren().add(valeurLabel);
            card.getChildren().add(descriptionLabel);

            Button disableBtn = new Button("Disable");
            disableBtn.setAlignment(Pos.TOP_RIGHT);
            disableBtn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            disableBtn.setOnAction(e -> {
                BadgeService bss = new BadgeService();
                badgeCards();
            });
            card.getChildren().add(disableBtn);
            badgePane.getChildren().add(card);
            badgePane.setMargin(card, new Insets(5, 5, 5, 5));
        }
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        badgeCards();
    }
    
}
