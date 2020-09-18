import java.io.*;
import java.util.*;

public class BinarySearch {

    static int bsRecursion(int[] a, int x, int i, int j) {
        int left = 0;
        int right = a.length;
        int k = (i + j) / 2;
        if (a[k] == x) {
            return k;
        }
        if (i <= j) {
            if (x < a[k]) {
                return bsRecursion(a, x, i, k - 1);
            } else {
                if (k == a.length - 1) {
                    return -1;
                }
                return bsRecursion(a, x, k + 1, j);
            }
        }
        return -1;
    }


    static int binarySearch(int[] a, int x) {
        return bsRecursion(a, x, 0, a.length - 1);
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
//        stressTest();

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch(a, b[i]) + " ");
//            System.out.print(linearSearch(a, b[i]) + " ");
        }
    }


    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
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


    private static void stressTest() {
        boolean errorFound = false;
        int size = 30000;
        int maxN = Integer.MAX_VALUE;
        int maxK = 100000;
        while (true) {
            int n = new Random().nextInt(size) + 1;
            int[] a = inputArray(maxN, n);

            printArr(n, a);

            int m = new Random().nextInt(size) + 1;
            int[] b = inputArray(maxK, m);
            printArr(m, b);

            int[] resultLinear = new int[m];
            int[] resultBinary = new int[m];

            for (int i = 0; i < m; i++) {
                resultLinear[i] = linearSearch(a, b[i]);
                resultBinary[i] = binarySearch(a, b[i]);
            }
            printArr(m,resultLinear);
            printArr(m,resultBinary);

            for (int i = 0; i < m; i++) {
                if (resultBinary[i] != resultLinear[i]) {
                    printError(n, a, m, b, resultLinear, resultBinary);

                    errorFound = true;
                    break;
                }
            }
            if (errorFound) {
                break;
            }
            System.out.println("ok ");

        }
    }

    private static void printArr(int n, int[] a) {
        System.out.print(n+" ");
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    private static int[] inputArray(int max, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            boolean duplicate=false;
            int v = new Random().nextInt(max) + 1;
            for (int x = 0; x <i; x++) {
                if (a[x] == v) {
                    i--;
                    duplicate=true;
                    break;
                }
            }
            if(!duplicate){
                a[i] = v;
            }
        }
        Arrays.sort(a);
        return a;
    }

    private static void printError(int n, int[] a, int m, int[] b, int[] resultLinear, int[] resultBinary) {
        System.out.println("Wrong answer.");

        System.out.print(n + " ");
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println();

        System.out.print(m + " ");
        for (int k = 0; k < b.length; k++) {
            System.out.print(b[k] + " ");
        }

        System.out.println();
        System.out.println("binary result:");
        for (int x = 0; x < m; x++) {
            System.out.print(resultBinary[x] + " ");
        }

        System.out.println();

        System.out.println("linear result:");
        for (int x = 0; x < m; x++) {
            System.out.print(resultLinear[x] + " ");
        }
        System.out.println();
    }
}
