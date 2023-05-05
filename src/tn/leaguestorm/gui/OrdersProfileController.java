/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.Initializable;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
public class OrdersProfileController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrderService os=new OrderService();
        orders = os.afficherTous();
        user.setCellValueFactory(new PropertyValueFactory<>("userid"));
        date.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        strip.setCellValueFactory(new PropertyValueFactory<>("stripe_session"));
        adr.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        mont.setCellValueFactory(new PropertyValueFactory<>("price"));
        etat.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
 
 
        TableView.setItems(orders);
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
    }        public static int numeroPDF = 0;

    @FXML
    private void pdf(ActionEvent event)  throws FileNotFoundException, DocumentException, BadElementException, IOException { 
    	 // Récupérez la commande sélectionnée dans le TableView
        Order selectedOrder = TableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Impposible ");
            alert.setHeaderText("Aucun commande selectionnee !!!");
            alert.setContentText("Veuillez essayer une autre !!.");
            alert.showAndWait();
        }
     // Créez un document PDF
        numeroPDF = numeroPDF + 1;
        String nom = "Order Details" + numeroPDF + ".pdf";
        try {
        	 
                  
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nom));

         document.open();

        // Ajoutez le contenu au document PDF
        Paragraph title = new Paragraph("Commande n°" + selectedOrder.getIdO(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        document.add(title);
        document.add(Chunk.NEWLINE);

      /*  Paragraph client = new Paragraph("Client : " + selectedOrder.getUser().getFirstName(), FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(client);
        document.add(Chunk.NEWLINE);*/

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Paragraph date = new Paragraph("Date de création : " + dateFormat.format(selectedOrder.getCreatedAt()), FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(date);
        document.add(Chunk.NEWLINE);

        Paragraph session = new Paragraph("Session Stripe : " + selectedOrder.getStripe_session(), FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(session);
        document.add(Chunk.NEWLINE);

        Paragraph delivery = new Paragraph("Adresse de livraison : " + selectedOrder.getDelivery(), FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(delivery);
        document.add(Chunk.NEWLINE);

        Paragraph amount = new Paragraph("Montant : " + selectedOrder.getPrice() + " EUR", FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(amount);
        document.add(Chunk.NEWLINE);

        Paragraph status = new Paragraph("Statut de la commande : " + selectedOrder.getOrderStatus(), FontFactory.getFont(FontFactory.HELVETICA, 14));
        document.add(status);

        // Fermez le document PDF
        document.close();
        Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" + nom));
        writer.close();
        }
        catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
 
}	 
    @FXML
    private void modifer(ActionEvent event) {
    	 // Récupérez la commande sélectionnée dans le TableView
        Order selectedOrder = TableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Impposible ");
            alert.setHeaderText("Aucun commande selectionnee !!!");
            alert.setContentText("Veuillez essayer une autre !!.");
            alert.showAndWait();
        }
       
        	 try {
        		  Alert alert = new Alert(AlertType.CONFIRMATION);
        		    alert.setTitle("Confirmation");
        		    alert.setHeaderText("Êtes-vous sûr de vouloir mettre à jour le statut de la commande ?");
        		    alert.setContentText("Le statut de la commande sera mis à jour dans la base de données.");
        		    Optional<ButtonType> result = alert.showAndWait();
        		    if (result.isPresent() && result.get() == ButtonType.OK) {
      	    	if (selectedOrder != null) {

          	         
          	        // Cr�er une bo�te de dialogue
         	        Dialog<Order> dialog = new Dialog<>();
         	        dialog.setTitle("Modifier la commande");

         	        // Cr�er un bouton pour valider la modification
         	        ButtonType validerButtonType = new ButtonType("Valider", ButtonData.OK_DONE);
         	        dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

         	        // Cr�er les champs de saisie pour la modification
         	        TextField orderstatuts = new TextField();

         	     
         	        // Ajouter les champs � la bo�te de dialogue
         	        GridPane grid = new GridPane();
         	        grid.add(new Label("Etat :"), 0, 0);
         	        grid.add(orderstatuts, 1, 0);
         	        
         	        dialog.getDialogPane().setContent(grid);

         	        // Valider la modification lorsque le bouton "Valider" est cliqu�
         	        dialog.setResultConverter(dialogButton -> {
         	            if (dialogButton == validerButtonType) {
         	              selectedOrder.setOrderStatus(orderstatuts.getText());
         	            	return selectedOrder;
         	            }
         	            return null;
         	        });

         	        // Afficher la bo�te de dialogue et attendre la r�ponse de l'utilisateur
         	        Optional<Order> result1 = dialog.showAndWait();

         	        // Si l'utilisateur a cliqu� sur "Valider", mettre � jour la commande dans le TableView
         	        result1.ifPresent(modifiedCommande -> {
         	            int index = TableView.getSelectionModel().getSelectedIndex();
         	            TableView.getItems().set(index, modifiedCommande); // Update the Commande object in the TableView
         	            TableView.refresh(); // Refresh the TableView to show the updated Commande object                        	            // Appeler le service de mise � jour de commande pour enregistrer les modifications
         	           
         	            try {
         	            	
								a.UpdateStatus(selectedOrder);
								// Affichez une alerte de succès
						        Alert success = new Alert(AlertType.INFORMATION);
						        success.setTitle("Succès");
						        success.setHeaderText("Statut de la commande mis à jour !");
						        success.setContentText("Le statut de la commande a été mis à jour dans la base de données.");
						        success.showAndWait();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
         	        });
     	    	}
        		    }
        } catch (Exception e) {
	        e.printStackTrace();
	    }
        
    
    }
    }
 
 
