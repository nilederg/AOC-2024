package Library;

import java.awt.geom.Point2D;

public class Miscellaneous {
    /**
     * Finds the point with the least sum distance to all given points, accurate to within the given precision.
     * @param points The set of points to find the geometric median of.
     * @param precision The approximate maximum distance acceptable between the returned value and the theoretical true median.
     * @return The approximate geometric median of the set of points.
     */
    public static Point2D.Float geometricMedian(Point2D.Float[] points, float precision) {
        if (points.length == 0) {
            throw new IllegalArgumentException("Point array cannot have length zero.");
        }
        if (points.length == 1) {
            return points[0];
        }
        // Get average point to start geometric median approximation
        float sumX = 0, sumY = 0;
        for (Point2D.Float point : points) {
            sumX += point.x;
            sumY += point.y;
        }
        if (points.length == 2) {
            return new Point2D.Float(sumX / points.length, sumY / points.length);
        }
        final Point2D.Float mean = new Point2D.Float(sumX / points.length, sumY / points.length);
        // samples[0] = center point (starts at mean)
        Point2D.Float[] samples = new Point2D.Float[] {mean, new Point2D.Float(), new Point2D.Float(), new Point2D.Float(), new Point2D.Float()};
        // Start with precision equal to the greatest distance between any point and the mean
        float currentPrecision = Float.MIN_VALUE;
        for (Point2D.Float point : points) {
            float distance = (float) point.distance(mean);
            if (distance > currentPrecision) {
                currentPrecision = distance;
            }
        }
        Point2D.Float[] offsets = new Point2D.Float[] {new Point2D.Float(0, 0), new Point2D.Float(-currentPrecision, -currentPrecision), new Point2D.Float(-currentPrecision, currentPrecision), new Point2D.Float(currentPrecision, -currentPrecision), new Point2D.Float(currentPrecision, currentPrecision)};
        Point2D.Float median;
        // Test a center point and cross of four nearby points, the best median approximation becomes the center for the next round, if the center is best, finish.
        while (true) {
            double minDistance = Double.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < offsets.length; i++) {
                Point2D.Float offset = offsets[i];
                Point2D.Float sample = new Point2D.Float(samples[0].x + offset.x, samples[0].y + offset.y);
                double sumDistance = 0;
                for (Point2D.Float point : points) {
                    sumDistance += point.distance(sample);
                }
                if (sumDistance < minDistance - 0.00000000001) {
                    minDistance = sumDistance;
                    minIndex = i;
                }
            }
            if (minIndex == 0) {
                // Use successively finer precision until needs are satisfied
                if (currentPrecision <= precision) {
                    median = samples[0];
                    break;
                }
                currentPrecision = currentPrecision / 2f;
                offsets = new Point2D.Float[] {new Point2D.Float(0, 0), new Point2D.Float(-currentPrecision, -currentPrecision), new Point2D.Float(-currentPrecision, currentPrecision), new Point2D.Float(currentPrecision, -currentPrecision), new Point2D.Float(currentPrecision, currentPrecision)};
                continue;
            }
            samples[0] = samples[minIndex];
        }
        return median;
    }

    public static char representInt(int value) {
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int modValue = value % chars.length;
        return chars[modValue];
    }

    public static long gcd(long a, long b) {
        return gcdFunct(Math.abs(a), Math.abs(b));
    }

    private static long gcdFunct(long p, long q) {
        if (q == 0) return p;
        if (p == 0) return q;

        // p and q even
        if ((p & 1L) == 0L && (q & 1L) == 0L) return gcd(p >> 1, q >> 1) << 1;

            // p is even, q is odd
        else if ((p & 1L) == 0L) return gcd(p >> 1, q);

            // p is odd, q is even
        else if ((q & 1L) == 0L) return gcd(p, q >> 1);

            // p and q odd, p >= q
        else if (p >= q) return gcd((p-q) >> 1, q);

            // p and q odd, p < q
        else return gcd(p, (q-p) >> 1);
    }
}
