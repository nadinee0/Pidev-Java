/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.time.LocalDate;

/**
 *
 * @author Bellalouna Iheb
 */
public class Role extends User {
    private String role;

    public Role(String email, String password, int isVerified, String firstName, String lastName, LocalDate birthDate, String country, String profilePictureName, String phoneNumber, String role) {
        super(email, password, isVerified, firstName, lastName, birthDate, country, profilePictureName, phoneNumber);
        this.role = role;
        //System.out.println("Role: " + role);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Role createUser(String email, String password, int isVerified, String firstName, String lastName, LocalDate birthDate, String country,  String profilePictureName, String phoneNumber) {
        return new Role(email, password, isVerified, firstName, lastName, birthDate, country, profilePictureName, phoneNumber, "[\"ROLE_USER\"]");
    }
    
    public static Role createPlayer(String email, String password, int isVerified, String firstName, String lastName, LocalDate birthDate, String country,String profilePictureName, String phoneNumber) {
        return new Role(email, password, isVerified, firstName, lastName, birthDate, country, profilePictureName, phoneNumber,"[\"ROLE_PLAYER\"]");
    }

    public static Role createOrganization(String email, String password, int isVerified, String firstName, String lastName, LocalDate birthDate, String country, String profilePictureName, String phoneNumber) {
        return new Role(email, password, isVerified, firstName, lastName, birthDate, country, profilePictureName, phoneNumber, "[\"ROLE_ORGANIZATION\"]");
    }

    public static Role createTeam(String email, String password, int isVerified, String firstName, String lastName, LocalDate birthDate, String country, String profilePictureName, String phoneNumber) {
        return new Role(email, password, isVerified, firstName, lastName, birthDate, country, profilePictureName, phoneNumber, "[\"ROLE_TEAM\"]");
    }
}
