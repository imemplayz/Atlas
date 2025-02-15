package tn.esprit.atlas.services;

import tn.esprit.atlas.entities.User;
import tn.esprit.atlas.main.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static Connection connection;

    public UserService() {
        this.connection = DatabaseConnection.getInstance().getCnx();
    }

    // ➤ Add User
    public void addUser(User user) {
        String query = "INSERT INTO Utilisateur (name, surname, age, email, password, adresse, role, profileImage, num_telph, voyageurPreferences, destinations_preferrees, budget) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getAdresse());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setString(8, user.getProfileImage());
            preparedStatement.setString(9, user.getNumTel());
            preparedStatement.setString(10, user.getVoyageurPreferences());
            preparedStatement.setString(11, user.getDestinationsPreferrees());
            preparedStatement.setDouble(12, user.getBudget());

            preparedStatement.executeUpdate();
            System.out.println("✅ User added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Get All Users
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Utilisateur";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("adresse"),
                        resultSet.getString("role"),
                        resultSet.getString("profileImage"),
                        resultSet.getString("num_telph"),
                        resultSet.getString("voyageurPreferences"),
                        resultSet.getString("destinations_preferrees"),
                        resultSet.getDouble("budget")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ➤ Update User
    public void updateUser(User user) {
        String query = "UPDATE Utilisateur SET name = ?, surname = ?, age = ?, email = ?, password = ?, adresse = ?, role = ?, profileImage = ?, num_telph = ?, voyageurPreferences = ?, destinations_preferrees = ?, budget = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getAdresse());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setString(8, user.getProfileImage());
            preparedStatement.setString(9, user.getNumTel());
            preparedStatement.setString(10, user.getVoyageurPreferences());
            preparedStatement.setString(11, user.getDestinationsPreferrees());
            preparedStatement.setDouble(12, user.getBudget());
            preparedStatement.setInt(13, user.getId());

            preparedStatement.executeUpdate();
            System.out.println("✅ User updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ➤ Delete User
    public void deleteUser(int userId) {
        String query = "DELETE FROM Utilisateur WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("✅ User deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
