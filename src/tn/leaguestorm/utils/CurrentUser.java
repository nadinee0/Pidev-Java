/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.utils;

import tn.leaguestorm.entities.User;

/**
 *
 * @author Bellalouna Iheb
 */
public class CurrentUser {
    
    public static String phoneProcedures;
    
    private static User currentUser;
    
    public static void setUser(User u) {
        currentUser = u;
    }
    
    public static User getUser() {
        return currentUser;
    }
    
    public static void endSession() {
        currentUser = null;
    }
    
    
    
    
    
    private static User selectedUser;
    
    public static void setSelecUser(User u) {
        selectedUser = u;
    }
    
    public static User getSelecUser() {
        return selectedUser;
    }
}