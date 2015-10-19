package com.fitness.fitness;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
        final WebView tvExerciseDesc = (WebView)findViewById(R.id.textViewExerciseInfoDescription);
        Button next_button = (Button) findViewById(R.id.button_next);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
            }
        });

        updateInfo();
    }

    void updateInfo()
    {
        if (exerciseInfo == null) return;

        final TextView tvExerciseName = (TextView)findViewById(R.id.textViewExerciseInfoName);
        final WebView tvExerciseDesc = (WebView)findViewById(R.id.textViewExerciseInfoDescription);

        if (pos < 0)
            return;

        pos++;

        if (pos >= 8)
            pos = 1;

        //ExerciseInfoRecord record = exerciseInfo.get(pos);
        tvExerciseName.setText("testing");

        tvExerciseDesc.setVerticalScrollBarEnabled(false);
        tvExerciseDesc.setHorizontalScrollBarEnabled(false);

       // tvExerciseDesc.getSettings().setLoadWithOverviewMode(true);
        tvExerciseDesc.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        tvExerciseDesc.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

       // tvExerciseDesc.loadDataWithBaseURL(null, html,"text/html", "utf-8", null);
        tvExerciseDesc.loadUrl("file:///android_asset/shoulders/shoulder_" + pos + ".html");
    }


}
