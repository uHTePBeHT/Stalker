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
                // Подготовка запросов для получения ID
                String groupQuery = "SELECT group_id FROM group_data WHERE group_name = ?";
                String rankQuery = "SELECT rank_id FROM rank WHERE rank_name = ?";
                String locationQuery = "SELECT location_id FROM location WHERE location_name = ?";
                String suitQuery = "SELECT suit_id FROM suit WHERE suit_name = ?";
                String weaponQuery = "SELECT weapon_id FROM weapon WHERE weapon_name = ?";

                // Подготовка запроса для вставки сталкеров
                String insertQuery = "INSERT INTO stalker (first_name, second_name, group_id, rank_id, location_id, suit_id, weapon_id, money) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement groupStatement = connection.prepareStatement(groupQuery);
                     PreparedStatement rankStatement = connection.prepareStatement(rankQuery);
                     PreparedStatement locationStatement = connection.prepareStatement(locationQuery);
                     PreparedStatement suitStatement = connection.prepareStatement(suitQuery);
                     PreparedStatement weaponStatement = connection.prepareStatement(weaponQuery);
                     PreparedStatement stalkerStatement = connection.prepareStatement(insertQuery)) {
                    for (Stalker stalker : stalkers) {
                        // Установка first_name, second_name и money для сталкера
                        stalkerStatement.setString(1, stalker.getFirstName());
                        stalkerStatement.setString(2, stalker.getSecondName());
                        stalkerStatement.setInt(8, stalker.getMoney());

                        // group_id
                        groupStatement.setString(1, stalker.getGroup());
                        groupStatement.execute();
                        groupStatement.getResultSet().next();
                        stalkerStatement.setInt(3, groupStatement.getResultSet().getInt("group_id"));

                        // rank_id
                        rankStatement.setString(1, stalker.getRank());
                        rankStatement.execute();
                        rankStatement.getResultSet().next();
                        stalkerStatement.setInt(4, rankStatement.getResultSet().getInt("rank_id"));

                        // location_id
                        locationStatement.setString(1, stalker.getLocation());
                        locationStatement.execute();
                        locationStatement.getResultSet().next();
                        stalkerStatement.setInt(5, locationStatement.getResultSet().getInt("location_id"));

                        // suit_id
                        suitStatement.setString(1, stalker.getSuit());
                        suitStatement.execute();
                        suitStatement.getResultSet().next();
                        stalkerStatement.setInt(6, suitStatement.getResultSet().getInt("suit_id"));

                        // weapon_id
                        weaponStatement.setString(1, stalker.getWeapon());
                        weaponStatement.execute();
                        weaponStatement.getResultSet().next();
                        stalkerStatement.setInt(7, weaponStatement.getResultSet().getInt("weapon_id"));

                        stalkerStatement.addBatch();
                    }
                    stalkerStatement.executeBatch();
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



        public static void main(String[] args) {
            DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost:5432/stalkers_db", "postgres", "postgres");
            List<Stalker> stalkersA = JsonFileDeserializer.deserializeObjects("D:\\Java Projects\\Stalker\\stalkers.json");
            System.out.println(stalkersA);
            databaseManager.insertGroups(Group.getGroups());
            databaseManager.insertRanks(Rank.getRanks());
            databaseManager.insertLocations(Location.getLocations());
            databaseManager.insertSuits(Suit.getSuits());
            databaseManager.insertWeapons(Weapon.getWeapons());
            databaseManager.insertStalkers(stalkersA);
        }
    }