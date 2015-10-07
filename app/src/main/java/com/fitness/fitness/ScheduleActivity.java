package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ScheduleActivity extends Activity {

    Database db = null;
    String date = null;
    ScheduleAdapter adapter = null;

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

        adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);

                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));


                openExersizeResultActivity(timestamp, exercise_id);
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
        adapter.swapCursor(c);
        adapter.notifyDataSetChanged();
    }

    void openNewExcersizeActivity()
    {
        Intent intent = new Intent(this, NewExersizeActivity.class);
        intent.putExtra("timestamp", date);
        startActivity(intent);
    }

    void openExersizeResultActivity(String timestamp, int exercise_id)
    {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
        intent.putExtra("timestamp", timestamp);
        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
