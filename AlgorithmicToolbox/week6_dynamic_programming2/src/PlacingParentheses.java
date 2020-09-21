import java.util.Scanner;

public class PlacingParentheses {


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

    //from digit i to digit j in digits
    private static int getMin(int i, int j, int[] digits, char[] operators) {//max(5−8+7×4−8+9) =?

        if (i == j) {
            return digits[i];
        }
        int returnValue = Integer.MAX_VALUE;
//        char[] operators = extractOps(i, j, exp);
//        int[] digits = extractDigits(i, j, exp);

        for (int k = i; k < j; k++) {//from operator i to operator j-1//when i = 0, j=1 -> k=0
            char ops = operators[k];
            int min = Integer.MAX_VALUE;
            if (ops == '+') {
                min = getMin(i, k, digits, operators) + getMin(k + 1, j, digits, operators);
            } else if (ops == '-') {
                min = getMin(i, k, digits, operators) - getMax(k + 1, j, digits, operators);
            } else if (ops == '*') {
                int a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                min = a;
                int b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b < min) {
                    min = b;
                }
                int c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c < min) {
                    min = c;
                }
                int d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                if (d < min) {
                    min = d;
                }

            }
            if (min < returnValue) {
                returnValue = min;
            }
        }
        return returnValue;
    }

    //from digit i to digit j in digits
    private static int getMax(int i, int j, int[] digits, char[] operators) {//max(5−8+7×4−8+9) =?
        if (i == j) {
            return digits[i];
        }
        int returnValue = Integer.MIN_VALUE;

        for (int k = i; k < j; k++) {//from operator i to operator j-1//when i = 0, j=1 -> k=0
            char ops = operators[k];
            int max = Integer.MIN_VALUE;
            if (ops == '+') {
                max = getMax(i, k, digits, operators) + getMax(k + 1, j, digits, operators);
            } else if (ops == '-') {
                max = getMax(i, k, digits, operators) - getMin(k + 1, j, digits, operators);
            } else if (ops == '*') {
                int a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                max = a;
                int b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b > max) {
                    max = b;
                }
                int c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c > max) {
                    max = c;
                }
                int d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                if (d > max) {
                    max = d;
                }

            }
            if (max > returnValue) {
                returnValue = max;
            }
        }
        return returnValue;

    }

    private static int[] getMinAndMax(int i, int j, int[] digits, char[] operators) {
        if (i == j) {
            int[] r = new int[2];
            r[0] = i;
            r[1] = i;
            return r;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int k = i; k < j; k++) {

            int minV = Integer.MAX_VALUE;
            int maxV = Integer.MIN_VALUE;
            if (operators[k] == '+') {

                minV = getMin(i, k, digits, operators) + getMin(k + 1, j, digits, operators);
                maxV = getMax(i, k, digits, operators) + getMax(k + 1, j, digits, operators);

            } else if (operators[k] == '-') {

                minV = getMin(i, k, digits, operators) - getMax(k + 1, j, digits, operators);
                maxV = getMax(i, k, digits, operators) - getMin(k + 1, j, digits, operators);

            } else if (operators[k] == '*') {

                int a = getMin(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
                maxV = a;
                minV = a;

                int b = getMin(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (b > maxV) {
                    maxV = b;
                }
                if (b < minV) {
                    minV = b;
                }

                int c = getMax(i, k, digits, operators) * getMax(k + 1, j, digits, operators);
                if (c > maxV) {
                    maxV = c;
                }
                if (c < minV) {
                    minV = c;
                }

                int d = getMax(i, k, digits, operators) * getMin(k + 1, j, digits, operators);
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
        int[] minMax = new int[2];
        minMax[0] = min;
        minMax[1] = max;
        return minMax;
    }


    private static long getMaximValue(String exp) {
        //write your code here

        char[] operators = extractOps(0, exp.length() - 1, exp);
        int[] digits = extractDigits(0, exp.length() - 1, exp);

        int[][] minimum = new int[digits.length][];
        for (int i = 0; i < digits.length; i++) {
            minimum[i] = new int[digits.length];
            minimum[i][i] = digits[i];
        }

        int[][] maximum = new int[digits.length][];
        for (int i = 0; i < digits.length; i++) {
            maximum[i] = new int[digits.length];
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

