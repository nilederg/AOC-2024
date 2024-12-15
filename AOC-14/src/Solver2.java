import Library.FreeDimensionalList;
import Library.Point2;
import Library.Tuple;
import Library.Vector2;
import Management.Input;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();
        Tuple<Point2, Vector2>[] robots = new Tuple[data.length - 1];

        int seconds = 100;
        int width = (int) data[0][0];
        int height = (int) data[0][1];

        if (width == 11) {
            return -1;
        }

        for (int i = 1; i < data.length; i++) {
            Point2 p = new Point2(data[i][0], data[i][1]);
            Vector2 v = new Vector2(data[i][2], data[i][3]);
            robots[i-1] = new Tuple<>(p, v);
        }

        int start = 7383;

        if (start != 0) {
            for (int i = 0; i < robots.length; i++) {
                Point2 newPoint = project(robots[i], start, width, height);
                robots[i] = new Tuple<>(newPoint, robots[i].getB());
            }
        }

        Scanner scanner = new Scanner(System.in);
        int time = start;
        while (true) {
            boolean getInput = printRobots(robots, time, width, height);
            for (int i = 0; i < robots.length; i++) {
                Point2 newPoint = project(robots[i], 1, width, height);
                robots[i] = new Tuple<>(newPoint, robots[i].getB());
            }
            time ++;
            if (time % 1000 == 0) {
                forcePrintln(time);
            }
            if (!getInput) {
                continue;
            }
            System.out.print("|");
            scanner.nextLine();
        }
    }

    public static Point2 project(Tuple<Point2, Vector2> robot, long seconds, long width, long height) {
        Point2 finalUnprojectedPoint = robot.getA().add(robot.getB().multiply(seconds));
        return new Point2(mod(finalUnprojectedPoint.x, width), mod(finalUnprojectedPoint.y, height));
    }

    public static boolean printRobots(Tuple<Point2, Vector2>[] robots, int time, int width, int height) {
        /*FreeDimensionalList<Integer> robotCount = new FreeDimensionalList<>(2);
        for (Tuple<Point2, Vector2> robot : robots) {
            int x = (int) robot.getA().x;
            int y = (int) robot.getA().y;
            Integer count = robotCount.get(x, y);
            if (count == null) {
                count = 0;
            }
            if (count != 0) {
                //return false;
            }
            robotCount.set(x, y, count + 1);
        }*/

        char[][] grid = new char[(int) height][(int) width];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.';
            }
        }

        if (grid[height / 2][width / 2] == '.') {
            //return false;
        }

        boolean sample = sampleGrid(grid);
        if (!sample) {
            //return false;
        }

        for (Tuple<Point2, Vector2> robot : robots) {
            grid[(int) robot.getA().y][(int) robot.getA().x] = '#';
        }
        for (char[] chars : grid) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < chars.length; j++) {
                line.append(chars[j]);
            }
            forcePrintln(line.toString());
        }
        forcePrintln(time, " Seconds elapsed");
        return true;
    }

    public static boolean sampleGrid(char[][] grid) {
        int dist = 1;
        //grid = new Input(".....\n.....\n.....\n...##\n...##").getCharGrid();
        for (int i = 0; i < grid.length-dist; i+=1) {
            for (int j = 0; j < grid[i].length-dist; j+=1) {
                char[][] sample = new char[dist][dist];
                for (int k = 0; k < dist; k++) {
                    for (int l = 0; l < dist; l++) {
                        sample[k][l] = grid[i+k][j+l];
                    }
                }
                boolean broken = false;
                for (int k = 0; k < dist; k++) {
                    for (int l = 0; l < dist; l++) {
                        if (sample[k][l] == '.') {
                            broken = true;
                        }
                    }
                }
                boolean real = grid[i][j] == '#' && grid[i][j+1] == '#' && grid[i+1][j] == '#' && grid[i+1][j+1] == '#';
                if (!real != broken) {
                    throw new ArrayIndexOutOfBoundsException("rage");
                }
                if (!broken) {
                    return true;
                }
            }
        }
        return false;
    }

    public static long mod(long a, long b) {
        if (a < 0) {
            return mod(a + (b << 4), b);
        }
        return a % b;
    }
}
