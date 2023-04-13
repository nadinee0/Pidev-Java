/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

/**
 *
 * @author USER
 */
public class Sponsors {
    private int id,num_tel;
    private String name,email,img,url;

    public Sponsors() {
    }

    public Sponsors(int num_tel, String name, String email, String img, String url) {
        this.num_tel = num_tel;
        this.name = name;
        this.email = email;
        this.img = img;
        this.url = url;
    }

    public Sponsors(int id, int num_tel, String name, String email, String img, String url) {
        this.id = id;
        this.num_tel = num_tel;
        this.name = name;
        this.email = email;
        this.img = img;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        final Sponsors other = (Sponsors) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
