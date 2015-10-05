package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ScheduleActivity extends Activity {

    Database db = null;
    String date = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
         date = bundle.getString("timestamp");

        if (date == null)
            date = "28-09-2015";



        Button cancel = (Button)findViewById(R.id.button_new_cancel);
        Button add_excersize = (Button)findViewById(R.id.button_add_excersize);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_excersize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewExcersizeActivity();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateExercisesList();
    }

    void updateExercisesList()
    {
        ListView listview = (ListView)findViewById(R.id.SchedulelistView);

        Cursor c = db.query(date);

        ScheduleAdapter adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(adapter);
    }

    void openNewExcersizeActivity()
    {
        Intent intent = new Intent(this, NewExersizeActivity.class);
        startActivity(intent);
    }
}
