package com.fitness.fitness.model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCategory {
    public String type;
    public String icon;
    public String name;
    public int id;

    public void setType(String type)
    {
        this.type = type;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public void setName(String name) { this.name = name; }

    public void setId(int id)
    {
        this.id = id;
    }


    private List<Exercise> exercises = new ArrayList<Exercise>();

    public List<Exercise> getExercises()
    {
        return exercises;
    }

    public void addExercise(Exercise exercise)
    {
        exercises.add(exercise);
    }
}

