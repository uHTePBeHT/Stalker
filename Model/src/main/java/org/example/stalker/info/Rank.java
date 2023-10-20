package org.example.stalker.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Rank {
    /*
        public enum RankEnum {
            NEWCOMER("Новичок"),
            EXPERIENCED("Опытный"),
            VETERAN("Ветеран"),
            MASTER("Мастер");

            private final String value;

            RankEnum(String value) {
                this.value = value;
            }
            public String getValue() {
                return value;
            }
        }
    */
        private String rank;
@JsonCreator
        public Rank(@JsonProperty("rank") String rank) {
            this.rank = rank;
        }

        public String getRankName() {
            return rank;
        }

        public static String[] getRanks() {
            return new String[] {"Newcomer", "Experienced", "Veteran", "Master"};
        }
    }
