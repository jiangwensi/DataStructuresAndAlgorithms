import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
//    static long getMaxPairwiseProduct(int[] numbers) {
//        long max_product = 0;
//        int n = numbers.length;
//
//        for (int first = 0; first < n; ++first) {
//            for (int second = first + 1; second < n; ++second) {
//                max_product = Math.max(max_product,
//                    Long.valueOf(numbers[first]) * Long.valueOf(numbers[second]));
//            }
//        }
//
//        return max_product;
//    }
    static long getMaxPairwiseProduct(int[] numbers) {
        int n = numbers.length;
        long largestNum = Long.MIN_VALUE;
        int largestNumIndex = 0;
        long secondLargestNum = Long.MIN_VALUE;

        for (int i = 0; i < n; ++i) {
            if(Long.valueOf(numbers[i]) > largestNum){
                largestNum = numbers[i];
                largestNumIndex = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if(i!=largestNumIndex && Long.valueOf(numbers[i]) > secondLargestNum){
                secondLargestNum = numbers[i];
            }
        }

        return largestNum * secondLargestNum;
    }

    public static void main(String[] args) {
//        int size = (int) (Math.random() * 10000);
//        int[] myNumbers = new int [size];
//        while(true){
//            Arrays.stream(myNumbers).forEach((e)->{
//                e=(int)(Math.random()*10000);
//                System.out.print(e+" ");
//            });
//            Long result = getMaxPairwiseProduct(myNumbers);
//            Long resultFast = getMaxPairwiseProductFast(myNumbers);
//            if(result!=resultFast){
//                System.out.println("result="+result+",resultFast="+resultFast);
//                break;
//            } else {
//                System.out.println("\nOK");
//            }
//        }

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
