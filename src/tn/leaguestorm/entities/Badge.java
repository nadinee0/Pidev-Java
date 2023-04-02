/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.util.Date;

/**
 *
 * @author Bellalouna Iheb
 */
public class Badge {
    private int id, valeur;
    private String logo, badgeFileName, description;
    private Date date;   

    public Badge(int valeur, String logo, String badgeFileName, String description) {
        this.valeur = valeur;
        this.logo = logo;
        this.badgeFileName = badgeFileName;
        this.description = description;
    }

    public Badge(int valeur, String logo, String badgeFileName, String description, Date date) {
        this.valeur = valeur;
        this.logo = logo;
        this.badgeFileName = badgeFileName;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBadgeFileName() {
        return badgeFileName;
    }

    public void setBadgeFileName(String badgeFileName) {
        this.badgeFileName = badgeFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Badge{" + "valeur=" + valeur + ", logo=" + logo + ", badgeFileName=" + badgeFileName + ", description=" + description + '}';
    }
    
}
