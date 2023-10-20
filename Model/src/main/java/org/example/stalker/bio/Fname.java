package org.example.stalker.bio;

import java.util.Random;

public class Fname {

    public enum FnameEnum {
        ANTON("Антон"),
        OLEG("Олег"),
        VLAD("Влад"),
        ALEXANDER("Александр"),
        ANDREY("Андрей"),
        SERGEY("Сергей"),
        DMITRY("Дмитрий"),
        IVAN("Ваня"),
        MAXIM("Максим"),
        MIKHAIL("Михаил"),
        ARTEM("Артем"),
        VLADIMIR("Владимир"),
        NIKOLAY("Николай"),
        ALEXEY("Алексей"),
        DENIS("Денис"),
        PAVEL("Павел"),
        YURI("Юрий"),
        KONSTANTIN("Константин"),
        VICTOR("Виктор"),
        IGOR("Игорь"),
        ROMAN("Роман"),
        EUGENE("Евгений"),
        TIMUR("Тимур"),
        LEONID("Леонид"),
        ILYA("Илья"),
        PETER("Пётр"),
        KIRILL("Кирилл"),
        FEDOR("Фёдор"),
        ALEKSANDR("Александр"),
        VASILY("Василий"),
        NIKITA("Никита"),
        SERGEI("Сергей"),
        EGOR("Егор"),
        ARSEN("Арсен"),
        DANIL("Данил"),
        ARKADIY("Аркадий"),
        ALEKS("Алекс"),
        NIKOLAI("Николай"),
        VIKTOR("Виктор"),
        ANATOLY("Анатолий"),
        MAKSIM("Максим"),
        TIMOTHY("Тимофей"),
        VALENTIN("Валентин"),
        GEORGE("Георгий"),
        SEMYON("Семён");

        private final String value;

        FnameEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    private static final Random random = new Random();
    private final String fname;

    public Fname() {
        FnameEnum[] values = FnameEnum.values();
        int randIndex = random.nextInt(values.length);
        fname = values[randIndex].getValue();
    }

    public String getFname() {
        return fname;
    }
}
