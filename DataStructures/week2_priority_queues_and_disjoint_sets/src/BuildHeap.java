import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private int leftChild(int parent) {
        int leftChild = (parent * 2) + 1;
        if (leftChild > data.length - 1) {
            leftChild = -1;
        }

        return leftChild;
    }

    private int rightChild(int parent) {
        int rightChild = (parent * 2) + 2;
        if (rightChild > data.length - 1) {
            rightChild = -1;
        }
        return rightChild;
    }

    private void swap(int i, int j) {
        swaps.add(new Swap(i, j));
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    private void generateSwaps() {
        swaps = new ArrayList<>();
        for (int i = data.length / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }

    private void shiftDown(int root) {

        int maxIndex = root;
        int left = leftChild(root);
        int right = rightChild(root);

        if (left != -1 && data[left] < data[maxIndex]) {
            maxIndex = left;
        }

        if (right != -1 && data[right] < data[maxIndex]) {
            maxIndex = right;
        }

        if (maxIndex != root) {
            swap(root, maxIndex);
            shiftDown(maxIndex);
        }
    }


//
//
//
//    private void generateSwaps() {
//        swaps = new ArrayList<Swap>();
//        // The following naive implementation just sorts
//        // the given sequence using selection sort algorithm
//        // and saves the resulting sequence of swaps.
//        // This turns the given array into a heap,
//        // but in the worst case gives a quadratic number of swaps.
//        //
//        // TODO: replace by a more efficient implementation
//        for (int i = 0; i < data.length; ++i) {
//            for (int j = i + 1; j < data.length; ++j) {
//                if (data[i] > data[j]) {
//                    swap(i, j);
//                }
//            }
//        }
//    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
