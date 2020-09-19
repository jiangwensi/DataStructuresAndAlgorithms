import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiang Wensi on 19/9/2020
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("main:");
        int[] a = {1,2,3,9,5,3,3,5,2,3,1};
        int[] sorted = mergeSort(a,0,a.length-1);
        System.out.println("result:");
        for(int i = 0; i < sorted.length; i++){
            System.out.print(sorted[i]+" ");
        }
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

//    private static int[] merge(int[] a, int leftStart, int leftEnd, int rightStart, int rightEnd) {
//        int i = leftStart;
//        int j = rightStart;
//        List<Integer> result = new ArrayList<>();
//        while(i<=leftEnd||j<=rightEnd){
//            if(i>leftEnd){
//                while(j<=rightEnd){
//                    result.add(a[j]);
//                    j++;
//                }
//                break;
//            }
//            if(j>rightEnd){
//                while(i<leftEnd){
//                    result.add(a[i]);
//                    i++;
//                }
//                break;
//            }
//            if(a[i]<a[j]){
//                result.add(a[i]);
//                i++;
//            } else {
//                result.add(a[j]);
//                j++;
//            }
//        }
//        int[] resultArr = new int[result.size()];
//        for(int m =0; m < result.size();m++){
//            resultArr[m]=result.get(m);
//        }
//        return resultArr;
//    }

//    private static int[] mergeSort(int[] a, int left, int right) {
//        List<Integer> result = new ArrayList<>();
//        if(left==right){
//            result.add(a[left]);
//        } else {
//            int m = (left + right) / 2;
//            int[] leftArr = mergeSort(a, left, m);
//            int[] rightArr = mergeSort(a, m + 1, right);
//            int i = 0, j = 0;
//            while (i < leftArr.length || j < rightArr.length) {
//                if (i >= leftArr.length) {
//                    while (j < rightArr.length) {
//                        result.add(rightArr[j]);
//                        j++;
//                    }
//                } else if (j >= leftArr.length) {
//                    while (i < leftArr.length) {
//                        result.add(leftArr[i]);
//                        i++;
//                    }
//                } else {
//                    if (leftArr[i] < rightArr[j]) {
//                        result.add(leftArr[i]);
//                        i++;
//                    } else if (leftArr[i] >= rightArr[j]) {
//                        result.add(rightArr[j]);
//                        j++;
//                    }
//
//                }
//            }
//        }
//        int [] returnResult = new int[result.size()];
//        for(int i = 0; i < result.size();i++){
//            returnResult[i] = result.get(i);
//        }
//        return returnResult;
//    }
}
