package level4;

import java.math.BigInteger;
import java.util.InputMismatchException;

/**
 * Created by neilprajapati on 11/28/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class BruteMinion2 {
    private static final int MODULUS = 123454321;

    private static final class Case{
        private static int n;

        private final int curIndex,r,l;

        public Case(int curIndex, int r, int l){
            if(curIndex < 0 || curIndex > n-1) throw new InputMismatchException("Invalid index");
            if(r < 0 || l < 0) throw new InputMismatchException("r or l is invalid");
            this.curIndex = curIndex;
            this.r = r;
            this.l = l;
        }

        public Case right() throws Exception
        {
            if(curIndex == n-1) throw new Exception("Locked");
            return new Case(curIndex + 1, r -1, l);
        }

        public Case left() throws Exception
        {
            if(curIndex == n-1) throw new Exception("Locked");
            return new Case(curIndex - 1, r, l-1);
        }

        public int calcPossibilities(){
            if(l <= 0)
            {
                return 1;
            }else{
                int out = 0;
                try{
                    out += right().calcPossibilities() % MODULUS;
                }catch (Exception e){}
                try{
                    out += left().calcPossibilities() % MODULUS;
                }catch (Exception e){}
                return out % MODULUS;

            }
        }
    }

    public static int rrllShell(int t, int n)
    {
        if((t-n+1)%2 == 1) return 0;
        int l = (t-n+1)/2;
        int r = t-l;
        Case.n = n;
        return (new Case(0, r, l)).calcPossibilities();
    }

    public static int rrllssShell(int t, int n)
    {
        Case.n = n;

        int s = 1-(t+n)%2;
        int l = (t-n+1-s)/2;
        int r = t-l-s;

        int out = 0;


        while(s <= t-n+1 && r >= n-1 && s > 0 && l > 0){
            int sPossibilities =  (permWithRepeats(s+l+r,l+r,s,0)).intValue();
            out += (new Case(0, r, l)).calcPossibilities() * sPossibilities % MODULUS;
            s+=2;
            l--;
            r--;
        }

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
