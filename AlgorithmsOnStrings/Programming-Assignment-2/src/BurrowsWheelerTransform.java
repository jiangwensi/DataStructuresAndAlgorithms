import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        // write your code here
        int textLen = text.length() - 1;
        ArrayList<Character>[] matrix = new ArrayList[textLen + 1];
        int index = textLen;
        String helper = text + text;
        for (int i = 0; i <= textLen; i++) {
            matrix[i] = new ArrayList<>();
            for (int j = 0; j <= textLen; j++) {
                matrix[i].add(helper.charAt(index + j));
            }
            index--;
        }

        Arrays.sort(matrix, new Comparator<ArrayList<Character>>() {
            @Override
            public int compare(ArrayList<Character> o1, ArrayList<Character> o2) {
                //find the index of first different character
                int index = -1;
                for (int i = 0; i < o1.size(); i++) {
                    if (o1.get(i) != o2.get(i)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    //equal if all characters are same
                    return 0;
                } else {
                    if(o1.get(index).equals('$')){
                        return -1;
                    } else if(o2.get(index).equals('$')){
                        return 1;
                    } else {
                        return o1.get(index).compareTo(o2.get(index));
                    }
                }
            }
        });

        for (int i = 0; i < matrix.length; i++) {
            result.append(matrix[i].get(matrix[i].size() - 1));
        }

        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
