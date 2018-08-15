package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepositoryImpl.class);

    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    private AtomicInteger index = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }


    @Override
    public Meal get(Integer id) {
        return meals.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return meals.values();
    }

    @Override
    public boolean remove(Integer id) {
        meals.remove(id);
        return true;
    }

    @Override
    public boolean save(Meal meal) {
        if(meal.isNew()) {
            meal.setId(index.getAndAdd(1));
        }
        meals.put(meal.getId(), meal);
        return true;
    }
}