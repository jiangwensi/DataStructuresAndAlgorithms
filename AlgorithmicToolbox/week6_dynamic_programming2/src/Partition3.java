import java.util.*;
import java.io.*;

public class Partition3 {

    static int[][] optimalWeight(int S, int[] v) {
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

        int[][] remainingItems = reConstruct(S, v, optimalArr);


        return remainingItems;
    }

    private static int[][] reConstruct(int S, int[] v, int[][] optimalArr) {

        List<Boolean[]> itemsTaken = new ArrayList<>();
        //init 1st itemsTaken arr
        itemsTaken.add(new Boolean[v.length]);
        for (int i = 0; i < itemsTaken.get(0).length; i++) {
            itemsTaken.get(0)[i] = false;
        }

        int takenItemLoop = 0;//looping first itemsTaken array

        while (takenItemLoop < itemsTaken.size()) {//for each itemsTaken array (may increase later in this while loop)
            int sum = S;//init sum to S
            int item = v.length;//init item to v.length

            while (sum > 0 && item > 0) {

                boolean foundPrevOptimal = false;

                if (sum - v[item - 1] >= 0 && optimalArr[sum - v[item - 1]][item - 1] + v[item - 1] == sum) {
                    //found prev optimal where item-1 is NOT in use
                    foundPrevOptimal = true;
                    itemsTaken.get(takenItemLoop)[item - 1] = true;
                    sum -= v[item - 1];
                }

                if (optimalArr[sum][item - 1] == sum) {
                    //found prev optimal where item-1 is in use

                    if (foundPrevOptimal) {

                        sum += v[item - 1];//restore sum from previous if condition

                        itemsTaken.add(new Boolean[v.length]);//add one more itemsTaken array, init to false;
                        for (int i = 0; i < itemsTaken.get(itemsTaken.size() - 1).length; i++) {
                            itemsTaken.get(itemsTaken.size() - 1)[i] = false;
                        }

                        //copy existing items to new itemsTaken array
                        for (int x = 0; x < v.length; x++) {
                            itemsTaken.get(itemsTaken.size()-1)[x] = itemsTaken.get(takenItemLoop)[x];
                        }
                        itemsTaken.get(itemsTaken.size()-1)[item - 1] = true;
                    } else {
                        itemsTaken.get(takenItemLoop)[item - 1] = true;
                    }
//                    takenItemLoop++;
//                sum-=v[item-1];
                }
                item--;
            }
            takenItemLoop++;
        }

        int[][] remainingItems = new int[itemsTaken.size()][];
        for (int i = 0; i < itemsTaken.size(); i++) {
            int remainingItemCount = v.length;
            for (int j = 0; j < itemsTaken.get(i).length; j++) {
                if (itemsTaken.get(i)[j]) {
                    remainingItemCount--;
                }
            }
            remainingItems[i] = new int[remainingItemCount];
            int index = 0;
            for (int j = 0; j < itemsTaken.get(i).length; j++) {
                if (!itemsTaken.get(i)[j]) {
                    remainingItems[i][index] = v[i];
                    index++;
                }
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
        int[][] remainingA = optimalWeight(sum / 3, A);
        if (remainingA == null) {
            return 0;
        }
        for (int i = 0; i < remainingA.length; i++) {
            remainingA = optimalWeight(sum / 3, remainingA[i]);
            if (remainingA != null) {
                return 1;
            }
        }

        return 0;
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

