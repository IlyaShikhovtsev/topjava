package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {

    Meal get(Integer id);

    Collection<Meal> getAll();

    boolean remove(Integer id);

    boolean save(Meal meal);
}