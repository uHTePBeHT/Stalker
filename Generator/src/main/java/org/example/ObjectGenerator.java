package org.example;


import org.example.stalker.info.Group;
import org.example.stalker.info.Location;
import org.example.stalker.info.Rank;
import org.example.stalker.inventory.Money;
import org.example.stalker.inventory.Suit;
import org.example.stalker.inventory.Weapon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectGenerator {
    private static Random random = new Random();

    private static List<String> FIRST_NAMES = loadNames("Model/src/main/resources/firstNames.txt");
    private static List<String> SECOND_NAMES = loadNames("Model/src/main/resources/secondNames.txt");

    private static List<String> loadNames(String fileName) {
        List<String> names = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, "Windows-1251");
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }


    public static List<Stalker> generateStalkerList(int count) {
        List<Stalker> stalkers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Stalker stalker = new Stalker(generateFirstName(), generateSecondName(), generateLocation(),
                    generateSuit(), generateWeapon(), generateMoney(), generateRank(), generateGroup());
            stalkers.add(stalker);
        }
        return stalkers;
    }

    private static String generateFirstName() {
        String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
        return firstName;
    }

    private static String generateSecondName() {
        String secondName = SECOND_NAMES.get(random.nextInt(SECOND_NAMES.size()));
        return secondName;
    }


    private static Money generateMoney() {
        Money money = new Money(random.nextInt((5000 - 400) / 5 + 1) * 5 + 400);
        return money;
    }

    private static Group generateGroup() {
        Group group = new Group(Group.getGroups()[random.nextInt(Group.getGroups().length)]);
        return group;
    }

    private static Location generateLocation() {
        Location location = new Location(Location.getLocations()[random.nextInt(Location.getLocations().length)]);
        return location;
    }

    private static Rank generateRank() {
        Rank rank = new Rank(Rank.getRanks()[random.nextInt(Rank.getRanks().length)]);
        return rank;
    }

    private static List<Suit> generateSuit() {
        int randNum = random.nextInt(3) + 1;
        List<Suit> stalkerSuit = new ArrayList<>();
        for (int i = 0; i < randNum; i++) {
            Suit suit = new Suit(Suit.getSuits()[random.nextInt(Suit.getSuits().length)]);
            stalkerSuit.add(suit);
        }
        return stalkerSuit;
    }

    private static List<Weapon> generateWeapon() {
        int randNum = random.nextInt(3) + 1;
        List<Weapon> stalkerWeapon = new ArrayList<>();
        for (int i = 0; i < randNum; i++) {
            Weapon weapon = new Weapon(Weapon.getWeapons()[random.nextInt(Weapon.getWeapons().length)]);
            stalkerWeapon.add(weapon);
        }
        return stalkerWeapon;
    }
}