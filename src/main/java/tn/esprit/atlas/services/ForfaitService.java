package tn.esprit.atlas.services;

import tn.esprit.atlas.entities.Forfait;
import tn.esprit.atlas.main.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForfaitService {

    private static Connection connection;

    public ForfaitService() {
        this.connection = DatabaseConnection.getInstance().getCnx();
    }

    // ➤ Add Forfait
    public void addForfait(Forfait forfait) {
        String query = "INSERT INTO Forfait (name, description, price, duration, destinations, availableSeats, packageImage) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, forfait.getName());
            preparedStatement.setString(2, forfait.getDescription());
            preparedStatement.setDouble(3, forfait.getPrice());
            preparedStatement.setInt(4, forfait.getDuration());
            preparedStatement.setString(5, String.join(",", forfait.getDestinations()));
            preparedStatement.setInt(6, forfait.getAvailableSeats());
            preparedStatement.setString(7, forfait.getPackageImage());

            preparedStatement.executeUpdate();
            System.out.println("✅ Forfait added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Get All Forfaits
    public static List<Forfait> getAllForfaits() {
        List<Forfait> forfaits = new ArrayList<>();
        String query = "SELECT * FROM Forfait";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                List<String> destinations = List.of(resultSet.getString("destinations").split(","));
                Forfait forfait = new Forfait(
                        resultSet.getInt("packaged"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("duration"),
                        destinations,
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("packageImage")
                );
                forfaits.add(forfait);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forfaits;
    }

    // ➤ Update Forfait
    public void updateForfait(Forfait forfait) {
        String query = "UPDATE Forfait SET name = ?, description = ?, price = ?, duration = ?, destinations = ?, availableSeats = ?, packageImage = ? WHERE packaged = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, forfait.getName());
            preparedStatement.setString(2, forfait.getDescription());
            preparedStatement.setDouble(3, forfait.getPrice());
            preparedStatement.setInt(4, forfait.getDuration());
            preparedStatement.setString(5, String.join(",", forfait.getDestinations()));
            preparedStatement.setInt(6, forfait.getAvailableSeats());
            preparedStatement.setString(7, forfait.getPackageImage());
            preparedStatement.setInt(8, forfait.getPackaged());

            preparedStatement.executeUpdate();
            System.out.println("✅ Forfait updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Delete Forfait
    public void deleteForfait(int packaged) {
        String query = "DELETE FROM Forfait WHERE packaged = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, packaged);
            preparedStatement.executeUpdate();
            System.out.println("✅ Forfait deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
