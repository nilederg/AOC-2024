package Library;

public class ArrayPoint <T> extends Point2 {
    public final T[][] array;

    /**
     * @param array The array (Accessible at [y][x]) within which this point exists
     */
    public ArrayPoint(int x, int y, T[][] array) {
        super(x, y);
        if (y < 0 || x < 0 || y >= array.length || x >= array[0].length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.array = array;
    }

    public ArrayPoint(Point2 point, T[][] array) {
        super(point);
        if (y < 0 || x < 0 || y >= array.length || x >= array[0].length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.array = array;
    }

    /**
     * Adds any number of vectors to this point.
     * @param vectors The vectors to add to this point.
     * @return A new point, moved by the given vectors.
     */
    @Override
    public Point2 add(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        int sumX = this.x + vector.x;
        int sumY = this.y + vector.y;
        if (sumY < 0 || sumX < 0 || sumY >= array.length || sumX >= array[0].length) {
            return null;
        }
        return new Point2(sumX, sumY);
    }

    /**
     * Subtracts any number of vectors from this point.
     * @param vectors The vectors to subtract from this point.
     * @return A new point, moved by the inverse of the given vectors.
     */
    @Override
    public Point2 subtract(Vector2... vectors) {
        Vector2 vector = Vector2.sum(vectors);
        return add(vector.inverse());
    }

    public T getValue() {
        return array[y][x];
    }

    public void setValue(T value) {
        array[y][x] = value;
    }
}
