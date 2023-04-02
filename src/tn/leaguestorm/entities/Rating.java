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
public class Rating {
   private int id, stars;
   private String comment;
   private Date created_at;
   //relation article - user 

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
   
}
