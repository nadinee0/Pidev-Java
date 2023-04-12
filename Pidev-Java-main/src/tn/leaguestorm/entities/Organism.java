/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author dell
 */
public class Organism {
    private int id, phone_organisation;
    private String nom_commercial, nom_juridique, email_organisation, image, more;
    
    private Date Date_de_fondation;
    private List<Team> teams;

    public Organism() {
        teams = new ArrayList<>();
    }
 
    public Organism(int id, String nom_commercial, String nom_juridique, Date Date_de_fondation, int phone_organisation, String email_organisation, String image, String more) {
        this.id=id;
        this.nom_commercial = nom_commercial;
        this.nom_juridique = nom_juridique;
        this.email_organisation = email_organisation;
      
        this.phone_organisation = phone_organisation;
        this.Date_de_fondation = Date_de_fondation;
          this.image = image;
        this.more = more;
        teams = new ArrayList<>();
    }
    
    public Organism(String nom_commercial, String nom_juridique, Date Date_de_fondation, int phone_organisation, String email_organisation, String image, String more) {
        this.nom_commercial = nom_commercial;
        this.nom_juridique = nom_juridique;
        this.email_organisation = email_organisation;
      
        this.phone_organisation = phone_organisation;
        this.Date_de_fondation = Date_de_fondation;
          this.image = image;
        this.more = more;
        teams = new ArrayList<>();
    }
    


    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public void addTeam(Team team) {
        teams.add(team);
    }
    
    public void removeTeam(Team team) {
        teams.remove(team);
    }
    
    public void removeAllTeams() {
        teams.clear();
    }



        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom_commercial() {
        return nom_commercial;
    }

    public void setNom_commercial(String nom_commercial) {
        this.nom_commercial = nom_commercial;
    }

    public String getNom_juridique() {
        return nom_juridique;
    }

    public void setNom_juridique(String nom_juridique) {
        this.nom_juridique = nom_juridique;
    }
       public Date getDate_de_fondation() {
        return Date_de_fondation;
    }

    public void setDate_de_fondation(Date Date_de_fondation) {
        this.Date_de_fondation = Date_de_fondation;
    }
       public int getPhone_organisation() {
        return phone_organisation;
    }

    public void setPhone_organisation(int phone_organisation) {
        this.phone_organisation = phone_organisation;
    }
    

    public String getEmail_organisation() {
        return email_organisation;
    }

    public void setEmail_organisation(String email_organisation) {
        this.email_organisation = email_organisation;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    
    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.nom_commercial);
        hash = 47 * hash + Objects.hashCode(this.nom_juridique);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organism other = (Organism) obj;
        if (!Objects.equals(this.nom_commercial, other.nom_commercial)) {
            return false;
        }
        if (!Objects.equals(this.nom_juridique, other.nom_juridique)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organism{" + "id=" + id + ", phone_organisation=" + phone_organisation + ", nom_commercial=" + nom_commercial + ", nom_juridique=" + nom_juridique + ", email_organisation=" + email_organisation + ", image=" + image + ", more=" + more + ", Date_de_fondation=" + Date_de_fondation + ", teams=" + teams + '}';
    }






}
 
    

   
   

 
 


