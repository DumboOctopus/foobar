package level4;

import level3.MinionBoredGame;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;



import static junit.framework.TestCase.assertEquals;

/**
 * Created by neilprajapati on 11/24/16.
 * neilprajapati, dont forget to javaDoc this file.
 */

public class MinionBoredGameTest {

    @Before
    public void reset()
    {
        BruteMinion.showWork = false;
    }

    //===========HELPER METHOD TEST=================//
    @Test
    public void testPermWithRepeats1()
    {
        long ans = MinionBoredGame.permWithRepeats(5, 2, 3, 0).intValue();
        assertEquals(10, ans);
    }

    @Test
    public void testPermWithRepeats2()
    {
        long ans = MinionBoredGame.permWithRepeats(12, 3, 2, 0).intValue();
        assertEquals(39916800, ans);
    }



    //=========================RRLL SHELL TEST=================//


    @Test
    public void testrrllShell1()
    {
        int ans = MinionBoredGame.rrllShell(4, 3).intValue();
        assertEquals(1, ans);
    }



    @Test
    public void testrrllShell2()
    {
        int ans = MinionBoredGame.rrllShell(5,  3).intValue();
        assertEquals(0, ans);
    }



    @Test
    public void testrrllShell4()
    {
        int ans = MinionBoredGame.rrllShell(6,  4).intValue(); //RRRRLR not possible
        assertEquals(0, ans);
    }



    @Test
    public void testrrllShell5()
    {
        int ans = MinionBoredGame.rrllShell(7,  4).intValue(); //RRRRLRL
        assertEquals(6, ans);
    }

    @Test
    public void testrrllShell6()
    {
        int t=8, n=3;
        int ans = MinionBoredGame.rrllShell(t, n).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion.bruteRRLL(t, n), ans);
    }

    @Test
    public void testrrllShell7()
    {
        int t=12, n=3;
        int ans = MinionBoredGame.rrllShell(t, n).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion.bruteRRLL(t, n), ans); //126
    }

    @Test
    public void testrrllShell8()
    {
        int t=15, n=6;
        int ans = MinionBoredGame.rrllShell(t, n).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion.bruteRRLL(t, n), ans); //792
    }

    @Ignore
    @Test
    public void testrrllShellMany(){
        for (int n = 3; n < 50; n++) {
            for(int t = n + 1; t < n+10; t++)
                assertEquals(BruteMinion.bruteRRLL(t, n)+"t:"+t+"n:"+n, MinionBoredGame.rrllShell(t, n)+"t:"+t+"n:"+n);
        }
    }


    //===============RRLLSS shell=================//
    @Test
    public void testrrllssShell1()
    {
        int ans = MinionBoredGame.rrllssShell(5,  3).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion.bruteRRLLSS(5,3), ans);
    }

    @Test
    public void testrrllssShell2()
    {
        int ans = MinionBoredGame.rrllssShell(6,  4).intValue(); //RRRRLRL
        assertEquals(BruteMinion.bruteRRLLSS(6,  4), ans);
    }

    @Test
    public void testrrllssShell3()
    {
        int ans = MinionBoredGame.rrllssShell(7,  3).intValue(); //RRRRLRL
        assertEquals(BruteMinion.bruteRRLLSS(7,  3), ans);
    }

    @Test
    public void testrrllssShel4()
    {
        int ans = MinionBoredGame.rrllssShell(10,  4).intValue(); //RRRRLRL
        assertEquals(BruteMinion.bruteRRLLSS(10,4), ans);
    }

    @Ignore
    @Test
    public void testRRLLSSshell5(){
        for (int n = 3; n < 50; n++) {
            for(int t = n + 2; t < n+10; t++)
                assertEquals(BruteMinion.bruteRRLLSS(t, n), MinionBoredGame.rrllssShell(t, n).intValue());
        }
    }


    @Test
    public void lol()
    {
        BruteMinion.showWork = true;
        System.out.println(BruteMinion.bruteRRLLSS(5, 3));
    }

    //==========================ANSWER TEST============//

    @Test
    public void testAnswer1()
    {
        int[] answers={-1, -1, 1, 4, 8, 26};
        for(int t=2; t<answers.length; t++)
            assertEquals(answers[t], MinionBoredGame.answer(t, 3));
    }

    @Test
    public void officialFoobarTest4(){
        int n = 6, t = 10;
        System.out.println(MinionBoredGame.rrssShell(t, n));
        BruteMinion.showWork = true;
        System.out.println(BruteMinion.bruteRRLLSS(t, n));

        //RRRRR RLRLS
        //RRRRR RLSSS
        //RRRRR SSSSS


    }
}
