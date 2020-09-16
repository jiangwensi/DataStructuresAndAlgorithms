import java.util.*;

public class FractionalKnapsack {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    private static class Item {
        private double vPerW;
        private double w;

        public Item(double vPerW, double w) {
            this.vPerW = vPerW;
            this.w = w;
        }
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        double remaining = capacity;
        Item[] items = new Item[values.length];

        for (int i = 0; i < values.length; i++) {
            items[i] = new Item((double) values[i] / (double) weights[i], (double) weights[i]);
        }
        List<Item> itemsSorted = new ArrayList<>(Arrays.asList(items));

        Collections.sort(itemsSorted, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.vPerW - o2.vPerW <= 0 ? 1 : -1;
            }
        });

        while (remaining > 0 && itemsSorted.size()>0) {
            Item item = itemsSorted.get(0);
            if(item.w<=remaining){
                remaining -= item.w;
                value += item.w * item.vPerW;
                itemsSorted.remove(item);
            } else {
                value += remaining*item.vPerW;
                item.w -= remaining;
                remaining = 0;
                break;
            }
        }

        return value;
    }
}

/*
pseudo codes:
class item {
    float vPerW;
    float w;
}
float[] item = {...}
float[] itemSorted = Collections.sort(item);
float remaining = W;
float value = 0;
while(remaining>0){
    if(itemSorted[0].w <= remaining){
        remaining -=f.w;
        itemSorted.remove(0);
        value += itemSorted[0].vPerW * w
    } else {
        value -= itemSorted[0].vPerW * remaining;
        break;
    }
}
return value;

 */