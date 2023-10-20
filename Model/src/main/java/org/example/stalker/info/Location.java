package org.example.stalker.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
/*
    public enum LocationEnum {
        AES("ЧАЭС"),
        AGROPROM("Агропром"),
        BAR("Бар"),
        DARKVALLEY("Тёмная Долина"),
        DEADCITY("Дикая территория"),
        ESCAPE("Кордон"),
        GARBAGE("Свалка"),
        MILITARY("Армейские склады"),
        PRIPYAT("Припять"),
        RADAR("Радар"),
        YANTAR("Янтарь");

        private final String value;

        LocationEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
*/
    private String location;
@JsonCreator
    public Location(@JsonProperty("location") String location) {
        this.location = location;
    }

    public String getLocationName() {
        return location;
    }

    public static String[] getLocations() {
        return new String[] {"AES", "Agroprom", "Bar", "Dark Valley",
                "Dead City", "Escape", "Garbage", "Military", "Pripyat",
                "Radar", "Yantar"};
    }
}
