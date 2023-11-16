package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeaponCrud {



    private final Connection connection;

    public WeaponCrud(Connection connection) {
        this.connection = connection;
    }

    public void createWeapon(String weaponName) {
        String query = "INSERT INTO weapon (weapon_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, weaponName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getWeaponId(String weaponName) {
        String query = "SELECT weapon_id FROM weapon WHERE weapon_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, weaponName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("weapon_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateWeapon(int weaponId, String newWeaponName) {
        String query = "UPDATE weapon SET weapon_name = ? WHERE weapon_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newWeaponName);
            preparedStatement.setInt(2, weaponId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWeapon(int weaponId) {
        String query = "DELETE FROM weapon WHERE weapon_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, weaponId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
