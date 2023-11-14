package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.example.JsonFileDeserializer;

public class DatabaseManager {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insertStalkers(List<Stalker> stalkers) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO stalkers (id, first_name, second_name, group_name, rank, location, suit, weapon, money) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Stalker stalker : stalkers) {
                    preparedStatement.setInt(1, stalker.getId());
                    preparedStatement.setString(2, stalker.getFirstName());
                    preparedStatement.setString(3, stalker.getSecondName());
                    preparedStatement.setString(4, stalker.getGroup().getGroupName());
                    preparedStatement.setString(5, stalker.getRank().getRankName());
                    preparedStatement.setString(6, stalker.getLocation().getLocationName());
                    preparedStatement.setString(7, stalker.getSuit().getSuitName());
                    preparedStatement.setString(8, stalker.getWeapon().getWeaponName());
                    preparedStatement.setInt(9, stalker.getMoney().getMoney());

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost:5432/stalkers", "postgres", "postgres");
        databaseManager.insertStalkers(JsonFileDeserializer.getStalkersD());
    }
}