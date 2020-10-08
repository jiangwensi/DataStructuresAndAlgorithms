import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here

        for (int i = 0; i < points.length; i++) {
            int l = -1;//number of segments having left end point to the left of x or equal to x
            int r = -1;//number of segments having right end point to the right of x of equal to x
            Arrays.sort(starts);
            for (int s = 0; s < starts.length; s++) {
                if(starts[s]>points[i]){
                    l=s-1+1;
                    break;
                }
            }
            if(l==-1){
                l=starts.length;
            }

            Arrays.sort(ends);
            for (int e = ends.length-1; e >=0; e--) {
                if(ends[e]<points[i]){
                    r=ends.length-e-1;
                    break;
                }
            }
            if(r==-1){
                r= ends.length;
            }
            cnt[i]=l+r-starts.length;

        }

        return cnt;
    }


//    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
//        int[] cnt = new int[points.length];
//        for (int i = 0; i < points.length; i++) {
//            for (int j = 0; j < starts.length; j++) {
//                if (starts[j] <= points[i] && points[i] <= ends[j]) {
//                    cnt[i]++;
//                }
//            }
//        }
//        return cnt;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

