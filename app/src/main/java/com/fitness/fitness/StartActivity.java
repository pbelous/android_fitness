package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fitness.fitness.model.ExerciseData;
import com.fitness.fitness.utils.Utils;

public class StartActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(Utils.getCurrentTheme());
        //Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.start_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //load all data
        ExerciseData.getInstance().init(getApplicationContext());

        final Button calendar_button = (Button) findViewById(R.id.button_calendar);
        final Button weight_button = (Button) findViewById(R.id.button_weight);
        final Button schedule_button = (Button) findViewById(R.id.button_schedule);
        final Button train_button = (Button) findViewById(R.id.button_schedule_today);

        weight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_button.setBackgroundColor(Color.GRAY);
                openWeightStatsActivity();
            }
        });

        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar_button.setBackgroundColor(Color.GRAY);
                openCalendarActivity();
            }
        });

        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedule_button.setBackgroundColor(Color.GRAY);
                openScheduleActivity();
            }
        });

        train_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                train_button.setBackgroundColor(Color.GRAY);
                openTrainActivity();
            }
        });
    }
    protected void onResume()
    {
        Button calendar_button = (Button) findViewById(R.id.button_calendar);
        Button weight_button = (Button) findViewById(R.id.button_weight);
        Button schedule_button = (Button) findViewById(R.id.button_schedule);
        final Button train_button = (Button) findViewById(R.id.button_schedule_today);

        if (Utils.getThemeId(this) != Utils.getCurrentTheme())
        {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }

        calendar_button.setBackgroundColor(Color.TRANSPARENT);
        weight_button.setBackgroundColor(Color.TRANSPARENT);
        schedule_button.setBackgroundColor(Color.TRANSPARENT);
        train_button.setBackgroundColor(Color.TRANSPARENT);

        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                openSettingsActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void openSettingsActivity()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    void openCalendarActivity()
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    void openWeightStatsActivity()
    {
        Intent intent = new Intent(this, WeightStatsActivity.class);
        startActivity(intent);
    }

    void openScheduleActivity()
    {
        Intent intent = new Intent(this, ExerciseInfoActivity.class);

        startActivity(intent);
    }

    void openTrainActivity()
    {
        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra("timestamp", Utils.getCurrentDate());
        startActivity(intent);
    }
}
