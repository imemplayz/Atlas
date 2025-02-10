package tn.esprit.atlas.entities;

import java.util.Objects;

public class Vol {

    private int vol_id;
    private String departure;
    private String destination;
    private String duration;
    private int availableSeats;


    public Vol() {}

    public Vol(int vol_id, String departure, String destination, String duration, int availableSeats) {
        this.vol_id = vol_id;
        this.departure = departure;
        this.destination = destination;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }


    public Vol(String departure, String destination, String duration, int availableSeats) {
        this.departure = departure;
        this.destination = destination;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }


    public int getId() {
        return vol_id;
    }


    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDuration() {
        return duration;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }


    public void setId(int id) {
        this.vol_id = id;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }



    @Override
    public String toString() {
        return "Vol{" +
                "id=" + vol_id +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", duration='" + duration + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }

}
