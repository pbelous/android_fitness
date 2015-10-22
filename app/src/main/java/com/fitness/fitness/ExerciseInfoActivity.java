package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseCategory;
import com.fitness.fitness.model.ExerciseData;

import java.util.List;

public class ExerciseInfoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_info_activity);

        final TextView tvExerciseName = (TextView)findViewById(R.id.textViewExerciseInfoName);
        final WebView tvExerciseDesc = (WebView)findViewById(R.id.textViewExerciseInfoDescription);
        Button next_button = (Button) findViewById(R.id.button_next);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectActivity();
            }
        });

        openSelectActivity();
    }

    void openSelectActivity()
    {
        Intent intent = new Intent(this, SelectExerciseActivity.class);
        //intent.putExtra("timestamp", date);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        int exerciseId = data.getIntExtra("exercise_id", 0);

        if (exerciseId > 0)
        {
            updateInfo(exerciseId);
        } else
        {
            openSelectActivity();
        }
    }

    void updateInfo(int exerciseId)
    {
        if (exerciseId < 1) return;

        final TextView tvExerciseName = (TextView)findViewById(R.id.textViewExerciseInfoName);
        final WebView tvExerciseDesc = (WebView)findViewById(R.id.textViewExerciseInfoDescription);
        final ScrollView svInfo = (ScrollView)findViewById(R.id.info_scroll_view);

        tvExerciseDesc.setVerticalScrollBarEnabled(false);
        tvExerciseDesc.setHorizontalScrollBarEnabled(false);

       // tvExerciseDesc.getSettings().setLoadWithOverviewMode(true);
        tvExerciseDesc.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        tvExerciseDesc.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        svInfo.setSmoothScrollingEnabled(false);
        svInfo.fullScroll(View.FOCUS_UP);
        svInfo.setSmoothScrollingEnabled(true);



        Exercise exercise = ExerciseData.getInstance().getExerciseById(exerciseId);

        tvExerciseName.setText(exercise.name);
       // tvExerciseDesc.loadDataWithBaseURL(null, html,"text/html", "utf-8", null);
        tvExerciseDesc.loadUrl("file:///android_asset/" + exercise.path);

        tvExerciseDesc.setBackgroundColor(Color.TRANSPARENT);
        //tvExerciseDesc.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    }
}
