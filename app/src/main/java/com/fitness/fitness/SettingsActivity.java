package com.fitness.fitness;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fitness.fitness.utils.Utils;

public class SettingsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Button apply_button = (Button)findViewById(R.id.settings_button_apply_theme);

        final RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup0);

        final RadioButton r0 = (RadioButton)findViewById(R.id.radio0);
        r0.setChecked(true);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void changeTheme(int theme)
    {
    }
}
