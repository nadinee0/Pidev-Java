/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.tests;

import java.sql.SQLException;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.entities.Category;
import tn.leaguestorm.services.ServiceArticle;
import tn.leaguestorm.services.ServiceCategory;
import tn.leaguestorm.services.ServiceSubcategory;

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
         
            ServiceCategory sc = new ServiceCategory();
            ServiceSubcategory ss = new ServiceSubcategory();
            ServiceArticle sa = new ServiceArticle();
      
            //-----Categ----
           Category c1 = new Category("catg", "ghfgfh");
           Category c2 = new Category(32,"hammer", "llllllooooo");

            sc.ajouter(c1);
            //System.out.println(sc.getAll());
            //sc.modifier(c2);
            // sc.supprimer(32);

           
            //-----SubCateg---- 
            
            
            
            //----------Article----------
            
          //  Article a1 = new Article(174,"pcccc", "hthfffff",5.5f,"ggggg",10,"For Rent");
        // Article a2 = new Article("pc", "hthf",5.5f,"ggg",10,"For Rent");

            //sp.ajouter(a1);
            //sa.ajouter2(a2);
            
            //System.out.println(sa.getAll());
            //sa.modifier(a1);
            
           // sa.supprimer(175);
           
           
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
}
    

