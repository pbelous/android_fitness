package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by pbelous on 01.10.2015.
 */
public class StartActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Button calendar_button = (Button)findViewById(R.id.button_calendar);
        Button weight_button = (Button)findViewById(R.id.button_weight);
        Button schedule_button = (Button)findViewById(R.id.button_schedule);

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

}
