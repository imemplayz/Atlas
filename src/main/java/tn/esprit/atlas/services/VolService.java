package tn.esprit.atlas.services;

import tn.esprit.atlas.entities.Vol;
import tn.esprit.atlas.main.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolService implements Iservice<Vol> {

    private Connection cnx;

    public VolService() {
        cnx = DatabaseConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vol vol) {
        String req = "INSERT INTO vol (departure, destination, departureDate, returnDate, availableSeats, price, airline_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, vol.getDeparture());
            stm.setString(2, vol.getDestination());
            stm.setString(3, vol.getDepartureDate());
            stm.setString(4, vol.getReturnDate());
            stm.setInt(5, vol.getAvailableSeats());
            stm.setDouble(6, vol.getPrice());
            stm.setInt(7, vol.getAirline_id()); // Ajout de airline_id à la requête
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Vol vol) {
        String req = "UPDATE vol SET departure = ?, destination = ?, departureDate = ?, returnDate = ?, availableSeats = ?, price = ?, airline_id = ? WHERE vol_id = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, vol.getDeparture());
            stm.setString(2, vol.getDestination());
            stm.setString(3, vol.getDepartureDate());
            stm.setString(4, vol.getReturnDate());
            stm.setInt(5, vol.getAvailableSeats());
            stm.setDouble(6, vol.getPrice());
            stm.setInt(7, vol.getAirline_id()); // Ajout de airline_id à la requête
            stm.setInt(8, vol.getVol_id()); // Utilisation de vol_id pour identifier le vol à mettre à jour
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(Vol vol) {
        String req = "DELETE FROM vol WHERE vol_id = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setInt(1, vol.getVol_id());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vol> getall() {
        List<Vol> vols = new ArrayList<>();
        String req = "SELECT * FROM vol";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Vol v = new Vol();
                v.setVol_id(rs.getInt("vol_id"));
                v.setDeparture(rs.getString("departure"));
                v.setDestination(rs.getString("destination"));
                v.setDepartureDate(rs.getString("departureDate"));
                v.setReturnDate(rs.getString("returnDate"));
                v.setAvailableSeats(rs.getInt("availableSeats"));
                v.setPrice(rs.getDouble("price"));
                v.setAirline_id(rs.getInt("airline_id")); // Récupération de airline_id
                vols.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vols;
    }

    @Override
    public Vol getone() {
        return null;
    }
}
