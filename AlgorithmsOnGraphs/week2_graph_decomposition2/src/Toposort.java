import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        boolean[] visited = new boolean[adj.length];
        int index = 0;
        while (index != -1) {
            dfs(index, adj, visited, order);
            index = notVisited(visited);
        }
        Collections.reverse(order);
        return order;
    }

    private static int notVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }


    private static void dfs(int index, ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> order) {
        if (visited[index]) {
            return;
        }
        visited[index] = true;

        for (int i = 0; i < adj[index].size(); i++) {
            dfs(adj[index].get(i), adj, visited, order);
        }
        order.add(index);
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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

