import java.util.*;

public class FibonacciPartialSum {

    private static Map<Long, Long> fabs;
    private static Map<Long, Integer> sums;

    private static long getFibonacciPartialSumFast(long from, long to) {
        if (to <= 1)
            return to;

        long current = 1;
        fabs = new HashMap<>();
        sums = new HashMap<>();

        sums.put(0l, 0);
        sums.put(1l, 1);

        long period = getPeriod(to, 10);

        int sumLowerHalf = calculateSum(from-1,period);
        int sum = calculateSum(to,period);

        return (sum-sumLowerHalf+10)%10;
    }

    private static int calculateSum(long n, long period) {
        int sum = 0;
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
//            sumLowerHalf = (int) (((to) / period) * periodFabSum) % 10;

            long reminder = (n + 1) % period;
//            long lowerHalfReminder = (to) % period;

            for (long i = 0; i < reminder; i++) {
                long fab = fabs.get(i);
                sum = (int) (fabs.get(i) + sum) % 10;
            }

//            for (long i = 0; i < lowerHalfReminder-1; i++) {
//                long fab = fabs.get(i);
//                sumLowerHalf = (int) (fabs.get(i) + sumLowerHalf) % 10;
//            }

        } else {
            for (long i = 0; i <= n; i++) {
                try {
                    sum = (int) (fabs.get(i) + sum) % 10;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


//            for (long i = 0; i <= to-1; i++) {
//                try {
//                    sumLowerHalf = (int) (fabs.get(i) + sumLowerHalf) % 10;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
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
                    fabs.put(i, current % 10);
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

    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumFast(from, to));
    }
}

