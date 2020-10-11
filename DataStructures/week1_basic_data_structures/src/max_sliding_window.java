import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jiang Wensi on 11/10/2020
 */
public class max_sliding_window {
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

    public static void main(String[] args) throws IOException {
        new max_sliding_window().solve();
    }

    public void solve() throws IOException {
//        stressTest();
//
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] values = new int[n];

        for (int i = 0; i < n; ++i) {
            values[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] maxList = findMax(values, m);
        for (int i = 0; i < maxList.length; i++) {
            System.out.print(maxList[i] + " ");
        }
    }

    private int[] findMax(int[] values, int m) {
        int[] max = new int[values.length - m + 1];
        MyStack stack1 = new MyStack();
        MyStack stack2 = new MyStack();
        for (int i = 0; i < m; i++) {
            stack2.push(values[i]);
        }
        for (int i = 0; i < m; i++) {
            stack1.push(stack2.pop());
        }
        int i = 0;
        while (i < values.length - m + 1) {
            if (stack1.isEmpty()) {
                max[i] = stack2.max();
            } else if (stack2.isEmpty()) {
                max[i] = stack1.max();
            } else {
                if (stack1.max() > stack2.max()) {
                    max[i] = stack1.max();
                } else {
                    max[i] = stack2.max();
                }
            }
            if (i + m > values.length - 1) {
                break;
            }
            stack1.pop();

            stack2.push(values[i + m]);

            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    int poped = stack2.pop();
                    stack1.push(poped);
                }
            }
            i++;
        }
        return max;
    }

    private int getMax(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }


    private class MyStack {
        MyNode head = new MyNode();
        MyNode maxHead = new MyNode();

        public void push(Integer value) {

            if (maxHead.next == null) {
                maxHead.next = new MyNode(value, maxHead.next);
            } else {
                if (maxHead.next.value <= value) {
                    maxHead.next = new MyNode(value, maxHead.next);
                }
            }

            head.next = new MyNode(value, head.next);
        }

        public int pop() {

            if (maxHead.next.value == head.next.value) {
                maxHead.next = maxHead.next.next;
            }
            MyNode myNode = head.next;
            head.next = head.next.next;
            return myNode.value;
        }

        public int max() {
            return maxHead.next.value;
        }

        public boolean isEmpty() {
            return head.next == null;
        }
    }

    private class MyNode {

        MyNode next;
        int value;

        public MyNode() {
        }

        public MyNode(int value, MyNode next) {
            this.next = next;
            this.value = value;
        }
    }
}
