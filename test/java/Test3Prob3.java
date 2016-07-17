import com.sun.deploy.util.ArrayUtil;
import com.sun.deploy.util.SystemUtils;
import level3.Level3Prob2;
import level3.Level3Prob3;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void test5()
    {
        String[] words = {"a", "ca", "cb"};
        String answer = Level3Prob3.answer(words);
        assertEquals("abc", answer);
    }

    @Test
    public void test6()
    {
        String[] words = {"ae", "ac", "ce", "ca"};
        String answer = Level3Prob3.answer(words);
        assertEquals("eac", answer);
    }

    @Test
    public void testGenerateAllAlphabets()
    {
        String[] words = {"ba", "ab", "cb"};
        ArrayList<String> alphabets = new ArrayList<>();
        Level3Prob3.generateAllAlphabets(words, alphabets);
        String[] actual = new String[alphabets.size()]; alphabets.toArray(actual);
        assertArrayEquals(new String[]{"bac"}, actual);
    }

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

    @Test
    public void testMerge1()
    {
        String[] alphabets = new String[]{"eca", "ac"};
        String answer = Level3Prob3.merge2Alphabets(alphabets[0], alphabets[1]);
        assertEquals("eac", answer);
    }
}
