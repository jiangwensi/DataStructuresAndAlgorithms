
import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        List points = new LinkedList();

        Arrays.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.start - o2.start;
            }
        });

//        List<Segment> segmentList = new LinkedList(Arrays.asList(segments));

        int size = segments.length;
        while (!allProcessed(segments)) {
//            List<Integer> intersectingIndex = new LinkedList<>();
            Segment curSegment = segments[firstUnprocessed(segments,0)];
            for (int j = 0; j < segments.length; j++) {
                int firstUnprocessed = firstUnprocessed(segments,j);
                if(firstUnprocessed==-1){
                    break;
                }
                Segment checkSegment = segments[firstUnprocessed];
                if (!checkSegment.processed) {
                    if (isIntersect(curSegment, checkSegment)) {
                        curSegment.start = Integer.max(curSegment.start, checkSegment.start);
                        curSegment.end = Integer.min(curSegment.end, checkSegment.end);
                        checkSegment.processed = true;
//                    intersectingIndex.add(j);
                    } else {
                        break;
                    }
                }
            }
            points.add(curSegment.start);
//            for (int i = 0; i < intersectingIndex.size(); i++) {
//                segmentList.remove((int) intersectingIndex.get(i));
//            }
//            intersectingIndex.forEach(e -> segmentList.remove((int) e));
        }

        return points.stream().mapToInt(p -> (Integer) p).toArray();
    }

    private static int firstUnprocessed(Segment[] segments, int index){
        for(int i = index; i < segments.length; i ++){
            if(!segments[i].processed){
                return i;
            }
        }
        return -1;
    }

    private static boolean allProcessed(Segment[] segments){
        for(int i = 0;i < segments.length;i++){
            if(!segments[i].processed){
                return false;
            }
        }
        return true;
    }

    private static boolean isIntersect(Segment cur, Segment check) {
        return cur.end >= check.start;
    }

    private static class Segment {
        int start, end;
        boolean processed = false;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
