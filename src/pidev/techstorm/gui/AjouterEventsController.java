/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev.techstorm.entities.Events;
import pidev.techstorm.services.ServiceEvents;
import org.controlsfx.control.textfield.TextFields;
import pidev.techstorm.entities.Tickets;
import pidev.techstorm.services.ServicesTickets;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterEventsController implements Initializable {
    private Stage stage;
     private Scene scene;
     private Parent root;

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfTITRE;
    @FXML
    private TextField tfDESC;
    @FXML
    private TextField tfIMAGE;
    @FXML
    private TextField tfLOCATION;
    @FXML
    private TextField tfURL;
    @FXML
    private TableView<Events> tableajout;
    @FXML
    private TableColumn<Events, Integer> col_id;
    @FXML
    private TableColumn<Events, String> col_titre;
    @FXML
    private TableColumn<Events, String> col_desc;
    @FXML
    private TableColumn<Events, String> col_img;
    @FXML
    private TableColumn<Events, String> col_loc;
    @FXML
    private TableColumn<Events, String> col_url;
        private ObservableList<Events> dataList1 = FXCollections.observableArrayList();
    @FXML
    private TextField tfID1;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfType;
    @FXML
    private TableView<Tickets> tableTickets;
    @FXML
    private TableColumn<Tickets, Integer> col_id1;
    @FXML
    private TableColumn<Tickets, Integer> col_quantite;
    @FXML
    private TableColumn<Tickets, Integer> col_prix;
    @FXML
    private TableColumn<Tickets,String > col_description;
    @FXML
    private TableColumn<Tickets, String> col_type;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tableajout.setRowFactory(tv -> new TableRow<Events>() {
    protected void updateItem(Events item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("-fx-background-color: transparent;");
        } else {
            setStyle("-fx-background-color: transparent;");
        }
    }
});
         tableTickets.setRowFactory(tv -> new TableRow<Tickets>() {
    protected void updateItem(Tickets item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("-fx-background-color: transparent;");
        } else {
            setStyle("-fx-background-color: transparent;");
        }
    }
});
         
        tableajout.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        col_id.setCellValueFactory(new PropertyValueFactory<Events, Integer>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<Events, String>("title"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Events, String>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<Events, String>("image"));
        col_loc.setCellValueFactory(new PropertyValueFactory<Events, String>("location"));
        col_url.setCellValueFactory(new PropertyValueFactory<Events, String>("url"));


                ObservableList<Events> dataList = FXCollections.observableArrayList();
        ServiceEvents sp = new ServiceEvents();
        try {
            sp.getAll().forEach(P->{dataList.add(P);});
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableajout.setItems(dataList);  
        // TODO
        //tickets
        tableTickets.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        col_id1.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("id"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Tickets, String>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<Tickets, String>("type"));
         ObservableList<Tickets> dataList2 = FXCollections.observableArrayList();
        ServicesTickets rp = new ServicesTickets();
        try {
            rp.getAll().forEach(P->{dataList2.add(P);});
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableTickets.setItems(dataList2);  
        
    }    
     


    @FXML
    private void select2(MouseEvent event) {
        
        Events E = tableajout.getSelectionModel().getSelectedItem();
   tfID.setText("" +E.getId());
   tfTITRE.setText("" +E.getTitle());
   tfDESC.setText("" +E.getDescription());
   tfIMAGE.setText("" +E.getImage());
   tfLOCATION.setText("" +E.getLocation());
   tfURL.setText("" +E.getUrl());
    
    }

    @FXML
    private void AfficheEvents(MouseEvent event) throws SQLException {
       ObservableList<Events> dataList1 = FXCollections.observableArrayList();
        ServiceEvents sp = new ServiceEvents();
         col_id.setCellValueFactory(new PropertyValueFactory<Events, Integer>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<Events, String>("title"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Events, String>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<Events, String>("image"));
        col_loc.setCellValueFactory(new PropertyValueFactory<Events, String>("location"));
        col_url.setCellValueFactory(new PropertyValueFactory<Events, String>("url"));
        tableajout.setItems(getEvents(sp.getAll()));
    }

    @FXML
    private void tfModifier(ActionEvent event) throws SQLException {
        ServiceEvents sp = new ServiceEvents();
        int id = Integer.parseInt(tfID.getText()) ;  
        Events E = new Events(Integer.parseInt(tfID.getText()), tfTITRE.getText(),tfDESC.getText(), tfIMAGE.getText(),tfLOCATION.getText(),tfURL.getText());
        sp.modifier(E);
        clean();
        // actualiser
        ObservableList<Events> dataList1 = FXCollections.observableArrayList();
        col_id.setCellValueFactory(new PropertyValueFactory<Events, Integer>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<Events, String>("title"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Events, String>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<Events, String>("image"));
        col_loc.setCellValueFactory(new PropertyValueFactory<Events, String>("location"));
        col_url.setCellValueFactory(new PropertyValueFactory<Events, String>("url"));
        tableajout.setItems(getEvents(sp.getAll()));
        //...
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evenement modifier avec succès!");
            alert.showAndWait();
    }
     private void clean(){
       tfID.setText(null);
       tfTITRE.setText(null);
       tfDESC.setText(null);
       tfIMAGE.setText(null);
       tfLOCATION.setText(null);
       tfURL.setText(null);
       
    }
     public ObservableList<Events> getEvents(List<Events> l){
       ObservableList<Events> dataList1 = FXCollections.observableArrayList();
       for (int i =0; i<=l.size()-1; i++){
          System.out.println("xxx:"+l.get(i));
          dataList1.add(l.get(i));
       }
       return dataList1;
    }
    @FXML
    private void tfSupprimer(ActionEvent event) throws SQLException {
        ServiceEvents sp = new ServiceEvents();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de bien vouloir supprimer cet élément?");
        Optional<ButtonType> action = alert.showAndWait();
        int id= Integer.parseInt(tfID.getText());
        sp.supprimer(id);
        clean();
        // actualiser
        ObservableList<Events> dataList1 = FXCollections.observableArrayList();
         col_id.setCellValueFactory(new PropertyValueFactory<Events, Integer>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<Events, String>("title"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Events, String>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<Events, String>("image"));
        col_loc.setCellValueFactory(new PropertyValueFactory<Events, String>("location"));
        col_url.setCellValueFactory(new PropertyValueFactory<Events, String>("url"));
        tableajout.setItems(getEvents(sp.getAll()));
        //...
        System.out.print("Supprimée avec succées ");
    }
    
    
    //Tickets

   
    
     private void clean2(){
       tfID1.setText(null);
       tfQuantite.setText(null);
       tfPrix.setText(null);
       tfDescription.setText(null);
       tfType.setText(null);
       
    }
    public ObservableList<Tickets> getTickets(List<Tickets> l){
       ObservableList<Tickets> dataList1 = FXCollections.observableArrayList();
       for (int i =0; i<=l.size()-1; i++){
          System.out.println("xxx:"+l.get(i));
          dataList1.add(l.get(i));
       }
       return dataList1;
    }

    @FXML
    private void tfModifier1(ActionEvent event) throws SQLException {
        ServicesTickets sp = new ServicesTickets();
        int id = Integer.parseInt(tfID1.getText()) ;    
        Tickets T = new Tickets(Integer.parseInt(tfID1.getText()),Integer.parseInt(tfQuantite.getText()),Integer.parseInt(tfPrix.getText()), tfDescription.getText(),tfType.getText());
        sp.modifier(T);
        clean2();
        // actualiser
        ObservableList<Tickets> dataList1 = FXCollections.observableArrayList();
        col_id1.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("id"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Tickets, String>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<Tickets, String>("type"));
        tableTickets.setItems(getTickets(sp.getAll()));
        //...
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tickets modifiée avec succès!");
            alert.showAndWait();
    }

    @FXML
    private void tfSupprimer1(ActionEvent event) throws SQLException {
        ServicesTickets sp = new ServicesTickets();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de bien vouloir supprimer cet élément?");
        Optional<ButtonType> action = alert.showAndWait();
        int id= Integer.parseInt(tfID1.getText());
        sp.supprimer(id);
        clean();
        // actualiser
        ObservableList<Tickets> dataList1 = FXCollections.observableArrayList();
        col_id1.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("id"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Tickets, String>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<Tickets, String>("type"));
        tableTickets.setItems(getTickets(sp.getAll()));        //...
        System.out.print("Supprimée avec succées ");
    }

    @FXML
    private void AfficheTickets(MouseEvent event) throws SQLException {
        ObservableList<Events> dataList1 = FXCollections.observableArrayList();
        ServicesTickets sp = new ServicesTickets();
        col_id1.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("id"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Tickets, Integer>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Tickets, String>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<Tickets, String>("type"));
        tableTickets.setItems(getTickets(sp.getAll()));
    }

    @FXML
    private void select3(MouseEvent event) {
        Tickets T = tableTickets.getSelectionModel().getSelectedItem();
   tfID1.setText("" +T.getId());
   tfQuantite.setText("" +T.getQuantite());
   tfPrix.setText("" +T.getPrix());
   tfDescription.setText("" +T.getDescription());
   tfType.setText("" +T.getType());
    }

    @FXML
    private void Retour(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AfficherEvents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Retour2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AfficherEvents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
   

  
    
}
