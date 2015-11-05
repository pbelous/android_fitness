package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;

import com.fitness.fitness.database.Database;
import com.fitness.fitness.model.ExerciseData;
import com.fitness.fitness.utils.Utils;

public class StartActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        train_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrainActivity();
            }
        });

        updateTodayInfo();
    }

    void openScheduleActivity(String date){
        Intent intent = new Intent(this, ScheduleActivity.class);

        intent.putExtra("timestamp", date);

        startActivity(intent);
    }

    void updateTodayInfo()
    {
        final TextView today_info = (TextView) findViewById(R.id.text_view_today_info);
        final Button today_button = (Button) findViewById(R.id.today_button);

        today_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScheduleActivity(Utils.getCurrentDate());
            }
        });

        Database db = new Database(this);

        Cursor c = db.queryWithDate(Utils.getCurrentDate());

        if (!c.moveToFirst())
        {
            today_info.setText(getResources().getString(R.string.today_info_no_exercises));
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            do {
                if (sb.length() !=  0) sb.append("\n");
                sb.append(c.getString(c.getColumnIndex("name")));
            } while (c.moveToNext());

            today_info.setText(sb.toString());
        }

        c.close();
    }

    protected void onResume()
    {
        Button calendar_button = (Button) findViewById(R.id.button_calendar);
        Button weight_button = (Button) findViewById(R.id.button_weight);
        Button schedule_button = (Button) findViewById(R.id.button_schedule);
        final Button train_button = (Button) findViewById(R.id.button_schedule_today);

        updateTodayInfo();

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
