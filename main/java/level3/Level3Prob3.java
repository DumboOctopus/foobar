package level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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


    public static String answer(String[] words) throws Exception
    {
        ArrayList<String> alphabets = new ArrayList<>();
        generateAllAlphabets(words, alphabets);
        return mergeAlphabets(alphabets);
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


    /**
     *TODO
     *
     * does the character exist in alphabet? y = revision mode, n = addition mode
     *
     * Addition mode:
     *  if there is a character before it which exists in alphabet, add it after that character.
     *  if there is a character after it that exists in the alphabet, add it before that character
     *  if neither is possible, throw this alphabet at the end of the queue.
     *
     * Revision mode:
     * does the currentAlphabet match( "beforeWhichInMaster.+char")?
     *  if not, move char right after before. Since all alphabets are true, this wont destroy previous alphabets orderings, just revise it.
     * does the currentAlphabet match( "char.+afterWhichInMaster")?
     *  if not move char right before after.
     nvm there is no revision mode. IF ALL ALPHBETS are correct, how can 2 have discrpency unless they do it after inserting?
     *
     *
     * @param alphabets
     * @return
     */
    private static String mergeAlphabets(ArrayList<String> alphabets) throws Exception
    {
        int offset = 0;
        ArrayList<String> trollList = new ArrayList<>();
        String master = removeLongest(alphabets);

        if(alphabets.size() < 1) return master; //lets hope all of the cases are like this one :D

        do{
            String newMaster = merge2Alphabets(master, alphabets.get(0));

            if(newMaster == null){
                trollList.add(alphabets.remove(0));
            }
            else {
                alphabets.remove(0);
                master = newMaster;
            }
        } while(alphabets.size() > offset);

        for(String str: trollList){
            String newMaster = merge2Alphabets(master, str);

            if(newMaster != null){
                master = newMaster;
            }
        }

        return master;
    }

    private static String removeLongest(ArrayList<String> alphabets)
    {
        int index = 0;
        for (int i = 1; i < alphabets.size(); i++) {
            if (alphabets.get(i).length() > alphabets.get(index).length()) index =i;
        }
        return alphabets.remove(index);
    }

    /**
     *
     * @param master
     * @param other
     * @return the merged alphabet if possible, otherwise null
     */

    public static String merge2Alphabets(String master, String other)
    {
        if(alphabetsMatch(master, other)) return master;
        for (int i = 0; i < other.length(); i++) {
            int after = lowestIndexAfter(master, other, i);
            int before = i-1;
            boolean revisionMode = master.contains(other.charAt(i) + "");


            if(before != -1)
            {
                if(revisionMode) {
                    //check if matches regex
                    if (!master.matches(".*" + other.charAt(before) + ".*" + other.charAt(i) +".*" )) {
                        master = master.replace(other.charAt(i) + "", "");
                        master = master.replace(other.charAt(before) + "", other.charAt(before) + "" + other.charAt(i));
                    }
                    continue;
                } else
                {
                    master = master.replace(other.charAt(before) + "", other.charAt(before) + "" + other.charAt(i));
                    continue;
                }
            }

            if(after != -1)
            {
                if(revisionMode) {
                    //check if matches regex
                    if(!master.matches(".*"+other.charAt(i) + ".*" + other.charAt(after) + ".*"))
                    {
                        master = master.replace(other.charAt(i) +"", "");
                        master = master.replace(other.charAt(after) + "", "" + other.charAt(i) + other.charAt(after));
                    }
                    continue;
                } else
                {
                    master = master.replace(other.charAt(after) + "", "" + other.charAt(i) + other.charAt(after));
                    continue;
                }
            }

            return null;

        }
        return master;
    }

    private static boolean alphabetsMatch(String master, String other)
    {
        int currIndexOfOther = 0;

        for (int i = 0; i < master.length(); i++) {
            if(currIndexOfOther >= other.length()) return true;
            if(master.charAt(i) == other.charAt(currIndexOfOther)) currIndexOfOther++;
        }
        return false;
    }


    private static int lowestIndexAfter(String master, String other, int middleInd)
    {
        for (int i = middleInd + 1; i < other.length(); i++) {
            if(master.contains(other.charAt(i) + "")) return i;
        }
        return -1;
    }
}
