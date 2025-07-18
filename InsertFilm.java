 public static void insertFilm(String titolo, String genere, int valutazione, String recensione) {
        String sql = "INSERT INTO film (titolo, genere, valutazione, recensione) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
