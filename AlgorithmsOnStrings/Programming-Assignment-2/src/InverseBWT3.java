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

        //build matrixStart for first column in bwt matrix
        //col1 is char, col2 is order
        int[][] matrixStart = new int[bwt.length()][];
        for (int i = 0; i < matrixStart.length; i++) {
            matrixStart[i] = new int[2];
        }

        char[] chars = bwt.toCharArray();
        char[] charsSorted = sortChars(chars);
        int[] count = new int[5];
        for (int i = 0; i < matrixStart.length; i++) {
            matrixStart[i][0]=charToInteger(charsSorted[i]);
            matrixStart[i][1]=count[charToInteger(charsSorted[i])];
        }

        //build matrixStart for last column in bwt matrix
        //col1 is char, col2 is order
        int[][] matrixEnd = new int[bwt.length()][];
        for (int i = 0; i < matrixEnd.length; i++) {
            matrixEnd[i] = new int[2];
        }

        count = new int[5];
        for (int i = 0; i < matrixEnd.length; i++) {
            matrixEnd[i][0]=charToInteger(chars[i]);
            matrixEnd[i][1]=count[charToInteger(chars[i])];
        }

        //build pos arr for matrixStart, col1 is char, col2 is order, value is pos in column 1
        ArrayList<Integer>[] posInMatrixStart = new ArrayList[5];//5 char
        for(int i = 0; i < posInMatrixStart.length; i ++){
            posInMatrixStart[i]=new ArrayList<>();//order
        }

        for(int i = 0; i < matrixStart.length; i ++){
            posInMatrixStart[matrixStart[i][0]].add(i);
        }

        //build order arr for matrixEnd, what's the char order in a position
        int[] orderInMatrixEnd = new int[bwt.length()];//index is position, value is the char order
        count = new int[5];
        for(int i = 0; i < chars.length;i++){
            orderInMatrixEnd[i]=++count[charToInteger(chars[i])];
        }

        StringBuilder result = new StringBuilder();

        int index = 0;
        while(result.length()<bwt.length()){
            result.append(integerToChar(matrixStart[index][0]));
            int match = matrixEnd[index][0];
            index = posInMatrixStart[match].get(orderInMatrixEnd[index]-1);
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
