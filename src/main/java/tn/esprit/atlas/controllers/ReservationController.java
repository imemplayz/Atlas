package tn.esprit.atlas.controllers;

import tn.esprit.atlas.entities.Reservation;
import tn.esprit.atlas.services.ReservationService;

import java.util.List;

public class ReservationController {

    private ReservationService reservationService = new ReservationService();

    public void createReservation(Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    public void listReservations() {
        List<Reservation> reservations = ReservationService.getAllReservations();
        reservations.forEach(reservation -> System.out.println(reservation.getTypeReservation() + " - " + reservation.getDescriptionReservation()));
    }

    public void modifyReservation(Reservation reservation) {
        reservationService.updateReservation(reservation);
    }

    public void removeReservation(int reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}