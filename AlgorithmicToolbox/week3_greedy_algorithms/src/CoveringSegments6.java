import java.util.*;
import java.util.stream.Collectors;

public class CoveringSegments6 {

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
        List<Point> allPoints = new ArrayList<>();

        //init points with 0 intersecting segments
        for (Segment s : segments) {
            int start = s.start;
            int end = s.end;

            Point p = getPoint(allPoints, start);
            if (p == null) {
                p = new Point();
                p.coordinate = start;
                allPoints.add(p);
            }
//            p.numberOfIntersectSegments++;
//            p.segments.add(s);

            p = getPoint(allPoints, end);
            if (p == null) {
                p = new Point();
                p.coordinate = end;
                allPoints.add(p);
            }
//            p.numberOfIntersectSegments++;
//            p.segments.add(s);
        }

        //update points with segments
        for (Point p : allPoints) {
            for (Segment s : segments) {
                if (s.start <= p.coordinate && s.end >= p.coordinate) {
                    p.segments.add(s);
                }
            }
        }
        Collections.sort(allPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.segments.size() > o2.segments.size()) {
                    return -1;
                } else if (o1.segments.size() == o2.segments.size()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        while (stillHasUnmarkedSegments(segments)) {
            for (Point p : allPoints) {
                if (!p.marked) {
                    result.add(p);
                    p.segments.forEach(s -> {
                        s.marked = true;
                        allPoints.stream().filter(e -> e.coordinate == s.start || e.coordinate == s.end).forEach(e -> e.marked =
                                true);
                    });
                    p.marked = true;
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
        List<CoveringSegments6.Segment> segments = new ArrayList<>();
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
 
