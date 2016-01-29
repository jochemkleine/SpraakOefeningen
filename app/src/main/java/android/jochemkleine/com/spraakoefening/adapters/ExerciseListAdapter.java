package android.jochemkleine.com.spraakoefening.adapters;

import android.content.Context;
import android.jochemkleine.com.spraakoefening.R;
import android.jochemkleine.com.spraakoefening.data.Exercise;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jochemkleine on 24-1-2016.
 */
public class ExerciseListAdapter extends ArrayAdapter {

    private ArrayList<Exercise> mExerciseArrayList;

    public ExerciseListAdapter(Context context, ArrayList<Exercise> exerciseList) {
        super(context, R.layout.exercise_list_item);
        mExerciseArrayList = exerciseList;
        System.out.println("ADAPTER CREATED. LIST SIZE: " + mExerciseArrayList.size());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("GETVIEW IS CALLED IN THE ADAPTER");
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.exercise_list_item, parent, false);
        TextView klanken = (TextView) customView.findViewById(R.id.klanken);
        klanken.setText(mExerciseArrayList.get(position).getInitiaal()
                + " - " + mExerciseArrayList.get(position).getMediaal()
                + " - " + mExerciseArrayList.get(position).getFinaal());
        return customView;

    }

    @Override
    public int getCount () {
        return mExerciseArrayList.size();
    }
}
