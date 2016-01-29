package android.jochemkleine.com.spraakoefening.ui;

import android.app.Activity;
import android.content.Intent;
import android.jochemkleine.com.spraakoefening.adapters.ExerciseListAdapter;
import android.jochemkleine.com.spraakoefening.data.Exercise;
import android.jochemkleine.com.spraakoefening.data.Word;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.jochemkleine.com.spraakoefening.R;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class PracticeSelectionFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private ArrayList<Word> dutchDictionary;
    private  NavigationDrawerActivity parentActivity;
    private OnFragmentInteractionListener mListener;
    private static final int EXERCISE_CREATION_CODE = 1;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public static ArrayList<Exercise> exerciseList;


    public PracticeSelectionFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (exerciseList == null) {
            exerciseList = new ArrayList<>();
        }
        parentActivity = (NavigationDrawerActivity) getActivity();

        // TODO: Change Adapter to display your content
   /*     mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS); */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);

        System.out.println("ON CREATE ZE VIEW IS CALLED IN SELECTIONFRAGMENT");
        Exercise exercise = null;
       /* if (getArguments() != null) {
            exercise = (Exercise) getArguments().get("exercise");
            exerciseList.add(exercise);
        }
        */


        // Set the adapter
        mListView = (AbsListView) rootView.findViewById(android.R.id.list);
        mAdapter = new ExerciseListAdapter(getActivity(), exerciseList);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        FloatingActionButton addPractice =  (FloatingActionButton) rootView.findViewById(R.id.addPractice);
        addPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Fragment fragment;
                fragment = new CreateExerciseFragment();
                getFragmentManager().beginTransaction()
                        .replace(getId(),fragment)
                        .commit();
                */

                Intent intent = new Intent(getActivity(), CreateExerciseActivity.class);
                startActivityForResult(intent, EXERCISE_CREATION_CODE);
            }
        });
        return rootView;
    }


    public void updateAdapter () {
        mAdapter = new ExerciseListAdapter(getActivity(), exerciseList);
        mListView.setAdapter(mAdapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

     /*   if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

        }*/
        Intent intent = new Intent(getActivity(), ExerciseActivity.class);
        // TODO Fix encapsulation problem involving TransactionTooLargeException so that code below works
        // intent.putExtra("exercise", exerciseList.get(position));
        intent.putExtra("index", position);
        startActivity(intent);


    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Exercise exercise = null;
        if (data != null) {
            exercise = (Exercise) data.getSerializableExtra("exercise");
            if (exercise != null) {
                exerciseList.add(exercise);
                System.out.println("EXERCISE SUCCESSFULLY ADDED. LIST SIZE:  " + exerciseList.size());
            }
        }
            updateAdapter();
    }
}

