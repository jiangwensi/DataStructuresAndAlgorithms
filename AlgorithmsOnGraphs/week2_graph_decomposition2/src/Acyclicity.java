import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here

        boolean[] visited ;
        for(int i = 0; i < adj.length;i++){
            visited = new boolean[adj.length];
            if(cyclicOrigin(i,adj,visited,i)){
                return 1;
            }
        }
        return 0;
    }

    private static boolean cyclicOrigin(int index, ArrayList<Integer>[] adj, boolean[] visited, int origin) {
        if (visited[index]) {
            if (index == origin) {
                return true;
            } else {
                return false;
            }
        }
        visited[index] = true;

        for (int i = 0; i < adj[index].size(); i++) {
            if (cyclicOrigin(adj[index].get(i), adj, visited, origin)) {
                return true;
            }
        }

        return false;
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
        System.out.println(acyclic(adj));
    }
}

