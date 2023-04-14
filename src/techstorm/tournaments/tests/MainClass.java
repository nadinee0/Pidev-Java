/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.tests;

import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;
import techstorm.tournaments.utils.MyConnection;

/**
 *
 * @author qiyanu
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
        TournamentCRUD tcd = new TournamentCRUD();
        Tournament t1 = new Tournament("test1", "2022-05-01", 16, "Incoming");
        Tournament t2 = new Tournament("test2", "2022-05-01", 16, "Incoming");
        tcd.addTournament(t2);
        tcd.displayAllTournaments();
    }
    
}
