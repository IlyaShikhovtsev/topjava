package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

public class MealRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGetAll() throws Exception {
        String json = mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Meal> meals = JsonUtil.readValues(json, MealWithExceed.class)
                .stream()
                .map(mealWithExceed -> new Meal(mealWithExceed.getId(), mealWithExceed.getDateTime(), mealWithExceed.getDescription(), mealWithExceed.getCalories()))
                .collect(Collectors.toList());
        assertMatch(meals, MEALS);
    }

    @Test
    public void testGet() throws Exception {
        String json = mockMvc.perform(get(REST_URL + "/" + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
        assertMatch(JsonUtil.readValues(json, Meal.class), MEAL1);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(MEAL1.getId(), MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories());
        meal.setDescription("Updated meal");
        meal.setCalories(666);
        mockMvc.perform(put(REST_URL + "/" + MEAL1_ID).content(JsonUtil.writeValue(meal)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(mealService.get(meal.getId(), USER_ID), meal);
    }

    @Test
    public void testCreate() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "new Meal", 999);
        mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(meal)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getBetweenTest() throws Exception {
        String json = mockMvc.perform(get(REST_URL +
                "/between?startDate=" + LocalDate.of(2015, Month.MAY, 30) +
                "&startTime=" + LocalTime.of(10, 0).format(DateTimeFormatter.ISO_LOCAL_TIME) +
                "&endDate=" + LocalDate.of(2015, Month.MAY, 30).format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "&endTime=" + LocalTime.of(20, 0).format(DateTimeFormatter.ISO_LOCAL_TIME)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Meal> meals = JsonUtil.readValues(json, MealWithExceed.class)
                .stream()
                .map(mealWithExceed -> new Meal(mealWithExceed.getId(), mealWithExceed.getDateTime(), mealWithExceed.getDescription(), mealWithExceed.getCalories()))
                .collect(Collectors.toList());
        assertMatch(meals, MEAL3, MEAL2, MEAL1);
    }
}