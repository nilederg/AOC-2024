package Library;

import java.lang.reflect.Array;

/**
 * An immutable class representing a point in 3-space, with integer values.
 */
public class Point3 {
    public final long x;
    public final long y;
    public final long z;

    public static Point3 ZERO = new Point3(0, 0, 0);

    public Point3(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds any number of vectors to this point.
     * @param vectors The vectors to add to this point.
     * @return A new point, moved by the given vectors.
     */
    public Point3 add(Vector3... vectors) {
        Vector3 vector = Vector3.sum(vectors);
        long sumX = this.x + vector.x;
        long sumY = this.y + vector.y;
        long sumZ = this.z + vector.z;
        return new Point3(sumX, sumY, sumZ);
    }

    /**
     * Subtracts any number of vectors from this point.
     * @param vectors The vectors to subtract from this point.
     * @return A new point, moved by the inverse of the given vectors.
     */
    public Point3 subtract(Vector3... vectors) {
        Vector3 vector = Vector3.sum(vectors);
        return add(vector.inverse());
    }

    public Point3(Point3 p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Point3(Vector3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public <T> T getValue(T[][][] array) {
        try {
            return array[(int)this.y][(int) this.x][(int) this.z];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getValue(int[][][] array) {
        return array[(int)this.y][(int) this.x][(int) this.z];
    }

    public long getValue(long[][][] array) {
        return array[(int)this.y][(int)this.x][(int)this.z];
    }

    public <T> void setValue(T[][][] array, T value) {
        array[(int)this.y][(int) this.x][(int) this.z] = value;
    }

    public void setValue(int[][][] array, int value) {
        array[(int)this.y][(int) this.x][(int) this.z] = value;
    }

    public void setValue(long[][][] array, long value) {
        array[(int)this.y][(int)this.x][(int)this.z] = value;
    }

    @Override
    public Point3 clone() {
        return new Point3(this);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + ",z=" + z + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point3 pt) {
            return (x == pt.x) && (y == pt.y) && (z == pt.z);
        }
        return super.equals(obj);
    }
}
