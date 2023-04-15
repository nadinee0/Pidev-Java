package tn.leaguestorm.gui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.MyConnection;

public class HomeController implements Initializable {

    private Button btnOverview;

    private Button btnOrders;

    private Button btnCustomers;

    @FXML
    private Button btnMenus;


    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Button btnProfile;
    
    @FXML
    private Button btnPassword;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblCountry;

    @FXML
    private ImageView userProfilePic;

    private User user;
    @FXML
    private Label lblBirthDate;

    User currentUser = CurrentUser.getUser();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        if (currentUser != null) {
    lblFullName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        lblEmail.setText(currentUser.getEmail());
        lblCountry.setText(currentUser.getCountry());
        LocalDate birthDate = currentUser.getBirthDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedBirthDate = birthDate.format(formatter);
        lblBirthDate.setText(formattedBirthDate);

        String userProfilePicPath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + currentUser.getProfilePictureName();
        Image userProfilePic = new Image("file:" + userProfilePicPath);
        this.userProfilePic.setImage(userProfilePic);
    } else {
            System.out.println("Error");
}
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }

    private Alert alert;

    private void handleBtnSelectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());

        if (selectedFile != null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Selected file: " + selectedFile.getAbsolutePath());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        //FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/Signin.fxml", "Sign in");
    }

    
    @FXML
    private void handleChangePasswordLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ChangePassword.fxml", "Change Password");
    }
    
    @FXML
    private void handleProfileUpdateLinkAction(ActionEvent event) throws IOException {
        FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/ProfileUpdate.fxml", "Edit Profile");
    }
}
