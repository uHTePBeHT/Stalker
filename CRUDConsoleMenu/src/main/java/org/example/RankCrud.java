package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankCrud {

    private final Connection connection;

    public RankCrud(Connection connection) {
        this.connection = connection;
    }

    public void createRank(String rankName) {
        String query = "INSERT INTO rank (rank_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rankName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRankId(String rankName) {
        String query = "SELECT rank_id FROM rank WHERE rank_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rankName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("rank_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateRank(int rankId, String newRankName) {
        String query = "UPDATE rank SET rank_name = ? WHERE rank_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newRankName);
            preparedStatement.setInt(2, rankId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRank(int rankId) {
        String query = "DELETE FROM rank WHERE rank_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rankId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean rankExists(int rankId) {
        try {
            String sql = "SELECT 1 FROM rank WHERE rank_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, rankId);

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // Вернет true, если ранг существует
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
