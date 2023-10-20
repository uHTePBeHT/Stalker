package org.example.stalker.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {
    /*public enum GroupEnum {
        FREEDOM("Свобода"),
        DOLG("ДОЛГ"),
        ECOLOGS("Ученые"),
        MILITARY("Военные"),
        BANDITS("Бандиты"),
        MONOLITH("Монолит"),
        KILLERS("Наемники"),
        ZOMBIED("Зомбированные"),
        NEUTRAL("Сталкер");

        private final String value;

        GroupEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }*/
    private String group;
@JsonCreator
    public Group(@JsonProperty("group") String group) {
        this.group = group;
    }

    public String getGroupName() {
        return group;
    }

    public static String[] getGroups() {
        return new String[] {"Freedom", "DOLG", "Ecologs", "Military",
                "Bandits", "Monolith", "Killers", "Zombied", "Neutral"};
    }
}

