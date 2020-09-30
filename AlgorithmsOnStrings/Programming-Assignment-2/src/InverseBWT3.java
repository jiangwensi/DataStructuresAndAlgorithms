import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
        StringBuilder result = new StringBuilder();

        //build empty matrix
        char[] chars = bwt.toCharArray();
        char[][] matrix = new char[bwt.length()][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new char[matrix.length];
        }

        //sort bwt char
        char[] charsSorted = sortChars(chars);

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = charsSorted[i];
            matrix[i][matrix.length - 1] = chars[i];
        }

        char[][] helper = new char[matrix.length][];
        char[][] helperSortd = new char[matrix.length][];

        for (int endIndex = 1; endIndex < matrix.length - 1; endIndex++) {
            for (int i = 0; i < matrix.length; i++) {
                helper[i] = new char[endIndex+1];
            }

            for (int i = 0; i < matrix.length; i++) {
                helper[i][0] = chars[i];
                for (int j = 0; j < endIndex; j++) {
                    helper[i][j+1] = matrix[i][j];
                }
            }

            char[][] sortedHelper = sortArr(helper,matrix,endIndex);

            for (int i = 0; i < matrix.length; i++) {
                matrix[i][endIndex] = sortedHelper[i][endIndex];
            }
            int i = 0;
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            result.append(matrix[0][i + 1]);
        }
        result.append("$");
        return result.toString();
    }

    private char[][] sortArr(char[][] helper,char[][] matrix,int endIndex) {
        HashMap<char[],Integer> sortedSection = new HashMap<>();
        for(int i = 0; i < matrix.length; i ++){
            sortedSection.put(Arrays.copyOf(matrix[i],endIndex),i);
        }

        HashMap<char[],char[]> helperWholeToSortedMap = new HashMap<>();
        for(int i = 0; i < matrix.length; i ++){
            helperWholeToSortedMap.put(helper[i],Arrays.copyOf(helper[i],endIndex));
        }

        char[][] sortedHelper = new char[helper.length][];
        for(int i = 0; i < helper[0].length;i++){
            sortedHelper[i]=new char[helper[0].length];
        }

        for(int i = 0; i < helper.length; i ++){
            System.out.println(helper[i]);
            System.out.println(helperWholeToSortedMap.get(helper[i]));
            System.out.println(sortedSection.get(helperWholeToSortedMap.get(helper[i])));
            sortedHelper[sortedSection.get(helperWholeToSortedMap.get(helper[i]))]=helper[i];
        }
        return sortedHelper;
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
