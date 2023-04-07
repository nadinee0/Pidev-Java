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
public class SubCategory {
  
    private int id;
    private String nomSubCategory;
    //relation category article

    public SubCategory() {
    }

    public SubCategory(String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
    }

    public SubCategory(int id, String nomSubCategory) {
        this.id = id;
        this.nomSubCategory = nomSubCategory;
    }

    public int getId() {
        return id;
    }

    public String getNomSubCategory() {
        return nomSubCategory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomSubCategory(String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
    }

    @Override
    public String toString() {
        return "SubCategory{" + " nomSubCategory=" + nomSubCategory + '}';
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
        final SubCategory other = (SubCategory) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
