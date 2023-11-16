package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuitCrud {


    private final Connection connection;

    public SuitCrud(Connection connection) {
        this.connection = connection;
    }

    public void createSuit(String suitName) {
        String query = "INSERT INTO suit (suit_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, suitName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSuitId(String suitName) {
        String query = "SELECT suit_id FROM suit WHERE suit_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, suitName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("suit_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateSuit(int suitId, String newSuitName) {
        String query = "UPDATE suit SET suit_name = ? WHERE suit_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newSuitName);
            preparedStatement.setInt(2, suitId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSuit(int suitId) {
        String query = "DELETE FROM suit WHERE suit_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, suitId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean suitExists(int suitId) {
        try {
            String sql = "SELECT 1 FROM suit WHERE suit_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, suitId);

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // Вернет true, если ранг существует
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
