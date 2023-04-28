/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.leaguestorm.entities.Badge;
import tn.leaguestorm.services.BadgeService;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.SelectedBadge;

/**
 *
 * @author Bellalouna Iheb
 */
public class BackBadgeController {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    @FXML
    private FlowPane badgePane;
    private ObservableList<Badge> badges;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnBadge;

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    public void initialize() {
        badgeCards();
    }

    public void badgeCards() {
        badgePane.getChildren().clear();
        BadgeService bs = new BadgeService();
        ObservableList<Badge> badgeList = FXCollections.observableArrayList();
        badgeList = bs.displayBadges();

        for (Badge b : badgeList) {
            VBox card = new VBox();
            card.setAlignment(Pos.CENTER);
            card.setPrefSize(250, 250);
            badgePane.setHgap(70);
            badgePane.setVgap(20);
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

            Button modifyBtn = new Button("Modify");
            modifyBtn.setAlignment(Pos.TOP_RIGHT);
            modifyBtn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            modifyBtn.setOnAction(e -> {
                try {
                    SelectedBadge.setBadge(b);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyBadgePopup.fxml"));
                    Parent root = loader.load();
                    
                    
                    Stage modifyStage = new Stage();
                    modifyStage.setScene(new Scene(root));
                    modifyStage.setTitle("Modify Badge Informations");
                    modifyStage.initStyle(StageStyle.UNDECORATED);
                    modifyStage.initModality(Modality.APPLICATION_MODAL);
                    modifyStage.showAndWait();
                    
                    badgeCards();
                } catch (IOException ex) {
                    Logger.getLogger(BackBadgeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                badgeCards();
            });
            card.getChildren().add(modifyBtn);
            badgePane.getChildren().add(card);
            badgePane.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }

    @FXML
    private void userAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Back.fxml", "User");
    }

    @FXML
    private void badgeAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/BackBadge.fxml", "Forgot");
    }
}
