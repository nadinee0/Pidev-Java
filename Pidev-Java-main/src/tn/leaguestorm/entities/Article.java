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
public class Article {
  
    private int id, stock;
    private String titre, image, description,type;
    private float prix;
   // relation category ,subcatg ratings locatioin ratings users 

    public Article() {
    }

    public Article( String titre, String image, float prix ,String description, int stock,String type) {
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public Article(int id, String titre, String image, float prix, String description, int stock, String type) {
        this.id = id; 
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }
    
    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
     public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Article{" + "stock=" + stock + ", titre=" + titre + ", image=" + image + ", description=" + description + ", prix=" + prix +  ", type=" + type + '}';
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
        final Article other = (Article) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
