import java.util.Random;
import java.util.Scanner;

public class Fibonacci {
  private static Long[] numbers;
//  private static long calc_fib(int n) {
//    if (n <= 1)
//      return n;
//
//    return calc_fib(n - 1) + calc_fib(n - 2);
//  }

  private static long calc_fib_fast(int n){

    if(numbers[n]!=null) {
      return numbers[n];
    } else {
      if(n<=1){
        numbers[n]=Long.valueOf(n);
        return n;
      } else {
        long result1;
        long result2;
        long result3;
        if(numbers[n-1]!=null){
          result1 = numbers[n-1];
        } else {
          result1 = calc_fib_fast(n-1);
          numbers[n-1]=result1;
        }
        if(numbers[n-2]!=null){
          result2 = numbers[n-2];
        } else {
          result2 = calc_fib_fast(n-2);
          numbers[n-2]=result2;
        }
        result3 = result1 + result2;
        numbers[n]= result3;

        return result3;
      }
    }
  }

  public static void main(String args[]) {

//    while(true){
//      int n = new Random().nextInt(10);
//      numbers = new Long[n+1];
//      long result1 = calc_fib(n);
//      long result2 = calc_fib_fast(n);
//      if(result1!=result2){
//        System.out.println("Something went wrong,result1="+result1+",result2="+result2);
//        break;
//      }else {
//        System.out.println("Fibonacci number of "+n+" is "+result1+". OK!");
//      }
//    }

    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    numbers = new Long[n+1];
    System.out.println(calc_fib_fast(n));
  }
}
