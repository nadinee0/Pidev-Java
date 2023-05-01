/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tn.leaguestorm.entities.Article;
import tn.leaguestorm.utils.MyConnection;
import tn.leaguestorm.gui.Chatbot;

import java.sql.*;

import javax.swing.*;

/**
 *
 * @author Nadine
 */
public class Chatbot extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea ca = new JTextArea();
    private JTextField cf = new JTextField();
    private JButton b = new JButton();
    private JLabel l = new JLabel();
    private MyConnection ds = MyConnection.getInstance();

    Chatbot() {
        try {

            JFrame f = new JFrame();
            f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            f.setVisible(true);
            f.setResizable(false);
            f.setLayout(null);
            f.setSize(400, 400);
            f.getContentPane().setBackground(Color.cyan);
            f.setTitle("ChatBot");
            f.add(ca);
            f.add(cf);
            ca.setSize(300, 310);
            ca.setLocation(1, 1);
            ca.setBackground(Color.BLACK);
            cf.setSize(300, 20);
            cf.setLocation(1, 320);
            f.add(b);
            l.setText("SEND");
            b.add(l);
            b.setSize(400, 20);
            b.setLocation(300, 320);

            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (e.getSource() == b) {
                        String text = cf.getText().toLowerCase();
                        ca.setForeground(Color.GREEN);
                        ca.append("You-->" + text + "\n");
                        cf.setText("");

                        if (text.contains("hi") || text.contains("hello") || text.contains("hey")) {
                            replyMeth("Hi there");
                        } else if (text.contains("how are you")) {
                            replyMeth("I'm Good :).Thank you for asking");
                        } else if (text.contains("what is your name")) {
                            replyMeth("I'm the AI STORM" + "\n" + "How can i help you ?");

                        } else if (text.contains("help")) {
                            replyMeth(" Sure tell me !");
                        } else if (text.contains("is " + getArticleTitle1(text) + " currently in stock?")) {
                            if (isInStock(getArticleTitle1(text))) {
                                replyMeth("Yes, it's available");
                            } else {
                                replyMeth("I'm sorry, it's out of stock");
                            }
                        } else if (text.contains("what is the price of " + getArticleTitle1(text) + "?")) {
                            replyMeth("It costs " + getPrix(getArticleTitle1(text)));
                        } else if (text.contains("thank you") || text.contains("thanks")) {
                            replyMeth("You're welcome");
                        } else if (text.contains("bye")) {
                            replyMeth("Goodbye !");
                        } else {
                            replyMeth("I can't understand");
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replyMeth(String s) {
        ca.append("ChatBot-->" + s + "\n");
    }

    private double getPrix(String articleTitle) {
        float prix = 0.0f;
        try {
            String query = "SELECT prix FROM article WHERE titre = ?";
            PreparedStatement statement = ds.getCnx().prepareStatement(query);
            statement.setString(1, articleTitle);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                prix = result.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prix;
    }

    private boolean isInStock(String title) {
        boolean inStock = false;
        try {
            Statement stmt = ds.getCnx().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT stock FROM article WHERE titre='" + title + "'");
            if (rs.next()) {
                inStock = rs.getInt("stock") > 0;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inStock;
    }


   
    private String getArticleTitle1(String text) {
        String title = null;
        try {
            Statement stmt = ds.getCnx().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT titre FROM article WHERE titre LIKE '%" + text + "%'");
            if (rs.next()) {
                title = rs.getString("titre");
            }
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    //Driver Class
    public static void main(String[] args) {             //main class

        new Chatbot();                                  // instantiation 
    }

}
