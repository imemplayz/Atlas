package tn.esprit.atlas.services;

import tn.esprit.atlas.entities.Reservation;
import tn.esprit.atlas.main.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private static Connection connection;

    public ReservationService() {
        this.connection = DatabaseConnection.getInstance().getCnx();
    }

    // ➤ Add Reservation
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO Reservation (type_reservation, description_reservation, prix, statut, dateReservation) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getTypeReservation());
            preparedStatement.setString(2, reservation.getDescriptionReservation());
            preparedStatement.setDouble(3, reservation.getPrix());
            preparedStatement.setString(4, reservation.getStatut());
            preparedStatement.setDate(5, new java.sql.Date(reservation.getDateReservation().getTime()));

            preparedStatement.executeUpdate();
            System.out.println("✅ Reservation added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Get All Reservations
    public static List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("type_reservation"),
                        resultSet.getString("description_reservation"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("statut"),
                        resultSet.getDate("dateReservation")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // ➤ Update Reservation
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservation SET type_reservation = ?, description_reservation = ?, prix = ?, statut = ?, dateReservation = ? WHERE reservation_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getTypeReservation());
            preparedStatement.setString(2, reservation.getDescriptionReservation());
            preparedStatement.setDouble(3, reservation.getPrix());
            preparedStatement.setString(4, reservation.getStatut());
            preparedStatement.setDate(5, new java.sql.Date(reservation.getDateReservation().getTime()));
            preparedStatement.setInt(6, reservation.getReservationId());

            preparedStatement.executeUpdate();
            System.out.println("✅ Reservation updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Delete Reservation
    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM Reservation WHERE reservation_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reservationId);
            preparedStatement.executeUpdate();
            System.out.println("✅ Reservation deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}