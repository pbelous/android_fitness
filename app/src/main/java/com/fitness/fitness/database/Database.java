package com.fitness.fitness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fitness.fitness.R;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseResult;

/**
 * Created by pbelous on 01.10.2015.
 */
public class Database {
    private  final Context context;
    private DBHelper DBHelper;
    private SQLiteDatabase sqlitedatabase;

    public Database(Context ctx){
        context = ctx;

        DBHelper = new DBHelper(context);

      //  addRecord("test", "28-09-2015");
      //  addRecord("test", "2015-10-01");
      //  addRecord("test", "2015-10-15");
    }

    /*
    public void addRecord(String description, String timestamp)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("desc", description);
        values.put("name", "");
        values.put("icon", R.drawable.calendar_cel_set);
        values.put("exercise_id", exercise.exercise_id);

        DBHelper.getWritableDatabase().insert(DBHelper.SCHEDULE_TABLE_NAME, null, values);
    }
    */

    public void addRecord(Exercise exercise, String timestamp)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("desc", exercise.description);
        values.put("name", exercise.name);
        values.put("icon", exercise.resource);
        values.put("exercise_id", exercise.exercise_id);

        DBHelper.getWritableDatabase().insert(DBHelper.SCHEDULE_TABLE_NAME, null, values);
    }

    public Cursor queryResults(String timestamp, int exercise_id)
    {
        return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, result FROM " + DBHelper.RESULTS_TABLE_NAME
                + " WHERE timestamp = '" + timestamp + "' and exercise_id = " + exercise_id, null);
    }

    public ExerciseResult queryResult(String timestamp, int exercise_id)
    {
        ExerciseResult result = null;

        Cursor c = (queryResults(timestamp, exercise_id));

        if (c.getCount() > 0)
        {
            c.moveToFirst();

            result = new ExerciseResult(exercise_id, timestamp, c.getString(c.getColumnIndex("result")));
        }
        return result;
    }

    public void addResult(String timestamp, String result, int exercise_id)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("exercise_id", exercise_id);
        values.put("result", result);

        DBHelper.getWritableDatabase().insertWithOnConflict(DBHelper.RESULTS_TABLE_NAME,
                "timestamp",
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor query(String timestamp)
    {
       return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, desc, name, icon FROM " + DBHelper.SCHEDULE_TABLE_NAME
               + " WHERE timestamp = '" + timestamp + "'", null);
    }

    public boolean checkRecords(String timestamp)
    {
       Cursor c =  DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp FROM " + DBHelper.SCHEDULE_TABLE_NAME
               + " WHERE timestamp = '" + timestamp + "'", null);
        return c.getCount() > 0;
        //TODO: close cursor
    }

    //Close Data Base
    public void closeDBHelper(){
        if(DBHelper != null){
            DBHelper.close();
        }
        //if (c != null) {
        //    c.close();
        //    c = null;
        //}
    }
}
