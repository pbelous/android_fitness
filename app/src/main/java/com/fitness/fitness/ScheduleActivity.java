package com.fitness.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;

public class ScheduleActivity extends Activity {

    Database db = null;
    String date = null;
    ScheduleAdapter adapter = null;

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
    //    listview.setOnItemClickListener(this);

        /*
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {



                Cursor cursor = (Cursor) adapter.getItem(position);

                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                int exercise_id = cursor.getInt(cursor.getColumnIndex("exercise_id"));

                db.deleteSchedule(timestamp, exercise_id);

                return true;
            }
        });

        this.openContextMenu();
        */


        Button cancel = (Button)findViewById(R.id.button_new_cancel);
        Button add_exercise = (Button)findViewById(R.id.button_add_excersize);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
            ListView lv = (ListView) v;
          //  AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //    YourObject obj = (YourObject) lv.getItemAtPosition(acmi.position);

            menu.add("Delete");
            //menu.add("Two");
            //menu.add("Three");
           // menu.add(obj.name);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Delete"))
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            deleteSchedule(info.position);

                //Log.d(TAG, "removing item pos=" + info.position);
                //mAdapter.remove(info.position);
            return true;

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
        Intent intent = new Intent(this, NewExersizeActivity.class);
        intent.putExtra("timestamp", date);
        startActivity(intent);
    }

    void openExerciseResultActivity(String timestamp, int exercise_id)
    {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
        intent.putExtra("timestamp", timestamp);
        intent.putExtra("id", exercise_id);
        startActivity(intent);
    }
}
