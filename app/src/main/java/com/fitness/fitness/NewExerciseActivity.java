package com.fitness.fitness;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.List;


public class NewExerciseActivity extends Activity {

    String date = null;
    ImageAdapter gridViewAdapter = null;
    List<Exercise> exercises = null;

    List<ExerciseCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_exercize_activity);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            date = bundle.getString("timestamp");

        if (date == null)
            date = Utils.getCurrentDate();

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                finish();
            }
        });
    }

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
                    addExerciseToDb(exercises.get(position));
                    finish();
                }
            });
        }
        else
        {
            gridViewAdapter.setExercises(exercises);
        }
    }


    void addExerciseToDb(Exercise exercise)
    {
        Database db = new Database(this);

        db.addRecord(exercise, date);
    }



}

