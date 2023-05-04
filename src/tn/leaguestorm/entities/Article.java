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
public class Article {

    private int id, stock, idcatg, idsubcatg;
    private String titre, image, description, type,nomcatg, nomsubcatg,color;
    private float prix;
    
    // relation category ,subcatg ratings locatioin  
    private Category category;

  
    private SubCategory subcategory;
    private List<User> users;
    private List<Rating> rating;

    public Article() {
    }

    public Article(String titre, String image, float prix, String description, int stock, String type) {
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public Article(int id, String titre, String image, float prix, String description, int stock) {
        this.id = id;
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

   

    public Article(String title, float price, String description, int stock) {
        this.stock = stock;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.type = type;
    }

    public Article(String title, float price, String description, int stock,  int categoryId, int subcategoryId) {
        this.stock = stock;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.idcatg = categoryId;
        this.idsubcatg = subcategoryId;
    }

    public Article(int articleId, String articleTitle, String articleImage, double articlePrice, String articleDescription, int articleStock, Category category, SubCategory subCategory) {
  this.stock = stock;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.category = category;
        this.subcategory = subCategory;
    }
 public Article(int id, String titre, String image, float prix, String description,  int stock, Category category) {
        this.id = id;
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.category = category;
        this.subcategory = subcategory;
    }

    public Article(int id , String titre, String image, float prix, String description, int stock, int idcatg) {
        this.id = id;
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.idcatg = idcatg;
        this.idsubcatg = idsubcatg;    
    }

  /*public Article(int id, String title, String image, float price, String description, int stock, Category category) {
       this.id = id;
    this.titre = title;
    this.image = image;
    this.prix = price;
    this.description = description;
    this.stock = stock;
    this.category = category;
    this.type = type;
    this.subcategory = subCategory;}
*/

  /*public Article( String titre, float prix, String description, int stock, int idcatg, int idsubcatg) {
        this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.idcatg = idcatg;
        this.idsubcatg = idsubcatg;    
    }
*/
    public Article(String title, String category) {
        this.titre = title;
        this.nomcatg=category;
    }

    public Article(String title, String image, float price, String description, int stock, Category category) {
   this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.category = category;
     
    }

    public Article(int id, String title, String image, float prix, String description, int stock, String category) {
this.stock = stock;
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.nomcatg = category;
       
    }

    public Article(String title, String image, float prix) {
      this.titre = titre;
        this.image = image;
        this.prix = prix;
             //   this.id = id;

    }

    public Article(int id, String titre, String image, float prix, String description/*, String category*/) {
     this.id = id;

        this.titre = titre;
        this.image = image;
        this.description = description;
        this.type = type;
        this.prix = prix;
      //  this.nomcatg = category;  
    }
    public Article(String titre, String image, float prix, String description, int stock) {
     this.id = id;

        this.titre = titre;
        this.image = image;
        this.description = description;    
        this.prix = prix;
     this.stock = stock;  
    }

    public String getNomcatg() {
        return nomcatg;
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

      public String getColor() {
        return color;
    }
      
    public Category getCategory() {
        return category;
    }


  public List<User> getUser() {
        return users;
    }

    public void setUser(List<User> user) {
        this.users = user;
    }
    
    
    public List<Rating> getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;

    }
public void setColor(String color) {
        this.color = color;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    
    

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

     public void addUser(User user) {
        users.add(user);
        user.addArticle(this);
    }
    
    public void removeUser(User user) {
        users.remove(user);
        user.removeArticle(this);
    }
    
    /*  public void setUser(List<User> user) {
        this.user = user;
    }*/
 /*@Override
    public String toString() {
        return "Article{" + "stock=" + stock + ", titre=" + titre + ", image=" + image + ", description=" + description + ", prix=" + prix +  ", type=" + type + '}';
    }
     */
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", stock=" + stock + ", titre=" + titre + ", image=" + image + ", description=" + description + ", type=" + type + ", prix=" + prix + ", category=" + category + ", subcategory=" + subcategory + '}';
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

    public void getPrix(float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addComment(String comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRating(double newRating) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
