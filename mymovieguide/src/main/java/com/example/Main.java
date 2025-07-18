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
                System.out.println("1. Inserisci film");
                System.out.println("2. Mostra tutti i film");
                System.out.println("3. Aggiorna valutazione");
                System.out.println("4. Elimina film");
                System.out.println("5. Esci");
                System.out.print("Scegli un'opzione: ");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                    //fatto da valerio che aveva fatto il metodo ma se lo usiamo diventa tutto rosso
                    //quindi lo scriviamo senza metodo
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Genere: ");
                        String genere = scanner.nextLine();

                        System.out.print("Recensione: ");
                        String recensione = scanner.nextLine();

                        System.out.print("Valutazione (1-10): ");
                        int valutazione = scanner.nextInt();
                        scanner.nextLine();

                        if (valutazione < 1 || valutazione > 10) {
                            System.out.println("Valutazione non valida. Inserisci un numero da 1 a 10.");
                            break;
                        }

                        String sqlInsert = "INSERT INTO film (titolo, genere, recensione, valutazione) VALUES (?, ?, ?, ?)";

                        try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                            stmt.setString(1, titolo);
                            stmt.setString(2, genere);
                            stmt.setString(3, recensione);
                            stmt.setInt(4, valutazione);

                            stmt.executeUpdate();
                            System.out.println("Film inserito con successo.");
                        } catch (SQLException e) {
                            System.out.println("Errore nell'inserimento del film:");
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                        visualizzaFilm(); // usa il metodo Giuliana
                        break;

                    case 3:
                    //fatto fa francesco senza metodo poichè down
                        System.out.print("Titolo del film da aggiornare: ");
                        String titoloUpdate = scanner.nextLine();
                        System.out.print("Nuova valutazione (1-10): ");
                        int nuovaValutazione = scanner.nextInt();
                        scanner.nextLine();

                        if (nuovaValutazione < 1 || nuovaValutazione > 10) {
                            System.out.println("Valutazione non valida.");
                            break;
                        }

                        String sqlUpdate = "UPDATE film SET valutazione = ? WHERE titolo = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                            stmt.setInt(1, nuovaValutazione);
                            stmt.setString(2, titoloUpdate);
                            int rows = stmt.executeUpdate();
                            if (rows > 0) {
                                System.out.println("Valutazione aggiornata.");
                            } else {
                                System.out.println("Film non trovato.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Errore durante l'aggiornamento.");
                            e.printStackTrace();
                        }
                        break;

                    case 4:
                        System.out.print("Inserisci l'ID del film da eliminare: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        cancellaFilm(id); //metodo di deborah
                        break;

                    case 5:
                        System.out.println("Uscita dal sistema.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Scelta non valida.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore di connessione al database.");
            e.printStackTrace();
        }
    }

    // Metodo Deborah: per eliminare un film
    public static void cancellaFilm(int id) {
        String sql = "DELETE FROM film WHERE id = ?"; 

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int righeCancellate = stmt.executeUpdate();

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

    // Metodo Giuliana: mostra tutti i film
    public static void visualizzaFilm() {
        String sql = "SELECT * FROM film";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nElenco film:");

            while (rs.next()) {
                System.out.printf("ID: %d - Titolo: %s - Genere: %s - Valutazione: %d - Recensione: %s\n",
                        rs.getInt("id"),
                        rs.getString("titolo"),
                        rs.getString("genere"),
                        rs.getInt("valutazione"),
                        rs.getString("recensione"));
            }

        } catch (SQLException e) {
            System.out.println("Errore durante la visualizzazione dei film.");
            e.printStackTrace();
        }
    }

    // Metodo Valerio: inserimento da metodo esterno
    public static void insertFilm(String titolo, String genere, int valutazione, String recensione) {
        String sql = "INSERT INTO film (titolo, genere, valutazione, recensione) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titolo);
            stmt.setString(2, genere);
            stmt.setInt(3, valutazione);
            stmt.setString(4, recensione);

            stmt.executeUpdate();
            System.out.println("Film inserito con successo.");

        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento del film:");
            e.printStackTrace();
        }
    }
}