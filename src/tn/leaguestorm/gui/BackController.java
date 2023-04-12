/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

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
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, Integer> phoneNumberColumn;
    @FXML
    private TableColumn<User, Void> buttonColumn;
    @FXML
    private TableView<User> tableView;

    private ObservableList<User> user;

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    public void initialize() {
        UserService userService = new UserService();
        try {
            user = FXCollections.observableArrayList(userService.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
      
            List<User> users = userService.getAll();
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            tableView.setItems(user);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
