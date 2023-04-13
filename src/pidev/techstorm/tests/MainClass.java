/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.tests;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import pidev.techstorm.entities.Events;
import pidev.techstorm.entities.Tickets;
import pidev.techstorm.services.ServiceEvents;
import pidev.techstorm.services.ServicesTickets;
import pidev.techstorm.utils.DataSource;

/**
 *
 * @author USER
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
       /*try {
            ServiceEvents sp = new ServiceEvents();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = (Date) dateFormat.parse("10/2/2022");
            Date endDate = (Date) dateFormat.parse("15/3/2022");
            Events p1 = new Events(5, "VALO", "hhhhhh", startDate, endDate, "hhhhhhhh", "yyyyyyyy", "tttttttttt");  
            //Personne p2 = new Personne("Fakhreddine", "Ghalleb");
            
            sp.ajouter(p1);
            //sp.ajouter2(p2);
            
            //System.out.println(sp.getAll());
            //sp.modifier(p1);
            
            //sp.supprimer(7);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
       
       
       try {
            ServicesTickets sp = new ServicesTickets();
            
            //Tickets p1 = new Tickets(31,1100, 50,"hhhhh","vip");
            Tickets p2 = new Tickets(34, 36500,58,"jikih","standard");
            
            //sp.ajouter(p1);
            sp.ajouter2(p2);
            System.out.println("ajout avec succés !");
            
            
            //System.out.println(sp.getAll());
            //sp.modifier(p1);
            
            //sp.supprimer(7);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
}
