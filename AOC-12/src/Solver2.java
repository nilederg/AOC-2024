import Library.*;
import Management.Input;

import java.util.Arrays;
import java.util.HashMap;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        char[][] data = input.getCharGrid();
        Character[][] map = new Character[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                map[i][j] = data[i][j];
            }
        }
        println(Arrays.deepToString(map));
        Boolean[][] visited = new Boolean[data.length][data[0].length];
        Miscellaneous.fill(visited, Boolean.FALSE);
        int total = 0;
        while(true) {
            int posI = -1;
            int posJ = -1;
            checkNext: for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (!visited[i][j]) {
                        posI = i;
                        posJ = j;
                        break checkNext;
                    }
                }
            }
            if (posI == -1) {
                break;
            }
            Boolean[][] region = new Boolean[data.length][data[0].length];
            HashMap<Vector2, Boolean>[][] perimeter = new HashMap[data.length][data[0].length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    perimeter[i][j] = new HashMap<>();
                    for (Vector2 v : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
                        perimeter[i][j].put(v, Boolean.FALSE);
                    }
                }
            }
            Miscellaneous.fill(region, Boolean.FALSE);
            region[posI][posJ] = true;
            Container<Integer> sum = new Container<>(0);
            int area = floodFill(region, map, new ArrayPoint<Character>(posI, posJ, map), perimeter) + 1;
            //println(Arrays.deepToString(region));
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (region[i][j]) {
                        visited[i][j] = true;
                    }
                }
            }
            int sides = 0;
            while (true) {
                int edgeI = -1;
                int edgeJ = -1;
                Vector2 edgeVector = null;
                checkNext: for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[0].length; j++) {
                        for (Vector2 v : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
                            if (perimeter[i][j].get(v)) {
                                edgeI = i;
                                edgeJ = j;
                                edgeVector = v;
                                break checkNext;
                            }
                        }
                    }
                }
                if (edgeI == -1) {
                    break;
                }
                boolean horizontal = (edgeVector.x == 0);
                ArrayPoint<HashMap<Vector2, Boolean>> point = new ArrayPoint<>(edgeI, edgeJ, perimeter);
                point.getValue().put(edgeVector, Boolean.FALSE);
                Vector2 traverse = null;
                if (horizontal) {
                    traverse = new Vector2(1, 0);
                } else {
                    traverse = new Vector2(0, 1);
                }
                ArrayPoint<HashMap<Vector2, Boolean>> movePoint = new ArrayPoint<>(edgeI, edgeJ, perimeter).add(traverse);
                while (movePoint != null && movePoint.getValue().get(edgeVector) == Boolean.TRUE) {
                    movePoint.getValue().put(edgeVector, Boolean.FALSE);
                    movePoint = movePoint.add(traverse);
                }
                movePoint = new ArrayPoint<>(edgeI, edgeJ, perimeter).subtract(traverse);
                while (movePoint != null && movePoint.getValue().get(edgeVector) == Boolean.TRUE) {
                    movePoint.getValue().put(edgeVector, Boolean.FALSE);
                    movePoint = movePoint.subtract(traverse);
                }
                sides++;
            }
            //println(perimeterSum, " ", sum.get());
            println(area, " ", sides);
            total += area * sides;
        }
        return total;
    }

    // Perim, Area
    public static int floodFill(Boolean[][] region, Character[][] map, ArrayPoint<Character> point, HashMap<Vector2, Boolean>[][] perimeter) {
        Character plant = point.getValue(map);
        int area = 0;
        for (Vector2 neighbor : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
            ArrayPoint<Character> neighborPoint = point.add(neighbor);
            if (neighborPoint != null && neighborPoint.getValue(map) == plant) {
                if (!neighborPoint.getValue(region)) {
                    area++;
                    neighborPoint.setValue(region, true);
                    //println(neighborPoint.toString());
                    int result = floodFill(region, map, neighborPoint, perimeter);
                    area += result;
                }
            } else {
                //println("boundary from ", point, " vector ", neighbor);
                if (point.getValue(perimeter).get(neighbor)) {
                    forcePrintln("ERROR");
                }
                point.getValue(perimeter).put(neighbor, true);
            }
        }
        return area;
    }
}
