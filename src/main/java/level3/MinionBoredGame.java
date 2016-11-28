package level3;

import java.math.BigInteger;

/**
 * where i left off:
 *
 *  REDO THE MATH FOR RLS SHELL!!!!!!!!!! SERIOUSLLY ITLL SAVE TIME>
 */

public class MinionBoredGame {

    /**
     *
     * Definitions:
     *      z=output of answer
     *      L=move left
     *      R=move right
     *      S=stay
     * Intersting things about the problem
     *  if n=2, z=t where t >= 1
     *  if t=n-1, z = 1
     *      since the only possible way to get to rightside is RR...
     *  if t=n, z= t!/( (n-1)!(t-n+1)! ) + 1
     *      there is still no opportunity to LR so the combinations are
     *      just permutation R and S. Plus the RR... combination.
     *      derived using combinations of indistiguishable items formula:
     *          n!/(n1! * n2! * ...)
     *   when adding:
     *      a+b (mod c) = [[a (mod c) ] + [b (mod c) ]] (mod c)
     *
     * CONCEPT:
     *  z= sum of possibilities of all occupied shells
     * Shells:
     *  RRRR shell,
     *      first appears: t=n-1
     *      1 possible state (RRRRR...)
     *  RRSS shell
     *      first appears: t=n
     *      {RRSS}.perm() states:
     *          t!/( (n-1)!(t-n+1 !)
     *
     * //---need more evidence to back up...double check work from here down--//
     *
     *  RLRL shell (no S) (special condition can't go L after on last peice)
     *      first appears: t=n+1 n>2 bc cant go backwards in 2 state sys
     *      Let L1 = {L*...}.perm(), L2 = {*...L}.perm(), L3 = {*...L*}.perm()
     *      {RR...LL...}.perm() - [n(L1)+n(L2)+n(L3)-n(L1 inter L2) - n(L1 inter L3)- n(L2 inter L3) + n(L1 inter L2 inter L3)
     *          we don't have to say -{*..R}.perm() because right on last move = move toward end
     *
     *          note: n(L1) = n(L2) = n(L3) = (t-1)!/(L-1)!/R!
     *          note: n(Ln inter Lk)= (t-2)!/(L-2)!/R! (see proof on line paper 1)
     *              when n!=k n,k element of [1,3]
     *          note: n(L1 inter L2 inter L3) = (t-3)!/(L-3)!/R!
     *
     *          in order for their to be no spaces num(R)-num(L) = n-1
     *          in order to be valid num(R)+num(L)=t
     *          also, it cant be like RRLLL where the number of L is more than the number of R.
     *          System equation yeilds: num(R) = (n-1)/2. num(L) = (1-n+t)/2
     *              Implies that not all systems with high enough values have a valid RLRL shell
     *  RRLRLRSS Shell(special condition can't go L after on last peice):
     *      Most complex shell... D:
     *      We are no longer garenteeded last move = in last 2 places
     *      Note: S doesn't change location. Thus, to determine whether state is possible
     *      Substract the S and check if simplified state (a RRLL) is possible.
     *      Still complex however bc u can sub S...S for R...L
     *
     *      t!/R!/L!/S! - degenerate L cases * (t+1)(t+2)....(t+s)/s!
     *
     *      because each L cases can be interspaces with s creating (t+1)(t+2)....(t+s)/s!
     *      more possibilities for each case.
     *
     *
     *
     */
    private static final BigInteger MODULUS = new BigInteger("123454321");

    public static int answer(int t, int n) {
        if(n==2) return t;

        BigInteger output = rrrrShell(t,n);
        if(t >= n)
            output =output.add(rrssShell(t, n));

        if(t >= n+1)
            output = output.add(rrllShell(t, n));

        if(t >= n+2)
            output =output.add( rrllssShell(t, n));

        return output.mod(MODULUS).intValue();
    }
    //==================SHELL CALCULATIONS=================//
    public static BigInteger rrrrShell(int t, int n)
    {
        if(t == n-1)
            return BigInteger.ONE;
        return BigInteger.ZERO;
    }

    public static BigInteger rrssShell(int t, int n)
    {
        //efficiency is not much of a concern here.
        return permWithRepeats(t, t-n+1, n-1, 0).mod(MODULUS);
    }

