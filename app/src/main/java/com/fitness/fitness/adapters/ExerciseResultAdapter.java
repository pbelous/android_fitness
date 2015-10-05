package com.fitness.fitness.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.fitness.R;

/**
 * Created by pbelous on 05.10.2015.
 */
public class ExerciseResultAdapter extends CursorAdapter {
    public ExerciseResultAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.shedule_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
       /* TextView tvName = (TextView) view.findViewById(R.id.textViewName);
        TextView tvDesc = (TextView) view.findViewById(R.id.textViewDescription);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.list_image);
        // Extract properties from cursor
        String date = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
        String desc = cursor.getString(cursor.getColumnIndexOrThrow("desc"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));

        int res = R.drawable.calendar_cel_set;

        try {
            res = cursor.getInt(cursor.getColumnIndexOrThrow("icon"));
        }
        catch (Exception e)
        {
        }

        // Populate fields with extracted properties
        tvName.setText(name);
        tvDesc.setText(desc);
        ivIcon.setImageResource(res);
        */
    }
}
