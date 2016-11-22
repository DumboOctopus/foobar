
import level3.Level3Prob3;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by neilprajapati on 7/16/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class Test3Prob3 {
    @Test
    public void foobarOfficalTest1()
    {
        String[] words = {"ba", "ab", "cb"};
        String answer = Level3Prob3.answer(words);
        assertEquals("bac", answer);

    }

    @Test
    public void foobarOfficalTest2()
    {
        String[] words = {"y", "z", "xy"};
        String answer = Level3Prob3.answer(words);
        assertEquals("yzx", answer);
    }

    @Test
    public void foobarOfficalTest3()
    {
        String[] words = {"z", "yx", "yz"};
        String answer = Level3Prob3.answer(words);
        assertEquals("xzy", answer);
    }


    @Test
    public void test1()
    {
        String[] words = {"a","ba", "ca", "cb"};
        String answer = Level3Prob3.answer(words);
        assertEquals("abc", answer);
    }

    @Test
    public void test2()
    {
        String[] words = {"ae", "ac", "ce", "ca"};
        String answer = Level3Prob3.answer(words);
        assertEquals("eac", answer);
    }

    @Test
    public void test3()
    {
        String[] words = {"able", "bell", "bla", "ble", "eebee" ,"lee"};
        String answer = Level3Prob3.answer(words);
        assertEquals("abel", answer);
    }

    @Test
    public void test4()
    {
        String[] words = {"able","ae", "alaaaa", "bell", "bla", "ble", "lal" ,"lee"};
        String answer = Level3Prob3.answer(words);
        assertEquals("abel", answer);
    }

    @Test
    public void test5()
    {
        String[] words = {"acc","acd", "ace", "aenemic", "all", "bell", "bla", "ble", "cal", "lal", "lc" ,"lee" };
        String answer = Level3Prob3.answer(words);
        assertEquals("abcdel", answer);
    }


    @Ignore
    @Test
    public void testGenerateAllAlphabets()
    {
        String[] words = {"ba", "ab", "cb"};
        ArrayList<String> alphabets = new ArrayList<>();
        Level3Prob3.generateAllAlphabets(words, alphabets);
        String[] actual = new String[alphabets.size()]; alphabets.toArray(actual);
        assertArrayEquals(new String[]{"bac"}, actual);
    }

    @Ignore
    @Test
    public void testGenerateAllAlphabets2()
    {
        long startTime = System.currentTimeMillis();
        String[] words = {"ae", "ac", "ce", "ca"};
        ArrayList<String> alphabets = new ArrayList<>();
        Level3Prob3.generateAllAlphabets(words, alphabets);
        System.out.println(System.currentTimeMillis() - startTime);
        String[] actual = new String[alphabets.size()]; alphabets.toArray(actual);
        System.out.println( Arrays.toString(actual));
        assertArrayEquals(new String[]{"ec", "ea", "ac"}, actual);
    }

    @Ignore
    @Test
    public void testGenerateAllAlphabets3()
    {
        long startTime = System.currentTimeMillis();
        //ae,
        String[] words = {"eca", "ecca", "ca", "ce"};
        ArrayList<String> alphabets = new ArrayList<>();
        Level3Prob3.generateAllAlphabets(words, alphabets);
        System.out.println(System.currentTimeMillis() - startTime);
        String[] actual = new String[alphabets.size()]; alphabets.toArray(actual);
        System.out.println( Arrays.toString(actual));
        assertArrayEquals(new String[]{"ec", "ea", "ac"}, actual);
    }

    @Ignore
    @Test
    public void testGenerateAllAlphabets4()
    {
        long startTime = System.currentTimeMillis();
        //ae,
        String[] words = {"uo", "ur", "uurkoovr", "uuz", "uuay", "uukb", "uuj", "uugvgcac", "uuebl", "uueyq", "uif", "ukm", "ufu", "ujqgovwb", "ujosbhkw", "ujuuaat", "ujuumtzn", "ujui", "ujul", "ujzxjzt"};
        ArrayList<String> alphabets = new ArrayList<>();
        Level3Prob3.generateAllAlphabets(words, alphabets);
        System.out.println(System.currentTimeMillis() - startTime);
        String[] actual = new String[alphabets.size()]; alphabets.toArray(actual);
        System.out.println( Arrays.toString(actual));
        assertArrayEquals(new String[]{"ec", "ea", "ac"}, actual);
    }


    @Test
    public void elimMerge()
    {
        String[] words = {"zbd", "zab", "abc", "cd", "za"};
        ArrayList<String> alpha = new ArrayList<>();
        Collections.addAll(alpha, words);
        String out = Level3Prob3.eliminateCombine(alpha);
        System.out.println(out);
    }
//    @Test
//    public void testMerge1()
//    {
//        String[] alphabets = new String[]{"eca", "ac"};
//        String answer = Level3Prob3.merge2Alphabets(alphabets[0], alphabets[1]);
//        assertEquals("eac", answer);
//    }
//
//    @Test
//    public void testMerge2()
//    {
//        ArrayList<String> alphabets = new ArrayList<>();
//        alphabets.add("eca"); alphabets.add( "bd"); alphabets.add( "de");
//        String answer = Level3Prob3.mergeAlphabets(alphabets);
//        assertEquals("bdeca", answer);
//    }

}
