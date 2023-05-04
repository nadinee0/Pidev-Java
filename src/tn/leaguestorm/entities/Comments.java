/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Comments {
    int id,rate;
    String user,content,email;
    Date created_at;

    public Comments() {
    }

    public Comments(int id, int rate, String user, String content, String email, Date created_at) {
        this.id = id;
        this.rate = rate;
        this.user = user;
        this.content = content;
        this.email = email;
        this.created_at = created_at;
    }

    public Comments(int rate, String user, String content, String email, Date created_at) {
        this.rate = rate;
        this.user = user;
        this.content = content;
        this.email = email;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
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
        final Comments other = (Comments) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
