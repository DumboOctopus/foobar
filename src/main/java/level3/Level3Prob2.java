package level3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by neilprajapati on 7/14/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class Level3Prob2 {


    public static String printRandomThing(String chunk, String word)
    {
        int numTimes = (int)(Math.random()* 10);
        for(int i = 0; i < numTimes; i++)
        {
            int randPos = (int)(Math.random() * chunk.length());
            chunk = chunk.substring(0, randPos) + word + chunk.substring(randPos);
        }
        return chunk;
    }


    //==================ACTUALLY CODE

    public static String answer(String chunk, String word) {
        ArrayList<String> psuedoQueue = new ArrayList<>();
        Set<String> alreadyDone = new HashSet<>();
        String out = chunk;
        psuedoQueue.add(chunk);


        //CONCEPT:
        //gernerate one each possible removal of a "word".
        //register all of them into psuedoQueue iff they aren't in already
        //find out which one is shortest and most lexographically good ->out
        //NOTE: there might be some sort of simplification but meh this is bruteforce so itll probably work

        do{
            String newChunk = psuedoQueue.remove(0);
            for(int i = 0, len = newChunk.length() - word.length() + 1; i < len; i++)
            {
                //check if we found a "word" at index i. NOTE: does not preform i += word.length() in order to capture all "word" in list
                if(newChunk.regionMatches(i, word, 0, word.length()))
                {
                    //generate substring that excludes "word" at i;
                    String sbstring = newChunk.substring(0,i) + newChunk.substring(word.length() + i);
                    //if it doesn't already exist in list... e.i if we didn't already check it
                    if(!alreadyDone.contains(sbstring)) {
                        //check if its the best option. if it is substring-> out
                        if(sbstring.length() < out.length())
                            out = sbstring;
                        else if(sbstring.compareTo(out) < 0)
                            out =sbstring;

                        //register the substring to psuedoQueue.
                        psuedoQueue.add(sbstring); //basically a recursive call
                        alreadyDone.add(sbstring);
                    }
                }
            }
        } while(psuedoQueue.size() > 0);


        return out;

    }


}
