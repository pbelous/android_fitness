package com.fitness.fitness;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ScheduleActivity extends AppCompatActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        Bundle bundle = getIntent().getExtras();

        String date = null;

        if (bundle != null)
         date = bundle.getString("timestamp");

        if (date == null)
            date = "28-09-2015";

        ListView listview = (ListView)findViewById(R.id.schedule_listview);

        Database db = new Database(this);

        Cursor c = db.query(date);

        ScheduleAdapter adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(adapter);

    }
}
