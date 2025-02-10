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
        String req = "INSERT INTO vol (departure, destination, duration, availableSeats) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, vol.getDeparture());
            stm.setString(2, vol.getDestination());
            stm.setString(3, vol.getDuration());
            stm.setInt(4, vol.getAvailableSeats());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Vol vol) {
        String req = "UPDATE vol SET departure = ?, destination = ?, duration = ?, availableSeats = ? WHERE vol_id = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, vol.getDeparture());
            stm.setString(2, vol.getDestination());
            stm.setString(3, vol.getDuration());
            stm.setInt(4, vol.getAvailableSeats());
            stm.setInt(5, vol.getId());
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
            stm.setInt(1, vol.getId());
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
                v.setId(rs.getInt("vol_id"));
                v.setDeparture(rs.getString("departure"));
                v.setDestination(rs.getString("destination"));
                v.setDuration(rs.getString("duration"));
                v.setAvailableSeats(rs.getInt("availableSeats"));
                vols.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vols;
    }

    @Override
    public Vol getone() {
        return null; // À implémenter si nécessaire
    }
}

