package tn.esprit.atlas.entities;

import java.util.Date;

public class Reservation {
    private int reservationId;
    private String typeReservation;
    private String descriptionReservation;
    private double prix;
    private String statut;
    private Date dateReservation;

    // Constructors
    public Reservation() {}

    public Reservation(int reservationId, String typeReservation, String descriptionReservation, double prix, String statut, Date dateReservation) {
        this.reservationId = reservationId;
        this.typeReservation = typeReservation;
        this.descriptionReservation = descriptionReservation;
        this.prix = prix;
        this.statut = statut;
        this.dateReservation = dateReservation;
    }

    // Getters and Setters
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public String getTypeReservation() { return typeReservation; }
    public void setTypeReservation(String typeReservation) { this.typeReservation = typeReservation; }

    public String getDescriptionReservation() { return descriptionReservation; }
    public void setDescriptionReservation(String descriptionReservation) { this.descriptionReservation = descriptionReservation; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    // To String (For Debugging)
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", typeReservation='" + typeReservation + '\'' +
                ", descriptionReservation='" + descriptionReservation + '\'' +
                ", prix=" + prix +
                ", statut='" + statut + '\'' +
                ", dateReservation=" + dateReservation +
                '}';
    }
}