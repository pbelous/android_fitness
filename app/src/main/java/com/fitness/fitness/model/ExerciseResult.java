package com.fitness.fitness.model;


public class ExerciseResult {

    public class ExerciseRep
    {
        public String reps;
        public String weight;
    }

    public String date;
    public ExerciseRep[] reps = new ExerciseRep[]{};
}
