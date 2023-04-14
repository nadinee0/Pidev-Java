/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author dell
 */
public class Team {
    private String nom_team, description_team, color, logo_team;
    private int id, wins_team, losses_team, organisme_id;
    private float rate_team;
    private Date date_de_creation_team;
    
   
public Team() {
}public Team(String nom_team, String description_team, int wins_team, int losses_team, float rate_team, Date date_de_creation_team,String color, String logo_team) {

    this.nom_team = nom_team;
    this.description_team = description_team;
    this.color = color;
    this.logo_team = logo_team;
    this.wins_team = wins_team;
    this.losses_team = losses_team;
    this.rate_team = rate_team;
    this.date_de_creation_team = date_de_creation_team;
    
}

public Team(int id, String nom_team, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team, Date date_de_creation_team, int organism_id) {
    this.id = id;
    this.nom_team = nom_team;
    this.description_team = description_team;
    this.color = color;
    this.logo_team = logo_team;
    this.wins_team = wins_team;
    this.losses_team = losses_team;
    this.rate_team = rate_team;
    this.date_de_creation_team = date_de_creation_team;
    this.organisme_id = organism_id;
}



public Team(String nom_team, int organism_id, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team, Date date_de_creation_team) {
    this.nom_team = nom_team;
    this.description_team = description_team;
    this.color = color;
    this.logo_team = logo_team;
    this.wins_team = wins_team;
    this.losses_team = losses_team;
    this.rate_team = rate_team;
    this.date_de_creation_team = date_de_creation_team;
    this.organisme_id = organism_id;
}
public Team(int id,String nom_team, int organism_id, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team, Date date_de_creation_team) {
    this.id = id;
    this.nom_team = nom_team;
    this.description_team = description_team;
    this.color = color;
    this.logo_team = logo_team;
    this.wins_team = wins_team;
    this.losses_team = losses_team;
    this.rate_team = rate_team;
    this.date_de_creation_team = date_de_creation_team;
    this.organisme_id = organism_id;
}

    

    
  public Team( String nom_team, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team, Date date_de_creation_team) {
        this.id = id;
        this.nom_team = nom_team;
        this.description_team = description_team;
        this.color = color;
        this.logo_team = logo_team;
        this.wins_team = wins_team;
        this.losses_team = losses_team;
        this.rate_team = rate_team;
        this.date_de_creation_team = date_de_creation_team;
        this.organisme_id = organisme_id;
        
        
    }

    public Team(String nom, String description, int wins, int losses, double rate, LocalDate date, String logo, String color) {
        this.nom_team = nom_team;
        this.description_team = description_team;
        this.color = color;
        this.logo_team = logo_team;
        this.wins_team = wins_team;
        this.losses_team = losses_team;
        this.rate_team = rate_team;
        this.date_de_creation_team = date_de_creation_team;
    }



    


    public String getNom_team() {
        return nom_team;
    }

    public void setNom_team(String nom_team) {
        this.nom_team = nom_team;
    }

    public String getDescription_team() {
        return description_team;
    }

    public void setDescription_team(String description_team) {
        this.description_team = description_team;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo_team() {
        return logo_team;
    }

    public void setLogo_team(String logo_team) {
        this.logo_team = logo_team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWins_team() {
        return wins_team;
    }

    public void setWins_team(int wins_team) {
        this.wins_team = wins_team;
    }

    public int getLosses_team() {
        return losses_team;
    }

    public void setLosses_team(int losses_team) {
        this.losses_team = losses_team;
    }

    public int getOrganisme_id() {
        return organisme_id;
    }

    public void setOrganisme_id(int organisme_id) {
        this.organisme_id = organisme_id;
    }

    public float getRate_team() {
        return rate_team;
    }

    public void setRate_team(float rate_team) {
        this.rate_team = rate_team;
    }

    public Date getDate_de_creation_team() {
        return date_de_creation_team;
    }

    public void setDate_de_creation_team(Date date_de_creation_team) {
        this.date_de_creation_team = date_de_creation_team;
    }

  

 

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nom_team);
        hash = 59 * hash + Objects.hashCode(this.color);
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
        final Team other = (Team) obj;
        if (!Objects.equals(this.nom_team, other.nom_team)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" + "nom_team=" + nom_team + ", description_team=" + description_team + ", color=" + color + ", logo_team=" + logo_team + ", id=" + id + ", wins_team=" + wins_team + ", losses_team=" + losses_team + ", rate_team=" + rate_team + ", date_de_creation_team=" + date_de_creation_team +", organisme_id=" + organisme_id +'}';
    }

    

    


}
    
 
