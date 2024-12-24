package Library;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * An immutable class representing a vector of N-space, with integer values.
 */
public class VectorN {
    public final long[] coordinates;

    public VectorN ZERO() {
        long[] newCoordinates = new long[coordinates.length];
        return new VectorN(newCoordinates);
    }
    public static Collection<VectorN> VON_NEUMANN_NEIGHBORHOOD(int dimensions) {
        ArrayList<VectorN> neighborhood = new ArrayList<>(dimensions * 2);
        for (int i = 0; i < dimensions; i++) {
            long[] coordinates = new long[dimensions];
            coordinates[i] = -1;
            neighborhood.add(new VectorN(coordinates));
            coordinates[i] = 1;
            neighborhood.add(new VectorN(coordinates));
        }
        return neighborhood;
    }
    public static Collection<VectorN> MOORE_NEIGHBORHOOD(int dimensions) {
        ArrayList<VectorN> neighborhood = new ArrayList<>(Maths.pow(3, dimensions) - 1);
        long[] coordinates = new long[dimensions];
        Arrays.fill(coordinates, -1);
        while (true) {
            boolean center = true;
            for (int i = 0; i < dimensions; i++) {
                if (coordinates[i] != 0) {
                    center = false;
                }
            }
            if (center) {
                continue;
            }
            neighborhood.add(new VectorN(coordinates));
            boolean finish = true;
            for (int i = 0; i < dimensions; i++) {
                if (coordinates[i] != 1) {
                    finish = false;
                    coordinates[i] ++;
                    break;
                } else {
                    coordinates[i] = -1;
                }
            }
            if (finish) {
                break;
            }
        }
        return neighborhood;
    }

    /**
     * Creates a new vector based on its coordinates.
     */
    public VectorN(long... coords) {
        this.coordinates = coords;
    }

    /**
     * Returns the inverse of this vector.
     * @return This vector, pointing in the opposite direction.
     */
    public VectorN inverse() {
        long[] newCoordinates = new long[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = coordinates[i] * -1;
        }
        return new VectorN(newCoordinates);
    }

    /**
     * Returns the vector pointing from one point to another.
     * @param from The point to point from. If you added the resultant vector to this, you would get the other point.
     * @param to The point to point to. If you subtracted the resultant vector from this, you would get the other point.
     * @return A vector which points from 'from' to 'to'.
     */
    public static VectorN fromPoints(PointN from, PointN to) {
        if (from.coordinates.length != to.coordinates.length) {
            throw new IllegalArgumentException("Points must have equivalent dimensionality");
        }
        long[] newCoordinates = new long[to.coordinates.length];
        for (int i = 0; i < to.coordinates.length; i++) {
            newCoordinates[i] = to.coordinates[i] - from.coordinates[i];
        }
        return new VectorN(newCoordinates);
    }

    /**
     * Finds the sum of a number of vectors.
     * This function is pure.
     * @param vectors The vectors to find the sum of
     * @return The sum of the input vectors.
     */
    public static VectorN sum(VectorN... vectors) {
        long[] sumCoordinates = new long[vectors.length];
        for (VectorN vector : vectors) {
            for (int i = 0; i < vector.coordinates.length; i++) {
                sumCoordinates[i] = sumCoordinates[i] + vector.coordinates[i];
            }
        }
        return new VectorN(sumCoordinates);
    }

    public VectorN multiply(long scalar) {
        long[] newCoordinates = new long[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = coordinates[i] * scalar;
        }
        return new VectorN(newCoordinates);
    }

    public VectorN(VectorN v) {
        this(Arrays.copyOf(v.coordinates, v.coordinates.length));
    }

    public VectorN(PointN p) {
        this(Arrays.copyOf(p.coordinates, p.coordinates.length));
    }

    @Override
    public VectorN clone() {
        return new VectorN(this);
    }

    @Override
    public String toString() {
        final char[] lowDimensionsLabels = "xyzwvu".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates.length <= lowDimensionsLabels.length) {
                stringBuilder.append(lowDimensionsLabels[i]);
            } else {
                stringBuilder.append("x");
                stringBuilder.append(i + 1);
            }
            stringBuilder.append("=");
            stringBuilder.append(coordinates[i]);
            if (i < coordinates.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VectorN pt) {
            boolean match = true;
            for (int i = 0; i < coordinates.length; i++) {
                if (coordinates[i] != pt.coordinates[i]) {
                    match = false;
                }
            }
            return match;
        }
        return super.equals(obj);
    }
}
