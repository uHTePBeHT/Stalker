package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StalkerCrud {

    private final Connection connection;
    private final GroupDataCrud groupDataCrud;
    private final RankCrud rankCrud;
    private final LocationCrud locationCrud;
    private final SuitCrud suitCrud;
    private final WeaponCrud weaponCrud;

    public StalkerCrud(Connection connection, GroupDataCrud groupDataCrud, RankCrud rankCrud,
                       LocationCrud locationCrud, SuitCrud suitCrud, WeaponCrud weaponCrud) {
        this.connection = connection;
        this.groupDataCrud = groupDataCrud;
        this.rankCrud = rankCrud;
        this.locationCrud = locationCrud;
        this.suitCrud = suitCrud;
        this.weaponCrud = weaponCrud;
    }

    public void createStalker(String firstName, String secondName, int groupId, int rankId,
                              int locationId, int suitId, int weaponId, int money) {
        try {
            // Проверяем, существуют ли переданные groupId, rankId, locationId, suitId, weaponId
            if (!groupDataCrud.groupExists(groupId) ||
                    !rankCrud.rankExists(rankId) ||
                    !locationCrud.locationExists(locationId) ||
                    !suitCrud.suitExists(suitId) ||
                    !weaponCrud.weaponExists(weaponId)) {
                System.out.println("Ошибка: Один из указанных элементов не существует.");
                return;
            }

            String sql = "INSERT INTO stalker (first_name, second_name, group_id, rank_id, location_id, suit_id, weapon_id, money) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, firstName);
                statement.setString(2, secondName);
                statement.setInt(3, groupId);
                statement.setInt(4, rankId);
                statement.setInt(5, locationId);
                statement.setInt(6, suitId);
                statement.setInt(7, weaponId);
                statement.setInt(8, money);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Ошибка: Создание сталкера не удалось.");
                } else {
                    System.out.println("Сталкер успешно создан.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStalkerId(String firstName, String secondName) {
        try {
            String sql = "SELECT stalker_id FROM stalker WHERE first_name = ? AND second_name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, secondName);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("stalker_id");
                } else {
                    System.out.println("Ошибка: Сталкер не найден.");
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void updateStalker(int stalkerId, String firstName, String secondName,
                              int groupId, int rankId, int locationId, int suitId, int weaponId, int money) {
        try {
            // Проверяем, существуют ли переданные groupId, rankId, locationId, suitId, weaponId
            if (!groupDataCrud.groupExists(groupId) ||
                    !rankCrud.rankExists(rankId) ||
                    !locationCrud.locationExists(locationId) ||
                    !suitCrud.suitExists(suitId) ||
                    !weaponCrud.weaponExists(weaponId)) {
                System.out.println("Ошибка: Один из указанных элементов не существует.");
                return;
            }

            String sql = "UPDATE stalker SET first_name = ?, second_name = ?, group_id = ?, rank_id = ?, location_id = ?, suit_id = ?, weapon_id = ?, money = ? WHERE stalker_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, secondName);
                statement.setInt(3, groupId);
                statement.setInt(4, rankId);
                statement.setInt(5, locationId);
                statement.setInt(6, suitId);
                statement.setInt(7, weaponId);
                statement.setInt(8, stalkerId);
                statement.setInt(9, money);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Ошибка: Обновление сталкера не удалось.");
                } else {
                    System.out.println("Сталкер успешно обновлен.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStalker(int stalkerId) {
        try {
            String sql = "DELETE FROM stalker WHERE stalker_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, stalkerId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Ошибка: Удаление сталкера не удалось.");
                } else {
                    System.out.println("Сталкер успешно удален.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int enterMoneyFromConsole() {
        System.out.print("Введите количество денег для сталкера: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public Stalker getStalkerById(int stalkerId) {
        try {
            String sql = "SELECT * FROM stalker WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, stalkerId);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // Создаем объект Stalker на основе данных из базы
                    Stalker stalker = new Stalker(
                            resultSet.getInt("stalker_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("second_name"),
                            resultSet.getString("location_id"),
                            resultSet.getString("suit_id"),
                            resultSet.getString("weapon_id"),
                            resultSet.getInt("money"),
                            resultSet.getString("rank_id"),
                            resultSet.getString("group_id")
                    );

                    return stalker;
                } else {
                    System.out.println("Ошибка: Сталкер не найден.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
