package com.fitness.fitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fitness.fitness.adapters.ExerciseResultAdapter;
import com.fitness.fitness.adapters.ScheduleAdapter;
import com.fitness.fitness.database.Database;
import com.fitness.fitness.model.ExerciseResult;
import com.fitness.fitness.utils.Utils;
import com.jjoe64.graphview.GraphView;

public class ExerciseResultActivity extends Activity {

  //  ExerciseResultAdapter adapter = null;
    Database db = null;

    int exercise_id = -1;
    String date = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.exersize_result_activity);

        db = new Database(this);

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            date = bundle.getString("timestamp");
            exercise_id = bundle.getInt("id");
        }

       // ListView resultsList = (ListView)findViewById(R.id.listViewExersizeResult);

        Cursor c = null;

        if (date != null && date.equals(Utils.getCurrentDate())) {
            c = db.queryResultsWithDate(date, exercise_id);
        }
        else
        {
            c = db.queryResults(exercise_id);
        }

        refreshTable(c);

        /*
        adapter = new ExerciseResultAdapter(this, c);

        resultsList.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = (String) v.getTag();
                openEditResultsActivity(date);
            }
        });
        */

        Button editResultsButton = (Button)findViewById(R.id.button_add);

        editResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditResultsActivity(Utils.getCurrentDate());
            }
        });
    }

    TableRow getTableRow(int startId, String text1, String text2, String text3, int color)
    {
        TableRow tr = new TableRow(this);

        tr.setBackgroundColor(color);
        tr.setId(100+startId);
        tr.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

//Create two columns to add as table data
        // Create a TextView to add date
        TextView label1 = new TextView(this);
        label1.setId(201 + startId);
        label1.setText(text1);
        label1.setPadding(2, 0, 5, 0);
        label1.setTextColor(Color.BLACK);
        label1.setBackgroundResource(R.drawable.cell_border);
        label1.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        label1.setGravity(Gravity.CENTER_HORIZONTAL);
        label1.setPadding(5,0,5,0);
        tr.addView(label1);


        TextView label2 = new TextView(this);
        label2.setId(202 + startId);
        label2.setText(text2);
        label2.setTextColor(Color.BLACK);
        label2.setBackgroundResource(R.drawable.cell_border);
        label2.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        label2.setGravity(Gravity.CENTER_HORIZONTAL);
        label2.setPadding(5, 0, 5, 0);
        tr.addView(label2);

        TextView label3 = new TextView(this);
        label3.setId(203 + startId);
        label3.setText(text3);
        label3.setTextColor(Color.BLACK);
        label3.setBackgroundResource(R.drawable.cell_border);
        label3.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        label3.setGravity(Gravity.CENTER_HORIZONTAL);
        label3.setPadding(5,0,5,0);
        tr.addView(label3);

        return tr;
    }

    void refreshTable(Cursor c)
    {
        TableLayout tl = (TableLayout)findViewById(R.id.table_layout_results);

        tl.removeAllViews();

        int id = 1;

        tl.addView(getTableRow(id, getResources().getString(R.string.date),
                        getResources().getString(R.string.weight),
                        getResources().getString(R.string.reps_short), Color.YELLOW),
                new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

        if (c.moveToFirst())
        {
            do {
                tl.addView(getTableRow(id,
                                c.getString(c.getColumnIndex("timestamp")),
                                "" + c.getDouble(c.getColumnIndex("result_weight")),
                                "" + c.getInt(c.getColumnIndex("result_reps")),
                                Color.WHITE),
                        new TableLayout.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));


            } while (c.moveToNext());
        }

        c.close();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateResultsList();
    }

    void updateResultsList()
    {
        Cursor c = null;

        if (date != null && date.equals(Utils.getCurrentDate()))
            c = db.queryResultsWithDate(date, exercise_id);
        else
            c= db.queryResults(exercise_id);

        refreshTable(c);

       // adapter.swapCursor(c);
       // adapter.notifyDataSetChanged();
    }

    void openEditResultsActivity(String date)
    {
        /*Intent intent = new Intent(this, AddExerciseResultActivity.class);

        if (date != null) {
            intent.putExtra("timestamp", date);
        }

        intent.putExtra("id", exercise_id);
        startActivity(intent);
        */

        showAddResultDialog();
    }

    void showAddResultDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage(getResources().getString(R.string.new_result));
        alert.setTitle(getResources().getString(R.string.enter_result));

        final EditText weight = new EditText(this);
        final EditText reps = new EditText(this);

        weight.setHint(getResources().getString(R.string.weight));
        reps.setHint(getResources().getString(R.string.reps));

        weight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        reps.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(weight);
        ll.addView(reps);

        alert.setView(ll);

        alert.setPositiveButton(getResources().getString(R.string.add),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String w = weight.getText().toString();
                        String r = reps.getText().toString();

                        if (w.length() < 1 || r.length() < 1) return;

                        Double wd = Double.valueOf(w);
                        int rr = Integer.valueOf(r);

                        ExerciseResult result = new ExerciseResult(exercise_id, Utils.getCurrentDate(), wd, rr);

                       // exerciseResult.addResult(w, r);
                       // adapter.notifyDataSetChanged();
                        db.addResult(result);

                        updateResultsList();
                    }
                });

        alert.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }
}
