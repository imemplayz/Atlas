-- Créer la base de données atlas si elle n'existe pas
CREATE DATABASE IF NOT EXISTS atlas;

-- Utiliser la base de données atlas
USE atlas;

-- Créer la table reservation
CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    type_reservation VARCHAR(255) NOT NULL,
    description_reservation TEXT,
    prix DECIMAL(10, 2) NOT NULL,
    statut VARCHAR(50) NOT NULL,
    dateReservation DATE NOT NULL
);
CREATE TABLE Forfait (
    packageld INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(255) NOT NULL,               
    description TEXT,                        
    price DOUBLE NOT NULL,                  
    duration INT NOT NULL,                    
    destinations TEXT,                         
    availableSeats INT NOT NULL,              
    packageImage VARCHAR(255)                
);
