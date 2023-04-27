/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

/**
 *
 * @author Nadine
 */
public class Category {
    
    private int id;
    private String nom, img;

    public Category() {
    }

    public Category(String nom, String img) {
        this.nom = nom;
        this.img = img;
    }

    public Category(int id, String nom, String img) {
        this.id = id;
        this.nom = nom;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImg(String img) {
        this.img = img;
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
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "nom=" + nom + ", img=" + img + '}';
    }
    
    
}
