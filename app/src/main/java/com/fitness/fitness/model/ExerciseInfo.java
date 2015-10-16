package com.fitness.fitness.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.fitness.fitness.R;
import com.fitness.fitness.utils.ExerciseParser;

import java.io.InputStream;
import java.util.List;

public class ExerciseInfo {



    public static List<ExerciseInfoRecord> getExerciseEnfo(Context context)
    {
        InputStream istream = context.getResources().openRawResource(R.raw.exercises_info);

        return ExerciseParser.parse(istream);
    }


}
