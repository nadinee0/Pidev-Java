/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class MyConnection {
    
    private final String URL="jdbc:mysql://localhost:3306/techStorm_3A39";
    private final String LOGIN="root";
    private final String PWD="";
    
    private Connection cnx;
    private static MyConnection instance;
    
        private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("DB Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
