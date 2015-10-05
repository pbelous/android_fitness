package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ScheduleActivity extends Activity {

    Database db = null;
    String date = null;
    ScheduleAdapter csh_adapter = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
         date = bundle.getString("timestamp");

        if (date == null)
            date = "28-09-2015";

        ListView listview = (ListView)findViewById(R.id.SchedulelistView);

        Cursor c = db.query(date);

        csh_adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(csh_adapter);

        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExersizeResultActivity();
            }
        });


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
        Cursor c = db.query(date);
        csh_adapter.swapCursor(c);
        csh_adapter.notifyDataSetChanged();
    }

    void openNewExcersizeActivity()
    {
        Intent intent = new Intent(this, NewExersizeActivity.class);
        intent.putExtra("timestamp", date);
        startActivity(intent);
    }

    void openExersizeResultActivity()
    {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
       // intent.putExtra("timestamp", date);
        startActivity(intent);
    }
}
