package tn.leaguestorm.tests;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import tn.leaguestorm.entities.Organism;
import tn.leaguestorm.entities.Team;
import tn.leaguestorm.services.ServiceOrganism;
import tn.leaguestorm.services.ServiceTeam;

public class MainClass {
    public static void main(String[] args) {
        
    
        
    
 
        
      /*-------------Organism Class-----------------------------------------------*/




   /* ---------------Ajout-------------//
       ServiceOrganism o = new ServiceOrganism();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse("2022-04-09");
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
            return;
        }
        Date sqlDate = new java.sql.Date(utilDate.getTime());
       Organism o2 = new Organism( "FCBarcelona .", "Barcelona Corporation", sqlDate, 123457218, "alaa@barcelona.com", "Barcelona.png", "This is SUIIII bluegrana");

        o.ajouter2(o2);}*/
    /*-------------Delete-----------------\\\\
    public static void main(String[] args) {
    ServiceOrganism o = new ServiceOrganism();
    int idToDelete = 23;
    o.supprimer(idToDelete);
    /-----------GetAll--------------------------//
        public static void main(String[] args) {
        ServiceOrganism o = new ServiceOrganism();
        try {
        List<Organism> organisms = o.getAll();
        for (Organism organism : organisms) {
            System.out.println(organism);
        }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }*//*
    /----------------------Update----------///
        ServiceOrganism orgService = new ServiceOrganism();
        Organism updatedOrg = new Organism();
        updatedOrg.setId(24);
        updatedOrg.setNom_commercial("Fanatics Org");
        updatedOrg.setNom_juridique("Sehliano  Org Juridique");
        updatedOrg.setDate_de_fondation(new java.sql.Date(System.currentTimeMillis()));
        updatedOrg.setPhone_organisation(12367890);
        updatedOrg.setEmail_organisation("yoyoy@example.com");
        updatedOrg.setImage("sure .png");
        updatedOrg.setMore("This is the updated organism");
        
        orgService.modifier(updatedOrg);*/


    
/*------------------------------------TEAM CLASS ---------------------------------------------------------------------------------------------*/


    /*     
   
    /*----------------Ajout----------*\\\
        ServiceTeam t = new ServiceTeam();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
        utilDate = dateFormat.parse("2032-04-09");
        } catch (ParseException ex) {
        System.out.println("Invalid date format!");
        return; 
        }
        Date sqlDate = new java.sql.Date(utilDate.getTime());
        Team t2 = new Team("Ac Milan", "Spanish football club", 13, 2, 4.5f, "#214e12", "real-madrid.png", sqlDate);
        t.ajouter(t2);}*/

    /*---------------------Update-------\\    
        ServiceTeam t3 = new ServiceTeam();
        
        
        Team updatedTeam = new Team();
        updatedTeam.setId(10);
        updatedTeam.setNom_team("FC CHELSEA");
        updatedTeam.setDescription_team("The blues never lose");
        updatedTeam.setWins_team(30);
        updatedTeam.setLosses_team(2);
        updatedTeam.setRate_team(0.12f);
        updatedTeam.setColor("#01E000");
        updatedTeam.setLogo_team("logo.png");
        updatedTeam.setDate_de_creation_team(new java.sql.Date(System.currentTimeMillis()));
        
        t3.modifier(updatedTeam);
        }*/
    /* -------DELETE------------------------------------------\\\
        ServiceTeam t = new ServiceTeam();
        int idToDelete = 9;
        t.supprimer(idToDelete);*/

     /*---------------------GetAll---------------\\\
        ServiceTeam t = new ServiceTeam();
        try {
        List<Team> teams = t.getAll();
        for (Team team : teams) {
            System.out.println(team);
        }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }*/


    }}



