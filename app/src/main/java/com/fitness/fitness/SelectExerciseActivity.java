package com.fitness.fitness;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.fitness.fitness.adapters.CategoryAdapter;
import com.fitness.fitness.adapters.ImageAdapter;
import com.fitness.fitness.database.Database;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseCategory;
import com.fitness.fitness.model.ExerciseData;
import com.fitness.fitness.utils.Utils;

import java.util.List;

public class SelectExerciseActivity extends Activity {

    ImageAdapter gridViewAdapter = null;
    List<Exercise> exercises = null;

    List<ExerciseCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_exercize_activity);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Utils.lastSelectedCategory = position;
                updateExercisesList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categories = ExerciseData.getInstance().getCategories();

        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.category_spinner_item, categories);

        spinner.setAdapter(adapter);


        Button cancel = (Button)findViewById(R.id.button_new_excersize_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        spinner.setSelection(Utils.lastSelectedCategory);
    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    } */

    void updateExercisesList(Integer position)
    {
        ExerciseCategory category = categories.get(position);
        exercises = category.getExercises();

        if (gridViewAdapter == null)
        {
            GridView gridView = (GridView) findViewById(R.id.gridView);
            gridViewAdapter = new ImageAdapter(this, exercises);
            gridView.setAdapter(gridViewAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    onExerciseSelected(exercises.get(position));
                }
            });
        }
        else
        {
            gridViewAdapter.setExercises(exercises);
        }
    }

    protected void onExerciseSelected(Exercise exercise)
    {
        Intent intent = new Intent();
        intent.putExtra("exercise_id", exercise.id);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
