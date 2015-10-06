package com.fitness.fitness;

import android.app.Activity;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersize_result_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            date = bundle.getString("timestamp");

        if (date == null)
            date = "";

        ListView resultsList = (ListView)findViewById(R.id.listViewExersizeResult);

        Cursor c = db.queryResults(date);

        adapter = new ExerciseResultAdapter(this, c);

        resultsList.setAdapter(adapter);


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

    }
}
