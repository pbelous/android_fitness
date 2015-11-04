package com.fitness.fitness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.DataInputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static int DB_VER = 11;
    final static String DB_NAME = "todo.db";

    //---------------------------------------------------

    public final String SCHEDULE_TABLE_NAME = "schedule";

    final String CREATE_SCHEDULE_TABLE = "CREATE TABLE "+SCHEDULE_TABLE_NAME+
            "( _id INTEGER PRIMARY KEY , "+
            " timestamp TEXT , "+
            " desc TEXT , "+
            " name TEXT , "+
            " exercise_id INTEGER , "+
            " icon INTEGER)";
    final String DROP_SCHEDULE_TABLE = "DROP TABLE IF EXISTS "+SCHEDULE_TABLE_NAME;

    //----------------------------------------------------

    public final String RESULTS_TABLE_NAME = "results";

    final String CREATE_RESULTS_TABLE = "CREATE TABLE "+RESULTS_TABLE_NAME+
            "( _id INTEGER PRIMARY KEY , "+
            " timestamp TEXT , "+
            " exercise_id INTEGER , "+
            " result_weight REAL , "+
            " result_reps INTEGER)";
    final String DROP_RESULTS_TABLE = "DROP TABLE IF EXISTS "+RESULTS_TABLE_NAME;

    //----------------------------------------------------

    public final String WEIGHT_TABLE_NAME = "weight";

    final String CREATE_WEIGHT_TABLE = "CREATE TABLE "+WEIGHT_TABLE_NAME+
            "( _id INTEGER PRIMARY KEY , "+
            " timestamp TEXT , "+
            " weight REAL)";

    final String DROP_WEIGHT_TABLE = "DROP TABLE IF EXISTS "+WEIGHT_TABLE_NAME;

    Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
        Log.d("DBHelper", "constructor called");
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "onCreate() called");
        db.execSQL(CREATE_SCHEDULE_TABLE);
        db.execSQL(CREATE_RESULTS_TABLE);
        db.execSQL(CREATE_WEIGHT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_SCHEDULE_TABLE);
        db.execSQL(DROP_RESULTS_TABLE);
        db.execSQL(DROP_WEIGHT_TABLE);
        onCreate(db);
    }

}