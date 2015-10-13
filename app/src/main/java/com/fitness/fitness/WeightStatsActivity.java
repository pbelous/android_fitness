package com.fitness.fitness;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;


public class WeightStatsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_stat);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        DataPoint[] data = new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)};

        Date date = new Date();
        DataPoint d = new DataPoint(date, 5);

        Cursor c = null;

      //  LineGraphSeries<DataPoint> series = new LineGraphSeries<>(c);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);

        graph.setTitle("title");
    }

}
