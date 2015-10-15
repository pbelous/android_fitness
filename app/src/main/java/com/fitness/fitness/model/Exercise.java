package com.fitness.fitness.model;

import android.content.Context;

import com.fitness.fitness.R;

import java.util.ArrayList;

public class Exercise {
    public String name;
    public String description;
    public int resource;
    public int exercise_id = -1;

    public static final int EXER_TYPE_ALL = 0;
    public static final int EXER_TYPE_BASE = 1;
    public static final int EXER_TYPE_ARM = 2;
    public static final int EXER_TYPE_LEG = 3;
    public static final int EXER_TYPE_CHEST = 4;
    public static final int EXER_TYPE_SHOULDER = 5;

    public static final int EXER_BENCH_PRESS = 0;
    public static final int EXER_PARALLEL_BAR_DIP = 1;
    public static final int EXER_STRENGTH_CHEST = 2;
    public static final int EXER_PUSH_UP = 3;
    public static final int EXER_SEATED_LAT_PULLDOWN = 4;
    public static final int EXER_PULL_UP = 5;
    public static final int EXER_LEG_PRESS = 6;
    public static final int EXER_RUN = 7;
    public static final int EXER_SQUAT = 8;


    public Exercise(int exercise_id, String name, String desc, int res)
    {
        this.name = name;
        this.description = desc;
        this.resource = res;
        this.exercise_id = exercise_id;
    }

    public static Exercise getExersise(Context ctx, int id)
    {
        Exercise ex = null;
        switch (id) {
            case EXER_BENCH_PRESS: {
                ex =  new Exercise(EXER_BENCH_PRESS, ctx.getResources().getString(R.string.bench_press), "bench press", R.drawable.bench_press_64);
            }
            break;
            case EXER_PARALLEL_BAR_DIP: {
                ex = new Exercise(EXER_PARALLEL_BAR_DIP, ctx.getResources().getString(R.string.parallel_bar_dip), "parallel bar dip", R.drawable.br_64);
            }
            break;
            case EXER_STRENGTH_CHEST: {
                ex = new Exercise(EXER_STRENGTH_CHEST, "strength chest", "strength chest", R.drawable.chest_64);
            }
            break;
            case EXER_PUSH_UP: {
                ex = new Exercise(EXER_PUSH_UP, ctx.getResources().getString(R.string.push_up), "push up", R.drawable.press2_64);
            }
            break;
            case EXER_SEATED_LAT_PULLDOWN: {
                ex =  new Exercise(EXER_SEATED_LAT_PULLDOWN, ctx.getResources().getString(R.string.seated_lat_pulldown), "SEATED_LAT_PULLDOWN", R.drawable.pull1_64);
            }
            break;
            case EXER_PULL_UP: {
                ex = new Exercise(EXER_PULL_UP, ctx.getResources().getString(R.string.pull_up), "pull up", R.drawable.pull_64);
            }
            break;
            case EXER_LEG_PRESS: {
                ex = new Exercise(EXER_LEG_PRESS, ctx.getResources().getString(R.string.leg_press), "Leg press", R.drawable.push_64);;
            }
            break;
            case EXER_RUN: {
                ex = new Exercise(EXER_RUN, ctx.getResources().getString(R.string.run), "run", R.drawable.run_64);
            }
            break;
            case EXER_SQUAT: {
                ex = new Exercise(EXER_SQUAT, ctx.getResources().getString(R.string.squat), "squat", R.drawable.squat_64);
            }
            break;
        }

        return ex;
    }

    public static Exercise[] getExercises(Context ctx, int type)
    {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        switch (type){
            case EXER_TYPE_ALL:
            {
                exercises.add(getExersise(ctx, EXER_BENCH_PRESS));
                exercises.add(getExersise(ctx, EXER_PARALLEL_BAR_DIP));
                exercises.add(getExersise(ctx, EXER_STRENGTH_CHEST));
                exercises.add(getExersise(ctx, EXER_PUSH_UP));
                exercises.add(getExersise(ctx, EXER_SEATED_LAT_PULLDOWN));
                exercises.add(getExersise(ctx, EXER_PULL_UP));
                exercises.add(getExersise(ctx, EXER_LEG_PRESS));
                exercises.add(getExersise(ctx, EXER_RUN));
                exercises.add(getExersise(ctx, EXER_SQUAT));
            }
            break;
            case EXER_TYPE_BASE:
            {
                exercises.add(getExersise(ctx, EXER_BENCH_PRESS));
                exercises.add(getExersise(ctx, EXER_SQUAT));
                exercises.add(getExersise(ctx, EXER_PULL_UP));
            }
            break;
            case EXER_TYPE_ARM:
            {
                exercises.add(getExersise(ctx, EXER_BENCH_PRESS));
                exercises.add(getExersise(ctx, EXER_PARALLEL_BAR_DIP));
                exercises.add(getExersise(ctx, EXER_STRENGTH_CHEST));
                exercises.add(getExersise(ctx, EXER_PUSH_UP));
                exercises.add(getExersise(ctx, EXER_SEATED_LAT_PULLDOWN));
                exercises.add(getExersise(ctx, EXER_PULL_UP));
            }
            break;
            case EXER_TYPE_LEG:
            {
                exercises.add(getExersise(ctx, EXER_LEG_PRESS));
                exercises.add(getExersise(ctx, EXER_RUN));
                exercises.add(getExersise(ctx, EXER_SQUAT));
            }
            break;
            case EXER_TYPE_CHEST:
            {
                exercises.add(getExersise(ctx, EXER_BENCH_PRESS));
                exercises.add(getExersise(ctx, EXER_PARALLEL_BAR_DIP));
                exercises.add(getExersise(ctx, EXER_STRENGTH_CHEST));
                //exercises.add(getExersise(ctx, EXER_PUSH_UP));
                //exercises.add(getExersise(ctx, EXER_SEATED_LAT_PULLDOWN));
                exercises.add(getExersise(ctx, EXER_PULL_UP));
                //exercises.add(getExersise(ctx, EXER_LEG_PRESS));
                //exercises.add(getExersise(ctx, EXER_RUN));
                //exercises.add(getExersise(ctx, EXER_SQUAT));
            }
            break;
            case EXER_TYPE_SHOULDER:
            {
                //exercises.add(getExersise(ctx, EXER_BENCH_PRESS));
                exercises.add(getExersise(ctx, EXER_PARALLEL_BAR_DIP));
                //exercises.add(getExersise(ctx, EXER_STRENGTH_CHEST));
                //exercises.add(getExersise(ctx, EXER_PUSH_UP));
                //exercises.add(getExersise(ctx, EXER_SEATED_LAT_PULLDOWN));
                //exercises.add(getExersise(ctx, EXER_PULL_UP));
                //exercises.add(getExersise(ctx, EXER_LEG_PRESS));
                //exercises.add(getExersise(ctx, EXER_RUN));
                //exercises.add(getExersise(ctx, EXER_SQUAT));
            }
            break;
        }

        Exercise[] exersizes_array = new Exercise[exercises.size()];

        return exercises.toArray(exersizes_array);
    }

    /*
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
    */
}
