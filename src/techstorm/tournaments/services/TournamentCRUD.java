/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import techstorm.tournaments.entities.Round;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.utils.MyConnection;

/**
 *
 * @author qiyanu
 */
public class TournamentCRUD {
    Connection cnx;

    public TournamentCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void addTournament(Tournament T){
    try {
        T.setTid(Tournament.getTidCounter());
        String query = " INSERT INTO tournament (Tid, name, startDate, participantsNumber, status)"
                +"VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst= cnx.prepareStatement(query);
        pst.setInt(1, T.getTid());
        pst.setString(2, T.getName());
        pst.setString(3, T.getStartDate());
        pst.setInt(4, T.getParticipantsNumber());
        pst.setString(5, T.getStatus());
        pst.executeUpdate();
        System.out.println("Tournament added!");
        Tournament.setTidCounter(Tournament.getTidCounter()+1);
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    int numRounds = (int) Math.ceil(Math.log(T.getParticipantsNumber()) / Math.log(2));
    TournamentCRUD tcd = new TournamentCRUD();
        for (int i = 1; i <= numRounds; i++) {
        try {
            Round round = new Round(i);
            String query = " INSERT INTO round (numRound, tournamentId)"
                    +"VALUES (?, ?)";
            PreparedStatement pst= cnx.prepareStatement(query);
            pst.setInt(1, i);
            pst.setInt(2, tcd.getTournament(T.getTid()).getId());
            pst.executeUpdate();
            
            System.out.println("round "+i+" added!");
        } catch (SQLException ex) {
            Logger.getLogger(TournamentCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    public Tournament getTournament(int Tid) {
        Tournament T = null;
        try {
            String query = "SELECT * FROM tournament WHERE Tid = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, Tid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                T = new Tournament(
                        rs.getInt("id"),
                        rs.getInt("Tid"),
                        rs.getString("name"),
                        rs.getString("startDate"),
                        rs.getInt("participantsNumber"),
                        rs.getString("status")
                );                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return T;
    }

    public void updateTournament(Tournament T)  {
        try {
            String query = "UPDATE tournament SET name = ?, startDate = ?, participantsNumber = ?, status = ? WHERE Tid = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, T.getName());
            pst.setString(2, T.getStartDate());
            pst.setInt(3, T.getParticipantsNumber());
            pst.setString(4, T.getStatus());
            pst.setInt(5, T.getTid());
            pst.executeUpdate();
            System.out.println("Tournament updated!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteTournament(int Tid) {
        try {
            String query = "DELETE FROM tournament WHERE Tid = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, Tid);
            pst.executeUpdate();
            System.out.println("Tournament deleted!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void displayAllTournaments() {
    try {
        String query = "SELECT * FROM tournament";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Tournament T = new Tournament(
                    rs.getInt("id"),
                    rs.getInt("Tid"),
                    rs.getString("name"),
                    rs.getString("startDate"),
                    rs.getInt("participantsNumber"),
                    rs.getString("status")
            );
            System.out.println(T);
            }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
    }
    
    
    public List<Tournament> getAllTournaments() {
    List<Tournament> tournaments = new ArrayList<>();
    try {
        String query = "SELECT * FROM tournament";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Tournament T = new Tournament(
                    rs.getInt("id"),
                    rs.getInt("Tid"),
                    rs.getString("name"),
                    rs.getString("startDate"),
                    rs.getInt("participantsNumber"),
                    rs.getString("status")
            );
            tournaments.add(T);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return tournaments;
}

    
    


}
