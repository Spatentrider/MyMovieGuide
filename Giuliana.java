package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Giuliana {

    // definisco metodo per visualizzare film
    public static void visualizzaFilm() {
        String sql = "SELECT * FROM film"; // dichiaro la query

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // stabilisco connessione
             PreparedStatement stmt = conn.prepareStatement(sql); // precompila la query
             ResultSet rs = stmt.executeQuery()) { // esegue la query

            System.out.println("\nElenco film:");

            while (rs.next()) { // inizio ciclo che scorre result set e col metodo next passa alla riga successiva
                System.out.printf("ID: %d - Titolo: %s - Genere: %s - Valutazione: %d - Recensione: %s\n",
                        rs.getInt("id"),
                        rs.getString("titolo"),
                        rs.getString("genere"),
                        rs.getInt("valutazione"),
                        rs.getString("recensione"));
            }

        } catch (SQLException e) { // cattura errori
            e.printStackTrace();
        } // chiudo try-catch
    
}
