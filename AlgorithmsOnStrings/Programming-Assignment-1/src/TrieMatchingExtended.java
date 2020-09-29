import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TrieMatchingExtended implements Runnable {


    int letterToIndex(char letter) {
        switch (letter) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                assert (false);
                return Node.NA;
        }
    }

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<Integer>();

        // write your code here
        List<Node> trie = buildTrie(patterns.toArray(new String[patterns.size()]));

        result = trieMatching(text, trie);

        return result;
    }

    private List<Integer> trieMatching(String text, List<Node> trie) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (prefixTrieMatching(text.substring(i), trie)) {
                result.add(i);
            }
        }
        Collections.sort(result);
        return result;
    }

    private boolean prefixTrieMatching(String text, List<Node> trie) {
        int index = 0;
        Character symbol;
        Node v = trie.get(0);
        while (true) {
            if (allNA(v.next) || v.patternEnd) {
                return true;
            }

            if (index < text.length()) {
                symbol = text.charAt(index);
            } else {
                return false;
            }

            if (v.next[letterToIndex(symbol)] != -1) {
                v = trie.get(v.next[letterToIndex(symbol)]);
                index++;
            } else {
                return false;
            }

        }
    }

    private boolean allNA(int[] next) {
        for (int i = 0; i < next.length; i++) {
            if (next[i] != -1) {
                return false;
            }
        }
        return true;
    }

    class Node {
        public static final int Letters = 4;
        public static final int NA = -1;
        public int next[];
        public boolean patternEnd;

        Node() {
            next = new int[Letters];
            Arrays.fill(next, NA);
            patternEnd = false;
        }
    }

    List<Node> buildTrie(String[] patterns) {
        List<Node> trie = new ArrayList<Node>();

        Node rootNode = new Node();
        trie.add(rootNode);

        for (int i = 0; i < patterns.length; i++) {
            Node currentNode = rootNode;

            for (int j = 0; j < patterns[i].length(); j++) {

                Character currentSymbol = patterns[i].charAt(j);

                if (currentNode.next[letterToIndex(currentSymbol)] != -1) {

                    currentNode = trie.get(currentNode.next[letterToIndex(currentSymbol)]);
                } else {

                    Node newNode = new Node();

                    trie.add(newNode);
                    currentNode.next[letterToIndex(currentSymbol)] = trie.size() - 1;
                    currentNode = newNode;

                }
                if (j == patterns[i].length() - 1) {
                    currentNode.patternEnd = true;
                }

            }
        }

        return trie;
    }

    public void run() {
//        stressTest();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            List<String> patterns = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                patterns.add(in.readLine());
            }

            List<Integer> ans = solve(text, n, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void stressTest() {
        while (true) {
            System.out.println("\n\nTest Case:");
            StringBuffer sb = new StringBuffer();
//            int textLength = new Random().nextInt(10000)+1;
            int textLength = 10000;
            for (int i = 0; i < textLength; i++) {
                sb.append(randomChar());
            }
            System.out.println(sb.toString());
            int n = new Random().nextInt(5000) + 1;
            System.out.println(n);
            List<String> patterns = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
//                int patternLength = new Random().nextInt(100)+1;
                int patternLength = 100;
                StringBuffer patternSB = new StringBuffer();
                for (int j = 0; j < patternLength; j++) {
                    patternSB.append(randomChar());
                }
                patterns.add(patternSB.toString());
                System.out.println(patternSB.toString());
            }
            long start = System.nanoTime();
            List<Integer> ans = solve(sb.toString(), n, patterns);
            long end = System.nanoTime();
            long timetaken = (end - start) / 1000000;
            System.out.println("\nTime taken:" + timetaken);
            System.out.println("\nResult:");
            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
            if (timetaken > 200) {
                break;
            }
        }
    }

    private char randomChar() {
        char[] chars = {'A', 'C', 'G', 'T'};
        return chars[new Random().nextInt(4)];
    }

    public static void main(String[] args) {
        new Thread(new TrieMatchingExtended()).start();
    }
}
