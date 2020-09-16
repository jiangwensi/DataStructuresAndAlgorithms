import java.util.*;

public class FibonacciSumSquares {
    private static Map<Long, Long> fabs;
    private static Map<Long, Integer> sums;
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }

    private static long getFibonacciSumSquaresFast(long n) {
        if (n <= 1)
            return n;

        long current = 1;
        int sum = 0;
        fabs = new HashMap<>();
        sums = new HashMap<>();

        sums.put(0l, 0);
        sums.put(1l, 1);

        long period = getPeriod(n, 10);

        if (period > 0) {
            long periodFabSum = 0;
            for (long i = 0; i < period; i++) {
                try {
                    periodFabSum = (fabs.get(i) + periodFabSum) % 10;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            sum = (int) (((n + 1) / period) * periodFabSum) % 10;

            long reminder = (n + 1) % period;

            for (long i = 0; i < reminder; i++) {
                long fab = fabs.get(i);
                sum = (int) (fabs.get(i) + sum) % 10;
            }
        } else {
            for (long i = 0; i <= n; i++) {
                try {
                    sum = (int) (fabs.get(i) + sum) % 10;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return sum;
    }


    private static Long getPeriod(long n, long m) {
        Long period = 0L;
        boolean foundPeriod = false;
        if (n <= 1) {
            fabs.put(n, n);
        } else {
            long previous = 0;
            long current = 1;
            fabs.put(0l, 0l);
            fabs.put(1l, 1l);
            period += 2;
            for (long i = 2; i <= n; ++i) {
                if (fabs.get(i) != null) {
                    previous = current;
                    current = fabs.get(i);
                } else {
                    long tmp_previous = previous;
                    previous = current;
                    current = (tmp_previous + current) % m;
                    fabs.put(i, current*current % 10);
                }
                period++;
                if (i != 0 && previous == 0 && current == 1) {
                    foundPeriod = true;
                    period -= 2;
                    break;
                }
            }
        }


        if (!foundPeriod) {
            return 0l;
        } else {
            return period;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquaresFast(n);
//        long s = getFibonacciSumSquaresNaive(n);
        System.out.println(s);
    }
}

