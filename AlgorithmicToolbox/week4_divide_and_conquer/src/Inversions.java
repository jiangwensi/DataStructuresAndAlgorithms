import java.util.*;

public class Inversions {

    private static long naiveSolution (int [] arr, int[] result, int left, int right){
        long count = 0;
        for(int i = 0; i < arr.length; i ++){
            for(int j = i; j< arr.length; j++){
                if(arr[i]>arr[j]){
                    count++;
                }
            }
        }
        return count;
    }

    private static long getNumberOfInversions(int[] arr, int[] result, int left, int right) {

        long numLeftInv = 0;
        long numRightInv = 0;
        long numMergeInv = 0;

        if (left == right) {
            return 0;
        }

        if (left +1 == right) {
            result[0]=arr[left];
            return 0;
        }

        int midIndex = (left + right) / 2;

        int[] leftResult = new int[midIndex - left];//exclude midIndex
        int[] rightResult = new int[right - midIndex];//include midIndex

        numLeftInv = getNumberOfInversions(arr, leftResult, left, midIndex);//exclude midIndex
        numRightInv = getNumberOfInversions(arr, rightResult, midIndex, right);//include midIndex
        numMergeInv = merge(leftResult, rightResult, result);

        return numLeftInv + numRightInv + numMergeInv;
    }

    private static long merge(int[] sortedLeft, int[] sortedRight, int[] sortedArr) {
        int l = 0;
        int r = 0;
        int i = 0;
        long invCount = 0;
        while (l < sortedLeft.length && r < sortedRight.length) {
            if (sortedLeft[l] <= sortedRight[r]) {
                sortedArr[i] = sortedLeft[l];
                l++;
            } else {
                sortedArr[i] = sortedRight[r];
                r++;
                invCount+=sortedLeft.length-l;
            }
            i++;
        }
        if (l < sortedLeft.length) {
            for (int index = l; index < sortedLeft.length; index++) {
                sortedArr[i] = sortedLeft[l];
                i++;
                l++;
            }
        }
        if (r < sortedRight.length) {
            for (int index = r; index < sortedRight.length; index++) {
                sortedArr[i] = sortedRight[r];
                i++;
                r++;
            }
        }
        return invCount;
    }

    public static void main(String[] args) {
//        stressTest();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
//        System.out.println(naiveSolution(a, b, 0, a.length));
    }

    private static void stressTest() {
        while(true){
            int n = new Random().nextInt(5)+1;
            System.out.println("\n\n"+n);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Random().nextInt(5)+1;
                System.out.print(a[i]+" ");
            }
            int[] b = new int[n];
            long advanced = getNumberOfInversions(a, b, 0, a.length);
            long naive = naiveSolution(a, b, 0, a.length);
            if(Long.compare(advanced,naive)!=0){
                System.out.println("mismatch: advanced="+advanced+",naive="+naive);
                break;
            }
        }
    }
}