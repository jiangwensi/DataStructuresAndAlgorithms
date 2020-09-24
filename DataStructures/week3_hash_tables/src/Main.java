/**
 * Created by Jiang Wensi on 24/9/2020
 */
public class Main {

    private static int prime = 1000000007;
    private static int multiplier = 263;
    private static int bucketCount = 5;

    public static void main(String[] args) {
        System.out.println((-2)%5);
//        System.out.println(hashFuncMy("world"));
//        System.out.println(hashFunc("world"));
//        cal();
    }

    private static void cal() {
        System.out.println("cal::");
        double result = ( 119
                        + 111 * 263
                        + 114 * Math.pow(263, 2)
                        + 108 * Math.pow(263, 3)
                        + 100 * Math.pow(263, 4));
        System.out.println(result);
        result=result% prime % bucketCount;
        System.out.println("cal="+result);
    }

    private static double hashFuncMy(String s) {
        System.out.println("hashFuncMy::");
        double hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i) * Math.pow(multiplier, i) ;
            System.out.println("s.charAt(i)="+(s.charAt(i)+0));
        }
        System.out.println(hash);
        hash %= prime;
        hash %= bucketCount;
        System.out.println("hashFuncMy="+hash);
        return hash;
    }

    private static int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            hash = (hash * multiplier + s.charAt(i)) % prime;
        }
        return (int) hash % bucketCount;
    }

}
