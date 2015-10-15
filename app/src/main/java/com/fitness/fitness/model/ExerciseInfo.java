package com.fitness.fitness.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.fitness.fitness.R;

public class ExerciseInfo {
    public class ExerciseInfoRecord
    {
        public String title;
        public int icon;
        public String description;
    }


    public ExerciseInfoRecord[] getExerciseEnfo(Context context)
    {
        Resources res = context.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.exercises_info);


        return null;
    }

    public void setName(String name)
    {

    }

    public void setIcon(String icon)
    {

    }

    public void setDescription(String icon)
    {

    }
}
