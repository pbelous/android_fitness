package com.fitness.fitness.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.fitness.R;

public class ExerciseResultAdapter extends CursorAdapter {
    private View.OnClickListener listener = null;


    public ExerciseResultAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.exercise_result_item, parent, false);
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvDate = (TextView) view.findViewById(R.id.textViewResultsDate);
        TextView tvResults = (TextView) view.findViewById(R.id.textViewResults);
        Button editButton = (Button) view.findViewById(R.id.buttonEditResults);

        int exercise_id = cursor.getInt(cursor.getColumnIndexOrThrow("exercise_id"));

        String date = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
        String results = cursor.getString(cursor.getColumnIndexOrThrow("result"));

        tvDate.setText(date);
        tvResults.setText(results);

        editButton.setTag(date);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }
}
