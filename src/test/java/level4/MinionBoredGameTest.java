package level4;

import level3.MinionBoredGame;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.math.BigInteger;

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
        assertEquals(BruteMinion2.rrllShell(t, n), ans);
    }

    @Test
    public void testrrllShell7()
    {
        int t=12, n=3;
        int ans = MinionBoredGame.rrllShell(t, n).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion2.rrllShell(t, n), ans); //126
    }

    @Test
    public void testrrllShell8()
    {
        int t=15, n=6;
        int ans = MinionBoredGame.rrllShell(t, n).intValue(); //RRRRLRL
        BruteMinion.showWork = true;
        assertEquals(BruteMinion2.rrllShell(t, n), ans); //792
    }

    @Ignore
    @Test
    public void testrrllShellMany(){
        for (int n = 3; n < 50; n++) {
            for(int t = n + 1; t < n+10; t++)
                assertEquals(BruteMinion2.rrllShell(t, n)+"t:"+t+"n:"+n, MinionBoredGame.rrllShell(t, n)+"t:"+t+"n:"+n);
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
        assertEquals(BruteMinion2.rrllssShell(t,n), MinionBoredGame.rrllssShell(t,n).intValue());
    }

    @Test
    public void officialFoobarTest5(){
        int n = 501, t = 1000;
        assertEquals(BruteMinion2.rrllShell(t,n), MinionBoredGame.rrllssShell(t,n).intValue());
    }

    @Test
    public void test()
    {
        System.out.println(BruteMinion2.rrllShell(11,4));
        System.out.println(BruteMinion.bruteRRLL(11, 4));

        int n = 4;
        for (int t = 5; t < 20; t+=2) {
            System.out.println(BruteMinion.bruteRRLLDegens(t, n)+ ", l" + (t-n+1)/2);
        }

        //l1: 3
        //L...   ...L   ...LR


        //1 new beginning, 1 new end
        //l2: 5
        //L...   ...L   ...LR   RLL... ...LLRR


        //2 new beginning, 2 new end
        //l3: 9
        //L...   ...L   ...LR   RLL... ...LLRR    RLRLL...    RRLLL...   ...LLLRRR   ...LLRLRR


        //5 new beginning, 5 new end
        //l4: 19
        //[l3]   RRRLLLL... RRLRLLL... RLRRLLL... RLRLRLL...  RRLLRLL ...LLLLRRRR ...LRLLLRRR  ...LLRLLRRR  ...LLLRLRRR ...LLLRRLRR


        //l at max can be 250
        //RRRRLLLLL

        //p2 = 1
        //p3 = 2
        //p4 = 5
        //p5 = 14
        //p6 = 42
        // pc = c!/r!/l! - 5(c-1)!/(l-1)!/r! - 2(c-3)!/(l-2)!/(r-1)! - (c-5)!/(l-3)!/(r-2)! - (c-7)!/(l-4)!/(r-3)! - ...

    }

    @Test
    public void t()
    {
        System.out.println(
                pc(6)
        );
    }

    public static int pc(int originalL) {
        int l = originalL; //number of non fixed l
        int r = l; //number of non fixed r
        int c = r + l; //number of mutatable things

        int out = permWithRepeats(c, r, l, 0);
        c--;
        l--;
        out -= permWithRepeats(c, r, l, 0);
        c--;
        r--;
        out -= permWithRepeats(c, r, l, 0);

        l--;r--;c -= 2;

        int[] p = {1, 1, 1, 2, 5, 14};
        while (l >= 0 && c > 0 && r >= 0){
            int fixedLs = originalL - l;
            out -= p[fixedLs] * permWithRepeats(c, r, l, 0);
            l--;r--;c -= 2;
        }

        return out;
    }


    public static int permWithRepeats(int t, int dMax, int d2, int d3)
    {
        BigInteger out = BigInteger.ONE;

        for(int i = dMax + 1; i <= t; i++)
            out =out.multiply(new BigInteger(i + "")) ;

        for(int i = d2; i > 1; i--)
            out = out.divide(new BigInteger(i + ""));

        for(int i = d3; i > 1; i--)
            out = out.divide(new BigInteger(i + ""));

        return out.intValue();
    }
}
