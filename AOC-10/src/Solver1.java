import Library.Point2;
import Library.Vector2;
import Management.IO;
import Management.Input;

import java.util.ArrayList;

public class Solver1 {
    public static long Evaluate(Input input) {
        char[][] data = input.getCharGrid();
        int[][] topoMap = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                topoMap[i][j] = Integer.parseInt(String.valueOf(data[i][j]));
            }
        }

        int total = 0;
        for (int y = 0; y < topoMap.length; y++) {
            for (int x = 0; x < topoMap[0].length; x++) {
                Point2 point = new Point2(x, y);
                boolean lowest = true;
                /*for (Vector2 neighbor : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
                    Point2 neighborPoint = point.add(neighbor);
                    if (neighborPoint.x < 0 || neighborPoint.y < 0 || neighborPoint.x >= topoMap[0].length || neighborPoint.y >= topoMap.length) {
                        continue;
                    }
                    if (neighborPoint.getValue(topoMap) == point.getValue(topoMap) - 1) {
                        lowest = false;
                        break;
                    }
                }*/
                if (point.getValue(topoMap) == 0) {
                    total += findNines(topoMap, point).size();
                }
            }
        }

        return total;
    }

    public static ArrayList<Point2> findNines(int[][] topoMap, Point2 head) {
        ArrayList<Point2> points = new ArrayList<>();
        for (Vector2 neighbor : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
            Point2 neighborPoint = head.add(neighbor);
            if (neighborPoint.x < 0 || neighborPoint.y < 0 || neighborPoint.x >= topoMap[0].length || neighborPoint.y >= topoMap.length) {
                continue;
            }
            if (neighborPoint.getValue(topoMap) == head.getValue(topoMap) + 1) {
                if (neighborPoint.getValue(topoMap) == 9) {
                    points.add(neighborPoint);
                } else {
                    points.addAll(findNines(topoMap, neighborPoint));
                }
                continue;
            }
        }
        //IO.println(points);
        ArrayList<Point2> condensedPoints = new ArrayList<>();
        for (Point2 point : points) {
            if (!condensedPoints.contains(point)) {
                condensedPoints.add(point);
            }
        }
        return condensedPoints;
    }
}
