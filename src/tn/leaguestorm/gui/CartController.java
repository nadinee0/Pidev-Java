
package tn.leaguestorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.scene.control.ButtonBar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.leaguestorm.entities.Address;
//import tn.leaguestorm.entities.CartItem;
import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.AddressService;
import tn.leaguestorm.services.OrderService;
import tn.leaguestorm.utils.CurrentUser;

/**
 * FXML Controller class
 *
 * @author azizbramli
 */
public class CartController {
    private GridPane productGrid;
    User currentUser = CurrentUser.getUser();
    private Label totalLabel;

    
    @FXML
    private TableView<Order> TableView;
    @FXML
    private TableColumn<?, ?> nom;
 
    @FXML
    private TableColumn<?, ?> adr;
    @FXML
    private TableColumn<?, ?> mont;
 
    @FXML
    private TableColumn<?, ?> etat;
 
 
    @FXML
    private GridPane cartGridPane;
    @FXML
    private VBox cartBox;
    @FXML
    private Button clearCartButton;
 
     
    public void initialize() {
     
     
    }
    
     

 Order o= new Order();
public void setCommande(Order commande) {
	this.o=commande;
	 ObservableList<Order> orders = FXCollections.observableArrayList();
	     System.out.println(currentUser.getFirstName());
	    orders.add(commande);
	    
	    TableView.setItems(orders);
	    // Bind the columns to the corresponding properties of the Order class
	    nom.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
	    adr.setCellValueFactory(new PropertyValueFactory<>("delivery"));
	    mont.setCellValueFactory(new PropertyValueFactory<>("price"));
	    etat.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
  }

 @FXML
private void ajout(ActionEvent event) throws IOException {
	 OrderService sc =new OrderService();
	 try {
		int ido=sc.getIdOrder();
		o.setIdO(ido);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 
	 o.setStatus(0);
	 o.setStripe_session("pas payye");
	 try {
		sc.update(o);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Order Created Successfully");
		alert.setHeaderText(null);
		alert.setContentText("Your order has been created successfully! Proceed to payment now?");

		// Create a new Dialog
		Dialog<ButtonType> dialog = new Dialog<>();

		// Set the dialog title and header text
		dialog.setTitle("Payment");
		dialog.setHeaderText("Proceed to Payment?");

		// Add buttons to the dialog
		ButtonType proceedButton = new ButtonType("Proceed to Payment", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(proceedButton, cancelButton);

		// Get the buttons and apply styles
		Button proceedButtonNode = (Button) dialog.getDialogPane().lookupButton(proceedButton);
		proceedButtonNode.getStyleClass().add("proceed-button");

		Button cancelButtonNode = (Button) dialog.getDialogPane().lookupButton(cancelButton);
		cancelButtonNode.getStyleClass().add("cancel-button");

		// Show the dialog and wait for a response
		Optional<ButtonType> result = dialog.showAndWait();

		// Check which button was clicked
		if (result.isPresent() && result.get() == proceedButton) {
			   try {
				    FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
				    Parent root = loader.load();
				    
				    // Get the controller of the Cart.fxml file
				    // Get the controller of the Cart.fxml file
				    PaymentController cartController = loader.getController();
				    
				    // Pass the commande object to the CartController
				    cartController.setCommande(o);
				    System.out.println("order"+o.toString());
				    Scene scene = new Scene(root);
				    Stage stage = new Stage();
				    stage.setScene(scene);
				    stage.show();
				} catch (IOException ex) {
				    ex.printStackTrace();
				}
		} else {
		    // User clicked "Cancel"
		}

 



	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 
}

