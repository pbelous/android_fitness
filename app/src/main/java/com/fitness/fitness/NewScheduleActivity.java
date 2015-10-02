package com.fitness.fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fitness.fitness.database.Database;

/**
 * Created by pbelous on 01.10.2015.
 */
public class NewScheduleActivity extends AppCompatActivity {

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
    }
}
