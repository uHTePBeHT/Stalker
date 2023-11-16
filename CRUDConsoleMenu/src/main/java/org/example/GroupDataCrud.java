package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDataCrud {

    private final Connection connection;

    public GroupDataCrud(Connection connection) {
        this.connection = connection;
    }

    public void createGroup(String groupName) {
        String query = "INSERT INTO group_data (group_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, groupName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getGroupId(String groupName) {
        String query = "SELECT group_id FROM group_data WHERE group_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, groupName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("group_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if not found (handle this appropriately in your application)
    }

    public void updateGroup(int groupId, String newGroupName) {
        String query = "UPDATE group_data SET group_name = ? WHERE group_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newGroupName);
            preparedStatement.setInt(2, groupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(int groupId) {
        String query = "DELETE FROM group_data WHERE group_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean groupExists(int groupId) {
        try {
            String sql = "SELECT 1 FROM group_data WHERE group_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, groupId);

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // Вернет true, если группа существует
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
