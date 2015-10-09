package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fitness.fitness.utils.Utils;

public class StartActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

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
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    void openTrainActivity()
    {
        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra("timestamp", Utils.getCurrentDate());
        startActivity(intent);
    }
}
