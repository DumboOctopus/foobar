package level3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by neilprajapati on 12/6/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class MinionBoredGamePValueGenerator {
    static ArrayList<BigInteger> pvalues;

    public static void main(String[] args) {

        pvalues = new ArrayList<>(Arrays.asList(new BigInteger[]{
                BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(5)
        }));
        int l = 0;
        while(l <= 250)
        {
            System.out.println("new BigInteger(\""+pc(l) + "\"),");
            l++;
        }

    }

    public static BigInteger pc(int originalL) {
        if(originalL <= 2) return BigInteger.ONE;
        int l = originalL; //number of non fixed l
        int r = l; //number of non fixed r
        int c = r + l; //number of mutatable things

        BigInteger out = permWithRepeats(c, r, l, 0);
        c--;
        l--;
        out =out.subtract( permWithRepeats(c, r, l, 0));
        c--;
        r--;
        out =out.subtract( permWithRepeats(c, r, l, 0));

        l--;r--;c -= 2;

        while (l >= 0 && c > 0 && r >= 0){
            int fixedLs = originalL - l;
            out = out.subtract(pvalues.get(fixedLs).multiply( permWithRepeats(c, r, l, 0)));
            l--;r--;c -= 2;
        }
        if(pvalues.size() <= originalL)
            pvalues.add(originalL, out);
        return out;
    }


    public static BigInteger permWithRepeats(int t, int dMax, int d2, int d3)
    {
        BigInteger out = BigInteger.ONE;

        for(int i = dMax + 1; i <= t; i++)
            out =out.multiply(new BigInteger(i + "")) ;

        for(int i = d2; i > 1; i--)
            out = out.divide(new BigInteger(i + ""));

        for(int i = d3; i > 1; i--)
            out = out.divide(new BigInteger(i + ""));

        return out;
    }
}
