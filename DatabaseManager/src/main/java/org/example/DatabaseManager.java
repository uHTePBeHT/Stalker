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
        if (stalkers == null) {
            System.err.println("Ошибка: Список stalkers равен null.");
            // Возможно, стоит зарегистрировать ошибку или обработать ее иным образом в зависимости от требований вашего приложения.
            System.exit(1); // Завершить программу с кодом ошибки.
        }
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO stalkers (first_name, second_name, group_name, rank, location, suit, weapon, money) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Stalker stalker : stalkers) {
                    //preparedStatement.setInt(1, stalker.getId());
                    preparedStatement.setString(1, stalker.getFirstName());
                    preparedStatement.setString(2, stalker.getSecondName());
                    preparedStatement.setString(3, stalker.getGroup().getGroupName());
                    preparedStatement.setString(4, stalker.getRank().getRankName());
                    preparedStatement.setString(5, stalker.getLocation().getLocationName());
                    preparedStatement.setString(6, stalker.getSuit().getSuitName());
                    preparedStatement.setString(7, stalker.getWeapon().getWeaponName());
                    preparedStatement.setInt(8, stalker.getMoney().getMoney());

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
        List<Stalker> stalkersA = JsonFileDeserializer.deserializeObjects("D:\\Java Projects\\Stalker\\stalkers.json");
        System.out.println(stalkersA);
        databaseManager.insertStalkers(stalkersA);
    }
}