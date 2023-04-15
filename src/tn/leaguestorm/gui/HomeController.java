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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;
import tn.leaguestorm.utils.MyConnection;

public class HomeController implements Initializable {


    @FXML
    private Label lblEmail;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblBirthDate;
    
    @FXML
    private Pane pnlOverview;

    User currentUser = CurrentUser.getUser();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
}
