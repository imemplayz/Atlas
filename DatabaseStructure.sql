-- Créer la base de données si elle n'existe pas
CREATE DATABASE IF NOT EXISTS atlas;

-- Utiliser la base de données
USE atlas;
-- Create the AirLine table if it does not exist
CREATE TABLE IF NOT EXISTS AirLine (
    airline_id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    pays VARCHAR(255),
    logo VARCHAR(255)
);

-- Créer la table Vol (Vol)
CREATE TABLE IF NOT EXISTS Vol (
    vol_id INT PRIMARY KEY AUTO_INCREMENT,
    departure VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    departureDate DATE NOT NULL,
    returnDate DATE NOT NULL,
    availableSeats INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    airline_id INT, -- Clé étrangère pointant vers Airline
    FOREIGN KEY (airline_id) REFERENCES Airline(airline_id)
);

