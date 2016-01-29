package android.jochemkleine.com.spraakoefening.data;

import android.provider.UserDictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jochemkleine on 24-1-2016.
 */
public class Exercise implements Serializable {

    private String initiaal = "";
    private String mediaal = "";
    private String finaal = "";
    private int maxLetters = 25;
    private ArrayList<Word> exerciseWordList;

    public Exercise () {

    }

    public ArrayList<Word> getExerciseWordList (){
        return exerciseWordList;
    }
    public String getInitiaal() {
        return initiaal;
    }

    public void setInitiaal(String initiaal) {
        this.initiaal = initiaal;
    }

    public String getMediaal() {
        return mediaal;
    }

    public void setMediaal(String mediaal) {
        this.mediaal = mediaal;
    }

    public String getFinaal() {
        return finaal;
    }

    public void setFinaal(String finaal) {
        this.finaal = finaal;
    }

    public int getMaxLetters() {
        return maxLetters;
    }

    public void setMaxLetters(int maxLetters) {
        this.maxLetters = maxLetters;
    }

    public void initalize (ArrayList<Word> wordList) {
        exerciseWordList = new ArrayList<Word>();
        boolean medEqualsInit = false;
        boolean medEqualsFin = false;
        if (mediaal.equals(initiaal)) {
            medEqualsInit = true;
        }
        if(mediaal.equals(finaal)){
            medEqualsFin = true;
        }

        for (int i =0; i < wordList.size(); i++) {
            String word = wordList.get(i).getWord();
            if (word.startsWith(initiaal) || initiaal.equals("")){
                if(word.endsWith(finaal) || finaal.equals("")){
                    if(word.contains(mediaal) || mediaal.equals("")){
                        if (wordOccurenceCount(word, mediaal) > 2) {
                            if (wordOccurenceCount(word, mediaal) > 2 || mediaal.equals("")) {
                                exerciseWordList.add(wordList.get(i));
                            } else if (medEqualsInit ^ medEqualsFin && wordOccurenceCount(word, mediaal) == 2){
                                exerciseWordList.add(wordList.get(i));
                             } else if (!medEqualsFin && !medEqualsInit){
                                exerciseWordList.add(wordList.get(i));
                            }
                        }
                    }
                }
            }
        }
        System.out.println("exercise list length: " + exerciseWordList.size());
    }

    public int wordOccurenceCount (String word, String sequence){

        Pattern pattern = Pattern.compile(sequence);
        Matcher matcher = pattern.matcher(word);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}
