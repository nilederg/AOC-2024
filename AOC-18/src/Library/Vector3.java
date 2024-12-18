package Library;

/**
 * An immutable class representing a vector of 2-space, with integer values.
 */
public class Vector3 {
    public final long x;
    public final long y;
    public final long z;

    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    public static Vector3[] ORTHOGONAL_NEIGHBORS = new Vector3[]{
            new Vector3(-1, 0, 0),
            new Vector3(0, -1, 0),
            new Vector3(0, 0, -1),
            new Vector3(1, 0, 0),
            new Vector3(0, 1, 0),
            new Vector3(0, 0, 1),
    };

    /**
     * Creates a new vector based on its x and y components.
     * @param x The vector's x component.
     * @param y The vector's y component.
     */
    public Vector3(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns the inverse of this vector.
     * @return This vector, pointing in the opposite direction.
     */
    public Vector3 inverse() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Returns the vector pointing from one point to another.
     * @param from The point to point from. If you added the resultant vector to this, you would get the other point.
     * @param to The point to point to. If you subtracted the resultant vector from this, you would get the other point.
     * @return A vector which points from 'from' to 'to'.
     */
    public static Vector3 fromPoints(Point3 from, Point3 to) {
        return new Vector3(to.x - from.x, to.y - from.y, to.z - from.z);
    }

    /**
     * Finds the sum of a number of vectors.
     * This function is pure.
     * @param vectors The vectors to find the sum of
     * @return The sum of the input vectors.
     */
    public static Vector3 sum(Vector3... vectors) {
        long sumX = 0;
        long sumY = 0;
        long sumZ = 0;
        for (Vector3 vector : vectors) {
            sumX += vector.x;
            sumY += vector.y;
            sumZ += vector.z;
        }
        return new Vector3(sumX, sumY, sumZ);
    }

    public Vector3 multiply(long scalar) {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public Vector3(Vector3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3(Point3 p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    @Override
    public Vector3 clone() {
        return new Vector3(this);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point3 pt) {
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }
}
