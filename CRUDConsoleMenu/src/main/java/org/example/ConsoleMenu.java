package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsoleMenu {
    private static final String URL = "jdbc:postgresql://localhost:5432/stalkers_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDataCrud groupDataCrud = new GroupDataCrud(connection);
            RankCrud rankCrud = new RankCrud(connection);
            LocationCrud locationCrud = new LocationCrud(connection);
            SuitCrud suitCrud = new SuitCrud(connection);
            WeaponCrud weaponCrud = new WeaponCrud(connection);
            StalkerCrud stalkerCrud = new StalkerCrud(connection, groupDataCrud, rankCrud, locationCrud, suitCrud, weaponCrud);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Выберите таблицу для выполнения операций:");
                System.out.println("1. Group Data");
                System.out.println("2. Rank");
                System.out.println("3. Location");
                System.out.println("4. Suit");
                System.out.println("5. Weapon");
                System.out.println("6. Stalker");
                System.out.println("0. Выход");

                System.out.print("Ваш выбор: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        performGroupDataOperations(scanner, groupDataCrud);
                        break;
                    case 2:
                        performRankOperations(scanner, rankCrud);
                        break;
                    case 3:
                        performLocationOperations(scanner, locationCrud);
                        break;
                    case 4:
                        performSuitOperations(scanner, suitCrud);
                        break;
                    case 5:
                        performWeaponOperations(scanner, weaponCrud);
                        break;
                    case 6:
                        performStalkerOperations(scanner, stalkerCrud);
                        break;
                    case 0:
                        System.out.println("Выход из программы.");
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
                }
            } while (choice != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void performGroupDataOperations(Scanner scanner, GroupDataCrud groupDataCrud) {
        int choice;

        do {
            System.out.println("Операции с Group Data:");
            System.out.println("1. Создать группу");
            System.out.println("2. Получить ID группы");
            System.out.println("3. Обновить группу");
            System.out.println("4. Удалить группу");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название группы: ");
                    String groupName = scanner.nextLine();
                    groupDataCrud.createGroup(groupName);
                    System.out.println("Группа успешно создана.");
                    break;
                case 2:
                    System.out.print("Введите название группы: ");
                    String groupNameForId = scanner.nextLine();
                    int groupId = groupDataCrud.getGroupId(groupNameForId);
                    System.out.println("ID группы: " + groupId);
                    break;
                case 3:
                    System.out.print("Введите ID группы для обновления: ");
                    int groupIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Введите новое название группы: ");
                    String newGroupName = scanner.nextLine();
                    groupDataCrud.updateGroup(groupIdToUpdate, newGroupName);
                    System.out.println("Группа успешно обновлена.");
                    break;
                case 4:
                    System.out.print("Введите ID группы для удаления: ");
                    int groupIdToDelete = scanner.nextInt();
                    groupDataCrud.deleteGroup(groupIdToDelete);
                    System.out.println("Группа успешно удалена.");
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }

    private static void performRankOperations(Scanner scanner, RankCrud rankCrud) {
        int choice;

        do {
            System.out.println("Операции с Rank:");
            System.out.println("1. Создать ранг");
            System.out.println("2. Получить ID ранга");
            System.out.println("3. Обновить ранг");
            System.out.println("4. Удалить ранг");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название ранга: ");
                    String rankName = scanner.nextLine();
                    rankCrud.createRank(rankName);
                    System.out.println("Ранг успешно создан.");
                    break;
                case 2:
                    System.out.print("Введите название ранга: ");
                    String rankNameForId = scanner.nextLine();
                    int rankId = rankCrud.getRankId(rankNameForId);
                    System.out.println("ID ранга: " + rankId);
                    break;
                case 3:
                    System.out.print("Введите ID ранга для обновления: ");
                    int rankIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Введите новое название ранга: ");
                    String newRankName = scanner.nextLine();
                    rankCrud.updateRank(rankIdToUpdate, newRankName);
                    System.out.println("Ранг успешно обновлен.");
                    break;
                case 4:
                    System.out.print("Введите ID ранга для удаления: ");
                    int rankIdToDelete = scanner.nextInt();
                    rankCrud.deleteRank(rankIdToDelete);
                    System.out.println("Ранг успешно удален.");
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }
    private static void performLocationOperations(Scanner scanner, LocationCrud locationCrud) {
        int choice;

        do {
            System.out.println("Операции с Location:");
            System.out.println("1. Создать локацию");
            System.out.println("2. Получить ID локации");
            System.out.println("3. Обновить локацию");
            System.out.println("4. Удалить локацию");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название локации: ");
                    String locationName = scanner.nextLine();
                    locationCrud.createLocation(locationName);
                    System.out.println("Локация успешно создана.");
                    break;
                case 2:
                    System.out.print("Введите название локации: ");
                    String locationNameForId = scanner.nextLine();
                    int locationId = locationCrud.getLocationId(locationNameForId);
                    System.out.println("ID локации: " + locationId);
                    break;
                case 3:
                    System.out.print("Введите ID локации для обновления: ");
                    int locationIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Введите новое название локации: ");
                    String newLocationName = scanner.nextLine();
                    locationCrud.updateLocation(locationIdToUpdate, newLocationName);
                    System.out.println("Локация успешно обновлена.");
                    break;
                case 4:
                    System.out.print("Введите ID локации для удаления: ");
                    int locationIdToDelete = scanner.nextInt();
                    locationCrud.deleteLocation(locationIdToDelete);
                    System.out.println("Локация успешно удалена.");
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }

    private static void performSuitOperations(Scanner scanner, SuitCrud suitCrud) {
        int choice;

        do {
            System.out.println("Операции с Suit:");
            System.out.println("1. Создать костюм");
            System.out.println("2. Получить ID костюма");
            System.out.println("3. Обновить костюм");
            System.out.println("4. Удалить костюм");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название костюма: ");
                    String suitName = scanner.nextLine();
                    suitCrud.createSuit(suitName);
                    System.out.println("Костюм успешно создан.");
                    break;
                case 2:
                    System.out.print("Введите название костюма: ");
                    String suitNameForId = scanner.nextLine();
                    int suitId = suitCrud.getSuitId(suitNameForId);
                    System.out.println("ID костюма: " + suitId);
                    break;
                case 3:
                    System.out.print("Введите ID костюма для обновления: ");
                    int suitIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Введите новое название костюма: ");
                    String newSuitName = scanner.nextLine();
                    suitCrud.updateSuit(suitIdToUpdate, newSuitName);
                    System.out.println("Костюм успешно обновлен.");
                    break;
                case 4:
                    System.out.print("Введите ID костюма для удаления: ");
                    int suitIdToDelete = scanner.nextInt();
                    suitCrud.deleteSuit(suitIdToDelete);
                    System.out.println("Костюм успешно удален.");
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }

    private static void performWeaponOperations(Scanner scanner, WeaponCrud weaponCrud) {
        int choice;

        do {
            System.out.println("Операции с Weapon:");
            System.out.println("1. Создать оружие");
            System.out.println("2. Получить ID оружия");
            System.out.println("3. Обновить оружие");
            System.out.println("4. Удалить оружие");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название оружия: ");
                    String weaponName = scanner.nextLine();
                    weaponCrud.createWeapon(weaponName);
                    System.out.println("Оружие успешно создано.");
                    break;
                case 2:
                    System.out.print("Введите название оружия: ");
                    String weaponNameForId = scanner.nextLine();
                    int weaponId = weaponCrud.getWeaponId(weaponNameForId);
                    System.out.println("ID оружия: " + weaponId);
                    break;
                case 3:
                    System.out.print("Введите ID оружия для обновления: ");
                    int weaponIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Введите новое название оружия: ");
                    String newWeaponName = scanner.nextLine();
                    weaponCrud.updateWeapon(weaponIdToUpdate, newWeaponName);
                    System.out.println("Оружие успешно обновлено.");
                    break;
                case 4:
                    System.out.print("Введите ID оружия для удаления: ");
                    int weaponIdToDelete = scanner.nextInt();
                    weaponCrud.deleteWeapon(weaponIdToDelete);
                    System.out.println("Оружие успешно удалено.");
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }

    private static void performStalkerOperations(Scanner scanner, StalkerCrud stalkerCrud) {
        int choice;

        do {
            System.out.println("Операции со сталкером:");
            System.out.println("1. Создать сталкера");
            System.out.println("2. Получить ID сталкера");
            System.out.println("3. Обновить сталкера");
            System.out.println("4. Удалить сталкера");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    createStalker(scanner, stalkerCrud);
                    break;
                case 2:
                    getStalkerId(scanner, stalkerCrud);
                    break;
                case 3:
                    updateStalker(scanner, stalkerCrud);
                    break;
                case 4:
                    deleteStalker(scanner, stalkerCrud);
                    break;
                case 0:
                    System.out.println("Возвращение в предыдущее меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
            }
        } while (choice != 0);
    }

    private static void createStalker(Scanner scanner, StalkerCrud stalkerCrud) {
        System.out.print("Введите имя сталкера: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите фамилию сталкера: ");
        String secondName = scanner.nextLine();

        System.out.print("Введите ID группы сталкера: ");
        int groupId = scanner.nextInt();

        System.out.print("Введите ID ранга сталкера: ");
        int rankId = scanner.nextInt();

        System.out.print("Введите ID локации сталкера: ");
        int locationId = scanner.nextInt();

        System.out.print("Введите ID костюма сталкера: ");
        int suitId = scanner.nextInt();

        System.out.print("Введите ID оружия сталкера: ");
        int weaponId = scanner.nextInt();

        System.out.print("Введите деньги сталкера: ");
        int money = scanner.nextInt();

        stalkerCrud.createStalker(firstName, secondName, groupId, rankId, locationId, suitId, weaponId, money);
    }

    private static void getStalkerId(Scanner scanner, StalkerCrud stalkerCrud) {
        System.out.print("Введите имя сталкера: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите фамилию сталкера: ");
        String secondName = scanner.nextLine();

        int stalkerId = stalkerCrud.getStalkerId(firstName, secondName);
        if (stalkerId != -1) {
            System.out.println("ID сталкера: " + stalkerId);
        }
    }

    private static void updateStalker(Scanner scanner, StalkerCrud stalkerCrud) {
        System.out.print("Введите ID сталкера для обновления: ");
        int stalkerIdToUpdate = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите новое имя сталкера: ");
        String newFirstName = scanner.nextLine();

        System.out.print("Введите новую фамилию сталкера: ");
        String newSecondName = scanner.nextLine();

        System.out.print("Введите новый ID группы сталкера: ");
        int newGroupId = scanner.nextInt();

        System.out.print("Введите новый ID ранга сталкера: ");
        int newRankId = scanner.nextInt();

        System.out.print("Введите новый ID локации сталкера: ");
        int newLocationId = scanner.nextInt();

        System.out.print("Введите новый ID костюма сталкера: ");
        int newSuitId = scanner.nextInt();

        System.out.print("Введите новый ID оружия сталкера: ");
        int newWeaponId = scanner.nextInt();

        System.out.print("Введите новое количество денег сталкера: ");
        int newMoney = scanner.nextInt();

        stalkerCrud.updateStalker(stalkerIdToUpdate, newFirstName, newSecondName, newGroupId, newRankId, newLocationId, newSuitId, newWeaponId, newMoney);
    }

    private static void deleteStalker(Scanner scanner, StalkerCrud stalkerCrud) {
        System.out.print("Введите ID сталкера для удаления: ");
        int stalkerIdToDelete = scanner.nextInt();

        stalkerCrud.deleteStalker(stalkerIdToDelete);
    }
}