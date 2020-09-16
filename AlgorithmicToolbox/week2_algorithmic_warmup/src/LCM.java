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

  private static int lcm_fast(int a, int b){
    return a*b/gcd_fast(a,b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_fast(a, b));
  }
}
