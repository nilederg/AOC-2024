package Library;

import java.util.Arrays;

/**
 * An immutable class representing a point in N-space, with integer values.
 */
public class PointN {
    public final long[] coordinates;

    public PointN(long... coords) {
        this.coordinates = coords;
    }

    public PointN ZERO() {
        long[] coords = new long[this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            coords[i] = 0;
        }
        return new PointN(coords);
    }

    public Point2 toPoint2() {
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Dimensions must be 2");
        }
        return new Point2(coordinates[0], coordinates[1]);
    }

    public Point3 toPoint3() {
        if (coordinates.length != 3) {
            throw new IllegalArgumentException("Dimensions must be 3");
        }
        return new Point3(coordinates[0], coordinates[1], coordinates[2]);
    }

    /**
     * Adds any number of vectors to this point.
     * @param vectors The vectors to add to this point.
     * @return A new point, moved by the given vectors.
     */
    public PointN add(VectorN... vectors) {
        VectorN vector = VectorN.sum(vectors);
        long[] sums = new long[this.coordinates.length];
        for (int i = 0; i < this.coordinates.length; i++) {
            sums[i] = this.coordinates[i] + vector.coordinates[i];
        }
        return new PointN(sums);
    }

    /**
     * Subtracts any number of vectors from this point.
     * @param vectors The vectors to subtract from this point.
     * @return A new point, moved by the inverse of the given vectors.
     */
    public PointN subtract(VectorN... vectors) {
        VectorN vector = VectorN.sum(vectors);
        return add(vector.inverse());
    }

    public PointN(PointN p) {
        this.coordinates = Arrays.copyOf(p.coordinates, p.coordinates.length);
    }

    public PointN(VectorN v) {
        this.coordinates = Arrays.copyOf(v.coordinates, v.coordinates.length);
    }

    private void verifyEqualDimensionality(PointN p) {
        if (this.coordinates.length != p.coordinates.length) {
            throw new IllegalArgumentException("Coordinate dimensions do not match");
        }
    }

    private void verifyEqualDimensionality(VectorN v) {
        if (this.coordinates.length != v.coordinates.length) {
            throw new IllegalArgumentException("Coordinate dimensions do not match");
        }
    }

    @Override
    public PointN clone() {
        return new PointN(this);
    }

    @Override
    public String toString() {
        return (new VectorN(coordinates)).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PointN pt) {
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
