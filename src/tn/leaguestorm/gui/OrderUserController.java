
package tn.leaguestorm.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.OrderService;
import tn.leaguestorm.utils.CurrentUser;
import tn.leaguestorm.utils.FXMLUtils;

/**
 * FXML Controller class
 *
 * @author khair
 */
public class OrderUserController implements Initializable {

        User currentUser = CurrentUser.getUser();
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button home;
   
    @FXML
    private Button btn;
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private TableView<Order> TableView;
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> mont;
    @FXML
    private TableColumn<?, ?> strip;
    @FXML
    private TableColumn<?, ?> adr;
    @FXML
    private TextField	filtre ;
    @FXML
    private TableColumn<?, ?> etat;
	 ObservableList<Order> orders = FXCollections.observableArrayList();
	    OrderService a = new OrderService();
	    @FXML
	    private Pane orderStatusPane;
	    private Line orderStatusLine;
    /**
     * Initializes the controller class.
     */
	    
	    private void setOrderStatusLine(String status) {
	        if (status.equalsIgnoreCase("created")) {
	            orderStatusLine.setStroke(Color.LIGHTGRAY);
	        } else if (status.equalsIgnoreCase("shipped_soon")) {
	            orderStatusLine.setStroke(Color.YELLOW);
	        } else if (status.equalsIgnoreCase("shipped")) {
	            orderStatusLine.setStroke(Color.BLUE);
	        } else if (status.equalsIgnoreCase("out_for_delivery")) {
	            orderStatusLine.setStroke(Color.ORANGE);
	        } else if (status.equalsIgnoreCase("delivered")) {
	            orderStatusLine.setStroke(Color.GREEN);
	        }
	    }
	   

	    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     	    orderStatusLine = new Line();
    	    orderStatusLine.setStroke(Color.RED);
    	    orderStatusLine.setStrokeWidth(2);
    	    orderStatusPane = new Pane();
    	    orderStatusPane.getChildren().add(orderStatusLine);

    	    // Add the order status pane to the scene, if the scene is not null
    	    Scene scene = TableView.getScene();
    	    if (scene != null) {
    	        Group root = (Group) scene.getRoot();
    	        root.getChildren().add(orderStatusPane);
    	    }
    	 
        OrderService os = new OrderService();
        try {
            orders = os.displayUserOrders(currentUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        date.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        strip.setCellValueFactory(new PropertyValueFactory<>("stripe_session"));
        adr.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        mont.setCellValueFactory(new PropertyValueFactory<>("price"));
        etat.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
 
 
        TableView.setItems(orders);
    } @FXML
    private void handleOrderSelected(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click detected
            // Get the selected order and its index
            Order selectedOrder = TableView.getSelectionModel().getSelectedItem();
            String status = selectedOrder.getOrderStatus();
            int selectedIndex = TableView.getSelectionModel().getSelectedIndex();

            // Calculate the position of the line
            double x1 = 0;
            double x2 = TableView.getWidth();
            double y = (selectedIndex + 1) * TableView.lookup(".table-row-cell").lookup(".text").getBoundsInLocal().getHeight();

            if (this.orderStatusPane == null) {
                this.orderStatusPane = new Pane();
            }

            // Remove the existing line from the orderStatusPane
            if (this.orderStatusPane.getChildren().contains(orderStatusLine)) {
                this.orderStatusPane.getChildren().remove(orderStatusLine);
            }

            // Set the position of the new line
            orderStatusLine.setStartX(x1);
            orderStatusLine.setEndX(x2);
            orderStatusLine.setStartY(y);
            orderStatusLine.setEndY(y);

            // Set the color of the line based on the order status
            setOrderStatusLine(status);

            // Add the new line to the orderStatusPane
            this.orderStatusPane.getChildren().add(orderStatusLine);

            // Create a new pop-up window to display the order status details
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Order Status Details");

            // Create a new pane to hold the order status line and any other details
            Pane popupPane = new Pane();
            popupPane.getChildren().addAll(orderStatusLine);

            // Add the popup pane to the popup scene
            Scene popupScene = new Scene(popupPane);
            popupStage.setScene(popupScene);

            // Display the pop-up window
            popupStage.showAndWait();
        }
    }


    private void GoBackToShop(ActionEvent event) throws IOException {
                FXMLUtils.changeScene(event, "/tn/leaguestorm/gui/shop.fxml", "shop");

    }

    @FXML
    private void showAzer() {
   
    }
    @FXML
    public void handleSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = filtre.getText().trim();
            if (searchText.isEmpty()) {
                TableView.setItems(orders);
            } else {
                ObservableList<Order> filteredList = FXCollections.observableArrayList();
                boolean productFound = false;
                for (Order b : orders) {
                    // search for name or description
                    if ((b.getStripe_session().toLowerCase().contains(searchText.toLowerCase())) 
                            || (b.getOrderStatus().toLowerCase().contains(searchText.toLowerCase()))) {
                        filteredList.add(b);
                        productFound = true;
                    }
                }
                if (!productFound) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Commande non trouve");
                    alert.setHeaderText("Aucun commande ne correspond a votre recherche");
                    alert.setContentText("Veuillez essayer une autre recherche.");
                    alert.showAndWait();
                }
                TableView.setItems(filteredList);
            }
        }
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
    	  try {
  		    FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
  		    Parent root = loader.load();
  		    
  		    
  		    
  		    Scene scene = new Scene(root);
  		    Stage stage = new Stage();
  		    stage.setScene(scene);
  		    stage.show();
  		} catch (IOException ex) {
  		    ex.printStackTrace();
  		}

}
}
  
  
    
 
 
