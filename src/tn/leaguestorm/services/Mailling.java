/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

/**
 *
 * @author khair
 */
/*import tn.leaguestorm.entities.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.leaguestorm.entities.Order;
 
public class Mailling {
private String username = "malek.bensaid@esprit.tn";
private String password = "malek";
    private static int id;
    public static int getIdd(int id) {
        id= id;
        return id;
    }

Order O = new Order();
public void envoyer(Order o) {
    OrderService os = new OrderService();
             Order O = os.getOrderItem(id);

// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","25");

Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("malek.bensaid@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("malek.bensaid@esprit.tn"));
message.setSubject("Techstorm");

            DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            System.out.println(Date.format(now));
            String htmlCode ="<h1>Confirmation Commande</h1> <br/><h2>Bonjour Mr/Mme  "+o.getUser().getFirstName()+" "+o.getUser().getLastName()+"<br/>Votre Commande a été bien éditée le :"+Date.format(now)+" dont le montant est:   TND<br/> Merci de choisir notre services </h2>";
            message.setContent(htmlCode,"text/html");
            


// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} 
}
}*/
