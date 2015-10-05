package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fitness.fitness.database.Database;

public class NewScheduleActivity extends Activity {

    Database db = null;
    String date = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_schedule_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            date = bundle.getString("timestamp");

        if (date == null)
            date = "28-09-2015";

        Button add = (Button)findViewById(R.id.button_new_schedule);
        Button cancel = (Button)findViewById(R.id.button_new_cancel);
        Button add_excersize = (Button)findViewById(R.id.button_add_excersize);

        final EditText ETdesc = (EditText)findViewById(R.id.editTextNewSchedule);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ETdesc.getText().length() > 0) {
                    db.addRecord(ETdesc.getText().toString(), date);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_excersize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewExcersizeActivity();
            }
        });
    }

    void openNewExcersizeActivity()
    {
        Intent intent = new Intent(this, NewExersizeActivity.class);
        startActivity(intent);
    }
}
