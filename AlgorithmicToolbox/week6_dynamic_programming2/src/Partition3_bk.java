import java.util.Scanner;

public class Partition3_bk {

    static int[] optimalWeight(int S, int[] v) {
        //write you code here
        int[][] optimalArr = new int[S + 1][];
        for (int sum = 0; sum <= S; sum++) {
            optimalArr[sum] = new int[v.length + 1];
        }
        for (int sum = 0; sum <= S; sum++) {
            optimalArr[sum][0] = 0;
        }
        for (int i = 0; i <= v.length; i++) {
            optimalArr[0][i] = 0;
        }

        for (int sum = 1; sum <= S; sum++) {
            int max = 0;
            for (int i = 1; i <= v.length; i++) {
                int a = optimalArr[sum][i - 1];
                if (a > max) {
                    max = a;
                }
                if (sum >= v[i - 1]) {
                    int b = optimalArr[sum - v[i - 1]][i - 1] + v[i - 1];
                    if (b > max) {
                        max = b;
                    }
                }
                optimalArr[sum][i] = max;
            }
        }
        //return null if a knapsack cannot fill exactly sum/3
        if (optimalArr[S][v.length] != S) {
            return null;
        }

        int[] remainingItems = reConstruct(S, v, optimalArr);


        return remainingItems;
    }

    private static int[] reConstruct(int S, int[] v, int[][] optimalArr) {
        boolean[] takenItems = new boolean[v.length];
        for (int i = 0; i < takenItems.length; i++) {
            takenItems[i] = false;
        }
        int sum = S;
        int item = v.length;
        while (sum > 0 && item > 0) {

            if (sum - v[item - 1] >= 0 && optimalArr[sum - v[item - 1]][item - 1] + v[item - 1] == sum) {
                takenItems[item - 1] = true;
                sum -= v[item - 1];
            }
            item--;
        }
        int remainingItemCount = v.length;
        for (int i = 0; i < takenItems.length; i++) {
            if (takenItems[i]) {
                remainingItemCount--;
            }
        }
        int[] remainingItems = new int[remainingItemCount];
        int index = 0;
        for (int i = 0; i < takenItems.length; i++) {
            if (!takenItems[i]) {
                remainingItems[index] = v[i];
                index++;
            }
        }
        return remainingItems;
    }

    private static int partition3(int[] A) {
        //write your code here
        if (A.length < 3) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0) {
            return 0;
        }

        boolean[] takenA = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            takenA[i] = false;
        }
        int[] remainingA = optimalWeight(sum / 3, A);
        if (remainingA == null) {
            return 0;
        }

        remainingA = optimalWeight(sum / 3, remainingA);
        if (remainingA == null) {
            return 0;
        }

        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

