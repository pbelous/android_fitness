package com.fitness.fitness.model;


public class ExerciseResult {

    public ExerciseResult(int exercise_id, String date, String resultsString)
    {
        this.exercise_id = exercise_id;
        this.date = date;

        String[] s = resultsString.split("|");

        for (String ss : s)
        {
            ExerciseRep r = new ExerciseRep();
            //repsadd s
        }

    }



    public class ExerciseRep
    {
        public String reps;
        public String weight;
    }

    public int exercise_id = -1;
    public String date;
    public ExerciseRep[] reps = new ExerciseRep[]{};
}
