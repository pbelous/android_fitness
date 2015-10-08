package com.fitness.fitness;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fitness.fitness.adapters.EditExerciseResultAdapter;
import com.fitness.fitness.database.Database;
import com.fitness.fitness.model.ExerciseResult;
import com.fitness.fitness.utils.Utils;

public class AddExerciseResultActivity extends Activity {

    Database db = null;

    int exercise_id = -1;
    EditExerciseResultAdapter adapter = null;
    ExerciseResult exerciseResult = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_results_dialog);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        String date = null;

        if (bundle != null) {
            date = bundle.getString("timestamp");
            exercise_id = bundle.getInt("id");
        }

        if (date != null) {
            exerciseResult = db.queryResultWithDate(date, exercise_id);
        }

        if (exerciseResult == null)
        {
            exerciseResult = new ExerciseResult(exercise_id, Utils.getCurrentDate(), "");
        }

        adapter = new EditExerciseResultAdapter(this, exerciseResult);

        ListView listView = (ListView)findViewById(R.id.listViewEditResults);

        listView.setAdapter(adapter);

        final EditText et_weights = (EditText)findViewById(R.id.editTextWeight);
        final EditText et_reps = (EditText)findViewById(R.id.editTextReps);

        Button add = (Button) findViewById(R.id.button_add_result);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = et_weights.getText().toString();
                String reps = et_reps.getText().toString();

                exerciseResult.addResult(weight, reps);
                adapter.notifyDataSetChanged();
                db.addResult(exerciseResult);
            }
        });

        //ListView lv_results = (ListView)findViewById(R.id.listViewExersizeResult);
    }
}