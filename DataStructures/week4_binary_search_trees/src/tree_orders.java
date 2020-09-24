import java.util.*;
import java.io.*;

public class tree_orders {
    ArrayList<Integer> result;
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

//        private int left(int i) {
//            return 2 * i + 1;
//        }
//
//        private int right(int i) {
//            return 2 * i + 2;
//        }

        List<Integer> inOrder() {
            result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            inOrderTraversal(0);
            return result;
        }

        private void inOrderTraversal(int i) {
            if (i !=-1) {
                inOrderTraversal(left[i]);
                result.add(key[i]);
                inOrderTraversal(right[i]);
            }
            return;
        }


        List<Integer> preOrder() {
            result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            preOrderTraversal(0);
            return result;
        }

        private void preOrderTraversal(int i) {
            if (i !=-1) {
                result.add(key[i]);
                preOrderTraversal(left[i]);
                preOrderTraversal(right[i]);
            }
            return;
        }

        List<Integer> postOrder() {
            result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            postOrderTraversal(0);
            return result;
        }

        private void postOrderTraversal(int i) {
            if (i !=-1) {
                postOrderTraversal(left[i]);
                postOrderTraversal(right[i]);
                result.add(key[i]);
            }
            return;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
