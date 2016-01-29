package android.jochemkleine.com.spraakoefening.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.jochemkleine.com.spraakoefening.data.DutchDictionary;
import android.jochemkleine.com.spraakoefening.data.Exercise;
import android.jochemkleine.com.spraakoefening.data.Word;
import android.jochemkleine.com.spraakoefening.tasks.InitWordListTask;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.jochemkleine.com.spraakoefening.R;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CreateExerciseActivity extends AppCompatActivity {

    private ArrayList<Word> dutchDictionary;
    private EditText initiaalEditText;
    private EditText finaalEditText;
    private EditText mediaalEditText;
    private Context context;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        toolbar.setTitle("Spraakoefening maken");

        FloatingActionButton finishCreationButton
                = (FloatingActionButton) findViewById(R.id.finishCreation);

        initiaalEditText = (EditText) findViewById(R.id.initaal);
        mediaalEditText = (EditText) findViewById(R.id.mediaal);
        finaalEditText = (EditText) findViewById(R.id.finaal);

        finishCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise exercise = new Exercise();
                if (initiaalEditText.getText().length() != 0) {
                    exercise.setInitiaal(initiaalEditText.getText().toString().trim());
                }
                if (finaalEditText.getText().length() != 0) {
                    exercise.setFinaal(finaalEditText.getText().toString().trim());
                }
                if (mediaalEditText.getText().length() != 0) {
                    exercise.setMediaal(mediaalEditText.getText().toString().trim());
                }
                ProgressDialog pd = new ProgressDialog(context);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("Running backup. Do not unplug drive");
                pd.setIndeterminate(true);
                pd.setCancelable(false);
                pd.show();
               // initProgressDialog();


                exercise.initalize(DutchDictionary.wordList);
               // fragmentTransaction(exercise);

                /* TODO Breaks encapsulation, however transferring the Exercise object
                   TODO through an intent causes a TransactionTooLargeException. Find elegant solution
                 */
                PracticeSelectionFragment.exerciseList.add(exercise);
                pd.dismiss();

               // Intent data = new Intent();
              //  data.putExtra("exercise", exercise);
              //  setResult(1, data);

                if (exercise.getExerciseWordList().size() < 1){
                    Toast.makeText(context, "Geen van de 146.329 woorden voldoet aan de gestelde eisen. Probeer opnieuw.",
                            Toast.LENGTH_LONG).show();
                    PracticeSelectionFragment.exerciseList.remove(exercise);
                } else {
                    finish();
                }
            }
        });



    }

    private void initProgressDialog() {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Laden");
        progress.setMessage("163.210 Nederlandse woorden sorteren...");
        progress.setCancelable(false);
        progress.show();

    }

/*
    public void initLocalDictionary () {
            if (dutchDictionary == null || dutchDictionary.size() ==0){
                try {
                    dutchDictionary = new InitWordListTask().execute(context).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }
    }
*/
/*
    public ArrayList<Word> initDictionary (){


    ArrayList<Word> wordList = new ArrayList<Word>();
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open("dutch_dictionary_extended.txt"), "UTF-8"));

        // do reading, usually loop until end of file reading
        String mLine;

        while ((mLine = reader.readLine()) != null) {
            wordList.add(new Word(mLine));
        }
    } catch (IOException e){
        System.out.println("EROOR WHILE LOADING THE WORDLIST ");
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                //log the exception
                e.printStackTrace();
            }
        }
       }
        return wordList;
    }
*/
}
