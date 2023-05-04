/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;
<<<<<<< HEAD

import java.util.List;
import javafx.scene.image.Image;
=======
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b

/**
 *
 * @author Nadine
 */
public class Category {
    
     private int id;
    private String nom, img;
    private List<Article> articles;
    private List<SubCategory> subcategory;


    public Category() {
    }

    public Category(String nom, String img) {
        this.nom = nom;
        this.img = img;
    }

      public Category(int id, String nom) {
        this.nom = nom;
        this.id = id;
    }
      
    public Category(int id, String nom, String img) {
        this.id = id;
        this.nom = nom;
        this.img = img;
    }

    public Category(int categoryId) {
        this.id = categoryId;
    }



    public Category(String categoryName) {
        this.nom = categoryName;
    }

    public Category(int id, String name, Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public List<Article> getArticles() {
        return articles;
    }

    public List<SubCategory> getSubcategory() {
        return subcategory;
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

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setSubcategory(List<SubCategory> subcategory) {
        this.subcategory = subcategory;
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
