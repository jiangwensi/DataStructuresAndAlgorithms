import java.util.*;

public class PrimitiveCalculator {

    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequenceList = new ArrayList<>();
        sequenceList.add(n);

        List<Integer> minOperationCountList = getMinOperationCountList(n);
        int i =n;
        while(i>1){
            if (minOperationCountList.get(i - 1) - 1 == minOperationCountList.get(i - 1 - 1)) {
                sequenceList.add(i - 1);
                i=i-1;
            } else if (i % 2 == 0 && minOperationCountList.get(i - 1) - 1 == minOperationCountList.get(i / 2 - 1)) {
                sequenceList.add(i / 2);
                i=i/2;
            } else if (i % 3 == 0 && minOperationCountList.get(i - 1) - 1 == minOperationCountList.get(i / 3 - 1)) {
                sequenceList.add(i / 3);
                i=i/3;
            }
        }
        Collections.reverse(sequenceList);
        return sequenceList;
    }

    private static List<Integer> getMinOperationCountList(int n) {
        List<Integer> countList = new ArrayList<>();
        countList.add(0);
        for (int i = 2; i <= n; i++) {
            int minOpsCount = Integer.MAX_VALUE;
            int opsCount = 0;
            if (i - 1 >= 1) {
                opsCount = countList.get(i - 1 - 1) + 1;
                if (opsCount < minOpsCount) {
                    minOpsCount = opsCount;
                }
            }
            if (i % 2 == 0 && i / 2 >= 1) {
                opsCount = countList.get(i / 2 - 1) + 1;
                if (opsCount < minOpsCount) {
                    minOpsCount = opsCount;
                }
            }
            if (i % 3 == 0 && i / 3 >= 1) {
                opsCount = countList.get(i / 3 - 1) + 1;
                if (opsCount < minOpsCount) {
                    minOpsCount = opsCount;
                }
            }
            countList.add(minOpsCount);
        }
        return countList;
    }


//    private static List<Integer> optimal_sequence(int n) {
//        List<Integer> sequence = new ArrayList<Integer>();
//        while (n >= 1) {
//            sequence.add(n);
//            if (n % 3 == 0) {
//                n /= 3;
//            } else if (n % 2 == 0) {
//                n /= 2;
//            } else {
//                n -= 1;
//            }
//        }
//        Collections.reverse(sequence);
//        return sequence;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

