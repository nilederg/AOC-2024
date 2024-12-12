import Library.*;
import Management.Input;

import java.util.Arrays;

import static Management.IO.println;

public class Solver1 {
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
            Miscellaneous.fill(region, Boolean.FALSE);
            region[posI][posJ] = true;
            Tuple<Integer, Integer> resultRaw = floodFill(region, map, new ArrayPoint<Character>(posI, posJ, map));
            Tuple<Integer, Integer> result = new Tuple<>(resultRaw.getA(), resultRaw.getB() + 1);
            //println(Arrays.deepToString(region));
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (region[i][j]) {
                        visited[i][j] = true;
                    }
                }
            }
            println(result, ", ", map[posI][posJ]);
            total += result.getA() * result.getB();
        }
        return total;
    }

    // Perim, Area
    public static Tuple<Integer, Integer> floodFill(Boolean[][] region, Character[][] map, ArrayPoint<Character> point) {
        Character plant = point.getValue(map);
        int perimeter = 0;
        int area = 0;
        for (Vector2 neighbor : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
            ArrayPoint<Character> neighborPoint = point.add(neighbor);
            if (neighborPoint != null && neighborPoint.getValue(map) == plant) {
                if (!neighborPoint.getValue(region)) {
                    area++;
                    neighborPoint.setValue(region, true);
                    //println(neighborPoint.toString());
                    Tuple<Integer, Integer> result = floodFill(region, map, neighborPoint);
                    perimeter += result.getA();
                    area += result.getB();
                }
            } else {
                //println("boundary from ", point, " vector ", neighbor);
                perimeter++;
            }
        }
        return new Tuple<>(perimeter, area);
    }
}
