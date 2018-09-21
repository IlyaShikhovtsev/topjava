package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/spring/spring-app.xml",
        "/spring/spring-db.xml"
})
@Sql(scripts = "/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        assertMatch(MEAL1, mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(MEAL1_ID, USER_ID);
        assertMatch(MEALS.subList(1, MEALS.size()), mealService.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        assertMatch(MEAL1, mealService.get(MEAL1_ID, ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        mealService.delete(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void update() {
        Meal meal = new Meal(MEAL1_ID, LocalDateTime.of(2018, 9, 20, 10, 0), "updated", 2001);
        assertMatch(mealService.update(meal, USER_ID), mealService.get(MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal meal = new Meal(MEAL1_ID, LocalDateTime.of(2018, 9, 20, 10, 0), "updated", 2001);
        mealService.update(meal, ADMIN_ID);
    }
}