/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qiyanu
 */
public class Tournament {
    private int id;    
    private int Tid = 0;    
    private String name;    
    private String startDate;
    private int participantsNumber;
    private String status;
    private String replayID;
    private static int TidCounter = 0;    

    private List<Round> rounds = new ArrayList<>();
    private List<RegisteredPlayer> players = new ArrayList<>();

    public Tournament(int id, int Tid, String name, String startDate, int participantsNumber, String status, String replayID) {
        this.id = id;
        this.Tid = Tid;
        this.name = name;
        this.startDate = startDate;
        this.participantsNumber = participantsNumber;
        this.status = status;
        this.replayID = replayID; 
    }

    public Tournament(String name, String startDate, int participantsNumber, String status, String replayID) {
        this.name = name;
        this.startDate = startDate;
        this.participantsNumber = participantsNumber;
        this.status = status;
        this.replayID = replayID;
        
    }

    @Override
    public String toString() {
        return "Tournament{" + "id=" + id + ", Tid=" + Tid + ", name=" + name + ", startDate=" + startDate + ", participantsNumber=" + participantsNumber + ", status=" + status + ", rounds=" + rounds + '}';
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int Tid) {
        this.Tid = Tid;
    }

    public static int getTidCounter() {
        return TidCounter;
    }

    public static void setTidCounter(int TidCounter) {
        Tournament.TidCounter = TidCounter;
    }


    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(int participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getReplayID() {
        return replayID;
    }

    public void setReplayID(String replayID) {
        this.replayID = replayID;
    }

    public List<RegisteredPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<RegisteredPlayer> players) {
        this.players = players;
    }   

    public Object getReplayId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
