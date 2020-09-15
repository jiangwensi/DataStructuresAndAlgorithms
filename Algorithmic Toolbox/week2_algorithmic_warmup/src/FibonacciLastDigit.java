import java.util.*;

public class FibonacciLastDigit {
    private static Long[] numbers;

    private static Long getFibonacciLastDigitFast(int n) {
        if (n <= 1)
            return (long)n;

        Long nMinus1 = 0L;
        Long current  = 1L;

        for (int i = 0; i < n - 1; ++i) {
            if(numbers[i]==null){
                Long nMinus2 = nMinus1;
                nMinus1 = current%10;
                current = nMinus2 + nMinus1;
                numbers[i]=current%10;
            } else {
                current = numbers[i];
                nMinus1 = current%10;
            }
        }

        return current % 10;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        numbers = new Long[n+1];
        Long c = getFibonacciLastDigitFast(n);
        System.out.println(c);
    }

}

