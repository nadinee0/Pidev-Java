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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;

/**
 *
 * @author azizo
 */
public class BackController implements Initializable {
    
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
    private FlowPane userPane;
    private ObservableList<User> users;

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userCards();
    }   
    
    
    public void userCards() {
        userPane.getChildren().clear();
        UserService us = new UserService();
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList = us.displayUsers();

        for (User u : userList) {
            VBox card = new VBox();
            card.setPrefSize(250, 250);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream("C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + u.getProfilePictureName())));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label firstNameLabel = new Label(u.getFirstName());
            Label lastNameLabel = new Label(u.getLastName());
            Label emailLabel = new Label(u.getEmail());

            firstNameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            firstNameLabel.setAlignment(Pos.CENTER);
            firstNameLabel.setStyle("-fx-text-fill: gray;");

            lastNameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            lastNameLabel.setAlignment(Pos.CENTER);
            lastNameLabel.setStyle("-fx-text-fill: gray;");

            emailLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            emailLabel.setAlignment(Pos.CENTER);
            emailLabel.setStyle("-fx-text-fill: gray;");

            card.getChildren().add(firstNameLabel);
            card.getChildren().add(lastNameLabel);
            card.getChildren().add(emailLabel);

            Button disableBtn = new Button("Disable");
            disableBtn.setAlignment(Pos.TOP_RIGHT);
            disableBtn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            disableBtn.setOnAction(e -> {
                UserService uss = new UserService();
                uss.banUser(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ban hammer");
                alert.setContentText("User " + u.getFirstName() + " has been banned!");
                alert.showAndWait();
                userCards();
            });
            card.getChildren().add(disableBtn);
            userPane.getChildren().add(card);
            userPane.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }
    
}
