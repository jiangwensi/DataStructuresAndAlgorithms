import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {
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

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        Map<Character,Integer> rootNode = new HashMap<>();
        trie.add(rootNode);

        for(int i = 0 ; i < patterns.length; i ++){
            Map<Character,Integer> currentNode = rootNode;
            for(int j = 0; j < patterns[i].length(); j ++){
                Character currentSymbol = patterns[i].charAt(j);
                if(currentNode.get(currentSymbol)!=null){
                    currentNode = trie.get(currentNode.get(currentSymbol));
                } else {
                    Map<Character,Integer> newNode = new HashMap<>();
                    trie.add(newNode);
                    currentNode.put(currentSymbol,trie.size()-1);
                    currentNode = newNode;
                }
            }
        }

        return trie;
    }
//    List<Map<Character, Integer>> buildTrie(String[] patterns) {
//        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
//
//        // write your code here
////        Node root = new Node(0);
//        Map<Character,Integer> map = new HashMap<>();
//        int index = 1;
//
//        for (int i = 0; i < patterns.length; i++) {
//            Node currentNode = root;
//            for (int j = 0; j < patterns[i].length(); j++) {
//                Character currentSymbol = patterns[i].charAt(j);
//                if (root.toEdge != null && root.toEdge.symbol.equals(currentSymbol)) {
//                    currentNode = root.toEdge.toNode;
//                } else{
//                    Node newNode = new Node(index);
//                    Edge newEdge = new Edge(currentNode,newNode,currentSymbol);
//                    Map<Character,Integer> map = new HashMap<>();
//                    map.put(currentSymbol,);
//                    currentNode = newNode;
//                }
//            }
//        }
//
//
//        return trie;
//    }

//    private static class Node {
//        int index;
//        Edge toEdge;
//
//        public Node() {
//        }
//
//        public Node(int index) {
//            this.index = index;
//        }
//
//        public Node(int index, Edge toEdge) {
//            this.index = index;
//            this.toEdge = toEdge;
//        }
//    }
//
//    private static class Edge {
//        Node fromNode;
//        Node toNode;
//        Character symbol;
//
//        public Edge() {
//        }
//
//        public Edge(Node fromNode, Node toNode, Character symbol) {
//            this.fromNode = fromNode;
//            this.toNode = toNode;
//            this.symbol = symbol;
//        }
//    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
