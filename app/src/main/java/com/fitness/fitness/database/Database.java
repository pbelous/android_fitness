package com.fitness.fitness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fitness.fitness.R;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseResult;

public class Database {
    private  final Context context;
    private DBHelper DBHelper;
    private SQLiteDatabase sqlitedatabase;

    public Database(Context ctx){
        context = ctx;

        DBHelper = new DBHelper(context);
    }

    public void addRecord(Exercise exercise, String timestamp)
    {
        if (checkRecords(exercise, timestamp))
            return; //do not add if exist

        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("desc", exercise.name);
        values.put("name", exercise.name);
        values.put("icon", 1);
        values.put("exercise_id", exercise.id);

        DBHelper.getWritableDatabase().insert(DBHelper.SCHEDULE_TABLE_NAME, null, values);
    }

    public Cursor queryResultsWithDate(String timestamp, int exercise_id)
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, result, exercise_id FROM " + DBHelper.RESULTS_TABLE_NAME
                + " WHERE timestamp = '" + timestamp + "' and exercise_id = " + exercise_id, null);
    }

    public Cursor queryResults(int exercise_id)
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, result, exercise_id FROM " + DBHelper.RESULTS_TABLE_NAME
                + " WHERE exercise_id = " + exercise_id, null);
    }

    public ExerciseResult queryResultWithDate(String timestamp, int exercise_id)
    {
        ExerciseResult result = null;

        Cursor c = (queryResultsWithDate(timestamp, exercise_id));

        if (c.getCount() > 0)
        {
            c.moveToFirst();

            result = new ExerciseResult(exercise_id, timestamp, c.getString(c.getColumnIndex("result")));
        }
        return result;
    }

    public ExerciseResult queryResult(int exercise_id)
    {
        ExerciseResult result = null;

        Cursor c = (queryResults(exercise_id));

        if (c.moveToFirst())
        {
            Log.i("fff", "queryResult c  OK " +  c.getCount());
        }

        if (c.getCount() > 0)
        {
            c.moveToFirst();

            result = new ExerciseResult(exercise_id, c.getString(c.getColumnIndex("timestamp")), c.getString(c.getColumnIndex("result")));
        }
        return result;
    }

    public void addResult(ExerciseResult result)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", result.date);
        values.put("exercise_id", result.exercise_id);
        values.put("result", result.resultsString);

        int rows = DBHelper.getWritableDatabase().update(DBHelper.RESULTS_TABLE_NAME, values, "timestamp=? and exercise_id=?", new String[] {result.date, String.valueOf(result.exercise_id)} );

        if (rows < 1)
        {
            DBHelper.getWritableDatabase().insert(DBHelper.RESULTS_TABLE_NAME, null, values);
        }
    }

    public Cursor queryWithDate(String timestamp)
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, desc, name, exercise_id, icon FROM " + DBHelper.SCHEDULE_TABLE_NAME
                + " WHERE timestamp = '" + timestamp + "'", null);
    }

    public Cursor query()
    {
        Cursor c = DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, desc, name, exercise_id, icon FROM " +
                DBHelper.SCHEDULE_TABLE_NAME, null);
        if (c.moveToFirst())
        {
            Log.i("fff", "query c  OK " +  c.getCount());
        }

        return c;
    }

    public void deleteSchedule(String timestamp, int exercise_id)
    {
        DBHelper.getWritableDatabase().delete(DBHelper.SCHEDULE_TABLE_NAME, "timestamp=? and exercise_id=?", new String[] {timestamp, String.valueOf(exercise_id)});
    }

    public void moveSchedule(String timestamp, String newdate, int exercise_id)
    {
        //DBHelper.getWritableDatabase().delete(DBHelper.SCHEDULE_TABLE_NAME, "timestamp=? and exercise_id=?", new String[] {timestamp, String.valueOf(exercise_id)});
        Log.i("h", "moveeee to" + newdate);
    }

    public boolean checkRecords(Exercise exercise, String timestamp)
    {
        Boolean exist = false;

        if (exercise != null)
        {
            Cursor c = DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp FROM " + DBHelper.SCHEDULE_TABLE_NAME
                    + " WHERE timestamp = '" + timestamp + "' and exercise_id = " + exercise.id, null);
            exist = c.getCount() > 0;
            c.close();
        }
        else
        {
            Cursor c = DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp FROM " + DBHelper.SCHEDULE_TABLE_NAME
                    + " WHERE timestamp = '" + timestamp + "'", null);
            exist = c.getCount() > 0;
            c.close();
        }

        return  exist;
    }

    //----------------

    public Cursor queryAllWeight()
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, weight FROM " + DBHelper.WEIGHT_TABLE_NAME, null);
    }


    public Cursor queryWeighWithDates(String timeFrom, String timeTo)
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, weight FROM " + DBHelper.WEIGHT_TABLE_NAME
                + " WHERE timestamp = '" + timeFrom + "'", null);
    }

    public void addWeight(String timestamp, float weight)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("weight", weight);

        int rows = DBHelper.getWritableDatabase().update(DBHelper.WEIGHT_TABLE_NAME, values, "timestamp=?", new String[] {timestamp} );

        if (rows < 1)
        {
            DBHelper.getWritableDatabase().insert(DBHelper.WEIGHT_TABLE_NAME, null, values);
        }
    }

    //-------------------

    //Close Data Base
    public void closeDBHelper(){
        if(DBHelper != null){
            DBHelper.close();
        }
    }
}
