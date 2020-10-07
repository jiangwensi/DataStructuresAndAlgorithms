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

    private static boolean isGreaterOrEqual(String digit, String max) {
        int len = min(digit.length(), max.length());
        for (int i = 0; i < len; i++) {
            if (digit.charAt(i) > max.charAt(i)) {
                return true;
            }
            if (digit.charAt(i) < max.charAt(i)) {
                return false;
            }
        }
        if(digit.length()<=max.length()){
            return true;
        }else {
            return false;
        }
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

