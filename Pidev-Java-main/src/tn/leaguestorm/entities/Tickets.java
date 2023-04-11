/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

/**
 *
 * @author USER
 */
public class Tickets {
    private int id,quantite;
    private double prix;
    private String description,type;
    
    //private Events events;

    public Tickets() {
    }

    public Tickets(int quantite, double prix, String description, String type) {
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.type = type;
        //this.events = events;
    }

    public Tickets(int id, int quantite, double prix, String description, String type) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.type = type;
        //this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*public Events getEvents() {
        return events;
    }*/

    /*public void setEvents(Events events) {
        this.events = events;
    }*/

    @Override
    public String toString() {
        return "Tickets{" + "quantite=" + quantite + ", prix=" + prix + ", description=" + description + ", type=" + type +'}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Tickets other = (Tickets) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
