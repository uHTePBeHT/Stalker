package org.example.stalker.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Money {
    private int money;
@JsonCreator
    public Money(@JsonProperty("money") int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
