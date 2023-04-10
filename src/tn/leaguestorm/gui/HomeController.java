package tn.leaguestorm.gui;

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
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.leaguestorm.entities.User;

public class HomeController implements Initializable {
    
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

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
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    
    @FXML
    private Button btnProfile;
    
    @FXML
    private Label lblFullName;
    
    @FXML
    private Label lblEmail;
    
    @FXML
    private Label lblCountry;
    
    @FXML
    private ImageView userProfilePic;
    
    private User user;

    public void setUser(User user) {
        this.user = user;
        initData(user);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        } 
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    public void initData(User user) {
        lblFullName.setText(user.getFirstName()+" "+user.getLastName());
        lblEmail.setText(user.getEmail());   
        lblCountry.setText(user.getCountry());  
        String userProfilePicPath = "C:\\leagueStorm\\src\\tn\\leaguestorm\\miscs\\user\\" + user.getProfilePictureName();
        Image userProfilePic = new Image("file:" + userProfilePicPath);
        this.userProfilePic.setImage(userProfilePic);
    }
}
