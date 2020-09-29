import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dijkstra {

    private static long bellmanFort(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] prev = new int[adj.length];
        long[] dist = new long[adj.length];
        for (int i = 0; i < adj.length; i++) {
            prev[i] = -1;
        }
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        dist[s] = 0;

        for (int a = 0; a < adj.length; a++) {
            for (int i = 0; i < adj.length; i++) {
                ArrayList<Integer> adjVertices = adj[i];
                ArrayList<Integer> weight = cost[i];
                for (int j = 0; j < adjVertices.size(); j++) {
                    if (dist[i] != Long.MAX_VALUE && dist[adjVertices.get(j)] > dist[i] + weight.get(j)) {
                        dist[adjVertices.get(j)] = dist[i] + weight.get(j);
                        prev[adjVertices.get(j)] = i;
                    }
                }
            }
        }
        long result = dist[t];
        if (result == Long.MAX_VALUE) {
            result = -1;
        }
        return result;
    }


    private static long dijkstra(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

//        int[] prev = new int[adj.length];
        long[] dist = new long[adj.length];
//        boolean[] processed = new boolean[adj.length];
//        for (int i = 0; i < adj.length; i++) {
////            prev[i] = -1;
//        }
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        dist[s] = 0;
        ArrayList<Integer> minHeap = makeQueue(adj, s, dist);
        while (minHeap.size() > 0) {

            int u = extractMin(minHeap, dist);
            ArrayList<Integer> adjVertices = adj[u];


            if (adjVertices == null || adjVertices.size() == 0) {
                continue;
            }
            ArrayList<Integer> weight = cost[u];

            for (int i = 0; i < adjVertices.size(); i++) {
                int v = adjVertices.get(i);
                if (dist[u] != Long.MAX_VALUE && dist[v] > dist[u] + weight.get(i)) {
                    dist[v] = dist[u] + weight.get(i);
//                    prev[v] = u;
                    shiftUp(minHeap, v, dist);
                }
            }
        }
        long result = dist[t];
        if (result == Long.MAX_VALUE) {
            result = -1l;
        }
        return result;
    }

    private static int extractMin(ArrayList<Integer> minHeap, long[] dist) {
        int min = minHeap.get(0);
        minHeap.set(0, minHeap.get(minHeap.size() - 1));
        minHeap.remove(minHeap.size() - 1);
        shiftDown(minHeap, 0, dist);
        return min;
    }

    private static ArrayList<Integer> makeQueue(ArrayList<Integer>[] adj, int start, long[] dist) {
        ArrayList<Integer> minHeap = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            minHeap.add(i);
        }
        shiftUp(minHeap, start, dist);
        return minHeap;
    }

    private static void shiftUp(ArrayList<Integer> minHeap, int arrIndex, long[] dist) {
        int heapIndex = -1;
        for (int i = 0; i < minHeap.size(); i++) {
            if (minHeap.get(i) == arrIndex) {
                heapIndex = i;
            }
        }
        int parent = parent(heapIndex);
        while (parent >= 0 &&
                dist[minHeap.get(parent)] > dist[minHeap.get(heapIndex)]) {
            swap(minHeap, parent, heapIndex);
            heapIndex=parent;
            parent = parent(heapIndex);
        }
    }

    private static int parent(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private static void shiftDown(ArrayList<Integer> minHeap, int index, long[] dist) {
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

    private static void swap(ArrayList<Integer> minHeap, int a, int b) {
        int tmp = minHeap.get(a);
        minHeap.set(a, minHeap.get(b));
        minHeap.set(b, tmp);
    }

    public static void main(String[] args) {
//        stressTest();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(dijkstra(adj, cost, x, y));
//        System.out.println(bellmanFort(adj, cost, x, y));
    }

    private static void stressTest() {
        while(true){
            System.out.println("Test Case:");
            int n = new Random().nextInt(100)+2;
            int m = new Random().nextInt(n*(n-1))+1;

            System.out.println(n+" "+m);

            ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
            ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<Integer>();
                cost[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                int x, y, w;
                x = new Random().nextInt(n)+1;
                y = -1;
                do{
                    y=new Random().nextInt(n)+1;
                } while(x==y);
                w = new Random().nextInt(100);

                System.out.println(x+" "+y+" "+w);

                adj[x - 1].add(y - 1);
                cost[x - 1].add(w);
            }
            int _x = new Random().nextInt(n)+1;
            int _y = -1;
            do{
                _y=new Random().nextInt(n)+1;
            } while(_x==_y);

            int x = _x - 1;
            int y = _y - 1;
            System.out.println(_x+" "+_y);

            long bellmanFort = bellmanFort(adj, cost, x, y);
            long dijkstra=dijkstra(adj, cost, x, y);
            if(dijkstra==bellmanFort){
                System.out.println("ok,"+dijkstra);
            } else {
                System.out.println("mismatch: dijkstra="+dijkstra+",bellmanFort="+bellmanFort);
                break;
            }

        }

    }
}

