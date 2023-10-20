package org.example.stalker.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weapon {
/*
    public enum WeaponEnum {
        ABAKAN("Абакан"),
        AK74("АК-74"),
        AK74U("АК-74У"),
        BERETTA("Беретта"),
        COLT("Кольт"),
        FN2000("ФН-2000"),
        G36("G36"),
        GAUSS("Гаусс-пушка"),
        GROZA("Гроза"),
        MP5("MP-5"),
        PB("ПБ"),
        SPAS12("SPAS-12"),
        SVD("СВД"),
        TOZ34("ТОЗ-34"),
        USP("USP"),
        VAL("Вал"),
        VINTOREZ("Винторез"),
        L85("Л-85"),
        PM("ПМ");

        private final String value;

        WeaponEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    */
    private String weapon;
@JsonCreator
    public Weapon(@JsonProperty("weapon") String weapon) {
        this.weapon = weapon;
    }

    public String getWeaponName() {
        return weapon;
    }

    public static String[] getWeapons() {
        return new String[] {"Abakan", "AK-74", "AK-74u", "Beretta",
                "Colt", "FN-2000", "G-36", "Gauss", "Groza", "MP-5",
                "PB", "SPAS-12", "SVD", "TOZ-34", "USP", "VAL",
                "Vintorez", "L-85", "PM"};
    }
}
