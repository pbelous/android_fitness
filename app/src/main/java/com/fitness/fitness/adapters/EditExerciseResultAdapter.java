package com.fitness.fitness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fitness.fitness.R;
import com.fitness.fitness.model.Exercise;
import com.fitness.fitness.model.ExerciseResult;

/**
 * Created by pbelous on 07.10.2015.
 */
public class EditExerciseResultAdapter extends BaseAdapter {

    private Context context;
    private ExerciseResult exerciseResult = null;

    public EditExerciseResultAdapter(Context context, ExerciseResult exerciseRes) {
        this.context = context;
        this.exerciseResult = exerciseRes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = LayoutInflater.from(context).inflate(R.layout.exercise_result_item, parent, false);

        TextView tv = (TextView)view.findViewById(R.id.textView); //fix


        return view;
    }

    @Override
    public int getCount() {
        return exerciseResult.reps.length;
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



