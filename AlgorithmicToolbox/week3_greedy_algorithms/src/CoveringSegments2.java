import javax.swing.text.Segment;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoveringSegments2 {

    private static Point getPoint(List<Point> points, int coordinate) {

        List<Point> result = points.stream().filter(p -> p.coordinate == coordinate).collect(Collectors.toList());
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    private static boolean stillHasUnmarkedSegments(Segment[] segments) {
        return Arrays.stream(segments).filter(s -> s.marked == false).collect(Collectors.toList()).size() > 0;
    }

    private static int[] optimalPoints(Segment[] segments) {
        //key = point coordinate
        //value = point
        int count = 0;
        List<Point> result = new ArrayList<>();
        Map<Integer,Point> allPoints = new HashMap<>();

        //init points with 0 intersecting segments
        for (Segment s : segments) {
            int start = s.start;
            int end = s.end;

            Point p = allPoints.get(start);
            if (p == null) {
                p = new Point();
                p.coordinate = start;
                allPoints.put(p.coordinate,p);
            }
//            p.numberOfIntersectSegments++;
//            p.segments.add(s);

            p = allPoints.get(end);
            if (p == null) {
                p = new Point();
                p.coordinate = end;
                allPoints.put(p.coordinate,p);
            }
//            p.numberOfIntersectSegments++;
//            p.segments.add(s);
        }

        //update points with segments
        for(Segment s: segments){
            for(int i =s.start; i<=s.end; i++){
                if(allPoints.get(i)!=null){
                    allPoints.get(i).segments.add(s);
                }
            }
//            allPoints.get(s.end).segments.add(s);
//            allPoints.get(s.start).segments.add(s);
        }

        allPoints = allPoints.entrySet().stream().sorted((o1, o2)->{
            if(o1.getValue().segments.size()<o2.getValue().segments.size()){
                return 1;
            } else if(o1.getValue().segments.size()>o2.getValue().segments.size()){
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toMap(e->e.getKey(),e->e.getValue(),(e1,e2)->e1,LinkedHashMap::new));

        while (stillHasUnmarkedSegments(segments)) {
            for (Map.Entry<Integer,Point> p: allPoints.entrySet()) {
                if (!p.getValue().marked) {
                    result.add(p.getValue());
                    for (Segment s : p.getValue().segments) {
                        s.marked = true;
                        allPoints.get(s.start).marked = true;
                        allPoints.get(s.end).marked = true;
                    }
                    p.getValue().marked = true;
                    count++;
                }
            }
        }

        int[] resultPoints = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultPoints[i] = result.get(i).coordinate;
        }
        return resultPoints;
    }

    private static class Segment {
        int start, end;
        boolean marked = false;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
            marked = false;
        }
    }

    private static class Point {
        int coordinate;
        List<CoveringSegments2.Segment> segments = new ArrayList<>();
        boolean marked = false;

        public Point() {
        }

        public Point(int coordinate) {
            this.coordinate = coordinate;
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

/*
pseudo codes:
class Point {
    int coordinate;
    int intersectPoints;
}
store all starting and ending points in array points, sort by intersectPoints in descending order
while (segments is not empty) {
    remove all segments has points[0];
    remove points[0];
    count++;
}
return count;

 */
 
