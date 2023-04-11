/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.tests;

import java.sql.SQLException;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.entities.SubCategory;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.services.ServiceCategory;
import tn.leaguestorm.services.ServiceSubcategory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import tn.leaguestorm.entities.Location;
import tn.leaguestorm.services.ServiceLocation;

/**
 *
 * @author Nadine
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    try {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     java.util.Date dateD = null;
        java.util.Date dateF = null;
        java.util.Date dateR = null;
        
        try {
            dateD = dateFormat.parse("2022-04-09");
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
            return;
        }
        Date dateDebut = new java.sql.Date(dateD.getTime());
        
          try {
            dateF = dateFormat.parse("2022-05-10");
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
            return;
        }
        Date dateFin = new java.sql.Date(dateF.getTime());
        
        
          try {
            dateR = dateFormat.parse("2022-05-15");
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
            return;
        }
        Date dateRetour = new java.sql.Date(dateR.getTime());
        
        
       Location l = new Location(dateD, dateR, "Not Returned", 100,dateRetour);


            ServiceCategory sc = new ServiceCategory();
            ServiceSubcategory ss = new ServiceSubcategory();
            ServiceArticle sa = new ServiceArticle();
            ServiceLocation sl = new ServiceLocation();

            //-----Categ----
           Category c1 = new Category("hkkl", "ghfgfh");
           Category c2 = new Category(97,"hammer", "llllllooooo");

         //  sc.ajouter2(c2);
          //sc.deleteCategory(c2);
            //System.out.println(sc.getAll());
            sc.modifier(c2);
            // sc.supprimer(32);

           
            //-----SubCateg---- 
            
            SubCategory subc1 = new SubCategory("ghfgfh");

          //  ss.ajouter(subc1);
                        System.out.println(ss.getAllCategoryNames());

            //----------Article----------
            
          //  Article a1 = new Article(174,"pcccc", "hthfffff",5.5f,"ggggg",10,"For Rent");
         Article a2 = new Article("pc", "hthf",5.5f,"ggg",10,"For Rent");

            //sa.ajouter(a1);
           // sa.ajouter2(a2);
            
            //System.out.println(sa.getAll());
            //sa.modifier(a1);
            
           // sa.supprimer(175);
           
           ///---------location----
           //sl.ajouter2(l);
                    //   System.out.println(sl.getAll());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
}
    

