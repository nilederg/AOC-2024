package Library;

/**
 * An immutable class representing a point in 2-space, with integer values.
 */
public class Point2 {
    public final int x;
    public final int y;

    public static Point2 ZERO = new Point2(0, 0);

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds any number of vectors to this point.
     * @param vectors The vectors to add to this point.
     * @return A new point, moved by the given vectors.
     */
    public Point2 add(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        int sumX = this.x + vector.x;
        int sumY = this.y + vector.y;
        return new Point2(sumX, sumY);
    }

    /**
     * Subtracts any number of vectors from this point.
     * @param vectors The vectors to subtract from this point.
     * @return A new point, moved by the inverse of the given vectors.
     */
    public Point2 subtract(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        return add(vector.inverse());
    }

    public Point2(Point2 p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public <T> T getValue(T[][] array) {
        return array[this.y][this.x];
    }

    public int getValue(int[][] array) {
        return array[this.y][this.x];
    }

    public <T> void setValue(T[][] array, T value) {
        array[this.y][this.x] = value;
    }

    public void setValue(int[][] array, int value) {
        array[this.y][this.x] = value;
    }

    @Override
    public Point2 clone() {
        return new Point2(this);
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
