import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class CoveringSegments4 {
    private static Segment[] remainingSegments;
    private static List<Long> resultPoints = new ArrayList<>();

    private static void optimalPoints(Segment[] segments) throws Exception {

        ArrayList<Segment> segmentsList = new ArrayList<>();
        for(int i = 0; i<segments.length;i++){
            segmentsList.add(segments[i]);
        }

        Collections.sort(segmentsList, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start < o2.start) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        if (segmentsList != null && segments.length > 0) {
            long start = segmentsList.get(0).start;
            long end = segmentsList.get(0).end;
            long targetPoint = start;
            int targetCount = 0;
            //find the point has the most crossing segment
            for (long i = start; i <= end; i++) {
                int count = 0;
                for (Segment segment : segmentsList) {
                    if (segment.start <= i && segment.end >= i) {
                        count++;
                    }
                }
                if (count > targetCount) {
                    targetPoint = i;
                    targetCount = count;
                }
            }
            if(resultPoints.contains(targetPoint)){
                StringBuffer sb = new StringBuffer();
                resultPoints.forEach(e->sb.append(e));
                throw new Exception("duplicate, targetPoint="+targetPoint+",existing points "+sb.toString());
            }

            resultPoints.add(targetPoint);

            List<Segment> crossingList = new ArrayList<>();
            for (Segment segment : segmentsList) {
                if (segment.start <= targetPoint && segment.end >= targetPoint) {
                    crossingList.add(segment);
                }
            }

            segmentsList.removeAll(crossingList);

            if(segmentsList!=null&& segmentsList.size()>0){

                remainingSegments = new Segment[segmentsList.size()];
                for (int i = 0; i < segmentsList.size(); i++) {
                    remainingSegments[i] = segmentsList.get(i);
                }
                optimalPoints(remainingSegments);
            }
        }

    }

    private static class Segment {
        long start, end;

        Segment(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            long start, end;
            start = scanner.nextLong();
            end = scanner.nextLong();
            segments[i] = new Segment(start, end);
        }

        optimalPoints(segments);

        long[] points = new long[resultPoints.size()];
        for(int i = 0; i<points.length;i++){
            points[i]=resultPoints.get(i);
        };

        System.out.println(points.length);
        for (long point : points) {
            System.out.print(point + " ");
        }
    }
}
 
