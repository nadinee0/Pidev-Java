/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import javafx.geometry.Insets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.UserService;

/**
 *
 * @author Bellalouna Iheb
 */
public class BackController {

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
    private TableColumn<User, String> emailColumn;
    private TableColumn<User, String> firstNameColumn;
    private TableColumn<User, String> lastNameColumn;
    private TableColumn<User, Integer> phoneNumberColumn;
    private TableView<User> tableView;

    private ObservableList<User> users;
    @FXML
    private FlowPane cardPane;
    @FXML
    private VBox userContainer;

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    public void initialize() {
        UserService userService = new UserService();
        try {
            users = FXCollections.observableArrayList(userService.getAll());
        for (User user : users) {
             VBox card = new VBox();
            card.getStyleClass().add("card");
            card.setPrefWidth(300);
            card.setSpacing(10);
            card.setPadding(new Insets(10, 10, 10, 10));

            HBox userInfo = new HBox();
            userInfo.getStyleClass().add("user-info");
            userInfo.setPrefWidth(250);
            userInfo.setSpacing(10);

            // Use a GridPane instead of a VBox for the user information labels
            GridPane userGrid = new GridPane();
            userGrid.setHgap(10);
            userGrid.setVgap(5);
            userGrid.setPadding(new Insets(5));

            Label emailLabel = new Label("Email:");
            Label emailValue = new Label(user.getEmail());
            GridPane.setConstraints(emailLabel, 0, 0);
            GridPane.setConstraints(emailValue, 1, 0);

            Label firstNameLabel = new Label("First Name:");
            Label firstNameValue = new Label(user.getFirstName());
            GridPane.setConstraints(firstNameLabel, 0, 1);
            GridPane.setConstraints(firstNameValue, 1, 1);

            Label lastNameLabel = new Label("Last Name:");
            Label lastNameValue = new Label(user.getLastName());
            GridPane.setConstraints(lastNameLabel, 0, 2);
            GridPane.setConstraints(lastNameValue, 1, 2);

            Label phoneNumberLabel = new Label("Phone Number:");
            Label phoneNumberValue = new Label(user.getPhoneNumber());
            GridPane.setConstraints(phoneNumberLabel, 0, 3);
            GridPane.setConstraints(phoneNumberValue, 1, 3);

            userGrid.getChildren().addAll(
                emailLabel, emailValue, 
                firstNameLabel, firstNameValue, 
                lastNameLabel, lastNameValue, 
                phoneNumberLabel, phoneNumberValue
            );

            userInfo.getChildren().addAll(userGrid);

            card.getChildren().addAll(userInfo);
            userContainer.getChildren().add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
