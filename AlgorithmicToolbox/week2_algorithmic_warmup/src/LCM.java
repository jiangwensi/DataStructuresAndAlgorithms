import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }

  private static int gcd_fast(int a, int b){
    if(a%b==0){
      return b;
    } else if(b%a==0){
      return a;
    }
    if(a>b){
      return gcd_fast(a%b,b);
    } else {
      return gcd_fast(a,b%a);
    }
  }

  private static long lcm_fast(int a, int b){

    long gcd = gcd_fast(a,b);
//    long ab = a*b;
//    long result = ab/gcd;
//    return result;
    return a/gcd*b;
  }

  public static void main(String args[]) {
//    while (true){
//      int a = new Random().nextInt(10000000)+1;
//      int b = new Random().nextInt(10000000)+1;
//      System.out.println("a="+a+",b="+b);
//      long fast = lcm_fast(a,b);
//      long naive = lcm_naive(a,b);
//      if(fast!=naive){
//        System.out.println("not same,fast="+fast+",naive="+naive);
//        break;
//      } else
//      {
//        System.out.println("OK");
//      }
//    }


    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_fast(a, b));
  }
}
