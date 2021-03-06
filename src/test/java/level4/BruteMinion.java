package level4;

import level3.MinionBoredGame;

import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by neilprajapati on 11/25/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class BruteMinion {
    public static boolean showWork = false;

    public static void main(String[] args) {
        System.out.println( bruteRRLL(8, 5));
    }

    /**
     * List permutation of a string
     *
     * @param s the input string
     * @return  the list of permutation
     */
    public static Set<String> permutation(String s) {
        // The result
        Set<String> res = new HashSet<>();
        // If input string's length is 1, return {s}
        if (s.length() == 1) {
            res.add(s);
        } else if (s.length() > 1) {
            int lastIndex = s.length() - 1;
            // Find out the last character
            String last = s.substring(lastIndex);
            // Rest of the string
            String rest = s.substring(0, lastIndex);
            // Perform permutation on the rest string and
            // merge with the last character
            res = merge(permutation(rest), last);
        }
        return res;
    }

    /**
     * @param list a result of permutation, e.g. {"ab", "ba"}
     * @param c    the last character
     * @return     a merged new list, e.g. {"cab", "acb" ... }
     */
    public static Set<String> merge(Set<String> list, String c) {
        Set<String> res = new HashSet<String>();
        // Loop through all the string in the list
        for (String s : list) {
            // For each string, insert the last character to all possible postions
            // and add them to the new list
            for (int i = 0; i <= s.length(); ++i) {
                String ps = new StringBuffer(s).insert(i, c).toString();
                res.add(ps);
            }
        }
        return res;
    }

    public static int bruteRRLLSS(int t, int n)
    {
        int s = 1-(t+n)%2;
        int l = (t-n+1-s)/2;
        int r = t-l-s;


        Set<String> all = new HashSet<>();

        while(s <= t-n+1 && r >= n-1 && s > 0 && l > 0){

            String base = "";
            for (int i = 0; i < s; i++) {
                base += "S";
            }
            for (int i = 0; i < r; i++) {
                base += "R";
            }
            for (int i = 0; i < l; i++) {
                base += "L";
            }
            Set<String> list = permutation(base);
            all.addAll(list);

            s+=2;
            l--;
            r--;
        }


        int count = 0;
        for(String string: all) {
            String stub = string.replaceAll("S", "");
            if(stub.charAt(0) != 'L' && isValid(stub, n) && stub.charAt(stub.length() - 1) != 'L'&& stub.charAt(stub.length() - 2) != 'L') {
                count ++;
                if(showWork) System.out.println(string);
            }
        }
        return count;
    }

    public static int bruteRRLL(int t, int n)
    {
        if((t-n+1)%2 == 1) return 0;
        int l = (t-n+1)/2;
        int r = t-l;


        Set<String> all = new HashSet<>();


        String base = "";
        for (int i = 0; i < r; i++) {
            base += "R";
        }
        for (int i = 0; i < l; i++) {
            base += "L";
        }
        Set<String> list = permutation(base);
        all.addAll(list);


        int count = 0;

        for(String string: all) {
            if (isValid(string, n)) {
                count ++;
            }
        }
        return count;
    }

    public static int bruteRRLLDegens(int t, int n)
    {
        if((t-n+1)%2 == 1) return 0;
        int l = (t-n+1)/2;
        int r = t-l;


        Set<String> all = new HashSet<>();


        String base = "";
        for (int i = 0; i < r; i++) {
            base += "R";
        }
        for (int i = 0; i < l; i++) {
            base += "L";
        }
        Set<String> list = permutation(base);
        all.addAll(list);


        int count = 0;

        for(String string: all) {
            if(!isValid(string, n)) {
                System.out.println(string);
                count++;
            }
        }
        System.out.println();
        return count;
    }


    private static boolean isValid(String command, int n)
    {
        int currIndex = 0;
        for (int i = 0; i < command.length(); i++) {
            if(currIndex == n-1) return false;

            if(command.charAt(i) == 'R')
                currIndex ++;
            else if(command.charAt(i) == 'L')
                currIndex--;

            if(currIndex < 0) return false;
        }

        return true;
    }

}

