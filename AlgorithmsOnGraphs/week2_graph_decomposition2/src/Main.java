import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jiang Wensi on 25/9/2020
 */
public class Main {
    public static void main(String[] args) {
        Integer[] test = {1,0,2,3};
        Arrays.sort(test,new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(int i  = 0; i < test.length;i++){
            System.out.println(test[i]+" ");
        }
    }
}
