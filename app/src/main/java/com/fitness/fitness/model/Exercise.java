package com.fitness.fitness.model;

import android.content.Context;

import com.fitness.fitness.R;

import java.util.ArrayList;

public class Exercise {
    public String name;
    public String description;
    public int resource;

    public static final int EXER_TYPE_ALL = 0;
    public static final int EXER_TYPE_BASE = 1;
    public static final int EXER_TYPE_ARM = 2;
    public static final int EXER_TYPE_LEG = 3;

    public Exercise(String name, String desc, int res)
    {
        this.name = name;
        this.description = desc;
        this.resource = res;
    }

    public static Exercise[] getExersizes(Context ctx, int type)
    {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        switch (type){
            case EXER_TYPE_ALL:
            {
                exercises.add(new Exercise(ctx.getResources().getString(R.string.bench_press), "test", R.drawable.bench_press_64));
                exercises.add(new Exercise("test", "test", R.drawable.br_64));
                exercises.add(new Exercise("test", "test", R.drawable.chest_64));
                exercises.add(new Exercise("test", "test", R.drawable.press2_64));
                exercises.add(new Exercise("test", "test", R.drawable.pull1_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.pull_up), "test", R.drawable.pull_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.leg_press), "Leg press", R.drawable.push_64));
                exercises.add(new Exercise("test", "test", R.drawable.run_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.squat), "test", R.drawable.squat_64));
            }
            break;
            case EXER_TYPE_BASE:
            {
                exercises.add(new Exercise(ctx.getResources().getString(R.string.bench_press), "test", R.drawable.bench_press_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.squat), "test", R.drawable.squat_64));
                //exercises.add(new Exercise("test", "test", R.drawable.br_64));
                //exercises.add(new Exercise("test", "test", R.drawable.chest_64));
                //exercises.add(new Exercise("test", "test", R.drawable.press2_64));
                //exercises.add(new Exercise("test", "test", R.drawable.pull1_64));
                //exercises.add(new Exercise("test", "test", R.drawable.pull_64));
                //exercises.add(new Exercise("test", "test", R.drawable.push_64));
                //exercises.add(new Exercise("test", "test", R.drawable.run_64));
            }
            break;
            case EXER_TYPE_ARM:
            {
                exercises.add(new Exercise(ctx.getResources().getString(R.string.bench_press), "test", R.drawable.bench_press_64));
                exercises.add(new Exercise("test", "test", R.drawable.br_64));
                exercises.add(new Exercise("test", "test", R.drawable.chest_64));
                exercises.add(new Exercise("test", "test", R.drawable.press2_64));
                exercises.add(new Exercise("test", "test", R.drawable.pull1_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.pull_up), "test", R.drawable.pull_64));
                //exercises.add(new Exercise("test", "test", R.drawable.push_64));
                //exercises.add(new Exercise("test", "test", R.drawable.run_64));
            }
            break;
            case EXER_TYPE_LEG:
            {
                //exercises.add(new Exercise("test", "test", R.drawable.bench_press_64));
                //exercises.add(new Exercise("test", "test", R.drawable.br_64));
                //exercises.add(new Exercise("test", "test", R.drawable.chest_64));
                //exercises.add(new Exercise("test", "test", R.drawable.press2_64));
                //exercises.add(new Exercise("test", "test", R.drawable.pull1_64));
                //exercises.add(new Exercise("test", "test", R.drawable.pull_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.leg_press), "test", R.drawable.push_64));
                exercises.add(new Exercise("test", "test", R.drawable.run_64));
                exercises.add(new Exercise(ctx.getResources().getString(R.string.squat), "test", R.drawable.squat_64));
            }
            break;
        }

        Exercise[] exersizes_array = new Exercise[exercises.size()];

        return exercises.toArray(exersizes_array);
    }

    public static Exercise[] getAllExersizes(Context ctx)
    {
        Exercise[] exercises = new Exercise[]{
                new Exercise(ctx.getResources().getString(R.string.bench_press), "test", R.drawable.bench_press_64),
                new Exercise("test", "test", R.drawable.br_64),
                new Exercise("test", "test", R.drawable.chest_64),
                new Exercise("test", "test", R.drawable.press2_64),
                new Exercise("test", "test", R.drawable.pull1_64),
                new Exercise(ctx.getResources().getString(R.string.pull_up), "test", R.drawable.pull_64),
                new Exercise(ctx.getResources().getString(R.string.leg_press), "test", R.drawable.push_64),
                new Exercise("test", "test", R.drawable.run_64)
        };

        return exercises;
    }
}
