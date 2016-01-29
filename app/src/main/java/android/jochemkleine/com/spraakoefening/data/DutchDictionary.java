package android.jochemkleine.com.spraakoefening.data;

import java.util.ArrayList;

/**
 * Created by Jochemkleine on 25-1-2016.
 */
public class DutchDictionary {


    public static ArrayList<Word> wordList = new ArrayList<>();

    public static void initialize (ArrayList<Word> dict) {
        wordList = dict;
    }


}
