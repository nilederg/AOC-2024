package Library;

/**
 * An immutable class representing a vector of 2-space, with integer values.
 */
public class Vector2 {
    public final int x;
    public final int y;

    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2[] VON_NEUMANN_NEIGHBORHOOD = new Vector2[]{
            new Vector2(1, 0),
            new Vector2(-1, 0),
            new Vector2(0, 1),
            new Vector2(0, -1),
    };
    public static final Vector2[] MOORE_NEIGHBORHOOD = new Vector2[]{
            new Vector2(-1, -1),
            new Vector2(0, -1),
            new Vector2(1, -1),
            new Vector2(-1, 0),
            new Vector2(1, 0),
            new Vector2(-1, 1),
            new Vector2(0, 1),
            new Vector2(1, 1)
    };

    /**
     * Creates a new vector based on its x and y components.
     * @param x The vector's x component.
     * @param y The vector's y component.
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the inverse of this vector.
     * @return This vector, pointing in the opposite direction.
     */
    public Vector2 inverse() {
        return new Vector2(-x, -y);
    }

    /**
     * Returns the vector pointing from one point to another.
     * @param from The point to point from. If you added the resultant vector to this, you would get the other point.
     * @param to The point to point to. If you subtracted the resultant vector from this, you would get the other point.
     * @return A vector which points from 'from' to 'to'.
     */
    public static Vector2 fromPoints(Point2 from, Point2 to) {
        return new Vector2(to.x - from.x, to.y - from.y);
    }

    /**
     * Finds the sum of a number of vectors.
     * This function is pure.
     * @param vectors The vectors to find the sum of
     * @return The sum of the input vectors.
     */
    public static Vector2 sum(Vector2... vectors) {
        int sumX = 0;
        int sumY = 0;
        for (Vector2 vector : vectors) {
            sumX += vector.x;
            sumY += vector.y;
        }
        return new Vector2(sumX, sumY);
    }

    public Vector2 multiply(int scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2(Point2 p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public Vector2 clone() {
        return new Vector2(this);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point2 pt) {
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }
}
