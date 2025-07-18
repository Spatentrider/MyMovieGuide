package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/myMovieGuide";
    static final String USER = "root";
    static final String PASSWORD = "";
    
         public static void cancellaFilm(int id) {
        // query per eliminare un film in base all'id
        String sql = "DELETE FROM myMovieGuide WHERE id = ?";
        // apertura connessione e preparedStatement
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Sostituisce il ? con l'ID
            stmt.setInt(1, id);
            // esecuzione della query
            int righeCancellate = stmt.executeUpdate();
            // controllo se è stata cancellata una riga
            if (righeCancellate > 0) {
                System.out.println("Film con ID " + id + " cancellato con successo.");
            } else {
                System.out.println("Nessun film trovato con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Errore durante la cancellazione del film.");
            e.printStackTrace();
        }
    
         }
        }
    



