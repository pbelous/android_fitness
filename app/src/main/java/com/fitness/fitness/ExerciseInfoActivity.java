package com.fitness.fitness;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitness.fitness.model.ExerciseInfo;
import com.fitness.fitness.model.ExerciseInfoRecord;

import java.util.List;

public class ExerciseInfoActivity extends Activity {

    private List<ExerciseInfoRecord> exerciseInfo = null;
    int pos = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_info_activity);

        exerciseInfo = ExerciseInfo.getExerciseEnfo(this);

        final TextView tvExerciseName = (TextView)findViewById(R.id.textViewExerciseInfoName);
        final TextView tvExerciseDesc = (TextView)findViewById(R.id.textViewExerciseInfoDescription);
        Button next_button = (Button) findViewById(R.id.button_next);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exerciseInfo.size() < 1)
                    return;

                pos++;

                if (pos >= exerciseInfo.size())
                    pos = 0;

                ExerciseInfoRecord record = exerciseInfo.get(pos);
                tvExerciseName.setText(record.name);

                tvExerciseDesc.setText(record.description);

            }
        });
    }


}
