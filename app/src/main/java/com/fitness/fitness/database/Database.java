package com.fitness.fitness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        addRecord("test", "28-09-2015");
        addRecord("test", "2015-10-01");
        addRecord("test", "2015-10-15");
    }

    public void addRecord(String description, String timestamp)
    {
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp);
        values.put("desc", description);

        DBHelper.getWritableDatabase().insert(DBHelper.TABLE_NAME, null, values);
    }


    public Cursor query(String timestamp)
    {
       return DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, desc FROM " + DBHelper.TABLE_NAME
               + " WHERE timestamp = '" + timestamp + "'", null);
    }

    public boolean checkRecords(String timestamp)
    {
       Cursor c =  DBHelper.getReadableDatabase().rawQuery("SELECT _id, timestamp, desc FROM " + DBHelper.TABLE_NAME
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
