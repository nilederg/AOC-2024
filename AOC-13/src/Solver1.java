import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.util.Arrays;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();
        int machines = (data.length + 2) / 4;
        Vector2[][] points = new Vector2[machines][];
        for (int i = 0; i < machines; i++) {
            Vector2[] machine = new Vector2[3];
            machine[0] = new Vector2((int) data[4*i][0], (int) data[4*i][1]);
            machine[1] = new Vector2((int) data[4*i+1][0], (int) data[4*i+1][1]);
            machine[2] = new Vector2((int) data[4*i+2][0], (int) data[4*i+2][1]);
            points[i] = machine;
        }
        int total = 0;
        for (int i = 0; i < machines; i++) {
            total += tokens(points[i][0], points[i][1], new Point2(points[i][2]));
        }
        return total;
    }

    public static int tokens(Vector2 shiftA, Vector2 shiftB, Point2 goal) {
        // solve for b
        long numerator = shiftA.x  * goal.y - shiftA.y * goal.x;
        long denominator = shiftA.x * shiftB.y - shiftA.y * shiftB.x;
        float bF = (float) numerator / denominator;
        int b = (int) bF;
        if (b != bF || b < 0) {
            return 0;
        }
        numerator = goal.x - shiftB.x * b;
        denominator = shiftA.x;
        float aF = (float) numerator / denominator;
        int a = (int) aF;
        if (a != aF || a < 0) {
            return 0;
        }
        if (a > 100 || b > 100) {
            throw new ArrayIndexOutOfBoundsException("what");
        }
        if (!Point2.ZERO.add(shiftA.multiply(a), shiftB.multiply(b)).equals(goal)) {
            throw new ArrayIndexOutOfBoundsException("what2");
        }
        return 3 * a + 1 * b;
    }
}
