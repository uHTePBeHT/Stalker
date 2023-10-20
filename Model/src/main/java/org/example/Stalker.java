package org.example;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.example.stalker.bio.Fname;
import org.example.stalker.bio.Sname;
import org.example.stalker.info.Group;
import org.example.stalker.info.Location;
import org.example.stalker.info.Rank;
import org.example.stalker.inventory.Money;
import org.example.stalker.inventory.Suit;
import org.example.stalker.inventory.Weapon;

import java.util.List;
import java.util.Random;

public class Stalker {
    private final String firstName;
    private final String secondName;
    private Location location;
    private List<Suit> suit;
    private List<Weapon> weapon;
    private Money money;
    private Rank rank;
    private Group group;


@JsonCreator
    public Stalker(@JsonProperty("firstName") String firstName, @JsonProperty("secondName") String secondName, @JsonProperty("location") Location location, @JsonProperty("suit") List<Suit> suit, @JsonProperty("weapon") List<Weapon> weapon, @JsonProperty("money") Money money, @JsonProperty("rank") Rank rank, @JsonProperty("group") Group group) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.location = location;
        this.suit = suit;
        this.weapon = weapon;
        this.money = money;
        this.rank = rank;
        this.group = group;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Location getLocation() {
        return location;
    }

    public List<Suit> getSuit() {
        return suit;
    }

    public List<Weapon> getWeapon() {
        return weapon;
    }

    public Money getMoney() {
        return money;
    }

    public Rank getRank() {
        return rank;
    }

    public Group getGroup() {
        return group;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSuit(List<Suit> suit) {
        this.suit = suit;
    }

    public void setWeapon(List<Weapon> weapon) {
        this.weapon = weapon;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
