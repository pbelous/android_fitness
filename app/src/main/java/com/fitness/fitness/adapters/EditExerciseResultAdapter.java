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
    private LayoutInflater inflater = null;

    public EditExerciseResultAdapter(Context context, ExerciseResult exerciseRes) {
        this.context = context;
        this.exerciseResult = exerciseRes;
        inflater = LayoutInflater.from(this.context);
    }

    /*
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = LayoutInflater.from(context).inflate(R.layout.exercise_result_item, parent, false);

        TextView tv = (TextView)view.findViewById(R.id.textView); //fix


        return view;
    }
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.edit_exercise_results_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ExerciseResult.ExerciseRep currentRep = (ExerciseResult.ExerciseRep)getItem(position);

        mViewHolder.tvWeight.setText(currentRep.weight);
        mViewHolder.tvReps.setText(currentRep.reps);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvWeight, tvReps;

        public MyViewHolder(View item) {
            tvWeight = (TextView) item.findViewById(R.id.textViewEditResWeight);
            tvReps = (TextView) item.findViewById(R.id.textViewEditResReps);
        }
    }

    @Override
    public int getCount() {
        return exerciseResult.reps.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseResult.reps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}



