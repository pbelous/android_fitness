package com.fitness.fitness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.DataInputStream;
import java.util.ArrayList;

/**
 * Created by pbelous on 01.10.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    final static int DB_VER = 2;
    final static String DB_NAME = "todo.db";
    public final String TABLE_NAME = "todo";

    final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            "( _id INTEGER PRIMARY KEY , "+
            " timestamp TEXT , "+
            " desc TEXT , "+
            " name TEXT , "+
            " icon INTEGER)";
    final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    //final String DATA_FILE_NAME = "data.txt";

    Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
        Log.d("DBHelper", "constructor called");
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "onCreate() called");
        db.execSQL(CREATE_TABLE);
        //fillData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

}