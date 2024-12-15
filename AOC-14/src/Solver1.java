import Library.Point2;
import Library.Tuple;
import Library.Vector2;
import Management.Input;

import java.awt.*;
import java.util.Arrays;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();
        Tuple<Point2, Vector2>[] robots = new Tuple[data.length - 1];

        int seconds = 100;
        int width = (int) data[0][0];
        int height = (int) data[0][1];

        for (int i = 1; i < data.length; i++) {
            Point2 p = new Point2(data[i][0], data[i][1]);
            Vector2 v = new Vector2(data[i][2], data[i][3]);
            robots[i-1] = new Tuple<>(p, v);
        }
        println(Arrays.deepToString(robots));
        Point2[] finalRobots = new Point2[robots.length];
        for (int i = 0; i < robots.length; i++) {
            finalRobots[i] = project(robots[i], seconds, width, height);
        }
        println("Final robots: " + Arrays.toString(finalRobots));
        int[][] quadrantTotals = new int[2][2];
        int widthBoundary = (width - 1) / 2;
        int heightBoundary = (height - 1) / 2;
        for (int i = 0; i < finalRobots.length; i++) {
            Point2 p = finalRobots[i];
            if (p.x == widthBoundary || p.y == heightBoundary) {
                continue;
            }
            int widthQuadrant = p.x < widthBoundary ? 0 : 1;
            int heightQuadrant = p.y < heightBoundary ? 0 : 1;
            quadrantTotals[widthQuadrant][heightQuadrant]++;
        }
        println("Quadrants: " + Arrays.deepToString(quadrantTotals));
        return (long) quadrantTotals[0][0] * quadrantTotals[1][0] * quadrantTotals[0][1] * quadrantTotals[1][1];
    }

    public static Point2 project(Tuple<Point2, Vector2> robot, long seconds, long width, long height) {
        Point2 finalUnprojectedPoint = robot.getA().add(robot.getB().multiply(seconds));
        return new Point2(mod(finalUnprojectedPoint.x, width), mod(finalUnprojectedPoint.y, height));
    }

    public static long mod(long a, long b) {
        if (a < 0) {
            return mod(a + (b << 4), b);
        }
        return a % b;
    }
}
