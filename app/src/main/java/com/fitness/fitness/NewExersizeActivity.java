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
import com.fitness.fitness.model.Exercise;

import java.util.ArrayList;
import java.util.List;


public class NewExersizeActivity extends Activity {

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
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
        GridView gridView = (GridView) findViewById(R.id.gridView);

        switch (name)
        {
            case "Base":
                gridView.setAdapter(new ImageAdapter(this, Exercise.getExersizes(this, Exercise.EXER_TYPE_BASE)));
                break;
            case "Arm":
                gridView.setAdapter(new ImageAdapter(this, Exercise.getExersizes(this, Exercise.EXER_TYPE_ARM)));
                break;
            case "Leg":
                gridView.setAdapter(new ImageAdapter(this, Exercise.getExersizes(this, Exercise.EXER_TYPE_LEG)));
                break;
            default:
                gridView.setAdapter(new ImageAdapter(this, Exercise.getExersizes(this, Exercise.EXER_TYPE_ALL)));
                break;
        }

        //gridView.
        gridView.invalidate();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Toast.makeText(getApplicationContext(),
                //         ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}

