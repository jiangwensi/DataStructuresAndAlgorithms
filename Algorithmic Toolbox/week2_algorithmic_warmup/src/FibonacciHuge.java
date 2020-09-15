import java.util.*;

public class FibonacciHuge {

    private static Map<Long, Long> numbers = new HashMap<>();
//    private static Long period = 1L;

    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    private static long getFibonacciHugeFast(long n, long m) {
        Long period = getPeriod(n, m);
        if(period>0){
            return numbers.get(n % period);
        } else {
            return numbers.get(n);
        }
    }

    private static Long getPeriod(long n, long m) {
        Long period = 0L;
        boolean foundPeriod = false;
        if (n <= 1) {
            numbers.put(n, n);
        } else {
            long previous = 0;
            long current = 1;
            numbers.put(0l, 0l);
            numbers.put(1l, 1l);
            for (long i = 2; i <= n; ++i) {
                if (numbers.get(i) != null) {
                    previous = current;
                    current = numbers.get(i);
                } else {
                    long tmp_previous = previous;
                    previous = current;
                    current = (tmp_previous + current) % m;
                    numbers.put(i, current);
                }
                period++;
                if (i != 0 && previous == 0 && current == 1) {
                    foundPeriod = true;
                    break;
                }
            }
        }
        if(!foundPeriod){
            return 0l;
        } else {
            return period;
        }
    }

    public static void main(String[] args) {

//        while(true){
//            long n = (long)new Random().nextInt(100);
//            long m = (long)new Random().nextInt(100)+1;
//            System.out.println("n="+n+",m="+m);
//            numbers = new HashMap<>();
//            long naiveResult = getFibonacciHugeNaive(n,m);
//            numbers = new HashMap<>();
//            long fastResult = getFibonacciHugeFast(n,m);
//            if(naiveResult!=fastResult){
//                System.out.println("Something is wrong, naiveResult="+naiveResult+",fastResult="+fastResult);
//                break;
//            } else {
//                System.out.println("result="+naiveResult+",ok");
//            }
//        }

        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeFast(n, m));
    }

}

