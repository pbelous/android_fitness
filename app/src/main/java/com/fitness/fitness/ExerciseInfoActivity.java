package com.fitness.fitness;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fitness.fitness.model.ExerciseCategory;
import com.fitness.fitness.model.ExerciseData;

import java.util.List;

public class ExerciseInfoActivity extends Activity {

    private List<ExerciseCategory> categories = null;

    int pos = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_info_activity);

        categories = ExerciseData.getInstance().getCategories();

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
        if (categories == null) return;

        final TextView tvExerciseName = (TextView)findViewById(R.id.textViewExerciseInfoName);
        final WebView tvExerciseDesc = (WebView)findViewById(R.id.textViewExerciseInfoDescription);
        final ScrollView svInfo = (ScrollView)findViewById(R.id.info_scroll_view);

        if (pos < 0)
            return;

        pos++;

        if (pos >= categories.size())
            pos = 0;

        ExerciseCategory cat = categories.get(pos);
        tvExerciseName.setText(cat.name);

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

       // tvExerciseDesc.loadDataWithBaseURL(null, html,"text/html", "utf-8", null);
        tvExerciseDesc.loadUrl("file:///android_asset/" + cat.getExercises().get(0).path);

        tvExerciseDesc.setBackgroundColor(Color.TRANSPARENT);
        //tvExerciseDesc.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    }


}
