import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int[][] optimalArr = new int[W + 1][];
        for (int weight = 0; weight <= W; weight++) {
            optimalArr[weight] = new int[w.length + 1];
        }
        for (int weight = 0; weight <= W; weight++) {
            optimalArr[weight][0] = 0;
        }
        for (int i = 0; i <= w.length; i++) {
            optimalArr[0][i] = 0;
        }

        for (int weight = 1; weight <= W; weight++) {
            int max = 0;
            for (int i = 1; i <= w.length; i++) {
                int a = optimalArr[weight][i - 1];
                if (a > max) {
                    max = a;
                }
                if (weight >= w[i-1]) {
                    int b = optimalArr[weight - w[i-1]][i - 1] + w[i-1];
                    if (b > max) {
                        max = b;
                    }
                }
                optimalArr[weight][i] = max;
            }
        }

        return optimalArr[W][w.length];

//        int result = 0;
//        for (int i = 0; i < w.length; i++) {
//          if (result + w[i] <= W) {
//            result += w[i];
//          }
//        }
//        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

