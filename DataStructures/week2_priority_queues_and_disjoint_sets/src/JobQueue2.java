import java.io.*;
import java.util.StringTokenizer;


public class JobQueue2 {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private Thread[] threads;
    long minRemainingTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue2().solve();
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

    private void shiftDown(int index) {

//        long timeStart = System.nanoTime();
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
//        long timeEnd = System.nanoTime();
//        System.out.println("shiftDown(" + index + "):" + (timeEnd - timeStart));

    }

    private void shiftUp(int index) {
//        long timeStart = System.nanoTime();
        if (index == 0) {
            return;
        }
        int parent = parentIndex(index);
        if (threads[parent].compareTo(threads[index]) == -1) {
            swap(index, parent);
            shiftUp(parent);
        }
//        long timeEnd = System.nanoTime();
//        System.out.println("shiftUp(" + index + "):" + (timeEnd - timeStart));
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

        long time = -1;
        int j = 0;

        while (true) {

            updateThreadStatus();
            updateMinRemainingTime();
            if (minRemainingTime > 0) {
                time = time + minRemainingTime;
                continue;
            } else if (minRemainingTime == 0) {
                time = time + 1;
            } else {
                System.out.println("Something went wrong. minRemainingTime=" + minRemainingTime);
            }

            boolean allJobProcessed = false;

            while (j < jobs.length) {
                Thread t = threads[0];
                if (t.occupied) {
                    //if the highest priority node is also occupied, meaning all are occupied
                    break;
                }

                t.occupied = true;
                t.currentJobRemainingTime = jobs[j];
                assignedWorker[j] = t.index;
                startTime[j] = time;

                if (j == jobs.length - 1) {
                    allJobProcessed = true;
                    break;
                }
                j++;
                shiftDown(0);
            }
            if (allJobProcessed) {
                break;
            }
        }
    }

    private void updateMinRemainingTime() {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < threads.length; i++) {
            if (threads[i].currentJobRemainingTime < min) {
                min = threads[i].currentJobRemainingTime;
            }
        }
        minRemainingTime = min;

    }

    private void updateThreadStatus() {
//        long timeStart = System.nanoTime();
        long advanceTime = minRemainingTime == 0 ? 1 : minRemainingTime;

        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];

            if (thread.occupied) {
                thread.currentJobRemainingTime = thread.currentJobRemainingTime - advanceTime;
                if (thread.currentJobRemainingTime == 0) {
                    thread.occupied = false;
                    shiftUp(i);
                }
            }

            if ((thread.occupied && thread.currentJobRemainingTime <= 0)
                    || (!thread.occupied && thread.currentJobRemainingTime > 0)) {
                System.out.println("something went wrong");
            }
        }

//        long timeEnd = System.nanoTime();
//        System.out.println("updateThreadStatus time used:" + (timeEnd - timeStart));

    }

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
