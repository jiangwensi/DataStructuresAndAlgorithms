import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class InverseBWT3 {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    String inverseBWT(String bwt) {

        //build empty matrix

        char[] chars = bwt.toCharArray();
        char[] charsSorted = sortChars(chars);
        int[][] matrix0 = new int[bwt.length()][];
        for (int i = 0; i < matrix0.length; i++) {
            matrix0[i] = new int[2];
        }

        int[] count1 = new int[5];
        for (int i = 0; i < matrix0.length; i++) {
            matrix0[i][0]=charToInteger(charsSorted[i]);
            matrix0[i][1]=count1[charToInteger(charsSorted[i])];
        }

        int[][] matrix1 = new int[bwt.length()][];
        for (int i = 0; i < matrix1.length; i++) {
            matrix1[i] = new int[2];
        }

        int[] count2 = new int[5];
        for (int i = 0; i < matrix1.length; i++) {
            matrix1[i][0]=charToInteger(chars[i]);
            matrix1[i][1]=count2[charToInteger(chars[i])];
        }

//        ArrayList<Integer>[][] pos=new ArrayList[2][];//first and last column
//        for(int i = 0; i < pos.length; i ++){
//            pos[i]= new ArrayList[5];//5 char
//        }
//        for(int p = 0; p < matrix0.length; p ++){
//            pos[0][matrix0[p][0]].add(p);
//        }
//        for(int p = 0; p < matrix1.length; p ++){
//            pos[1][matrix1[p][1]].add(p);
//        }

        ArrayList<Integer>[] pos0 = new ArrayList[5];//5 char
        for(int i = 0; i < pos0.length; i ++){
            pos0[i]=new ArrayList<>();//order
        }

        for(int i = 0; i < matrix0.length; i ++){
            pos0[matrix0[i][0]].add(i);
        }

        ArrayList<Integer>[] pos1 = new ArrayList[5];//5 char
        for(int i = 0; i < pos1.length; i ++){
            pos1[i]=new ArrayList<>();//order
        }

        for(int i = 0; i < matrix1.length; i ++){
            pos1[matrix1[i][0]].add(i);
        }

        int[] order0 = new int[bwt.length()];//index is position, value is the char order
        int[] count = new int[5];
        for(int i = 0; i < charsSorted.length;i++){
            order0[i]=++count[charToInteger(charsSorted[i])];
        }

        int[] order1 = new int[bwt.length()];//index is position, value is the char order
        count = new int[5];
        for(int i = 0; i < chars.length;i++){
            order1[i]=++count[charToInteger(chars[i])];
        }

        StringBuilder result = new StringBuilder();

        int index = 0;
        while(result.length()<bwt.length()){
            result.append(integerToChar(matrix0[index][0]));

            int match = matrix1[index][0];
//            index = pos0[match].get(order0[index]-1);
            index = pos0[match].get(order1[index]-1);
        }


        return result.reverse().toString();
    }

    private void sortArr(char[][] helper) {
        Arrays.sort(helper, new Comparator<char[]>() {
            @Override
            public int compare(char[] o1, char[] o2) {
                int index = -1;
                for (int i = 0; i < o1.length; i++) {
                    if (o1[i] != o2[i]) {
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    //equal if all characters are same
                    return 0;
                } else {
                    if (o1[index] > o2[index]) {
                        return 1;
                    } else if (o1[index] < o2[index]) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }

    private char[] sortChars(char[] chars) {

        int[] count = new int[5];
        for (int i = 0; i < chars.length; i++) {
            count[charToInteger(chars[i])]++;
        }

        char[] sorted = new char[chars.length];
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                sorted[index] = integerToChar(i);
                index++;
            }
        }
        return sorted;
    }

    private int charToInteger(char value) {
        if (value == '$') {
            return 0;
        } else if (value == 'A') {
            return 1;
        } else if (value == 'C') {
            return 2;
        } else if (value == 'G') {
            return 3;
        } else {
            return 4;
        }
    }

    private char integerToChar(int value) {
        if (value == 0) {
            return '$';
        } else if (value == 1) {
            return 'A';
        } else if (value == 2) {
            return 'C';
        } else if (value == 3) {
            return 'G';
        } else {
            return 'T';
        }
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT3().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
