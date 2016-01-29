package android.jochemkleine.com.spraakoefening.ui;

import android.content.Intent;
import android.jochemkleine.com.spraakoefening.data.Exercise;
import android.jochemkleine.com.spraakoefening.data.Word;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.jochemkleine.com.spraakoefening.R;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class ExerciseActivity extends AppCompatActivity {

    private ArrayList<Word> wordList;
    private TextToSpeech tts;
    private int currentWordNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Exercise exercise = (Exercise) getIntent().getExtras().getSerializable("exercise");
        int index = (Integer) getIntent().getExtras().get("index");
        Exercise exercise = PracticeSelectionFragment.exerciseList.get(index);
        wordList = exercise.getExerciseWordList();
        TextView practiceWord = (TextView) findViewById(R.id.practiceWord);
        practiceWord.setText(wordList.get(0).getWord());

        textToSpeech();

        FloatingActionButton textToSpeech = (FloatingActionButton) findViewById(R.id.textToSpeech);
        textToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(wordList.get(currentWordNum).getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    public void textToSpeech() {
         tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= TextToSpeech.ERROR){
                    tts.setLanguage(new Locale("nl_NL"));
                }

            }
        });


    }
    public void nextWord (View view) {
        Random r = new Random();
        int x = r.nextInt(wordList.size());
        TextView practiceWord = (TextView) findViewById(R.id.practiceWord);
        practiceWord.setText(wordList.get(x).getWord());
        currentWordNum = x;
    }

}
