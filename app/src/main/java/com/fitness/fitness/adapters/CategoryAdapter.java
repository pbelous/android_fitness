package com.fitness.fitness.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.fitness.R;
import com.fitness.fitness.model.ExerciseCategory;
import com.fitness.fitness.utils.Utils;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<ExerciseCategory> {
    private Context context;
    private LayoutInflater inflater;
    List<ExerciseCategory> categories;

    public CategoryAdapter(Context context, int textViewResourceId,
                           List<ExerciseCategory> categories) {
        super(context, textViewResourceId, categories);
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.categories = categories;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row=inflater.inflate(R.layout.category_spinner_item, parent, false);

        TextView label=(TextView)row.findViewById(R.id.category_name);
        ImageView icon=(ImageView)row.findViewById(R.id.category_icon);

        ExerciseCategory category = categories.get(position);

        label.setText(category.name);
        icon.setImageResource(Utils.getDrawableIdByName(context, category.icon));

        return row;
    }
}