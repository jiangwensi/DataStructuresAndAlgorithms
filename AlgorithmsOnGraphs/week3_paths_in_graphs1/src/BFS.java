import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        int prev[] = new int[adj.length];
        boolean visited[] = new boolean[adj.length];
        int dist[] = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            prev[i] = -1;
            visited[i] = false;
            dist[i] = -1;
        }

        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(s);
        visited[s]=true;
        dist[s]=0;
        while (queue.size() > 0) {
            int index = queue.remove(0);
            ArrayList<Integer> adjNodes = adj[index];
            adjNodes.forEach(e -> {
                if(!visited[e]){
                    queue.add(e);
                    dist[e] = dist[index] + 1;
                    prev[e] = index;
                    visited[e]=true;
                }
            });
        }

        return dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

