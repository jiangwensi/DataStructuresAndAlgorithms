import java.util.*;
import java.io.*;

public class is_bst {
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

    public class IsBST {
        public boolean solve() {
            if(tree.length==0){
                return true;
            }
            updateMinMax(0);
            return solve(0);
        }

        private boolean solve(int i) {
            int left = tree[i].left;
            int right = tree[i].right;
            boolean validLeft = true;
            boolean validright = true;
            if (left != -1) {
                if(tree[left].max<=tree[i].key){
                    validLeft = solve(left);
                } else {
                    return false;
                }
            }
            if (right != -1) {
                if(tree[right].min>=tree[i].key){
                    validright = solve(right);
                } else {
                    return false;
                }
            }
            return validLeft && validright;
        }

        private int max(int a, int b) {
            if (a > b) {
                return a;
            } else {
                return b;
            }
        }

        private int min(int a, int b) {
            if (a < b) {
                return a;
            } else {
                return b;
            }
        }

        private void updateMinMax(int i) {
            int left = tree[i].left;
            int right = tree[i].right;
            if (left != -1) {
                updateMinMax(left);
                tree[i].max = max(tree[i].max, tree[tree[i].left].key);
                tree[i].min = min(tree[i].min, tree[tree[i].left].key);
            }
            if (right != -1) {
                updateMinMax(right);
                tree[i].max = max(tree[i].max, tree[tree[i].right].key);
                tree[i].min = min(tree[i].min, tree[tree[i].right].key);
            }
        }

        class Node {
            int key;
            int left;
            int right;
            int max;
            int min;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
                this.max = key;
                this.min = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            // Implement correct algorithm here
            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
