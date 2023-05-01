/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;
<<<<<<< HEAD

import java.util.List;
=======
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b

/**
 *
 * @author Nadine
 */
public class SubCategory {

    private int id, idcatg;
    private String nomSubCategory, nomcatg;
    //relation category article
    private List<Article> articles;
    private Category category;

    public SubCategory() {
    }

    public SubCategory(String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
    }

    public SubCategory(int id, String nomSubCategory) {
        this.id = id;
        this.nomSubCategory = nomSubCategory;
    }

    public SubCategory(Category category, String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
        this.articles = articles;
        this.category = category;
    }

    public SubCategory(int id, Category category, String nomSubCategory) {
        this.id = id;
        this.nomSubCategory = nomSubCategory;
        this.articles = articles;
        this.category = category;
    }

    public SubCategory(int id, String nomSubCategory, Category category) {
        this.id = id;
        this.nomSubCategory = nomSubCategory;
        this.category = category;
    }

    public SubCategory(int id, String nomSubCategory, int idcatg) {
        this.id = id;
        this.nomSubCategory = nomSubCategory;
        this.idcatg = idcatg;
    }

    public SubCategory(String category, String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
        this.nomcatg = category;
    }

    public SubCategory(int subCategoryId) {
        this.id = subCategoryId;
    }

    public int getId() {
        return id;
    }

    public String getNomSubCategory() {
        return nomSubCategory;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Category getCategory() {
        return category;
    }

    public int getCategoryId() {
        return category == null ? 0 : category.getId();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomSubCategory(String nomSubCategory) {
        this.nomSubCategory = nomSubCategory;
    }

    /*    @Override
    public String toString() {
        return "SubCategory{" + " nomSubCategory=" + nomSubCategory + '}';
    }
     */
    @Override
    public String toString() {
        return "SubCategory{" + "id=" + id + ", nomSubCategory=" + nomSubCategory + ", category=" + category + '}';
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
