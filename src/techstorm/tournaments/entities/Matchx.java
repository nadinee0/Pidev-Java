/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.entities;

/**
 *
 * @author qiyanu
 */
public class Matchx {
    private int id;
    private int numMatch;
    private int player1;
    private int player2;
    private int player1Score;
    private int player2Score;
    private Integer winner;

    public Matchx(int id, int numMatch, int player1, int player2, int player1Score, int player2Score, int winner) {
        this.id = id;
        this.numMatch = numMatch;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winner = winner;
    }

    public Matchx(int numMatch, int player1, int player2) {
        this.numMatch = numMatch;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = 0;
        this.player2Score = 0;
        this.winner = null;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", numMatch=" + numMatch + ", player1=" + player1 + ", player2=" + player2 + ", player1Score=" + player1Score + ", player2Score=" + player2Score + ", winner=" + winner + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumMatch() {
        return numMatch;
    }

    public void setNumMatch(int numMatch) {
        this.numMatch = numMatch;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }
    
    
    
    
    
}
