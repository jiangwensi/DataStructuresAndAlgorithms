import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

        // write your code here
        Character[] chars = bwt.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        ArrayList<Character>[] matrix = new ArrayList[bwt.length()];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new ArrayList<>();
        }

        Character[] charsSorted = new Character[matrix.length];
        for(int i = 0; i<charsSorted.length; i++){
            charsSorted[i]=chars[i];
        }

        sortChars(charsSorted);

        for (int i = 0; i < matrix.length; i++) {
            matrix[0].add(charsSorted[i]);
            matrix[matrix.length-1].add(chars[i]);
        }
//        ArrayList<Character>[] helper=null;
        for (int endIndex = 1; endIndex < matrix.length - 1; endIndex++) {
            ArrayList<Character>[] helper = new ArrayList[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                helper[i] = new ArrayList<Character>();
            }
            for (int i = 0; i < matrix.length; i++) {
                helper[i].add(chars[i]);
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < endIndex; j++) {
                    helper[i].add(matrix[j].get(i));
                }
            }
            sortArrayList(helper);
            for(int i = 0; i < matrix.length; i++){
                matrix[endIndex].add(helper[i].get(endIndex));
            }
            int i = 0;
        }

        for(int i = 0;i<matrix.length-1; i++){
            result.append(matrix[i+1].get(0));
        }
        result.append("$");
        return result.toString();
    }

    private void sortArrayList(ArrayList<Character>[] helper) {
        Arrays.sort(helper, new Comparator<ArrayList<Character>>() {
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
//                    if(o1.get(index).equals('$')){
//                        return -1;
//                    } else if(o2.get(index).equals('$')){
//                        return 1;
//                    } else {
                        return o1.get(index).compareTo(o2.get(index));
//                    }
                }
            }
        });
    }

    private void sortChars(Character[] charsSorted) {
        Arrays.sort(charsSorted, new Comparator<Character>() {

            @Override
            public int compare(Character o1, Character o2) {
//                if (o1.equals(o2)) {
//                    return 0;
//                } else if (o1.equals("$")) {
//                    return -1;
//                } else if (o2.equals("$")) {
//                    return 1;
//                } else {
                    return o1.compareTo(o2);
//                }
            }
        });
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
