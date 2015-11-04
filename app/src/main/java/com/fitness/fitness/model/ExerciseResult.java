package com.fitness.fitness.model;


import java.util.ArrayList;

public class ExerciseResult {
    public int exercise_id = -1;
    public String date = "";
    public double resultWeight;
    public double resultReps;

    public class ExerciseRep
    {
        public String reps;
        public String weight;
    }

    public ArrayList<ExerciseRep> reps = new ArrayList<ExerciseRep>();

    public ExerciseResult(int exercise_id, String date, double resultWeight, int resultReps)
    {
        this.exercise_id = exercise_id;
        this.date = date;
        this.resultWeight = resultWeight;
        this.resultReps = resultReps;

        //reloadResultsFromString(resultsString);
    }

    //format: 20 5\n10 5

    /*
    void reloadResultsFromString(String resultsString)
    {
        reps.clear();

        String[] results = resultsString.split("\n");

        for (String s : results)
        {
            String[] result = s.split(" ");
            if (result.length == 2) {
                ExerciseRep r = new ExerciseRep();
                r.reps = result[0];
                r.weight = result[1];
                reps.add(r);
            }
        }
    }

    public void addResult(String weight, String reps)
    {
        resultsString  = resultsString + "\n" + weight + " " + reps;
        reloadResultsFromString(resultsString);
    }
    */
}
