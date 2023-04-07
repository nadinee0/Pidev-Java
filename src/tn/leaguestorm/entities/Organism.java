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
public class Organism {
    private String nom_commercial, nom_juridique, email_organisation, image, more;
    private int id, phone_organisation;

    public Organism(int id, String nom_commercial, String nom_juridique, int phone_organisation, String email_organisation, String image, String more ) {
        this.nom_commercial = nom_commercial;
        this.nom_juridique = nom_juridique;
        this.email_organisation = email_organisation;
        this.image = image;
        this.more = more;
        this.id = id;
        this.phone_organisation = phone_organisation;
    }
    
    public Organism(String nom_commercial, String nom_juridique, int phone_organisation, String email_organisation, String image, String more) {
        this.nom_commercial = nom_commercial;
        this.nom_juridique = nom_juridique;
        this.email_organisation = email_organisation;
        this.image = image;
        this.more = more;
        this.phone_organisation = phone_organisation;
    }
    
    public Organism() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone_organisation() {
        return phone_organisation;
    }

    public void setPhone_organisation(int phone_organisation) {
        this.phone_organisation = phone_organisation;
    }

    @Override
    public String toString() {
        return "Organism{" + "nom_commercial=" + nom_commercial + ", nom_juridique=" + nom_juridique + ", email_organisation=" + email_organisation + ", image=" + image + ", more=" + more + ", phone_organisation=" + phone_organisation + '}';
    }
      
}
