import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
       int[] deno = {10,5,1};
       int remaining = m;
       int count = 0;
       while(remaining>0){
           for(int i: deno){
               if(i<=remaining){
                   count++;
                   remaining -=i;
                   break;
               }
           }
       }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

