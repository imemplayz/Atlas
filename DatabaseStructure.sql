-- Create the database
CREATE DATABASE IF NOT EXISTS atlas;
USE atlas;

-- Create the Utilisateur table with roles for Voyageur, SupportClient, and Admin
CREATE TABLE Utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    age INT,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    adresse VARCHAR(255),
    role ENUM('Voyageur', 'SupportClient', 'Admin') NOT NULL,
    profileImage VARCHAR(255),
    
    -- Voyageur-specific fields
    num_telph VARCHAR(20),
    voyageurPreferences VARCHAR(255),
    destinations_preferrees TEXT,
    budget DOUBLE,
    reservationHistorique TEXT,
    completedTrips TEXT,
    
    -- SupportClient-specific fields
    assignedTickets TEXT,
    
    -- Admin-specific fields
    adminPrivileges TEXT
);
