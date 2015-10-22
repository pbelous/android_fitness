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
import com.fitness.fitness.utils.Utils;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<Exercise> exercises;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
        inflater = LayoutInflater.from(this.context);
    }

    public void setExercises(List<Exercise> exercises)
    {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = null;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_item, null);
        } else {
            gridView = (View) convertView;
        }

        TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

        Exercise exercise = exercises.get(position);

        textView.setText(exercise.name);
        imageView.setImageResource(Utils.getDrawableIdByName(context, exercise.icon));

        return gridView;
    }

    @Override
    public int getCount() {
        return exercises.size();
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