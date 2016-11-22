package level3;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.prefs.BackingStoreException;
import java.util.zip.DataFormatException;

/**
 * Created by neilprajapati on 7/16/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class Level3Prob3 {


    /*Concept.
        *go the all first elements of queue words
        *put them in list in that order.
        *generate new sublist of characters withs same first letter
        *register that into queue
        repeat until queue is empty.
        return out
       */
//    public static String answer(String[] words) {
//
//        String out = "";
//        Queue<String[]> lists = new LinkedList<>();
//        lists.add(words);
//
//
//        do{
//            String[] currArray = lists.poll();
//
//            ArrayList<String> commons = new ArrayList<>();
//            char previousChar = currArray[0].charAt(0);
//
//
//
//            for (int i = 0; i < currArray.length; i++) {
//
//                char currentCharacter = currArray[i].charAt(0);
//
//                if (previousChar != currentCharacter) {
//                    register(lists, commons);
//                    previousChar = currentCharacter;
//                }
//                String substring = currArray[i].substring(1);
//                addIfNotEmpty(commons, substring);
//
//                Character characterBefore = i-1 >= 0? currArray[i-1].charAt(0): null;
//                Character characterAfter = i+1 < currArray.length? currArray[i+1].charAt(0): null;
//                if(!out.contains(currentCharacter + "")) {
//
//                    if(characterBefore != null)
//                    {
//                        out = out.replace(characterBefore + "", characterBefore + "" + currentCharacter + "");
//                    } else if(characterAfter != null && out.contains(characterAfter +""))
//                    {
//                        out = out.replace(characterAfter + "", currentCharacter + "" + characterAfter);
//                    } else
//                    {
//                        out = out + currentCharacter;
//                    }
//                } else
//                {
//                    //now do we need to move it?
//                    //the case where we have [ae, ac, ce, ca] where first insert is not 100 correct
//                    if (characterAfter != null && out.contains(""+characterAfter) && !out.matches(currentCharacter + ".+"+characterAfter)) {
//                        out = out.replace(currentCharacter+"", "");
//                        out = out.replace(characterAfter + "", currentCharacter + "" + characterAfter);
//                    }
//                    if (characterBefore != null && !out.matches(characterBefore + ".+"+ currentCharacter)) {
//                        out = out.replace(currentCharacter+"", "");
//                        out = out.replace(characterAfter + "", currentCharacter + "" + characterAfter);
//                    }
//                }
//            }
//
//            register(lists, commons);
//
//        }while(lists.size() > 0);
//
//        return out;
//    }
//
//    private static void addIfNotEmpty(ArrayList<String> commons, String substring) {
//        if(substring.length() != 0)
//            commons.add(substring);
//    }
//
//    private static void register(Queue<String[]> lists, ArrayList<String> commons) {
//        String[] prcsed = new String[commons.size()];
//        commons.toArray(prcsed);
//        if(prcsed.length > 1)
//            lists.add(prcsed);
//        commons.clear();
//    }
    private static int pn = 0;

    /*
    Test case 4:
        [uo, ur, uurkoovr, uuz, uuay, uukb, uuj, uugvgcac, uuebl, uueyq, uif, ukm, ufu, ujqgovwb, ujosbhkw, ujuuaatt, ujuumtzn, ujui, ujul, ujzxjzt
     */

    public static String answer(String[] words)
    {
        ArrayList<String> alphabets = new ArrayList<>();
        generateAllAlphabets(words, alphabets);
        return eliminateCombine(alphabets);
    }

    /**
     *
     * @param words cannot have any empty strings or nulls
     * @param allAlphabets
     */
    public static void generateAllAlphabets(String[] words, ArrayList<String> allAlphabets)
    {
        String currLevelAlphabet = "";


        ArrayList<String> nextLevelAlphabet = new ArrayList<>();

        //initialization
        if (words[0].length() > 1)
            nextLevelAlphabet.add(words[0].substring(1));
        currLevelAlphabet += words[0].charAt(0);
        Character previousChar = words[0].charAt(0);

        //the loop thing.
        for (int i = 1; i < words.length; i++) {
            String word = words[i];

            if (previousChar != word.charAt(0)) {
                currLevelAlphabet += word.charAt(0);
                startNextAlphabet(allAlphabets, nextLevelAlphabet);
                nextLevelAlphabet.clear();
            }

            if (word.length() > 1)
                nextLevelAlphabet.add(word.substring(1));
            previousChar = word.charAt(0);
        }

        startNextAlphabet(allAlphabets, nextLevelAlphabet);

        if(currLevelAlphabet.length() > 1)
            allAlphabets.add(currLevelAlphabet);

        //this makes me so happy that its so nicely refactored from what it used to be :D
    }

    private static void startNextAlphabet(ArrayList<String> allAlphabets, ArrayList<String> nextLevelAlphabet) {
        if (nextLevelAlphabet.size() > 1) {
            String[] nextLevel = new String[nextLevelAlphabet.size()];
            nextLevelAlphabet.toArray(nextLevel);
            generateAllAlphabets(nextLevel, allAlphabets);
        }
    }

    public static String eliminateCombine(ArrayList<String> alphabets) {
        String out = "";

        do{
            char first = getFirstInAlphabet(alphabets);
            out += first;

            removeFirst(alphabets, first);

        } while(alphabets.size() > 0);
        return out;
    }

    private static void removeFirst(ArrayList<String> alphabets, char first) {
        for (int i = alphabets.size() - 1; i >= 0; i--) {
            String prcs = alphabets.get(i);
            prcs = prcs.replace("" + first, "");
            if(prcs.length() == 0)
            {
                alphabets.remove(i);
            } else
            {
                alphabets.set(i, prcs);
            }
        }
    }

    private static char getFirstInAlphabet(ArrayList<String> alphabets) {
        char first = alphabets.get(0).charAt(0);

        for(String alphabet: alphabets)
        {
            if(alphabet.indexOf(first) > 0) first = alphabet.charAt(0);
        }

        return first;
    }


}
