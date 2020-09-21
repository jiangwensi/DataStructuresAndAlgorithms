import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlacingParentheses {

    private static Map<String,Long> min = new HashMap<>();
    private static Map<String,Long> max = new HashMap<>();

    private static int[] extractDigits(int i, int j, String exp) {
        int[] digits = new int[(j - i) / 2 + 1];
        int d = 0;
        for (int m = i; m <= j; m = m + 2) {
            digits[d] = Character.getNumericValue(exp.charAt(m));
            d++;
        }
        return digits;
    }

    private static char[] extractOps(int i, int j, String exp) {
        char[] ops = new char[(j - i) / 2];
        int o = 0;
        for (int m = i + 1; m < j; m = m + 2) {
//            System.out.println("exp="+exp+",m="+m+",exp.chartAt(m)="+exp.charAt(m));
            ops[o] = exp.charAt(m);
            o++;
        }
        return ops;
    }

    private static String getKey(int i, int j){
        String key="";
        String a=String.valueOf(i);
        if(a.length()==1){
            a="0"+a;
        }
        String b = String.valueOf(j);
        if(b.length()==1){
            b="0"+b;
        }
        return a+b;
    }

    //from digit i to digit j in digits
    private static long getMin(int i, int j, int[] digits, char[] operators) {//max(5−8+7×4−8+9) =?
        String key = getKey(i,j);
        if(min.containsKey(key)){
            return min.get(key);
        }
        if (i == j) {
            return digits[i];
        }
        long returnValue = Long.MAX_VALUE;
//        char[] operators = extractOps(i, j, exp);
//        int[] digits = extractDigits(i, j, exp);

        for (int k = i; k < j; k++) {//from operator i to operator j-1//when i = 0, j=1 -> k=0
            char ops = operators[k];
            long min = Long.MAX_VALUE;
            if (ops == '+') {
                min = getMin(i, k, digits, operators) + getMin(k + 1, j, digits, operators);
            } else if (ops == '-') {
                min = getMin(i, k, digits, operators) - getMax(k + 1, j, digits, operators);
            } else if (ops == '*') {
                long a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                min = a;
                long b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b < min) {
                    min = b;
                }
                long c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c < min) {
                    min = c;
                }
                long d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                if (d < min) {
                    min = d;
                }

            }
            if (min < returnValue) {
                returnValue = min;
            }
        }
        min.put(key,returnValue);
        return returnValue;
    }

    //from digit i to digit j in digits
    private static long getMax(int i, int j, int[] digits, char[] operators) {//max(5−8+7×4−8+9) =?

        String key = getKey(i,j);
        if(max.containsKey(key)){
            return max.get(key);
        }

        if (i == j) {
            return digits[i];
        }
        long returnValue = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {//from operator i to operator j-1//when i = 0, j=1 -> k=0
            char ops = operators[k];
            long max = Long.MIN_VALUE;
            if (ops == '+') {
                max = getMax(i, k, digits, operators) + getMax(k + 1, j, digits, operators);
            } else if (ops == '-') {
                max = getMax(i, k, digits, operators) - getMin(k + 1, j, digits, operators);
            } else if (ops == '*') {
                long a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                max = a;
                long b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b > max) {
                    max = b;
                }
                long c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c > max) {
                    max = c;
                }
                long d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                if (d > max) {
                    max = d;
                }

            }
            if (max > returnValue) {
                returnValue = max;
            }
        }
        max.put(key,returnValue);
        return returnValue;

    }

    private static long[] getMinAndMax(int i, int j, int[] digits, char[] operators) {
        if (i == j) {
            long[] r = new long[2];
            r[0] = i;
            r[1] = i;
            return r;
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {

            long minV = Long.MAX_VALUE;
            long maxV = Long.MIN_VALUE;
            if (operators[k] == '+') {

                minV = getMin(i, k, digits, operators) + getMin(k + 1, j, digits, operators);
                maxV = getMax(i, k, digits, operators) + getMax(k + 1, j, digits, operators);

            } else if (operators[k] == '-') {

                minV = getMin(i, k, digits, operators) - getMax(k + 1, j, digits, operators);
                maxV = getMax(i, k, digits, operators) - getMin(k + 1, j, digits, operators);

            } else if (operators[k] == '*') {

                long a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                maxV = a;
                minV = a;

                long b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b > maxV) {
                    maxV = b;
                }
                if (b < minV) {
                    minV = b;
                }

                long c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c > maxV) {
                    maxV = c;
                }
                if (c < minV) {
                    minV = c;
                }

                long d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                if (d > maxV) {
                    maxV = d;
                }
                if (d < minV) {
                    minV = d;
                }
            } else {
                System.out.println("wrong operator " + operators[k]);
            }
            if (minV < min) {
                min = minV;
            }
            if (maxV > max) {
                max = maxV;
            }
        }
        long[] minMax = new long[2];
        minMax[0] = min;
        minMax[1] = max;
        return minMax;
    }


    private static long getMaximValue(String exp) {
        //write your code here

        char[] operators = extractOps(0, exp.length() - 1, exp);
        int[] digits = extractDigits(0, exp.length() - 1, exp);

        long[][] minimum = new long[digits.length][];
        for (int i = 0; i < digits.length; i++) {
            minimum[i] = new long[digits.length];
            minimum[i][i] = digits[i];
        }

        long[][] maximum = new long[digits.length][];
        for (int i = 0; i < digits.length; i++) {
            maximum[i] = new long[digits.length];
            maximum[i][i] = digits[i];
        }

        for (int s = 1; s < digits.length; s++) {
            for (int i = 0; i < digits.length - s; i++) {
                int j = i + s;
                minimum[i][j] = getMinAndMax(i, j, digits, operators)[0];//from digits i to j
                maximum[i][j] = getMinAndMax(i, j, digits, operators)[1];
            }
        }

        return maximum[0][digits.length - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

