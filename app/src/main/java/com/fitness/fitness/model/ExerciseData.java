package com.fitness.fitness.model;

import android.content.Context;

import com.fitness.fitness.R;

import java.io.InputStream;
import java.util.List;

public class ExerciseData {

    private static ExerciseData instance = new ExerciseData();

    private List<ExerciseCategory> categories = null;

    private Context context = null;

    private ExerciseData()
    {

    }

    public static ExerciseData getInstance()
    {
        return instance;
    }

    public void init(Context context)
    {
        this.context = context;

        parseExerciseXML(context);
    }

    private void parseExerciseXML(Context context)
    {
        InputStream istream = context.getResources().openRawResource(R.raw.exercises);
        ExerciseCategoryParser parser = new ExerciseCategoryParser();
        categories = parser.parse(istream);
    }

    public List<ExerciseCategory> getCategories()
    {
        return categories;
    }

    public ExerciseCategory getCategory(String type)
    {
        for (ExerciseCategory category : categories)
        {
            if (category.type.equalsIgnoreCase(type))
                return category;
        }
        return null;
    }

    public Exercise getExerciseById(int id)
    {
        for (ExerciseCategory category : categories)
        {
            List<Exercise> exercises = category.getExercises();

            for (Exercise exercise : exercises)
            if (exercise.id == id)
                return exercise;
        }
        return null;
    }

}
