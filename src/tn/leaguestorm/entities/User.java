/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDate;
import java.util.Date;
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b

/**
 *
 * @author Bellalouna Iheb
 */
public class User {
<<<<<<< HEAD
    private int id, phoneNumber;
    private String email, roles, password, firstName, lastName, country;
 private List<Article> articles;
   
    public User(int phoneNumber, String email, String roles, String password, String firstName, String lastName, String country) {
=======

    private int id, isVerified;
    private String email, password, firstName, lastName, country, profilePictureName, phoneNumber;
    LocalDate birthDate;
    private boolean banned;

    public User() {
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User(String phoneNumber, String email, String password, String firstName, String lastName, String country) {
>>>>>>> cf43fd36fd744e570acaf47d9c781454e930fc1b
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public User(int id, String email, String password, String firstName, String lastName, String country, String phoneNumber, String profilePictureName, LocalDate birthDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profilePictureName = profilePictureName;
        this.birthDate = birthDate;
    }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    public User(String email, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password, int isVerified, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.isVerified = isVerified;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public User(String email, String password, int verified, String firstName, String lastName, LocalDate birthDate, String country, String profilePictureName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profilePictureName = profilePictureName;
    }

    public User(String email, String password, int verified, String firstName, String lastName, String country, String phoneNumber, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
 public void addArticle(Article article) {
        articles.add(article);
        article.addUser(this);
    }
    
    public void removeArticle(Article article) {
        articles.remove(article);
        article.removeUser(this);
    }
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", phoneNumber=" + phoneNumber + '}';
    }

    public String getRole() {
        if (this instanceof Role) {
            return ((Role) this).getRole();
        } else {
            return null;
        }
    }
}
