package org.example.stalker.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Suit {
/*
    public enum SuitEnum {
        LEATHER("Кожаная куртка"),
        BANDIT("Бандитская куртка"),
        MERCENARY("Комбинезон наёмника"),
        DAWN("Комбинезон 'Заря'"),
        DUTY("Броня 'Долга'"),
        WIND("Ветер свободы"),
        GUARD("Страж свободы"),
        MONOLITH("Комбинезон 'Монолит'"),
        SSP99("Эколог"),
        SSP99M("ССП-99М"),
        PSZ9MD("ПСЗ-9Мд 'Универсальная защита'"),
        CEBA("СЕВА"),
        BERILL("Берилл-5М"),
        ARMY("Армейский бронекостюм"),
        EXO("Экзоскелет");

        private final String value;

        SuitEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }*/
    private String suit;
@JsonCreator
    public Suit(@JsonProperty("suit") String suit) {
        this.suit = suit;
    }

    public String getSuitName() {
        return suit;
    }

    public static String[] getSuits() {
        return new String[] {"Leather", "Bandit", "Mercenary", "Dawn",
                "Duty", "Wind", "Guard", "Monolith", "SSP-99", "PSZ-9Md",
                "CEBA", "Berill", "Army", "Exo"};
    }
}
