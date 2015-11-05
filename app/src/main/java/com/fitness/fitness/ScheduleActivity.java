package com.fitness.fitness;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;
import com.fitness.fitness.dialogs.DatePickerFragment;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseData;
import com.fitness.fitness.utils.Utils;

public class ScheduleActivity extends FragmentActivity {

    Database db = null;
    String date = null;
    ScheduleAdapter adapter = null;

    private static final int MENU_ID_MOVE=Menu.FIRST+1;
    private static final int MENU_ID_COPY=Menu.FIRST+2;
    private static final int MENU_ID_DELETE=Menu.FIRST+3;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
         date = bundle.getString("timestamp");

        ListView listview = (ListView)findViewById(R.id.SchedulelistView);

        Cursor c = null;

        if (date != null)
        {
            c = db.queryWithDate(date);
        }
        else
        {
            c = db.query();
        }

        adapter = new ScheduleAdapter(this, c);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                //cursor.moveToPosition(position);

                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));


                openExerciseResultActivity(timestamp, exercise_id);
            }
        });

        registerForContextMenu(listview);

        Button add_exercise = (Button)findViewById(R.id.button_add_excersize);

        add_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewExerciseActivity();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.SchedulelistView) {
            menu.add(0, MENU_ID_COPY, 0, getResources().getString(R.string.copy));
            menu.add(0, MENU_ID_MOVE, 0, getResources().getString(R.string.move));
            menu.add(0, MENU_ID_DELETE, 0, getResources().getString(R.string.delete));
        }
    }

    void deleteSchedule(int position)
    {
        Cursor cursor = (Cursor) adapter.getItem(position);

        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));

        db.deleteSchedule(timestamp, exercise_id);

        updateExercisesList();
    }

    void moveSchedule(int position, String newdate)
    {
        Cursor cursor = (Cursor) adapter.getItem(position);

        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));

        db.moveSchedule(timestamp, newdate, exercise_id);

        updateExercisesList();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;

        if (item.getItemId() == MENU_ID_DELETE)
        {
            deleteSchedule(position);
            return true;
        }
        else if (item.getItemId() == MENU_ID_MOVE)
        {
            DatePickerFragment newFragment = new DatePickerFragment();

            newFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    moveSchedule(position, year + "-" + monthOfYear + "-" + dayOfMonth);
                }
            });

            newFragment.show(getSupportFragmentManager(), "datePicker");
        }

        return super.onContextItemSelected(item);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_delete:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public void onResume()
    {
        super.onResume();
        updateExercisesList();
    }

    void updateExercisesList()
    {
        Cursor c = null;

        if (date != null)
        c = db.queryWithDate(date);
        else
        c= db.query();

        adapter.swapCursor(c);
        adapter.notifyDataSetChanged();
    }

    void openNewExerciseActivity()
    {
        Intent intent = new Intent(this, SelectExerciseActivity.class);
        //intent.putExtra("timestamp", date);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        int exerciseId = data.getIntExtra("exercise_id", 0);

        if (exerciseId > 0)
        {
            addExerciseToDb(exerciseId);
        }
    }

    void addExerciseToDb(int exerciseId)
    {
        Exercise exercise = ExerciseData.getInstance().getExerciseById(exerciseId);
        Database db = new Database(this);

        db.addRecord(exercise, date);

       // ExerciseData.getInstance().getCategories()
    }

    void openExerciseResultActivity(String timestamp, int exercise_id)
    {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
        intent.putExtra("timestamp", timestamp);
        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
