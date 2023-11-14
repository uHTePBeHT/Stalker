package org.example;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//import org.example.stalker.bio.Fname;
//import org.example.stalker.bio.Sname;
import org.example.stalker.info.Group;
import org.example.stalker.info.Location;
import org.example.stalker.info.Rank;
import org.example.stalker.inventory.Money;
import org.example.stalker.inventory.Suit;
import org.example.stalker.inventory.Weapon;

import java.util.List;
import java.util.Random;

public class Stalker {
    private final int id;
    private final String firstName;
    private final String secondName;
    private String location;
    private String suit;
    private String weapon;
    private int money;
    private String rank;
    private String group;


@JsonCreator
    public Stalker(@JsonProperty("id") int id, @JsonProperty("firstName") String firstName, @JsonProperty("secondName") String secondName, @JsonProperty("location") String location, @JsonProperty("suit") String suit, @JsonProperty("weapon") String weapon, @JsonProperty("money") int money, @JsonProperty("rank") String rank, @JsonProperty("group") String group) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.location = location;
        this.suit = suit;
        this.weapon = weapon;
        this.money = money;
        this.rank = rank;
        this.group = group;
    }



    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLocation() {
        return location;
    }

    public String getSuit() {
        return suit;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getMoney() {
        return money;
    }

    public String getRank() {
        return rank;
    }

    public String getGroup() {
        return group;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
