import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.math.BigInteger;
import java.util.Arrays;

import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();
        int machines = (data.length + 2) / 4;
        Vector2[][] points = new Vector2[machines][];
        for (int i = 0; i < machines; i++) {
            Vector2[] machine = new Vector2[3];
            machine[0] = new Vector2((int) data[4*i][0], (int) data[4*i][1]);
            machine[1] = new Vector2((int) data[4*i+1][0], (int) data[4*i+1][1]);
            machine[2] = new Vector2(data[4*i+2][0] + 10000000000000L, data[4*i+2][1] + 10000000000000L);
            points[i] = machine;
        }
        long total = 0;
        for (int i = 0; i < machines; i++) {
            total += tokens(points[i][0], points[i][1], new Point2(points[i][2]));
        }
        return total;
    }

    public static long tokens(Vector2 shiftA, Vector2 shiftB, Point2 goal) {
        // solve for b
        println("");
        println(shiftA.x + " " + shiftA.y);
        println(shiftB.x + " " + shiftB.y);
        println(goal.x + " " + goal.y);
        BigInteger numerator = BigInteger.valueOf(shiftA.x).multiply(BigInteger.valueOf(goal.y))
                .subtract(BigInteger.valueOf(shiftA.y).multiply(BigInteger.valueOf(goal.x)));
        BigInteger denominator = BigInteger.valueOf(shiftA.x).multiply(BigInteger.valueOf(shiftB.y))
                .subtract(BigInteger.valueOf(shiftA.y).multiply(BigInteger.valueOf(shiftB.x)));
        if (numerator.signum() == -1) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        if (denominator.signum() == -1) {
            println("failB signum");
            return 0;
        }
        println(numerator, " / ", denominator);
        if (!numerator.mod(denominator).equals(BigInteger.ZERO)) {
            println("failB");
            return 0;
        }
        long b = numerator.divide(denominator).longValue();
        numerator = BigInteger.valueOf(goal.x).subtract(BigInteger.valueOf(shiftB.x).multiply(BigInteger.valueOf(b)));
        denominator = BigInteger.valueOf(shiftA.x);
        if (numerator.signum() == -1) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        if (denominator.signum() == -1) {
            println("failA signum");
            return 0;
        }
        println(numerator, " / ", denominator);
        if (!numerator.mod(denominator).equals(BigInteger.ZERO)) {
            println("failA");
            return 0;
        }
        long a = numerator.divide(denominator).longValue();
        //if (a > 100 || b > 100) {
        //    throw new ArrayIndexOutOfBoundsException("what");
        //}
        if (!Point2.ZERO.add(shiftA.multiply(a), shiftB.multiply(b)).equals(goal)) {
            throw new ArrayIndexOutOfBoundsException("what2");
        }
        println("return");
        return 3 * a + 1 * b;
    }
}
