package level3;

/**
 * Created by neilprajapati on 7/19/16.
 * neilprajapati, dont forget to javaDoc this file.
 */
public class Test {
    public static void main(String[] args) {
        for (char i = 'A'; i <='Z'; i++) {
            for (char j = 'A'; j <='Z'; j++)
                System.out.println("private static class "+i+""+j+" extends Exception{}");
        }

        for (char i = 'a'; i <='z'; i++) {
            for (char j = 'a'; j <='z'; j++)
                System.out.println("\tcase \""+i+""+j+"\":\n\t\tthrow new " + ("" + i).toUpperCase() + ("" + j).toUpperCase() + "();");
        }
//
//        for (int i = 1; i <=12; i++) {
//            System.out.println("\tcase "+i+":\n\t\tthrow new " + ( (char)('a'+ i) + "").toUpperCase() + "();");
//        }

    }

    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
}
