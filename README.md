# MyMovieGuide
Creazione del database myMovieGuide
con una tabella che contiene:id che si autoincrementa,  titolo , genere , recensione , valutazione.
Compiti:
Deborah:parte cancellazione
Giuliana: parte visualizzazione
Francesco Pierno:parte read
Valerio: parte create

creazione del database in mySql:
-- Creazione del database
CREATE DATABASE myMovieGuide;

-- Selezione del database appena creato
USE myMovieGuide;

-- Creazione della tabella "film"
CREATE TABLE film (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(255),
    genere VARCHAR(100),
    valutazione int CHECK (valutazione >= 1 AND valutazione <= 10),
    recensione TEXT
);