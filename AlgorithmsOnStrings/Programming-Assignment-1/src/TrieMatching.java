import java.io.*;
import java.util.*;

class Node {
    public static final int Letters = 4;
    public static final int NA = -1;
    public int next[];

    Node() {
        next = new int[Letters];
        Arrays.fill(next, NA);
    }
}

public class TrieMatching implements Runnable {

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<Integer>();

        // write your code here
        List<Map<Character, Integer>> trie = buildTrie(patterns.toArray(new String[patterns.size()]));

        result = trieMatching(text, trie);

        return result;
    }

    private List<Integer> trieMatching(String text, List<Map<Character, Integer>> trie) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (prefixTrieMatching(text.substring(i), trie)) {
                result.add(i);
            }
            ;
        }
        Collections.sort(result);
        return result;
    }

    private boolean prefixTrieMatching(String text, List<Map<Character, Integer>> trie) {
        int index = 0;
        Character symbol;
        Map<Character, Integer> v = trie.get(0);
        while (true) {
            if (v.size() == 0) {
                return true;
            }

            if (index < text.length()) {
                symbol = text.charAt(index);
            } else {
                return false;
            }

            if (v.get(symbol) != null) {
                v = trie.get(v.get(symbol));
                index++;
            } else {
                return false;
            }

        }
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        Map<Character, Integer> rootNode = new HashMap<>();
        trie.add(rootNode);

        for (int i = 0; i < patterns.length; i++) {
            Map<Character, Integer> currentNode = rootNode;
            for (int j = 0; j < patterns[i].length(); j++) {
                Character currentSymbol = patterns[i].charAt(j);
                if (currentNode.get(currentSymbol) != null) {
                    currentNode = trie.get(currentNode.get(currentSymbol));
                } else {
                    Map<Character, Integer> newNode = new HashMap<>();
                    trie.add(newNode);
                    currentNode.put(currentSymbol, trie.size() - 1);
                    currentNode = newNode;
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
        while(true){
            System.out.println("\n\nTest Case:");
            StringBuffer sb = new StringBuffer();
//            int textLength = new Random().nextInt(10000)+1;
            int textLength = 10000;
            for(int i = 0; i < textLength; i ++){
                sb.append(randomChar());
            }
            System.out.println(sb.toString());
            int n = new Random().nextInt(5000)+1;
            System.out.println(n);
            List<String> patterns = new ArrayList<String>();
            for(int i = 0; i < n; i ++){
//                int patternLength = new Random().nextInt(100)+1;
                int patternLength = 100;
                StringBuffer patternSB = new StringBuffer();
                for(int j = 0; j<patternLength; j++){
                    patternSB.append(randomChar());
                }
                patterns.add(patternSB.toString());
                System.out.println(patternSB.toString());
            }
            long start = System.nanoTime();
            List<Integer> ans = solve(sb.toString(), n, patterns);
            long end = System.nanoTime();
            long timetaken=(end-start)/1000000;
            System.out.println("\nTime taken:"+timetaken);
            System.out.println("\nResult:");
            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
            if(timetaken>200){
                break;
            }
        }
    }

    private char randomChar() {
        char[] chars = {'A','C','G','T'};
        return chars[new Random().nextInt(4)];
    }

    public static void main(String[] args) {
        new Thread(new TrieMatching()).start();
    }
}
