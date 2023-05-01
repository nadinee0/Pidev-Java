/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

<<<<<<< HEAD
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import com.twilio.converter.Promoter;


import java.net.URI;
import java.math.BigDecimal;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class BackController implements Initializable {
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.SelectedBadge;

/**
 *
 * @author Bellalouna Iheb
 */
public class BackController {
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b

    @FXML
    private Button btnOverview;
    @FXML
<<<<<<< HEAD
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
=======
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
<<<<<<< HEAD
    @FXML
    private VBox pnItems;
    @FXML
    private Button btnCategory;
    @FXML
    private Button btnArticle;
  @FXML
    private Button sendSMSButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
=======
    
    @FXML
    private FlowPane userPane;
    private ObservableList<User> users;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnBadge;
    @FXML
    private Button btnAddUsers;
    @FXML
    private Button btnAddBadges;
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b

    @FXML
    private void handleClicks(ActionEvent event) {
    }

<<<<<<< HEAD
    @FXML
    private void category(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Category.fxml"));
            Parent root = loader.load(); // load the new FXML file
            Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
            Node sourceNode = (Node) event.getSource(); // get the source node of the current event
            Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
            Stage stage = (Stage) currentScene.getWindow(); // get the current stage
            stage.setScene(scene); // set the new scene as the content of the stage
    }


    private void subcategory(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/SubCategory.fxml"));
            Parent root = loader.load(); // load the new FXML file
            Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
            Node sourceNode = (Node) event.getSource(); // get the source node of the current event
            Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
            Stage stage = (Stage) currentScene.getWindow(); // get the current stage
            stage.setScene(scene); // set the new scene as the content of the stage
    }

    @FXML
    private void article(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/leaguestorm/gui/Article.fxml"));
            Parent root = loader.load(); // load the new FXML file
            Scene scene = new Scene(root); // create a new scene with the new FXML file as its content
            Node sourceNode = (Node) event.getSource(); // get the source node of the current event
            Scene currentScene = sourceNode.getScene(); // get the current scene from the source node
            Stage stage = (Stage) currentScene.getWindow(); // get the current stage
            stage.setScene(scene); // set the new scene as the content of the stage
    }






    
  }


=======
    public void initialize() {
        userCards();
    }

    public void userCards() {
        userPane.getChildren().clear();
        UserService us = new UserService();
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList = us.displayUsers();

        for (User u : userList) {
            VBox card = new VBox();
            card.setAlignment(Pos.CENTER);
            card.setPrefSize(250, 250);
            userPane.setHgap(70);
            userPane.setVgap(20);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3); -fx-clip-shape: circle;");
            
            Hyperlink editLink = new Hyperlink("Edit");
            editLink.setOnAction(e -> {
                try {
                    CurrentUser.setSelecUser(u);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUserPopup.fxml"));
                    Parent root = loader.load();
                    
                    
                    Stage modifyStage = new Stage();
                    modifyStage.setScene(new Scene(root));
                    modifyStage.setTitle("Modify User Informations");
                    modifyStage.initStyle(StageStyle.UNDECORATED);
                    modifyStage.initModality(Modality.APPLICATION_MODAL);
                    modifyStage.showAndWait();
                    
                    userCards();
                } catch (IOException ex) {
                    Logger.getLogger(BackBadgeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                userCards();
            });
            card.getChildren().add(editLink);
            
            ImageView imageView;
            try {
                Image image = new Image(new FileInputStream("C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + u.getProfilePictureName()));
                imageView = new ImageView(image);
                imageView.setClip(new Circle(75, 75, 75));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label firstAndLastNameLabel = new Label(u.getLastName() + "." + u.getFirstName());
            Label emailLabel = new Label(u.getEmail());

            firstAndLastNameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            firstAndLastNameLabel.setAlignment(Pos.CENTER);
            firstAndLastNameLabel.setStyle("-fx-text-fill: gray;");

            emailLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            emailLabel.setAlignment(Pos.CENTER);
            emailLabel.setStyle("-fx-text-fill: gray;");

            card.getChildren().add(firstAndLastNameLabel);
            card.getChildren().add(emailLabel);

            Hyperlink disableLink = new Hyperlink(u.isBanned() ? "Unban" : "Ban");
            disableLink.setStyle("-fx-text-fill: blue; -fx-font-size: 14px;");
            disableLink.setOnAction(e -> {
                UserService uss = new UserService();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(u.isBanned() ? "Unban Confirmation" : "Ban Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(u.isBanned() ? "Are you sure you want to unban this user?" : "Are you sure you want to ban this user?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (u.isBanned()) {
                        uss.unbanUser(u);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Unbanned!");
                        successAlert.setContentText("User " + u.getFirstName() + " has been unbanned!");
                        successAlert.showAndWait();
                    } else {
                        uss.banUser(u);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Banned!");
                        successAlert.setContentText("User " + u.getFirstName() + " has been banned!");
                        successAlert.showAndWait();
                    }
                    userCards();
                }
            });
            card.getChildren().add(disableLink);

            userPane.getChildren().add(card);
            userPane.setMargin(card, new Insets(5, 5, 5, 5));
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


    @FXML
    private void addBadgeAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/AddBadgePopup.fxml", "Add Badge");
    }
}
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b
