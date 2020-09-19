import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MajorityElement {

    private static int getMajorityElementMergeSort(int[] a, int left, int right){
        a=mergeSort(a);
        if(isMajority(a,a.length/2)){
            return 1;
        } else {
            return -1;
        }
    }

    private static boolean isMajority(int[] a,int x){
        int count = 0;
        for(int i=0; i < a.length;i++){
            if(a[i]==a[x]){
                count++;
            }
        }
        return count*2>a.length;
    }


    private static int[] mergeSort(int[] a){
        return mergeSort(a,0,a.length-1);
    }

    private static int[] mergeSort(int[] a,int left, int right){
        if(left==right){
            return a;
        }
        int mid = (left+right)/2;
        int[] leftArr = new int[mid-left+1];
        int[] rightArr = new int[right-mid];
        if(mid-left>0){
            leftArr=mergeSort(a,left,mid);
        } else {
            leftArr[0]=a[left];
        }
        if(right-mid-1>0){
            rightArr=mergeSort(a,mid+1,right);
        } else {
            rightArr[0]=a[right];
        }
        return merge(leftArr,rightArr);
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();
        while(i<leftArr.length||j<rightArr.length){
            if(i>=leftArr.length){
                while(j<rightArr.length){
                    result.add(rightArr[j]);
                    j++;
                }
                break;
            }
            if(j>=rightArr.length){
                while(i<leftArr.length){
                    result.add(leftArr[i]);
                    i++;
                }
                break;
            }
            if(leftArr[i]<rightArr[j]){
                result.add(leftArr[i]);
                i++;
            } else {
                result.add(rightArr[j]);
                j++;
            }
        }
        int[] resultArr = new int[result.size()];
        for(int a=0;a<result.size();a++){
            resultArr[a]=result.get(a);
        }
        return resultArr;
    }

    public static void main(String[] args) {
//        stressTest();
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElementMergeSort(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static void stressTest(){
        int count = 0;
        while(true){
            int n = new Random().nextInt(100000)+1;
            int[] a = new int[n];
            for(int i = 0; i < n; i++){
                a[i]=new Random().nextInt(1000000000);
            }
//            printArr(a);
            int naive = getMajorityElementNaive(a,0,a.length-1);
            int fast = getMajorityElementMergeSort(a,0,a.length-1);
            if(naive!=fast){
                System.out.println("something went wrong,naive="+naive+",fast="+fast);
                break;
            } else{
                System.out.print(".");
            }
            count++;
            if(count%100==0){
                System.out.println();
            }
        }
    }

    private static void printArr(int[] a) {
        System.out.print(a.length);
        for(int i = 0; i <a.length;i++){
            System.out.print(" "+a[i]);
        }
        System.out.println();
    }



    private static int getMajorityElementNaive(int[] a, int left, int right) {
        Arrays.sort(a);
        int maxCount = 1;

        int i = 0;
        int curr = a[0];
        int currCount = 1;
        for (int j = 1; j < a.length; j++) {

            if (a[j] == curr) {
                currCount++;
            } else {
                curr = a[j];
                if (maxCount < currCount) {
                    maxCount = currCount;
                }
                currCount = 0;
            }
        }
        if (maxCount * 2 >= a.length + 1) {
            return 1;
        }
        return -1;
    }
}

