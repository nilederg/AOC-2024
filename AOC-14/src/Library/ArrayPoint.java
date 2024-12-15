/*package Library;

/**
 * A point confined to the space within a 2-dimensional array.

public class ArrayPoint extends Point2 {
    public final int iSize;
    public final int jSize;

    public ArrayPoint(int i, int j, int iSize, int jSize) {
        super(j, i);
        if (y < 0 || x < 0 || y >= iSize || x >= jSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.iSize = iSize;
        this.jSize = jSize;
    }

    public static <T> ArrayPoint fromArray(int i, int j, T[][] array) {
        return new ArrayPoint(i, j, array.length, array[0].length);
    }

    public static <T> ArrayPoint fromArray(Point2 point, T[][] array) {
        return new ArrayPoint(point.y, point.x, array.length, array[0].length);
    }

    /**
     * Adds any number of vectors to this point.
     * @param vectors The vectors to add to this point.
     * @return A new point, moved by the given vectors. Null if that point would be outside the array.
     *//*
    @Override
    public ArrayPoint add(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        int sumX = this.x + vector.x;
        int sumY = this.y + vector.y;
        if (sumY < 0 || sumX < 0 || sumY >= iSize || sumX >= jSize) {
            return null;
        }
        return new ArrayPoint(sumY, sumX, iSize, jSize);
    }

    /**
     * Subtracts any number of vectors from this point.
     * @param vectors The vectors to subtract from this point.
     * @return A new point, moved by the inverse of the given vectors. Null if that point would be outside the array.
     *//*
    @Override
    public ArrayPoint subtract(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        return add(vector.inverse());
    }
}*/
