package com.fitness.fitness.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.fitness.R;

public class CategoryAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private LayoutInflater inflater;
    Integer[] categories;

    public CategoryAdapter(Context context, int textViewResourceId,
                           Integer[] categories) {
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

        String name = context.getResources().getString(categories[position]);

        label.setText(name);

        switch (categories[position])
        {
            case R.string.category_base:
                icon.setImageResource(R.drawable.man_orig);
                break;
            case R.string.category_arm:
                icon.setImageResource(R.drawable.arms);
                break;
            case R.string.category_leg:
                icon.setImageResource(R.drawable.legs);
                break;
            case R.string.category_chest:
                icon.setImageResource(R.drawable.chest);
                break;
            case R.string.category_shoulder:
                icon.setImageResource(R.drawable.shoulders);
                break;
            case R.string.category_neck:
                icon.setImageResource(R.drawable.neck);
                break;
            case R.string.category_back:
                icon.setImageResource(R.drawable.back);
                break;
            case R.string.category_press:
                icon.setImageResource(R.drawable.press);
                break;
            default: //all
                icon.setImageResource(R.drawable.man_orig);
                break;
        }

        return row;
    }
}