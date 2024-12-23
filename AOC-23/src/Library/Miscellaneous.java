package Library;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.function.Function;

public class Miscellaneous {
    /**
     * Finds the point with the least sum distance to all given points, accurate to within the given precision.
     *
     * @param points    The set of points to find the geometric median of.
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
        Point2D.Float[] samples = new Point2D.Float[]{mean, new Point2D.Float(), new Point2D.Float(), new Point2D.Float(), new Point2D.Float()};
        // Start with precision equal to the greatest distance between any point and the mean
        float currentPrecision = Float.MIN_VALUE;
        for (Point2D.Float point : points) {
            float distance = (float) point.distance(mean);
            if (distance > currentPrecision) {
                currentPrecision = distance;
            }
        }
        Point2D.Float[] offsets = new Point2D.Float[]{new Point2D.Float(0, 0), new Point2D.Float(-currentPrecision, -currentPrecision), new Point2D.Float(-currentPrecision, currentPrecision), new Point2D.Float(currentPrecision, -currentPrecision), new Point2D.Float(currentPrecision, currentPrecision)};
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
                offsets = new Point2D.Float[]{new Point2D.Float(0, 0), new Point2D.Float(-currentPrecision, -currentPrecision), new Point2D.Float(-currentPrecision, currentPrecision), new Point2D.Float(currentPrecision, -currentPrecision), new Point2D.Float(currentPrecision, currentPrecision)};
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

    public static <T> long max(Collection<T> items, Function<T, Long> valueGetter) {
        long max = Long.MIN_VALUE;
        for (T item : items) {
            long value = valueGetter.apply(item);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static <T> long min(Collection<T> items, Function<T, Long> valueGetter) {
        long min = Long.MAX_VALUE;
        for (T item : items) {
            long value = valueGetter.apply(item);
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public static <T> long sum(Collection<T> items, Function<T, Long> valueGetter) {
        long sum = 0;
        for (T item : items) {
            long value = valueGetter.apply(item);
            sum += value;
        }
        return sum;
    }

    public static Byte[] pointerizeByte(byte[] bytes) {
        Byte[] result = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }
    public static Short[] pointerizeShort(short[] shorts) {
        Short[] result = new Short[shorts.length];
        for (int i = 0; i < shorts.length; i++) {
            result[i] = shorts[i];
        }
        return result;
    }
    public static Integer[] pointerizeInt(int[] ints) {
        Integer[] result = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++) {
            result[i] = ints[i];
        }
        return result;
    }
    public static Long[] pointerizeLong(long[] longs) {
        Long[] result = new Long[longs.length];
        for (int i = 0; i < longs.length; i++) {
            result[i] = longs[i];
        }
        return result;
    }
    public static Float[] pointerizeFloat(float[] floats) {
        Float[] result = new Float[floats.length];
        for (int i = 0; i < floats.length; i++) {
            result[i] = floats[i];
        }
        return result;
    }
    public static Double[] pointerizeDouble(double[] doubles) {
        Double[] result = new Double[doubles.length];
        for (int i = 0; i < doubles.length; i++) {
            result[i] = doubles[i];
        }
        return result;
    }
    public static Character[] pointerizeChar(char[] chars) {
        Character[] result = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[i];
        }
        return result;
    }
    public static Boolean[] pointerizeBoolean(boolean[] booleans) {
        Boolean[] result = new Boolean[booleans.length];
        for (int i = 0; i < booleans.length; i++) {
            result[i] = booleans[i];
        }
        return result;
    }

    public static Object[] deepPointerize(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("The given array is not an array.");
        }
        int depth = 1;
        Class<?> klass = null;
        Object subArray = array;
        while (true) {
            if (subArray.getClass().getComponentType().isPrimitive()) {
                klass = subArray.getClass().getComponentType();
                break;
            }
            subArray = ((Object[]) subArray)[0];
        }
        return deepPointerize(array, depth, klass);
    }

    private static Object[] deepPointerize(Object array, int depth, Class<?> klass) {
        if (depth > 1) {
            Object[] deepArray = (Object[]) array;
            Object[] output = new Object[deepArray.length];
            for (int i = 0; i < deepArray.length; i++) {
                if (!deepArray[i].getClass().isArray()) {
                    throw new IllegalArgumentException("Inconsistent array depth");
                }
                output[i] = deepPointerize(deepArray[i], depth - 1, klass);
            }
            return output;
        } else if (depth == 1) {
            if (!array.getClass().isArray()) {
                throw new IllegalArgumentException("Inconsistent array depth");
            }
            if (array instanceof byte[]) {
                if (klass != byte.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeByte((byte[]) array);
            } else if (array instanceof short[]) {
                if (klass != short.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeShort((short[]) array);
            } else if (array instanceof int[]) {
                if (klass != int.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeInt((int[]) array);
            } else if (array instanceof long[]) {
                if (klass != long.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeLong((long[]) array);
            } else if (array instanceof float[]) {
                if (klass != float.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeFloat((float[]) array);
            } else if (array instanceof double[]) {
                if (klass != double.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeDouble((double[]) array);
            } else if (array instanceof char[]) {
                if (klass != char.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeChar((char[]) array);
            } else if (array instanceof boolean[]) {
                if (klass != boolean.class) {
                    throw new IllegalArgumentException("Inconsistent internal class!");
                }
                return pointerizeBoolean((boolean[]) array);
            } else {
                throw new IllegalArgumentException("Unknown error!");
            }
        } else {
            throw new IllegalStateException("Unreachable state");
        }
    }

    public static <T> T getSingularItem(Collection<T> collection) {
        if (collection.size() != 1) {
            throw new IllegalArgumentException("The given collection does not contain exactly one element.");
        }
        return collection.iterator().next();
    }
}
