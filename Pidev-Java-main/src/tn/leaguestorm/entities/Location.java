/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

import java.util.Date;

/**
 *
 * @author Nadine
 */
public class Location {
    
    private int id;
    private Date dateD, dateF, date_retour;
    private String statut;
    private float fraisRetard;
    //relation article user 

    public Location() {
    }

    public Location(Date dateD, Date dateF, String statut, float fraisRetard, Date date_retour) {
        this.dateD = dateD;
        this.dateF = dateF;
        this.statut = statut;
        this.date_retour = date_retour;
        this.fraisRetard = fraisRetard;
    }

    public Location(int id, Date dateD, Date dateF, String statut, float fraisRetard, Date date_retour) {
        this.id = id;
        this.dateD = dateD;
        this.dateF = dateF;
        this.statut = statut;
        this.date_retour = date_retour;
        this.fraisRetard = fraisRetard;
    }

    public int getId() {
        return id;
    }

    public Date getDateD() {
        return dateD;
    }

    public Date getDate_retour() {
        return date_retour;
    }
    
    
    public Date getDateF() {
        return dateF;
    }

    public String getStatut() {
        return statut;
    }

    public float getFraisRetard() {
        return fraisRetard;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setFraisRetard(float fraisRetard) {
        this.fraisRetard = fraisRetard;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    
    @Override
    public String toString() {
        return "Location{" +  "dateD=" + dateD + ", dateF=" + dateF + ", statut=" + statut + ", fraisRetard=" + fraisRetard + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Location other = (Location) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