    public static BigInteger rrllShell(int t, int n)
    {
        //if its impossible
        if((t-n+1)%2 == 1) return new BigInteger("0");

        int l = (t-n+1)/2;
        int r = t-l;

        BigInteger out = permWithRepeats(t,r,l,0).mod(MODULUS);
//        if(l >= 3)
//            out -= permWithRepeats(t-3,r,l-3,0)*(
//                    3*(t-1)*(t-2)/((l-1)*(l-2))-3*(t-2)/(l-2) + 1
//            )%123454321;
//        else if(l == 2)
//            out -= 3*permWithRepeats(t-2,r,l-2,0)*(
//                    (t-1)/(l-1)-1
//            )%123454321;
//        else if(l==1)
//            out -= 3*permWithRepeats(t-1,r,l-1,0)%123454321;

        BigInteger intersections = new BigInteger("0");
        if(t >= 1 && l >= 1 && r >= 0)
            if(l-1 > r)
                intersections = intersections.add(permWithRepeats(t-1, l-1, r,0).multiply(new BigInteger("3")).mod(MODULUS));
            else
                intersections = intersections.add(permWithRepeats(t-1, r, l-1,0).multiply(new BigInteger("3")).mod(MODULUS));
        if(t >= 2 && l>=2 && r >= 0)
            if(l-2 > r)
                intersections = intersections.add(permWithRepeats(t-2, l-2, r,0).multiply(new BigInteger("-3")).mod(MODULUS));
            else
                intersections = intersections.add(permWithRepeats(t-2, r, l-2, 0).multiply(new BigInteger("-3")).mod(MODULUS));
        if(t >= 3 && l >= 3 && r >= 0)
            if(l-3 > r)
                intersections = intersections.add(permWithRepeats(t-3, l-3, r,0).mod(MODULUS));
            else
                intersections =intersections.add(permWithRepeats(t-3, r ,l-3, 0).mod(MODULUS));
        out =out.subtract( intersections.mod(MODULUS) ) ;

        return out.mod(MODULUS);
    }


    public static BigInteger rrllssShell(int t, int n)
    {

        int s = 1+(t+n)%2;
        int l = (t-n+1-s)/2; //check this!!!
        int r = t-l-s;

        System.out.println("r = " + r+"l = " + l+"s = " + s + "t = " + t + "n = " +n) ;

        BigInteger out = new BigInteger("0");

        //max is when all are RRRRR and the rest are S -1.
        while(r >= n-1 && l > 0 && s > 0){

            out = out.add( permWithRepeats(t,r,l,s).mod(MODULUS));


            BigInteger sPossibilities =  (permWithRepeats(s+l+r,l+r,s,0).mod(MODULUS));
            if(l >= 3)
                out = out.subtract(
                        sPossibilities.multiply(permWithRepeats(t-3-s,r,l-3,0)).multiply(
                                new BigInteger((3*(t-1-s)*(t-2-s)/(l-1)/(l-2)-3*(t-2-s)/(l-2) + 1)+ "")
                        ).mod(MODULUS)
                );

            else if(l == 2)
                out = out.subtract( (new BigInteger("3")).multiply(sPossibilities).multiply(permWithRepeats(t-2-s,r,l-2,0)).multiply(
                        new BigInteger(
                                ((t-1-s)/(l-1)-1) + ""
                        )
                ).mod(MODULUS));
            else if(l == 1)
                out = out.subtract( (new BigInteger("3")).multiply(sPossibilities).multiply(permWithRepeats(t-1-s,r,l-1,0)).mod(MODULUS) ); //-s asdjasdjlksd
            //if l ==0 there are no degenerate l cases :D

            s+=2;
            l--;
            r--;
        }

        return out.mod(MODULUS);
    }




    //==================HELPER MATH METHOD=============//
    /**
     * Calculate t!/dMax!/d2!/d3!
     * It is not necessary to supply largest denom as second argument
     * but that is the most efficient.
     * @param t the numerator
     * @param dMax the largest denominator
     * @param d2 second denomenintor
     * @param d3 second denomenintor
     */
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
