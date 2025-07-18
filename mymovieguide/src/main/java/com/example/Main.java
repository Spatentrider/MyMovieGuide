package com.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myMovieGuide";
    private static final String USER = "jaita"; 
    private static final String PASS = "jaita"; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            while (true) {
                System.out.println("\nBenvenuto in MyMovieGuide!");
                System.out.print("Scegli un'opzione: ");
                System.out.println("1. Inserisci film");
                System.out.println("2. Mostra tutti i film");
                System.out.println("3. Aggiorna valutazione");
                System.out.println("4. Elimina film");
                System.out.println("5. Esci");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                       

                    case 2:
                        String sqlSelect = "SELECT * FROM film";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(sqlSelect)) {
                            while (rs.next()) {
                                System.out.println("\nTitolo: " + rs.getString("titolo"));
                                System.out.println("Genere: " + rs.getString("genere"));
                                System.out.println("Recensione: " + rs.getString("recensione"));
                                System.out.println("Valutazione: " + rs.getInt("valutazione"));
                            }
                        }
                        break;

                    case 3:
                        

                    case 4:
                        

                    case 5:
                        System.out.println("Uscita dal sistema.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Scelta non valida.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}