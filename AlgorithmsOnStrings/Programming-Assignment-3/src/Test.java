/**
 * Created by Jiang Wensi on 30/9/2020
 */
public class Test {
    public static void main(String[] args) {
        int[] s = prefixFunction("ACATACATACACA");
        for(int i = 0; i < s.length; i ++){
            System.out.println(s[i]+" ");
        }
    }

    private static int[] prefixFunction(String p) {
        int[] s = new int[p.length()];
        s[0] = 0;
        int borderLen = 0;
        for (int i = 1; i < p.length(); i++) {
            while (borderLen > 0 && p.charAt(i) != p.charAt(borderLen)) {
                borderLen=s[borderLen-1];
            }
            if(p.charAt(i) == p.charAt(borderLen)){
                borderLen++;
            } else {
                borderLen=0;
            }
            s[i]=borderLen;
        }
        return s;
    }

}
