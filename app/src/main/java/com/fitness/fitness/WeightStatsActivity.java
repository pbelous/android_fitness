package com.fitness.fitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fitness.fitness.database.Database;
import com.fitness.fitness.utils.Utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class WeightStatsActivity extends Activity {

    GraphView graph = null;
    Database db = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.weight_stat);

        db = new Database(this);

        db.addWeight("2015-10-10", 10);
        db.addWeight("2015-10-11", 14);
        db.addWeight("2015-10-15", 12);
        db.addWeight("2015-10-20", 100);

        graph = (GraphView) findViewById(R.id.graph);

        graph.setTitle("Weight");

        Button add_weight = (Button)findViewById(R.id.button_add_weight);

        add_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnterWeightDialog();
            }
        });

        updateData();
    }

    private void updateData()
    {
        graph.removeAllSeries();

        Cursor c = db.queryAllWeight();

        if (c.moveToFirst())
        {
            ArrayList<DataPoint> data = new ArrayList<DataPoint>();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            Date dataStart = null;
            Date dataEnd = null;

            Double minWeight = -1.;
            Double maxWeight = 0.;

            do {
                String date_string = c.getString(c.getColumnIndex("timestamp"));
                Double weight = c.getDouble(c.getColumnIndex("weight"));

                try {
                    Date date = format.parse(date_string);

                    DataPoint d = new DataPoint(date, weight);

                    data.add(d);

                    if (dataStart == null)
                        dataStart = date;

                    dataEnd = date;

                    if (minWeight == -1 || minWeight > weight) minWeight = weight;
                    if (maxWeight < weight) maxWeight = weight;

                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            } while (c.moveToNext());

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data.toArray(new DataPoint[data.size()]));
            graph.addSeries(series);

            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
            graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

            //graph.getGridLabelRenderer().

            graph.getViewport().setMinX(dataStart.getTime()+100000000*5);
            graph.getViewport().setMaxX(dataEnd.getTime()-100000000*5);

            graph.getViewport().setMinY(minWeight);
            graph.getViewport().setMaxY(maxWeight);

            graph.getViewport().setXAxisBoundsManual(true);

            graph.getViewport().setScrollable(true);
            graph.getViewport().setScalable(true);
        }
    }

    private void showEnterWeightDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(getResources().getString(R.string.enter_weight));
        alert.setMessage(getResources().getString(R.string.new_weight));


        final EditText input = new EditText(this);

        input.setFilters(new InputFilter[]{
                // Maximum 3 characters.
                new InputFilter.LengthFilter(3),
                // Digits only.
                DigitsKeyListener.getInstance(),  // Not strictly needed, IMHO.
        });

// Digits only & use numeric soft-keyboard.
        input.setKeyListener(DigitsKeyListener.getInstance());

        alert.setView(input);
        input.setText("0");

        alert.setPositiveButton(getResources().getString(R.string.add),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String weight = input.getText().toString();
                        db.addWeight(Utils.getCurrentDate(), Integer.parseInt(weight));
                        updateData();
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
