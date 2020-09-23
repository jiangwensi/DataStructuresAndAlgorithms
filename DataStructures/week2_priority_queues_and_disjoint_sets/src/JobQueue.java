import java.io.*;
import java.util.List;
import java.util.StringTokenizer;


public class JobQueue {
    private int numWorkers;
    private int[] jobs;
//    private Worker[] assignedWorker;

    private int[] assignedWorker;
    private long[] startTime;

    private Thread[] threads;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private class Worker {
        int threadIndex;
        int time;

        public Worker(int threadIndex, int time) {
            this.threadIndex = threadIndex;
            this.time = time;
        }
    }

    private class Thread implements Comparable<Thread> {
        int index = -1;
        boolean occupied = false;
        long currentJobRemainingTime = 0;

        public Thread(int index, boolean occupied) {
            this.index = index;
            this.occupied = occupied;
        }

        @Override
        public int compareTo(Thread o) {
            if (this.occupied == true && o.occupied == true) {
                return 0;
            }
            if (this.occupied == true && o.occupied == false) {
                return -1;
            }
            if (this.occupied == false && o.occupied == false) {
                if (this.index < o.index) {
                    return 1;
                }
                if (this.index == o.index) {
                    return 0;
                }
                if (this.index > o.index) {
                    return -1;
                }
            }
            if (this.occupied == false && o.occupied == true) {
                return 1;
            }

            return 0;
        }
    }

    private Thread getMax() {
        return threads[0];
    }

//    private Thread extractMax() {
//        Thread max = threads[0];
//        Thread leaf = threads[threads.length - 1];
//        threads[0] = leaf;
//        shiftDown(0);
//        return max;
//    }

    private void shiftDown(int index) {

        int maxIndex = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);

        if (leftChild != -1 && threads[maxIndex].compareTo(threads[leftChild]) == -1) {
            maxIndex = leftChild;
        }
        if (rightChild != -1 && threads[maxIndex].compareTo(threads[rightChild]) == -1) {
            maxIndex = rightChild;
        }

        if (maxIndex != index) {
            swap(maxIndex, index);
            shiftDown(maxIndex);
        }

    }

    private void shiftUp(int index) {
        if (index == 0) {
            return;
        }
        int parent = parentIndex(index);
        if (threads[parent].compareTo(threads[index]) == -1) {
            swap(index, parent);
            shiftUp(parent);
        }
    }

    private int parentIndex(int index) {
        if (index == 0) {
            return index;
        }
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        Thread tmp = threads[i];
        threads[i] = threads[j];
        threads[j] = tmp;
    }

    private int rightChild(int index) {
        int child = index * 2 + 2;
        if (child < threads.length) {
            return child;
        } else {
            return -1;
        }
    }

    private int leftChild(int index) {
        int child = index * 2 + 1;
        if (child < threads.length) {
            return child;
        } else {
            return -1;
        }
    }

    private void assignJobs() {
        threads = new Thread[numWorkers];
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        for (int i = 0; i < numWorkers; i++) {
            threads[i] = new Thread(i, false);
        }
        int time = -1;
        int j = 0;
        while (true) {
            time++;
            boolean allJobProcessed = false;
            updateThreadStatus();
            while (j < jobs.length) {

                Thread t = threads[0];
                if (t.occupied) {
                    break;
                }
                t.occupied = true;
                t.currentJobRemainingTime = jobs[j];
                assignedWorker[j] = t.index;
                startTime[j] = time;
                j++;
                allJobProcessed = j == jobs.length;
                shiftDown(0);
            }
            if (allJobProcessed) {
                break;
            }
        }
    }

    private void updateThreadStatus() {
        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            if (thread.occupied) {
                thread.currentJobRemainingTime--;
                if (thread.currentJobRemainingTime == 0) {
                    thread.occupied = false;
                    shiftUp(i);
                }
            }
            if ((thread.occupied && thread.currentJobRemainingTime <= 0) || (!thread.occupied && thread.currentJobRemainingTime > 0)) {
                System.out.println("something went wrong");
            }
        }
    }

    private void sortHeap() {

    }

//    private void assignJobs() {
//        // TODO: replace this code with a faster algorithm.
//        assignedWorker = new int[jobs.length];
//        startTime = new long[jobs.length];
//        long[] nextFreeTime = new long[numWorkers];
//        for (int i = 0; i < jobs.length; i++) {
//            int duration = jobs[i];
//            int bestWorker = 0;
//            for (int j = 0; j < numWorkers; ++j) {
//                if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                    bestWorker = j;
//            }
//            assignedWorker[i] = bestWorker;
//            startTime[i] = nextFreeTime[bestWorker];
//            nextFreeTime[bestWorker] += duration;
//        }
//    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
