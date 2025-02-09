package esprit.tn.services;

import esprit.tn.entities.Hotel;
import esprit.tn.main.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelService implements IService<Hotel> {

    private Connection connection;

    public HotelService() {
        connection = DatabaseConnection.getInstance().getCnx();
    }

    @Override
    public void add(Hotel hotel) {
        String query = "INSERT INTO Hotel (name, address, rating) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setFloat(3, hotel.getRating());
            stmt.executeUpdate();
            System.out.println("Hotel added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Hotel hotel) {
        String query = "UPDATE Hotel SET name = ?, address = ?, rating = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setFloat(3, hotel.getRating());
            stmt.setInt(4, hotel.getId());
            stmt.executeUpdate();
            System.out.println("Hotel updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hotel hotel) {
        String query = "DELETE FROM Hotel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotel.getId());
            stmt.executeUpdate();
            System.out.println("Hotel deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotel";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getFloat("rating")
                );
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    @Override
    public Hotel getOne() {
        // Example to get a specific hotel by ID (you can extend this based on your needs)
        return null;
    }
}
