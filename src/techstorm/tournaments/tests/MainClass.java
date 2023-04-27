/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techstorm.tournaments.tests;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import techstorm.tournaments.entities.Tournament;
import techstorm.tournaments.services.TournamentCRUD;
import techstorm.tournaments.utils.MyConnection;

/**
 *
 * @author qiyanu
 */
public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TournamentCRUD tcd = new TournamentCRUD();
        
        System.out.print("How many tournaments do you want to input? ");
        int numTournaments = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < numTournaments; i++) {
            System.out.println("Enter details for tournament " + (i + 1) + ":");
            
            String name = null;
            while (name == null || name.isEmpty()) {
                System.out.print("Enter the tournament name: ");
                name = scanner.nextLine();
            }
            
            String startDateStr = null;
            LocalDate startDate = null;
            while (startDate == null) {
                try {
                    System.out.print("Enter the tournament start date (yyyy-mm-dd): ");
                    startDateStr = scanner.nextLine();
                    startDate = LocalDate.parse(startDateStr);
                    if (startDate.isBefore(LocalDate.now())) {
                        throw new IllegalArgumentException("Start date must be a future date.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter a date in yyyy-mm-dd format.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    startDate = null;
                }
            }
            
            int maxPlayers = 0;
            while (maxPlayers <= 0) {
                try {
                    System.out.print("Enter the maximum number of players: ");
                    maxPlayers = scanner.nextInt();
                    scanner.nextLine();             
                } catch (NumberFormatException e) {
                    System.out.println("Maximum players must be a positive integer.");
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Maximum players must be a positive integer.");
                    scanner.nextLine();
                }
            }
            
            String status = null;
            while (status == null || status.isEmpty()) {
                System.out.print("Enter the tournament status: ");
                status = scanner.nextLine();
            }
            
            Tournament t = new Tournament(name, startDateStr, maxPlayers, status);
            tcd.addTournament(t);
        }
        
        tcd.displayAllTournaments();
    }
}
