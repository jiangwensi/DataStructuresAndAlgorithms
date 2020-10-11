import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
//        stressTest();
//
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        MyStack stack = new MyStack();
        List maxList = new LinkedList<>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
                maxList.add(stack.max());
            }
        }
        maxList.forEach(System.out::println);
    }

    private void stressTest() {
        int size = 100000;
        System.out.println(size);
        MyStack stack0 = new MyStack();
        for (int i = 0; i < size; i ++) {
            String[] words = {
                    "push", "push", "push", "max", "max", "pop", "max"
            };
            String word = words[new Random().nextInt(7)];
            System.out.print(word+" ");
            if (word.equals("push")) {
                int value = new Random().nextInt(100000);
                System.out.println(value);
                stack0.push(value);
            } else if (word.equals("pop")) {
                System.out.println();
                stack0.pop();
            } else {
                System.out.println(stack0.max());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
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

        public void pop() {

            if (maxHead.next.value == head.next.value) {
                maxHead.next = maxHead.next.next;
            }
            head.next = head.next.next;
        }

        public int max() {
            return maxHead.next.value;
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
