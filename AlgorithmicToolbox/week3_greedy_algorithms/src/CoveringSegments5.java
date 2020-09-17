import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CoveringSegments5 {

    /*
    sort points according to the number of crossing segment descending
    remove segments crossing point[0]
    optimalPoints(remainingSegment)
    */

    private static List<Segment> segmentsArrToList(Segment[] segments) {
        List<Segment> segmentList = new ArrayList<>();
        for(int i = 0; i < segments.length;i++){
            segmentList.add(segments[i]);
        }
        return segmentList;
    }

    private static Segment[] segmentsListToArr(List<Segment> segments){
        Segment[] segmentArr = new Segment[segments.size()];
        for(int i = 0; i<segments.size();i++){
            segmentArr[i]=segments.get(i);
        }
        return  segmentArr;
    }

    private static Segment[] removeSegments(Segment[] segments,Segment[] removeSegments){
        List<Segment> segmentList = segmentsArrToList(segments);
        List<Segment> removeSegmentList = segmentsArrToList(removeSegments);
        segmentList.removeAll(removeSegmentList);
        return segmentsListToArr(segmentList);
    }

    private static boolean pointExists(int coord, List<Point> points){
        return points.stream().filter(e->e.coord==coord).collect(Collectors.toList()).size()>0;
    }

    private static int[] optimalPoints(Segment[] segments) {
        if(segments==null||segments.length==0){
            return null;
        }
        List<Point> points = new ArrayList<>();
        for(int i=0;i<segments.length;i++){
            if(!pointExists(segments[i].start,points)){
                Point point = new Point();
                point.coord = segments[i].start;
                points.add(point);
            }
            if(!pointExists(segments[i].end,points)){
                Point point = new Point();
                point.coord = segments[i].end;
                points.add(point);
            }
        }

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.coord>o2.coord){
                    return 1;
                } else if(o1.coord<o2.coord){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for(int i = 0; i < points.size();i++){
            Point point = points.get(i);

            for(CoveringSegments5.Segment s: segments){

                if(point.coord>=s.start && point.coord<=s.end){
                    point.crossingSegment.add(s);
                }
                if(point.coord<s.start){
                    break;
                }
            }
        }

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.crossingSegment.size()>o2.crossingSegment.size()){
                    return -1;
                } else if(o1.crossingSegment.size()<o2.crossingSegment.size()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        segments = removeSegments(segments,segmentsListToArr(points.get(0).crossingSegment));

        return combinePoint(points.get(0).coord,optimalPoints(segments));
    }

    private static int[] combinePoint(int point, int[] optimalPoints) {
        int len = optimalPoints==null?0:optimalPoints.length;
        int[] points = new int[len+1];
        points[0]=point;
        for(int i=0; i<len;i++){
            points[i+1]=optimalPoints[i];
        }
        return points;
    }

    private static class Point{
        int coord;
        List<Segment> crossingSegment = new ArrayList<>();

    }

    private static class Segment implements Comparable<Segment>{
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            if(start > o.start){
                return 1;
            } else if(start < o.start){
                return -1;
            } else {
                return 0;
            }
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

        Arrays.sort(segments);

        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }

}
 
