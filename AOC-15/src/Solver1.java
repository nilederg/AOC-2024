import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.util.Arrays;
import java.util.Objects;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        String[] lines = input.getLines();
        int height = 0;
        while (!Objects.equals(lines[height], "")) {
            height++;
        }
        int width = lines[0].length();
        int orderCount = (lines.length - height - 1) * lines[height + 1].length();
        Vector2[] orders = new Vector2[orderCount];
        int index = 0;
        for (int i = height + 1; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                switch (line[j]) {
                    case '>':
                        orders[index] = new Vector2(1, 0);
                        break;
                    case '<':
                        orders[index] = new Vector2(-1, 0);
                        break;
                    case 'v':
                        orders[index] = new Vector2(0, 1);
                        break;
                    case '^':
                        orders[index] = new Vector2(0, -1);
                        break;
                }
                index++;
            }
        }

        Tile[][] map = new Tile[height][width];
        Point2 robot = null;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = lines[i].charAt(j);
                switch (c) {
                    case '#':
                        map[i][j] = Tile.Wall;
                        break;
                    case 'O':
                        map[i][j] = Tile.Box;
                        break;
                    case '.':
                        map[i][j] = Tile.Empty;
                        break;
                    case '@': {
                        robot = new Point2(i, j);
                        map[i][j] = Tile.Empty;
                        break;
                    }
                }
            }
        }
        if (robot == null) {
            throw new RuntimeException("No robot found");
        }

        println(Arrays.deepToString(map));
        println(robot);

        for (Vector2 order : orders) {
            println(order, "order");
            Point2 pushIn = robot.add(order);
            println(pushIn, "pushIn");
            int boxes = 0;
            boolean possible = true;
            CheckBox: while (true) {
                switch (pushIn.getValue(map)) {
                    case Tile.Wall: {
                        possible = false;
                        break CheckBox;
                    }
                    case Tile.Box: {
                        pushIn = pushIn.add(order);
                        boxes++;
                        break;
                    }
                    case Tile.Empty: {
                        break CheckBox;
                    }
                }
            }
            if (possible) {
                robot = robot.add(order);
                if (boxes != 0) {
                    robot.setValue(map, Tile.Empty);
                    robot.add(order.multiply(boxes)).setValue(map, Tile.Box);
                }
            }
            println(Arrays.deepToString(map));
            println(robot);
        }



        long total = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Tile tile = map[i][j];
                if (tile == Tile.Box) {
                    total += 100L * i + j;
                }
            }
        }

        return total;
    }
}
