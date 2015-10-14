package com.fitness.fitness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Exercise[] exercises;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, Exercise[] exercises) {
        this.context = context;
        this.exercises = exercises;
        inflater = LayoutInflater.from(this.context);
    }

    public void setExercises(Exercise[] exercises)
    {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_item, null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(exercises[position].name);

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            imageView.setImageResource(exercises[position].resource);
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return exercises.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}