import java.util.ArrayList;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here

        //build adj
        ArrayList<Integer>[] adj = new ArrayList[x.length];
        ArrayList<Double>[] segDist = new ArrayList[x.length];
        for (int i = 0; i < x.length; i++) {
            adj[i] = new ArrayList<>();
            segDist[i] = new ArrayList<>();
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    adj[i].add(j);
                    segDist[i].add(distance(x[i], y[i], x[j], y[j]));
                }
            }
        }

        //helper arrays
        boolean[] visited = new boolean[x.length];
        int[] parent = new int[x.length];
        double[] dist = new double[x.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }

        dist[0] = 0;
        ArrayList<Integer> minHeap = initMinHeap(x, dist);

        while (minHeap.size() > 0) {

            int u = extractMin(minHeap, dist);
            visited[u] = true;
            ArrayList<Integer> adjVertices = adj[u];

            if (adjVertices == null || adjVertices.size() == 0) {
                continue;
            }
            ArrayList<Double> seg = segDist[u];

            for (int i = 0; i < adjVertices.size(); i++) {
                int v = adjVertices.get(i);
                if (!visited[v] && dist[v] > seg.get(i)) {
                    dist[v] = seg.get(i);
                    parent[v] = u;
                    shiftUp(minHeap, v, dist);
                }
            }
        }

        double totalLength = 0;
        for (int i = 1; i < x.length; i++) {
            ArrayList<Integer> adjVertices = adj[i];
            int p = parent[i];
            double d = segDist[i].get(adjVertices.indexOf(p));
            totalLength += d;
        }

        return totalLength;
    }

    private static int extractMin(ArrayList<Integer> minHeap, double[] dist) {
        int min = minHeap.get(0);
        minHeap.set(0, minHeap.get(minHeap.size() - 1));
        minHeap.remove(minHeap.size() - 1);
        shiftDown(minHeap, 0, dist);
        return min;
    }

    private static ArrayList<Integer> initMinHeap(int[] x, double[] dist) {
        ArrayList<Integer> minHeap = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            minHeap.add(i);
        }
        shiftUp(minHeap, 0, dist);
        return minHeap;
    }

    private static void shiftUp(ArrayList<Integer> minHeap, int arrIndex, double[] dist) {
        int heapIndex = -1;
        for (int i = 0; i < minHeap.size(); i++) {
            if (minHeap.get(i) == arrIndex) {
                heapIndex = i;
            }
        }
        int parent = parent(heapIndex);
        while (parent >= 0 && dist[minHeap.get(parent)] > dist[minHeap.get(heapIndex)]) {
            swap(minHeap, parent, heapIndex);
            heapIndex = parent;
            parent = parent(heapIndex);
        }
    }

    private static void shiftDown(ArrayList<Integer> minHeap, int index, double[] dist) {
        int minIndex = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < minHeap.size() && dist[minHeap.get(minIndex)] > dist[minHeap.get(left)]) {
            minIndex = left;
        }
        if (right < minHeap.size() && dist[minHeap.get(minIndex)] > dist[minHeap.get(right)]) {
            minIndex = right;
        }
        if (index != minIndex) {
            swap(minHeap, index, minIndex);
            shiftDown(minHeap, minIndex, dist);
        }
    }

    private static int parent(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private static void swap(ArrayList<Integer> minHeap, int a, int b) {
        int tmp = minHeap.get(a);
        minHeap.set(a, minHeap.get(b));
        minHeap.set(b, tmp);
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + (Math.pow(y1 - y2, 2)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

