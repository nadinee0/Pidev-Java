/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.leaguestorm.entities.Order;
import tn.leaguestorm.entities.User;
import tn.leaguestorm.services.OrderService;
import tn.leaguestorm.utils.CurrentUser;

/**
 * FXML Controller class
 *
 * @author khair
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField carte;
    @FXML
    private DatePicker year;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker month;
    @FXML
    private TextField cvc;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;
    User currentUser = CurrentUser.getUser();

Order order=new Order();
	public void setCommande(Order o) {
		 this.order=o;
		 System.out.println("hello");
		 System.out.println("o"+order.toString());
             if (prix != null && this.order != null) {
    prix.setText(String.valueOf(order.getPrice()));
}

		
	}
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if (this.order != null) {
             		 System.out.println("ok"+order.getPrice());

    	        prix.setText(String.valueOf(order.getPrice()));
    	    }
    	 else {System.out.println("null");}
       // prix.setDisable(true);
        // TODO
//                this.prix.setText(String.valueOf(CommandeFrontController.prix));
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    if (controleDeSaisi()) {
               
                        
                        if (carte.getText().isEmpty()) {
                            carte.setText("");
                        }
                       
                        if (cvc.getText().isEmpty()) {
                            cvc.setText("");
                        }
                        
                    }
 

Stripe.apiKey="sk_test_51MgtWsA6z8AeSQcNyUg2JEZ8nReVYmHvBOVbWP9IVrQN3zsNKpAK2ScR4OvcV9b9oUZW1jua2zWkkLwVHUixyrJr00n1J47x1I";

Map<String, Object> cardParams = new HashMap<String, Object>();
cardParams.put("number", Long.parseLong(carte.getText()));
cardParams.put("exp_month",month.getValue().getMonthValue());
cardParams.put("exp_year", year.getValue().getYear());
cardParams.put("cvc",  Integer.parseInt(cvc.getText()));

Map<String, Object> tokenParams = new HashMap<String, Object>();
tokenParams.put("card", cardParams);

Token token = Token.create(tokenParams); //i have to fix this one as the jdk.nashorn.internal.parser.Token cannot be used no more



Map<String, Object> chargeParams = new HashMap<>();
chargeParams.put("amount", (int) Double.parseDouble(prix.getText()));

chargeParams.put("currency", "eur");
chargeParams.put("source", "tok_visa");
 

Map<String, String> initialMetadata = new HashMap<>();
initialMetadata.put("id", "1");
chargeParams.put("metadata", initialMetadata);

Charge.create(chargeParams);
System.out.print("transaction reussie");

String subject = "Staut Payement  ";
String body = "Bonjour,Payement Avec Succees !!!!   .\n\nCordialement,\n l'equipe de support";
sendEmail(currentUser.getEmail(), subject, body);
                } catch (StripeException ex) {
                	showAlert(Alert.AlertType.ERROR, "Donn�es erron�es", "V�rifiez les donn�es", ex.toString());

                }
            }
        });
        
        
       Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                     Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

            }

        });
        
    }
    private void sendEmail(String to, String subject, String body) {
        String username = "malek.bensaid@esprit.tn";
        String password = "byrjnvqrdxfmqeku"
        		 ;
         Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // Change this to your SMTP server host(yahoo...)
            props.put("mail.smtp.port", "587"); // Change this to your SMTP server port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session;
            session = Session.getInstance(props,new Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });
           
           
               try {
                // Create a MimeMessage object
     
    // Create a new message
                MimeMessage message = new MimeMessage(session);
                // Set the From, To, Subject, and Text fields of the message
                message.setFrom(new InternetAddress(username));
                message.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(body);

                // Send the message using Transport.send
                Transport.send(message);

                System.out.println("Email sent successfully");
            } catch (MessagingException ex) {
                System.err.println("Failed to send email: " + ex.getMessage());
            }
               OrderService os = new OrderService();
        try {
            os.UpdatePayement(this.order);
showAlert(Alert.AlertType.CONFIRMATION, "Données Valide", "Success", "Payment avec succes!");
              try {
				    FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderUser.fxml"));
				    Parent root = loader.load();
				   
				    Scene scene = new Scene(root);
				    Stage stage = new Stage();
				    stage.setScene(scene);
				    stage.show();
				} catch (IOException ex) {
				    ex.printStackTrace();
				}
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }  

    
     private boolean controleDeSaisi() {  

         // Check if the year and month are valid
         if (month.getValue() == null || year.getValue().getYear() == 0) {
             // Show an alert
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText(null);
             alert.setContentText("Please enter a valid year and month.");
             alert.showAndWait();
         } 

      // V�rification du num�ro de carte
         if (!Pattern.matches("^\\d{16}$", carte.getText())) {
             showAlert(Alert.AlertType.ERROR, "Donn�es erron�es", "V�rifiez les donn�es", "Veuillez entrer un num�ro de carte valide (16 chiffres) !");
             carte.requestFocus();
             carte.selectEnd();
             return false;
         }

         // V�rification du CVC
         if (!Pattern.matches("^\\d{3,4}$", cvc.getText())) {
             showAlert(Alert.AlertType.ERROR, "Donn�es erron�es", "V�rifiez les donn�es", "Veuillez entrer un code CVC valide (3 ou 4 chiffres) !");
             cvc.requestFocus();
             cvc.selectEnd();
             return false;
         }
           
        
        return true;
         
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }


 
  
    }
 