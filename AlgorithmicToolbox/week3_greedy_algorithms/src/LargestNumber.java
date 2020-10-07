import java.util.*;
import java.util.stream.Collectors;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        String result = "";
//        List<String> digitsStr = new LinkedList(Arrays.asList(a));
        List<String> digits = new LinkedList(Arrays.asList(a));
//        List<Integer> digits = digitsStr.stream().map(d->Integer.parseInt(d)).collect(Collectors.toCollection(LinkedList::new));
        while (digits.size() > 0) {
            String maxDigit = digits.get(0);
            int maxIndex = 0;
            for (int i = 0; i < digits.size(); i++) {
                if (isGreaterOrEqual(digits.get(i), maxDigit)) {
                    maxDigit = digits.get(i);
                    maxIndex = i;
                }
            }

            result += maxDigit;
            digits.remove(maxIndex);
        }
        return result;
    }

    private static boolean isGreaterOrEqual(String a, String b) {
        String ab = a + b;
        String ba = b + a;
        if (ab.compareTo(ba) >= 0) {
            return true;
        }
        return false;
    }

    private static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

