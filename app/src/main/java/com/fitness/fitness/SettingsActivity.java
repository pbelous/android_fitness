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
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.settings_activity);

        Button apply_button = (Button)findViewById(R.id.settings_button_apply_theme);

        final RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup0);

        final RadioButton r0 = (RadioButton)findViewById(R.id.radio0);
        r0.setChecked(true);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int checkedId = group.getCheckedRadioButtonId();

                int button = 0;

                if (checkedId == R.id.radio1) button = 1;
                if (checkedId == R.id.radio2) button = 2;

                changeTheme(button);
            }
        });
    }

    private void changeTheme(int theme)
    {
        Utils.changeToTheme(this, theme);
    }
}
