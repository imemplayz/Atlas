-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS atlas;

-- Use the atlas
USE atlas;

-- Create the Vol table if it does not exist
CREATE TABLE IF NOT EXISTS Vol (
    vol_id INT PRIMARY KEY AUTO_INCREMENT,
    departure VARCHAR(255),
    destination VARCHAR(255),
    duration INT,
    availableSeats INT
);


-- Create the AirLine table if it does not exist
CREATE TABLE IF NOT EXISTS AirLine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    pays VARCHAR(255),
    logo VARCHAR(255)
);