import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here

        int[] startSorted = mergeSortIntArr(starts, 0, starts.length, false);
        int[] endSorted = mergeSortIntArr(ends, 0, ends.length, false);
        for (int i = 0; i < points.length; i++) {
            int l = -1;//number of segments having left end point to the left of x or equal to x
            int r = -1;//number of segments having right end point to the right of x of equal to x

            l = bs(startSorted, 0, startSorted.length-1, points[i],false)+1;

//            for (int s = 0; s < startSorted.length; s++) {
//                if (startSorted[s] > points[i]) {
//                    l = s - 1 + 1;
//                    break;
//                }
//            }
//
//            if (l == -1) {
//                l = startSorted.length;
//            }
            int endBsResult = bs(endSorted, 0, endSorted.length-1, points[i],true);
            r = endSorted.length - endBsResult-1;
//            for (int e = 0; e < ends.length; e++) {
//                if (endSorted[e] < points[i]) {
//                    r = e - 1 + 1;
//                    break;
//                }
//            }

//            if (r == -1) {
//                r = ends.length;
//            }
            cnt[i] = l + r - starts.length;
        }

        return cnt;
    }

    private static int bs(int[] sorted, int left, int right, int point,boolean searchingRightBound) {

        if (right < left) {
                return left -1;
        }
        int mid = (left + right) / 2;

        if (point < sorted[mid]) {
            return bs(sorted, left, mid - 1, point,searchingRightBound);
        } else if (point > sorted[mid]) {
            return bs(sorted, mid + 1, right, point,searchingRightBound);
        } else {
            int i = mid;
            if(!searchingRightBound){
                while(i<sorted.length-1){
                    if(sorted[i+1]==sorted[mid]){
                        i++;
                    } else{
                        break;
                    }
                }
            } else {
                while(i>0){
                    if(sorted[i-1]==sorted[mid]){
                        i--;
                    } else{
                        break;
                    }
                }
                i--;
            }
            return i;
        }
    }

    private static int[] mergeSortIntArr(int[] arr, int left, int right, boolean reverse) {
        if (right - left == 1) {
            int[] sorted = new int[1];
            sorted[0] = arr[left];
            return sorted;
        }
        int mid = (left + right) / 2;
        int[] leftSorted = mergeSortIntArr(arr, left, mid, reverse);
        int[] rightSorted = mergeSortIntArr(arr, mid, right, reverse);
        return merge(leftSorted, rightSorted, reverse);
    }

    private static int[] merge(int[] leftSorted, int[] rightSorted, boolean reverse) {
        int left = 0;
        int right = 0;
        int index = 0;
        int[] sorted = new int[leftSorted.length + rightSorted.length];
        while (left < leftSorted.length && right < rightSorted.length) {
            if (leftSorted[left] <= rightSorted[right]) {
                if (reverse) {
                    sorted[index] = rightSorted[right];
                    right++;
                } else {
                    sorted[index] = leftSorted[left];
                    left++;
                }
            } else {
                if (reverse) {
                    sorted[index] = leftSorted[left];
                    left++;
                } else {
                    sorted[index] = rightSorted[right];
                    right++;
                }
            }
            index++;
        }
        if (left < leftSorted.length) {
            for (int i = left; i < leftSorted.length; i++) {
                sorted[index] = leftSorted[i];
                index++;
            }
        }
        if (right < rightSorted.length) {
            for (int i = right; i < rightSorted.length; i++) {
                sorted[index] = rightSorted[i];
                index++;
            }
        }
        return sorted;
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

