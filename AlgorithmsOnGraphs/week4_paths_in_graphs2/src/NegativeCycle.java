import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {

        ArrayList<Integer>[] adjUpdated = new ArrayList[adj.length + 1];
        ArrayList<Integer>[] costUpdated = new ArrayList[cost.length + 1];

        adjUpdated[adjUpdated.length-1]=new ArrayList<>();
        costUpdated[adjUpdated.length-1]=new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            adjUpdated[i] = adj[i];
            costUpdated[i] = cost[i];
            adjUpdated[adjUpdated.length-1].add(i);
            costUpdated[adjUpdated.length-1].add(0);
        }

        int[] prev = new int[adjUpdated.length];
        long[] dist = new long[adjUpdated.length];
        for (int i = 0; i < adjUpdated.length; i++) {
            prev[i] = -1;
        }
        for (int i = 0; i < adjUpdated.length; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        dist[adjUpdated.length-1] = 0;

        for (int a = 0; a <= adjUpdated.length; a++) {
            for (int i = 0; i < adjUpdated.length; i++) {
                ArrayList<Integer> adjVertices = adjUpdated[i];
                ArrayList<Integer> weight = costUpdated[i];
                for (int j = 0; j < adjVertices.size(); j++) {
                    if (dist[i] != Long.MAX_VALUE && dist[adjVertices.get(j)] > dist[i] + weight.get(j)) {
                        if (a == adjUpdated.length) {
                            return 1;
                        }
                        dist[adjVertices.get(j)] = dist[i] + weight.get(j);
                        prev[adjVertices.get(j)] = i;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
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
        System.out.println(negativeCycle(adj, cost));
    }
}

