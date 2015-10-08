package com.fitness.fitness;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.fitness.fitness.adapters.ImageAdapter;
import com.fitness.fitness.database.Database;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class NewExersizeActivity extends Activity {

    String date = null;
    ImageAdapter gridViewAdapter = null;
    Exercise[] exercises = null;

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
                String item = parent.getItemAtPosition(position).toString();

                updateExercisesList(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Base");
        categories.add("Arm");
        categories.add("Leg");
        //categories.add("Back");
        //categories.add("Chest");
        //categories.add("Press");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        Button cancel = (Button)findViewById(R.id.button_new_excersize_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    void updateExercisesList(String name)
    {
        switch (name)
        {
            case "Base":
                exercises =  Exercise.getExersises(this, Exercise.EXER_TYPE_BASE);
                break;
            case "Arm":
                exercises =  Exercise.getExersises(this, Exercise.EXER_TYPE_ARM);
                break;
            case "Leg":
                exercises =  Exercise.getExersises(this, Exercise.EXER_TYPE_LEG);
                break;
            default:
                exercises =  Exercise.getExersises(this, Exercise.EXER_TYPE_ALL);
                break;
        }

        if (gridViewAdapter == null)
        {
            GridView gridView = (GridView) findViewById(R.id.gridView);
            gridViewAdapter = new ImageAdapter(this, exercises);
            gridView.setAdapter(gridViewAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    addExerciseToDb(exercises[position]);
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

