package android.jochemkleine.com.spraakoefening.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.jochemkleine.com.spraakoefening.data.Word;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Jochemkleine on 23-1-2016.
 */
public class InitWordListTask extends AsyncTask<Context, Void, ArrayList<Word>> {

    private ArrayList<Word> wordList;
    @Override
    protected ArrayList<Word> doInBackground(Context... params) {

        wordList = new ArrayList<Word>();
        Context context = params[0];


        if (context == null) {
            System.out.println("CONTEXT IS NULL IN ASYNCTASK");
        }
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("dutch_dictionary_extended.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;

            while ((mLine = reader.readLine()) != null) {
                wordList.add(new Word(mLine));
            }
        } catch (IOException e) {
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
}
