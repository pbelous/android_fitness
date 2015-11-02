package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
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

        Button calendar_button = (Button)findViewById(R.id.button_calendar);
        Button weight_button = (Button)findViewById(R.id.button_weight);
        Button schedule_button = (Button)findViewById(R.id.button_schedule);
        Button traing_button = (Button)findViewById(R.id.button_schedule_today);

        weight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightStatsActivity();
            }
        });

        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarActivity();
            }
        });

        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScheduleActivity();
            }
        });

        traing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainActivity();
            }
        });

    }

    protected void onResume()
    {
        if (Utils.getThemeId(this) != Utils.getCurrentTheme())
        {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }

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
