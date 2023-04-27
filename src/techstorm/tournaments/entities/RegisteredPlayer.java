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
public class RegisteredPlayer {
    private int id;
    private int idp;
    private String username;
    private boolean eliminated;

    public RegisteredPlayer(int id, int idp, String username,boolean eliminated) {
        this.id = id;
        this.idp = idp;
        this.username = username;
        this.eliminated = eliminated;
    }

    
    
    public RegisteredPlayer(int idp, String username) {
        this.id = idp;
        this.username = username;
    }

    @Override
    public String toString() {
        return "RegisteredPlayer{" + "id=" + id + ", idp=" + idp + ", username=" + username + ", eliminated=" + eliminated + '}';
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
    
       
    
    
}
