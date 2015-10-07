package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fitness.fitness.adapters.ExerciseResultAdapter;
import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ExerciseResultActivity extends Activity {

    ExerciseResultAdapter adapter = null;
    Database db = null;
    String date = "";
    int exercise_id = -1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersize_result_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            date = bundle.getString("timestamp");
            exercise_id = bundle.getInt("id");
        }

        if (date == null)
            date = "";

        ListView resultsList = (ListView)findViewById(R.id.listViewExersizeResult);

        Cursor c = db.queryResults(date, exercise_id);

        adapter = new ExerciseResultAdapter(this, c);

        resultsList.setAdapter(adapter);

        //adapter.se


        Button editResultsButton = (Button)findViewById(R.id.button_add);

        editResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditResultsActivity();
            }
        });
    }

    void openEditResultsActivity()
    {
        Intent intent = new Intent(this, AddExerciseResultActivity.class);
        intent.putExtra("timestamp", date);
        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
