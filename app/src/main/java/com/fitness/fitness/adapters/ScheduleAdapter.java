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
import com.fitness.fitness.utils.Utils;

public class ScheduleAdapter extends CursorAdapter {
    public ScheduleAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.shedule_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvName = (TextView) view.findViewById(R.id.textViewName);
        TextView tvDesc = (TextView) view.findViewById(R.id.textViewDescription);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.list_image);
        // Extract properties from cursor
        String date = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
        String desc = cursor.getString(cursor.getColumnIndexOrThrow("desc"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));

        String icon_name = cursor.getString(cursor.getColumnIndexOrThrow("icon"));

        int icon = Utils.getDrawableIdByName(context, icon_name);

        // Populate fields with extracted properties
        tvName.setText(name);
        tvDesc.setText(desc);
        ivIcon.setImageResource(icon);
    }
}