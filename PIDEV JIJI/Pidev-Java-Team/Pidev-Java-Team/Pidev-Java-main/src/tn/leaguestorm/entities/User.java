/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

/**
 *
 * @author Bellalouna Iheb
 */
public class User {
    private int id, phoneNumber;
    private String email, roles, password, firstName, lastName, country;

    public User(int phoneNumber, String email, String roles, String password, String firstName, String lastName, String country) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
    
    public User(String email, String firstName, String lastName, String country, int phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String roles, String password) {
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", phoneNumber=" + phoneNumber + '}';
    }
  
}

