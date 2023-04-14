/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qiyanu
 */
public class Round {
    private int id;
    private int numRound;
    private List<Matchx> matches = new ArrayList<>();

    public Round(int id, int numRound) {
        this.id = id;
        this.numRound = numRound;
    }

    public Round(int numRound) {
        this.numRound = numRound;
    }

    @Override
    public String toString() {
        return "Round{" + "id=" + id + ", numRound=" + numRound + ", matches=" + matches + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumRound() {
        return numRound;
    }

    public void setNumRound(int numRound) {
        this.numRound = numRound;
    }

    public List<Matchx> getMatches() {
        return matches;
    }

    public void setMatches(List<Matchx> matches) {
        this.matches = matches;
    }
    
    
    
}
