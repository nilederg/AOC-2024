import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        String[] lines = input.getLines();
        int height = 0;
        while (!Objects.equals(lines[height], "")) {
            height++;
        }
        int width = lines[0].length() * 2;
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
            for (int j = 0; j < width / 2; j++) {
                char c = lines[i].charAt(j);
                switch (c) {
                    case '#':
                        map[i][j * 2] = Tile.Wall;
                        map[i][j * 2 + 1] = Tile.Wall;
                        break;
                    case 'O':
                        map[i][j * 2] = Tile.Box;
                        map[i][j * 2 + 1] = Tile.Empty;
                        break;
                    case '.':
                        map[i][j * 2] = Tile.Empty;
                        map[i][j * 2 + 1] = Tile.Empty;
                        break;
                    case '@': {
                        robot = new Point2(j * 2, i);
                        map[i][j * 2] = Tile.Empty;
                        map[i][j * 2 + 1] = Tile.Empty;
                        break;
                    }
                }
            }
        }
        if (robot == null) {
            throw new RuntimeException("No robot found");
        }

        //println(Arrays.deepToString(map));
        println(robot, "robot");
        printMap(map, robot);

        for (Vector2 order : orders) {
            println(order, "order");
            Tile inWay = robot.add(order).getValue(map);
            Tile inWaySide = robot.add(order).subtract(new Vector2(1, 0)).getValue(map);
            if (robot.add(order).getValue(map) == Tile.Wall) {
                println("order failed");
                continue;
            } else if (inWay == Tile.Box || inWaySide == Tile.Box) {
                Point2 box = robot.add(order);
                if (inWaySide == Tile.Box) {
                    box = robot.add(order).subtract(new Vector2(1, 0));
                }
                boolean pushable = pushBoxWrapper(map, box, order);
                if (pushable) {
                    robot = robot.add(order);
                }
            } else {
                robot = robot.add(order);
            }
            //println(Arrays.deepToString(map));
            println(robot, "robot");
            printMap(map, robot);
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

    public static ArrayList<Point2> boxQueue = new ArrayList<>();

    public static boolean pushBoxWrapper(Tile[][] map, Point2 box, Vector2 direction) {
        if (direction.x == 0 && direction.y == 0) {
            throw new RuntimeException("what");
        }
        boolean success = pushBox(map, box, direction);
        if (success) {
            println("Pushing boxes");
            for (Point2 moveBox : boxQueue) {
                moveBox.setValue(map, Tile.Empty);
            }
            for (Point2 moveBox : boxQueue) {
                println("Box to ", moveBox.add(direction));
                moveBox.add(direction).setValue(map, Tile.Box);
            }
        }
        boxQueue = new ArrayList<>();
        return success;
    }

    public static boolean pushBox(Tile[][] map, Point2 box, Vector2 direction) {
        boxQueue.add(box);
        Point2 desiredPosA = box.add(direction);
        Point2 desiredPosB = desiredPosA.add(new Vector2(1, 0));
        if (desiredPosA.getValue(map) == Tile.Wall || desiredPosB.getValue(map) == Tile.Wall) {
            return false;
        }
        if (direction.y == 0) {
            if (direction.x == -1) {
                Point2 hit = box.subtract(new Vector2(2, 0));
                if (hit.getValue(map) == Tile.Box) {
                    return pushBox(map, hit, direction);
                } else {
                    return true;
                }
            } else {
                Point2 hit = box.add(new Vector2(2, 0));
                if (hit.getValue(map) == Tile.Box) {
                    return pushBox(map, hit, direction);
                } else {
                    return true;
                }
            }
        }
        if (desiredPosA.getValue(map) == Tile.Box) {
            return pushBox(map, desiredPosA, direction);
        }
        else if (desiredPosB.getValue(map) == Tile.Box || desiredPosA.subtract(new Vector2(1, 0)).getValue(map) == Tile.Box) {
            if (desiredPosB.getValue(map) == Tile.Box && desiredPosA.subtract(new Vector2(1, 0)).getValue(map) == Tile.Box) {
                boolean successA = pushBox(map, desiredPosB, direction);
                boolean successB = pushBox(map, desiredPosA.subtract(new Vector2(1, 0)), direction);
                return successA && successB;
            }
            Point2 hitBox = null;
            if (desiredPosB.getValue(map) == Tile.Box) {
                hitBox = desiredPosB;
            }
            if (desiredPosA.subtract(new Vector2(1, 0)).getValue(map) == Tile.Box) {
                hitBox = desiredPosA.subtract(new Vector2(1, 0));
            }
            if (hitBox == null) {
                throw new RuntimeException("what");
            }
            return pushBox(map, hitBox, direction);
        }
        else {
            return true;
        }
    }

    public static void printMap(Tile[][] map, Point2 robot) {
        for (int i = 0; i < map.length; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < map[i].length; j++) {
                char c = switch (map[i][j]) {
                    case Wall -> '#';
                    case Box -> 'O';
                    case Empty -> '.';
                };
                if (robot.y == i && robot.x == j) {
                    c = '@';
                }
                line.append(c);
            }
            println(line);
        }
    }
}
