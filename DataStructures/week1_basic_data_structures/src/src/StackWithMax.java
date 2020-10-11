package src;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
//        Stack<Integer> stack = new Stack<Integer>();
        MyStack stack = new MyStack();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
//                System.out.println(Collections.max(stack));
                System.out.println(stack.max());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }

    private class MyStack {
        MyNode head = new MyNode();
        MyNode maxStackHead = new MyNode();

        public void push(Integer value) {

            MyNode myNode = new MyNode(value, head.next);
            head.next = myNode;

            int max;
            if (maxStackHead.next == null) {
                max = value;
            } else {
                max = maxStackHead.next.value > value ? maxStackHead.next.value : value;
            }
            MyNode maxNode = new MyNode(max, maxStackHead.next);
            maxStackHead.next = maxNode;
        }

        public void pop() {
            head.next = head.next.next;
            maxStackHead.next = maxStackHead.next.next;
        }

        public int max() {
            return maxStackHead.next.value;
        }
    }

    private class MyNode {

        MyNode next;
        int value;

        public MyNode() {
        }

        public MyNode(int value) {
            this.value = value;
        }

        public MyNode(MyNode next) {
            this.next = next;
        }

        public MyNode(int value, MyNode next) {
            this.next = next;
            this.value = value;
        }
    }
}
