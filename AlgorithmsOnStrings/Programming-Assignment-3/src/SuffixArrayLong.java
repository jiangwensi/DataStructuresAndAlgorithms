import java.util.*;
import java.io.*;

public class SuffixArrayLong {
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        int[] result = new int[text.length()];

        // write your code here
        int[] order = sortCharacters(text);
        int[] cls = computeCharClass(text, order);
        int l = 1;
        while (l < text.length()) {
            order = sortDoubled(text, l, order, cls);
            cls = updateClasses(order, cls, l);
            l *= 2;
        }
        return order;
    }

    private int[] updateClasses(int[] newOrder, int[] cls, int l) {
        int n = newOrder.length;
        int[] newCls = new int[n];
        newCls[newOrder[0]] = 0;
        for (int i = 1; i < n; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + l) % n;
            int midPrev = (prev + l) % n;
            if(cls[cur]!=cls[prev]||cls[mid]!=cls[midPrev]){
                newCls[cur]=newCls[prev]+1;
            } else{
                newCls[cur]=newCls[prev];
            }
        }
        return newCls;
    }

    private int[] sortDoubled(String text, int l, int[] order, int[] cls) {
        int count[] = new int[text.length()];
        int newOrder[] = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            count[cls[i]] = count[cls[i]] + 1;
        }
        for (int j = 1; j < text.length(); j++) {
            count[j] += count[j - 1];
        }
        for (int i = text.length() - 1; i >= 0; i--) {
            int start = (order[i] - l + text.length()) % text.length();
            int cl = cls[start];
            count[cl]--;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }

    private int[] computeCharClass(String text, int[] order) {
        int[] cls = new int[text.length()];
        cls[order[0]] = 0;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(order[i]) != text.charAt(order[i - 1])) {
                cls[order[i]] = cls[order[i - 1]] + 1;
            } else {
                cls[order[i]] = cls[order[i - 1]];
            }
        }
        return cls;
    }

    private int[] sortCharacters(String text) {
        int[] order = new int[text.length()];
        int[] count = new int[5];
        for (int i = 0; i < text.length(); i++) {
            count[charToInt(text.charAt(i))]++;
        }
        for (int j = 1; j < 5; j++) {
            count[j] += count[j - 1];
        }
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            count[charToInt(c)]--;
            order[count[charToInt(c)]] = i;
        }
        return order;
    }

    private int charToInt(char c) {
        if (c == '$') {
            return 0;
        }
        if (c == 'A') {
            return 1;
        }
        if (c == 'C') {
            return 2;
        }
        if (c == 'G') {
            return 3;
        }
        if (c == 'T') {
            return 4;
        }
        return -1;
    }

    private char intToChar(int i) {
        if (i == 0) {
            return '$';
        }
        if (i == 1) {
            return 'A';
        }
        if (i == 2) {
            return 'C';
        }
        if (i == 3) {
            return 'G';
        }
        if (i == 4) {
            return 'T';
        }
        return '?';
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
