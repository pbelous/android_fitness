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
import com.fitness.fitness.utils.Utils;

public class ExerciseResultActivity extends Activity {

    ExerciseResultAdapter adapter = null;
    Database db = null;

    int exercise_id = -1;
    String date = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersize_result_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            date = bundle.getString("timestamp");
            exercise_id = bundle.getInt("id");
        }

        ListView resultsList = (ListView)findViewById(R.id.listViewExersizeResult);

        Cursor c = null;

        if (date != null && date.equals(Utils.getCurrentDate())) {
            c = db.queryResultsWithDate(date, exercise_id);
        }
        else
        {
            c = db.queryResults(exercise_id);
        }

        adapter = new ExerciseResultAdapter(this, c);

        resultsList.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = (String) v.getTag();
                openEditResultsActivity(date);
            }
        });

        Button editResultsButton = (Button)findViewById(R.id.button_add);

        editResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditResultsActivity(Utils.getCurrentDate());
            }
        });

        Button okButton = (Button)findViewById(R.id.button_ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateResultsList();
    }

    void updateResultsList()
    {
        Cursor c = null;

        if (date != null && date.equals(Utils.getCurrentDate()))
            c = db.queryResultsWithDate(date, exercise_id);
        else
            c= db.queryResults(exercise_id);

        adapter.swapCursor(c);
        adapter.notifyDataSetChanged();
    }

    void openEditResultsActivity(String date)
    {
        Intent intent = new Intent(this, AddExerciseResultActivity.class);

        if (date != null) {
            intent.putExtra("timestamp", date);
        }

        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
