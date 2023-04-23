/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import pidev.techstorm.entities.Events;
import pidev.techstorm.entities.Tickets;
import pidev.techstorm.services.ServiceEvents;
import pidev.techstorm.services.ServicesTickets;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherEventsController implements Initializable {
     private Stage stage;
     private Scene scene;
     private Parent root;
     private byte[] fichier;

    private TextField tfID;
    @FXML
    private TextField tfTITRE;
    @FXML
    private TextArea tfDESC;
    @FXML
    private TextField tfURL;
    @FXML
    private TextField tfLOCATION;
    @FXML
    private TextField tfIMAGE;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfType;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ImageView imageView;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] possibleWords2= {"Standard","VIP"};
        TextFields.bindAutoCompletion(tfType, possibleWords2);
         String[] possibleWords= {"Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jandouba","Kairouan","Kasserine","Kébili","Kef","Mahdia","Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan"};
        TextFields.bindAutoCompletion(tfLOCATION, possibleWords);
        // TODO
    } 
    
    @FXML
    private void tfAJOUTER(ActionEvent event) throws SQLException, IOException {
        String titre = tfTITRE.getText();
        String description = tfDESC.getText();
        String image = tfIMAGE.getText();
        String location = tfLOCATION.getText();
        String url = tfURL.getText();
        if(  controleTextField3 (tfTITRE) || controleTextField3 (tfLOCATION) ) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
     
        alert.setContentText("Veuillez remplir les données");
        alert.show();    
       }  else{
        Events e = new Events(titre,description,image,location,url);
        ServiceEvents se= new ServiceEvents();
        se.ajouter(e);
        clean();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Evenement ajouté avec succès!");
        alert.show();
        
        Stage stage = (Stage) tfTITRE.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvents.fxml"));
        Parent root = loader.load();
        AjouterEventsController categoriesController = loader.getController();
      
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
       
}
    }
    private void clean(){
       tfTITRE.setText(null);
       tfDESC.setText(null);
       tfIMAGE.setText(null);
       tfLOCATION.setText(null);
       tfURL.setText(null);
       
    }
    
    public boolean controleTextFieldNumerique2(TextField textField) {
        if (!textField.getText().matches(".*[1-9].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("La Quantite est invalide");
            alert.showAndWait();
            return true;
                    }

             return false;
    }
    public boolean controleTextFieldNumerique3(TextField textField) {
        if (!textField.getText().matches(".*[1-9].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Le prix est invalide");
            alert.showAndWait();
            return true;
                    }

             return false;
    }
     public boolean controleTextField1(TextArea textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(null,"Veuillez saisir des lettres");
            return true;
        }
        return false;
    }
     public boolean controleTextField3(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(null,"Veuillez saisir des lettres");
            return true;
        }
        return false;
    }
     public boolean controleTextField2(TextField textField) {
        if (!tfType.getText().contains("Standard") && !tfType.getText().contains("VIP")) {
            JOptionPane.showMessageDialog(null,"Veuillez saisir dans l'url: Standard ou bien VIP");
            return true;
        }
        return false;
    }

    @FXML
    private void tfAJOUTER1(ActionEvent event) throws SQLException, IOException {
        int quantite =Integer.parseInt(tfQuantite.getText());
        int prix =Integer.parseInt(tfPrix.getText());
        String description = tfDescription.getText();
        String type = tfType.getText();
        if(  controleTextFieldNumerique2(tfQuantite) ||controleTextFieldNumerique3 (tfPrix) || controleTextField1 (tfDescription) || controleTextField2 (tfType) ) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText(null);
     
        alert.setContentText("Veuillez modifier les données en fonction de leur type d'attribut respectif.");
        alert.show();    
       }  else{
        Tickets e = new Tickets(quantite,prix,description,type);
        ServicesTickets se= new ServicesTickets();
        se.ajouter(e);
        clean2();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Ticket ajouté avec succès!");
        alert.show();
        Stage stage = (Stage) tfTITRE.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvents.fxml"));
        Parent root = loader.load();
        AjouterEventsController categoriesController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
    private void clean2(){
       tfQuantite.setText(null);
       tfPrix.setText(null);
       tfDescription.setText(null);
       tfType.setText(null);
       
    }

    @FXML
    private void checkinputQ(KeyEvent event) {
        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        tfQuantite.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
    } else {
        tfQuantite.setStyle("-fx-border-color: green; -fx-border-width: 4px;");
    }
    }

    @FXML
    private void checkinputP(KeyEvent event) {
         if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
        event.consume();
        tfPrix.setStyle("-fx-border-color: red; -fx-border-width: 4px;");
    } else {
        tfPrix.setStyle("-fx-border-color: green; -fx-border-width: 4px;");
    }
    }

    @FXML
    private void Back(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AjouterEvents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Back2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AjouterEvents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void parcourir(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(new Stage());
    if (file != null) {
        tfIMAGE.setText(file.getAbsolutePath());
        try {
            Path path = Paths.get(file.getAbsolutePath());
            fichier = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    
    
}
