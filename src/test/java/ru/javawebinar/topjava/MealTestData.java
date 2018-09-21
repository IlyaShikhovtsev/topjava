package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int MEAL2_ID = START_SEQ + 3;
    public static final int MEAL3_ID = START_SEQ + 4;
    public static final int MEAL4_ID = START_SEQ + 5;
    public static final int MEAL5_ID = START_SEQ + 6;
    public static final int MEAL6_ID = START_SEQ + 7;
    public static final int MEAL7_ID = START_SEQ + 8;
    public static final int MEAL8_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.of(2015, 5, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.of(2015, 5, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, LocalDateTime.of(2015, 5, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL4_ID, LocalDateTime.of(2015, 5, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL5_ID, LocalDateTime.of(2015, 5, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL6_ID, LocalDateTime.of(2015, 5, 31, 20, 0), "Ужин", 510);

    public static final Meal ADMIN_MEAL7 = new Meal(MEAL7_ID, LocalDateTime.of(2015, 6, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL8 = new Meal(MEAL8_ID, LocalDateTime.of(2015, 6, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> MEALS = new ArrayList<>();

    static {
        MEALS.add(MEAL1);
        MEALS.add(MEAL2);
        MEALS.add(MEAL3);
        MEALS.add(MEAL4);
        MEALS.add(MEAL5);
        MEALS.add(MEAL6);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}