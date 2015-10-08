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

        ListView listview = (ListView)findViewById(R.id.SchedulelistView);

        Cursor c = null;

        if (date != null)
        {
            c = db.queryWithDate(date);
        }
        else
        {
            c = db.query();
        }

        adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                //cursor.moveToPosition(position);

                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));


                openExerciseResultActivity(timestamp, exercise_id);
            }
        });


        Button cancel = (Button)findViewById(R.id.button_new_cancel);
        Button add_exercise = (Button)findViewById(R.id.button_add_excersize);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewExerciseActivity();
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
        Cursor c = null;

        if (date != null)
        c = db.queryWithDate(date);
        else
        c= db.query();

        adapter.swapCursor(c);
        adapter.notifyDataSetChanged();
    }

    void openNewExerciseActivity()
    {
        Intent intent = new Intent(this, NewExersizeActivity.class);
        intent.putExtra("timestamp", date);
        startActivity(intent);
    }

    void openExerciseResultActivity(String timestamp, int exercise_id)
    {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
        intent.putExtra("timestamp", timestamp);
        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
