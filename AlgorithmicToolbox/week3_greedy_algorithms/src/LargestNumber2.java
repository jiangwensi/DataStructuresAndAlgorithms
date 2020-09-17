import java.util.*;

public class LargestNumber2 {
    private static String largestNumber(String[] a) {
        if (a == null || a.length == 0) {
            return "";
        }
        //write your code here

        List<Number> oneD = new ArrayList<>();
        List<Number> twoD = new ArrayList<>();
        List<Number> threeD = new ArrayList<>();
        List<Number> fourD = new ArrayList<>();
        List<Combined> combinedList = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (a[i].length() == 1) {
                oneD.add(new Number(a[i], i));
            } else if (a[i].length() == 2) {
                twoD.add(new Number(a[i], i));
            } else if (a[i].length() == 3) {
                threeD.add(new Number(a[i], i));
            } else {
                fourD.add(new Number(a[i], i));
            }
        }

        Collections.sort(oneD);
        Collections.sort(twoD);
        Collections.sort(threeD);
        Collections.sort(fourD);

        getThreeDigitsNumbers(oneD, twoD, threeD, combinedList);
        if (combinedList.size() == 0) {
            if (oneD.size() >= 2) {
                Combined c = new Combined();
                c.numbers.add(oneD.get(0));
                c.numbers.add(oneD.get(1));
                combinedList.add(c);
            }
            if (twoD.size() >= 1) {
                Combined c = new Combined();
                c.numbers.add(twoD.get(0));
                combinedList.add(c);
            }
        }
        if (combinedList.size() == 0) {
            if (oneD.size() >= 1) {
                Combined c = new Combined();
                c.numbers.add(oneD.get(0));
                combinedList.add(c);
            }
        }
        if(combinedList.size()==0){
            if(fourD.size()>=1){
                for(int i =0; i < fourD.size();i++){
                    Combined c = new Combined();
                    c.numbers.add(fourD.get(i));
                    combinedList.add(c);
                }
            }
        }
//
//        String result = "";
//        for (int i = 0; i < a.length; i++) {
//            result += a[i];
//        }
        Combined largest = combinedList.get(0);
        a = remove(a, largest.numbers);
        return combinedList.get(0).getCombinedValue() + largestNumber(a);
    }

    private static void getThreeDigitsNumbers(List<Number> oneD, List<Number> twoD, List<Number> threeD, List<Combined> combinedList) {
        if (oneD.size() >= 3) {
            Combined c = new Combined();
            c.numbers.add(oneD.get(0));
            c.numbers.add(oneD.get(1));
            c.numbers.add(oneD.get(2));
            combinedList.add(c);
        }

         if (oneD.size() >= 1 && twoD.size() >= 1) {
            Combined c = new Combined();
            c.numbers.add(oneD.get(0));
            c.numbers.add(twoD.get(0));
            combinedList.add(c);

            c = new Combined();
            c.numbers.add(twoD.get(0));
            c.numbers.add(oneD.get(0));
            combinedList.add(c);
        }

         if (oneD.size() >= 1 && threeD.size() >= 1) {
            Combined c = new Combined();
            c.numbers.add(oneD.get(0));
            c.numbers.add(threeD.get(0));
            combinedList.add(c);
        }

         if (twoD.size() >= 2) {
            Combined c = new Combined();
            c.numbers.add(twoD.get(0));
            c.numbers.add(twoD.get(1));
            combinedList.add(c);
        }

         if (twoD.size() >= 1 && threeD.size() >= 1) {
            Combined c = new Combined();
            c.numbers.add(twoD.get(0));
            c.numbers.add(threeD.get(0));
            combinedList.add(c);
        }
         if (threeD.size() >= 1) {
            Combined c = new Combined();
            c.numbers.add(threeD.get(0));
            combinedList.add(c);
        }

        Collections.sort(combinedList, new Comparator<Combined>() {
            @Override
            public int compare(Combined o1, Combined o2) {
                if(o1.getCombinedValue().length()==o2.getCombinedValue().length()){
                    return -o1.getCombinedValue().compareTo(o2.getCombinedValue());
                } else {

                }

                //TODO to decide which value is larger when diff length

                int minLen = Integer.MAX_VALUE;
                if(o1.getCombinedValue().length()<o2.getCombinedValue().length()){
                    minLen=o1.getCombinedValue().length();
                } else {
                    minLen=o2.getCombinedValue().length();
                }
                String v1=o1.getCombinedValue().substring(0,minLen);
                String v2=o2.getCombinedValue().substring(0,minLen);
                return -v1.compareTo(v2);
            }
        });
    }

    private static String[] remove(String[] a, List<Number> numbers) {
        List<String> aList = new ArrayList<>();
        List<String> removing = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            aList.add(i, a[i]);
        }
        for (int i = 0; i < numbers.size(); i++) {
            removing.add(numbers.get(i).value);
        }

        removing.forEach(e->aList.remove(e));
        String[] result = new String[aList.size()];
        for (int i = 0; i < aList.size(); i++) {
            result[i] = aList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }

    private static class Number implements Comparable<Number> {
        String value;
        int index;

        public Number(String value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Number o) {
            return -this.value.compareTo(o.value);
        }
    }

    private static class Combined implements Comparable<Combined> {
        private List<Number> numbers = new ArrayList<>();

        private String getCombinedValue() {
            String combinedValue = "";
            for (int i = 0; i < numbers.size(); i++) {
                combinedValue += numbers.get(i).value;
            }
            return combinedValue;
        }

        @Override
        public int compareTo(Combined o) {
            return -this.getCombinedValue().compareTo(o.getCombinedValue());
        }
    }
}

