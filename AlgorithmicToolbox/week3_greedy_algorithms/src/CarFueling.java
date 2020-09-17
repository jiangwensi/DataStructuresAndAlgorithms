import java.util.*;
import java.io.*;

public class CarFueling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }

    static int computeMinRefills(int dist, int tank, int[] stops) {
        int count = 0;
        int current = 0;
        int n = stops.length;
        int[] allStops = new int[n + 2];
        allStops[0]=0;
        for (int i = 1; i <= n; i++) {
            allStops[i] = stops[i-1];
        }
        allStops[n+1]=dist;

        int nextRefill = 0;
        while(current<=n){
            while(nextRefill+1 <=n+1 && allStops[nextRefill+1]-allStops[current]<=tank ){
                ++nextRefill;
            }
            if(nextRefill == current) {
                return -1;
            } else {
                if(nextRefill!=n+1){
                    count++;
                }
                current = nextRefill;
            }
        }

        return count;
    }
}























