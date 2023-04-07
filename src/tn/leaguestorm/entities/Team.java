/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

/**
 *
 * @author dell
 */
public class Team {
    private String nom_team, description_team, color, logo_team;
    private int id, wins_team, losses_team;

    public Team( int id,String nom_team, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team) {
        this.nom_team = nom_team;
        this.description_team = description_team;
        this.color = color;
        this.logo_team = logo_team;
        this.id = id;
        this.wins_team = wins_team;
        this.losses_team = losses_team;
        this.rate_team = rate_team;
    }
    private float rate_team;

    public Team(String nom_team, String description_team, int wins_team, int losses_team, float rate_team, String color, String logo_team) {
        this.nom_team = nom_team;
        this.description_team = description_team;
        this.color = color;
        this.logo_team = logo_team;
        this.wins_team = wins_team;
        this.losses_team = losses_team;
        this.rate_team = rate_team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team() {
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

    public float getRate_team() {
        return rate_team;
    }

    public void setRate_team(float rate_team) {
        this.rate_team = rate_team;
    }

    @Override
    public String toString() {
        return "Team{" + "nom_team=" + nom_team + ", description_team=" + description_team + ", color=" + color + ", logo_team=" + logo_team + ", wins_team=" + wins_team + ", losses_team=" + losses_team + ", rate_team=" + rate_team + '}';
    }
    
}
