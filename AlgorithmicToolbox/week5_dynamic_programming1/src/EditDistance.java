import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        //write your code here
        int[][] d = new int[s.length()+1][];
        for (int i = 0; i <= s.length(); i++) {
            d[i] = new int[t.length()+1];
        }
        for (int i = 0; i <= s.length(); i++) {
            d[i][0] = i;
        }
        for (int i = 0; i <= t.length(); i++) {
            d[0][i] = i;
        }
        d[0][0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {

                int del = d[i - 1][j] + 1;
                int ins = d[i][j - 1] + 1;
                int matchOrMismatch = d[i - 1][j - 1];
                if (s.charAt(i-1) != t.charAt(j-1)) {
                    matchOrMismatch += 1;
                }
                int minCount = del;
                if (ins < minCount) {
                    minCount = ins;
                }
                if (matchOrMismatch < minCount) {
                    minCount = matchOrMismatch;
                }
                d[i][j] = minCount;
            }
        }
        return d[s.length()][t.length()];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
