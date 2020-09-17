import java.util.*;

public class DotProduct {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextLong();
        }
        System.out.println(maxDotProduct(a, b));
    }

    private static long maxDotProduct(long[] a, long[] b) {
        //write your code here
        long result = 0;
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = a.length-1; i >= 0; i--) {
            result += a[i] * b[i];
        }
        return result;
    }
}

/*
pseudo codes:

Collections.sort(a);
Collections.sort(b);
int sum = 0;
for(int i =0; i<n; i++){
    sum+=ai*bi;
}
return sum;
 */

