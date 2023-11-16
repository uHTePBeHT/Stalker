package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.example.JsonFileDeserializer;
import org.example.Stalker;
import org.example.stalker.info.Group;
import org.example.stalker.info.Location;
import org.example.stalker.info.Rank;
import org.example.stalker.inventory.Suit;
import org.example.stalker.inventory.Weapon;


public class DatabaseManager {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private void insertStalkers(List<Stalker> stalkers) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO stalker (first_name, second_name, money) " +
                    "VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Stalker stalker : stalkers) {
                    preparedStatement.setString(1, stalker.getFirstName());
                    preparedStatement.setString(2, stalker.getSecondName());
                    preparedStatement.setInt(3, stalker.getMoney());

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertGroups(String[] groups) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO group_data (group_name) " +
                    "VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (String group : groups) {
                    preparedStatement.setString(1, group);

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRanks(String[] ranks) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO rank (rank_name) " +
                    "VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (String rank : ranks) {
                    preparedStatement.setString(1, rank);

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertLocations(String[] locations) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO location (location_name) " +
                    "VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (String location : locations) {
                    preparedStatement.setString(1, location);

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertSuits(String[] suits) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO suit (suit_name) " +
                    "VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (String suit : suits) {
                    preparedStatement.setString(1, suit);

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertWeapons(String[] weapons) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO weapon (weapon_name) " +
                    "VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (String weapon : weapons) {
                    preparedStatement.setString(1, weapon);

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*private void findID() {
        try (Connection connection = getConnection()) {
            String query = "";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost:5432/stalkers_db", "postgres", "postgres");
        List<Stalker> stalkersA = JsonFileDeserializer.deserializeObjects("D:\\Java Projects\\Stalker\\stalkers.json");
        System.out.println(stalkersA);
        databaseManager.insertStalkers(stalkersA);
        databaseManager.insertGroups(Group.getGroups());
        databaseManager.insertRanks(Rank.getRanks());
        databaseManager.insertLocations(Location.getLocations());
        databaseManager.insertSuits(Suit.getSuits());
        databaseManager.insertWeapons(Weapon.getWeapons());
    }
}