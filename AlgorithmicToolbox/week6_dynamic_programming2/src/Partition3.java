import java.util.Scanner;

public class Partition3 {
    private static int partition3(int[] giftValues) {
        //write your code here
        int sum = 0;
        int valuePerKnapsack = 0;
        for (int i = 0; i < giftValues.length; i++) {
            sum += giftValues[i];
        }
        if (sum % 3 != 0) {
            return 0;
        } else {
            valuePerKnapsack = sum / 3;
        }


        //solve knapsack and get resultant weight
        //init value array
        int[][][] value = new int[valuePerKnapsack + 1][][];
        for (int i = 0; i < valuePerKnapsack + 1; i++) {
            value[i] = new int[valuePerKnapsack + 1][];
            for (int j = 0; j < valuePerKnapsack + 1; j++) {
                value[i][j] = new int[giftValues.length + 1];
            }
        }


        for (int k = 1; k <= giftValues.length; k++) {//decide gift k goes to bag 1, 2 or 3

            for (int totalValueBagOne = 0; totalValueBagOne <= valuePerKnapsack; totalValueBagOne++) {

                for (int totalValueBagTwo = 0; totalValueBagTwo <= valuePerKnapsack; totalValueBagTwo++) {

                    int val3 = value[totalValueBagOne][totalValueBagTwo][k - 1];//item k goes to bag 3
                    int val2 = 0;
                    int val1 = 0;
                    int giftIndex = k - 1;
                    if (giftValues[giftIndex] <= totalValueBagOne) {//item k goes to bag 1
                        val1 =
                                value[totalValueBagOne - giftValues[giftIndex]][totalValueBagTwo][k - 1] + giftValues[giftIndex];
                    }
                    if (giftValues[giftIndex] <= totalValueBagTwo) {//item k goes to bag 2
                        val2 =
                                value[totalValueBagOne][totalValueBagTwo - giftValues[giftIndex]][k - 1] + giftValues[giftIndex];
                    }
                    value[totalValueBagOne][totalValueBagTwo][k] = max(val1, val2, val3);
                }
            }
        }
        int knapsackValue = value[valuePerKnapsack][valuePerKnapsack][giftValues.length];
        if (knapsackValue == valuePerKnapsack * 2) {
            return 1;
        }
        return 0;
    }

    private static int max(int val1, int val2, int val3) {
        int max = val1;
        if (val2 > max) {
            max = val2;
        }
        if (val3 > max) {
            max = val3;
        }
        return max;
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

