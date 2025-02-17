package tn.esprit.atlas.entities;

import java.util.Objects;

public class Vol {

    private int vol_id;
    private String departure;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int availableSeats;
    private double price;
    private int airline_id;  // Ajout de l'attribut airline_id pour la clé étrangère

    // Constructeurs
    public Vol() {}

    public Vol(int vol_id, String departure, String destination, String departureDate, String returnDate, int availableSeats, double price, int airline_id) {
        this.vol_id = vol_id;
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.availableSeats = availableSeats;
        this.price = price;
        this.airline_id = airline_id;
    }

    public Vol(String departure, String destination, String departureDate, String returnDate, int availableSeats, double price, int airline_id) {
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.availableSeats = availableSeats;
        this.price = price;
        this.airline_id = airline_id;
    }

    // Getters
    public int getVol_id() {
        return vol_id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public int getAirline_id() {
        return airline_id;  // Getter pour airline_id
    }

    // Setters
    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;  // Setter pour airline_id
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Vol{" +
                "vol_id=" + vol_id +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                ", airline_id=" + airline_id +
                '}';
    }
}
