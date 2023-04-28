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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import techstorm.tournaments.entities.RegisteredPlayer;
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
    TournamentCRUD tcd = new TournamentCRUD();
    try {
        T.setTid(tcd.getMaxTid()+1);
        String query = " INSERT INTO tournament (Tid, name, startDate, participantsNumber, status, replayid)"
                +"VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst= cnx.prepareStatement(query);
        pst.setInt(1, T.getTid());
        pst.setString(2, T.getName());
        pst.setString(3, T.getStartDate());
        pst.setInt(4, T.getParticipantsNumber());
        pst.setString(5, T.getStatus());
        pst.setString(6, T.getReplayID());
        pst.executeUpdate();
        System.out.println("Tournament added!");
        Tournament.setTidCounter(Tournament.getTidCounter()+1);
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    int numRounds = (int) Math.ceil(Math.log(T.getParticipantsNumber()) / Math.log(2));
    
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
                        rs.getString("status"),
                        rs.getString("replayid")
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
                    rs.getString("status"),
                    rs.getString("replayid")
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
                    rs.getString("status"),
                    rs.getString("replayid")
            );
            tournaments.add(T);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return tournaments;
    }
    
    
    public List<RegisteredPlayer> getRegisteredPlayers(Tournament T) {
        TournamentCRUD tcd = new TournamentCRUD();
        List<RegisteredPlayer> players = new ArrayList<>();
        try {
            String query = "SELECT * FROM registeredplayer WHERE idTournament = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, tcd.getTournament(T.getTid()).getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idp = rs.getInt("idp");
                String username = rs.getString("username");
                boolean eliminated = rs.getBoolean("eliminated");
                RegisteredPlayer player = new RegisteredPlayer(id, idp, username, eliminated);
                players.add(player);
            }
            System.out.println("List of players registered to tournament retrieved!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return players;
    }
    
    public List<RegisteredPlayer> getNonEliminatedPlayers(List<RegisteredPlayer> players) {
    List<RegisteredPlayer> nonEliminatedPlayers = new ArrayList<>();
    for (RegisteredPlayer player : players) {
        if (!player.isEliminated()) {
            nonEliminatedPlayers.add(player);
        }
    }
    return nonEliminatedPlayers;
}

    public Map<Integer, Integer> getMatchWinnersByNumMatch(Round round) {
    Map<Integer, Integer> matchWinners = new HashMap<>();
    try {
        String query = "SELECT * FROM match WHERE roundId = ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setInt(1, round.getNumRound());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int numMatch = rs.getInt("numMatch");
            int winner = rs.getInt("winner");
            matchWinners.put(numMatch, winner);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return matchWinners;
}

    
    public void generatematches(Tournament T, int RoundNumber){
        
        
        
    }
    
    
    public int getMaxTid() {
    int maxTid = 0;
    try {
        String query = "SELECT MAX(Tid) AS max_tid FROM tournament";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            maxTid = rs.getInt("max_tid");
            if (rs.wasNull()) {
                maxTid = 0;
            }
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return maxTid;
}


    
    


}
