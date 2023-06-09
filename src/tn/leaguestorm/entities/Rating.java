/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.util.Date;

/**
 *
 * @author Nadine
 */
public class Rating {
   private int id, stars;
   private String comment;
   private Date created_at;
   //relation article - user 
    private Article article;
    //private User user;


    public Rating() {
    }

    public Rating(int stars, String comment, Date created_at) {
        this.stars = stars;
        this.comment = comment;
        this.created_at = created_at;
    }

    public Rating(int id, int stars, String comment, Date created_at) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.created_at = created_at;
    }

    /*
    public Rating(int stars, String comment, Date created_at, Article article, User user) {
        this.stars = stars;
        this.comment = comment;
        this.created_at = created_at;
        this.article = article;
        this.user = user;
    }

    public Rating(int id, int stars, String comment, Date created_at, Article article, User user) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.created_at = created_at;
        this.article = article;
        this.user = user;
    }
*/
    
    
    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Rating{" + "stars=" + stars + ", comment=" + comment + ", created_at=" + created_at + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Rating other = (Rating) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setCurrentRating(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
}
