package level3;

import level3.Level3Prob2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by neilprajapati on 7/14/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class Testl3p2 {


    @Test
    public void test1()
    {
        String answer = Level3Prob2.answer("lololololo", "lol");
        assertEquals("looo", answer);
    }

    @Test
    public void test2(){

        String answer = Level3Prob2.answer("goodgooogoogfogoood", "goo");
        assertEquals("dogfood", answer);
    }

    @Test
    public void test3(){

        String answer = Level3Prob2.answer("eyesyesasyeyeyessyeyyesesssy", "yes");
        assertEquals("easy", answer);
    }


}
