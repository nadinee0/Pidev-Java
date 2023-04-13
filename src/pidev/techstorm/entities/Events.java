    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class Events {
    private int id;
    private String title,description,image,location,url;
    
    private List<Tickets> tickets;

    public Events() {
    }

    public Events(String title, String description, String image, String location, String url) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.location = location;
        this.url = url;
    }

    public Events(int id, String title, String description, String image, String location, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.location = location;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Events{" + "title=" + title + ", description=" + description + ", image=" + image + ", location=" + location + ", url=" + url + '}';
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
        final Events other = (Events) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    

    
}
