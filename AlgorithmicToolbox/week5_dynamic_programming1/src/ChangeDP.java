import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        //write your code here
        int[] coins = {1, 3, 4};
        int[] minChangeCount = new int[m+1];
        minChangeCount[0]=0;
        for (int i = 1; i <= m; i++) {
//            int money = i;
            int minCount = Integer.MAX_VALUE;
            for (int c = 0; c < coins.length; c++) {
                if(i>=coins[c]){
                    int count = minChangeCount[i-coins[c]]+1;
                    if(count<minCount){
                        minCount=count;
                    }
                }
            }
            minChangeCount[i]=minCount;
        }


        return minChangeCount[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

