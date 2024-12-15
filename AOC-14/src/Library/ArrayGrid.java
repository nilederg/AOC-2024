/*package Library;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * Represents a boundless grid, with a value at each whole number 2D point.
 * @param <T> The type contained in each grid pixel
 *//*
public class ArrayGrid<T> {
    //            Grid (x)  Column (y)                  x, y
    private final ArrayList<ArrayList<T>> quadrant1; // +, +
    private final ArrayList<ArrayList<T>> quadrant2; // -, +
    private final ArrayList<ArrayList<T>> quadrant3; // -, -
    private final ArrayList<ArrayList<T>> quadrant4; // +, -

    private final T defaultValue;

    public ArrayGrid(T defaultValue) {
        quadrant1 = new ArrayList<>();
        quadrant2 = new ArrayList<>();
        quadrant3 = new ArrayList<>();
        quadrant4 = new ArrayList<>();
        this.defaultValue = defaultValue;
    }

    public ArrayGrid() {
        this(null);
    }

    public T get(Point2 index) {
        Container<T> datum = getContainer(index);
        if (datum == null) {
            return defaultValue;
        } else {
            return datum.get();
        }
    }

    public T get(int x, int y) {
        return get(new Point2(x, y));
    }

    public void set(Point2 index, T value) {
        Container<T> datum = getContainer(index);
        if (datum == null) {
            ArrayList<ArrayList<T>> quadrant = getQuadrant(getQuadrantIndex(index));
            Point2 i = getIndexInQuadrant(index);
            for (int x = quadrant.size(); x <= i.x; x++) {
                quadrant.add(new ArrayList<>());
            }
            ArrayList<T> column = quadrant.get(i.x);
            for (int y = column.size(); y <= i.y; y++) {
                column.add(null);
            }
            column.set(i.y, value);
        } else {
            datum.set(value);
        }
    }

    public void set(int x, int y, T value) {
        set(new Point2(x, y), value);
    }

    public void delete(Point2 index) {
        Container<T> datum = getContainer(index);
        if (datum != null) {
            datum.set(null);
        }
    }

    public void delete(int x, int y) {
        delete(new Point2(x, y));
    }

    public boolean isDefault(Point2 index) {
        Container<T> datum = getContainer(index);
        return datum == null;
    }

    public boolean isDefault(int x, int y) {
        return isDefault(new Point2(x, y));
    }

    private Container<T> getContainer(Point2 index) {
        ArrayList<ArrayList<T>> quadrant = getQuadrant(getQuadrantIndex(index));
        Point2 i = getIndexInQuadrant(index);
        if (i.x >= quadrant.size()) {
            return null;
        }
        ArrayList<T> column = quadrant.get(i.x);
        if (i.y >= column.size()) {
            return null;
        }
        return new Container<>((T v) -> column.set(i.y, v), () -> column.get(i.y));
    }

    private ArrayList<ArrayList<T>> getQuadrant(int quad) {
        return switch (quad) {
            case 1 -> quadrant1;
            case 2 -> quadrant2;
            case 3 -> quadrant3;
            case 4 -> quadrant4;
            default -> throw new IllegalArgumentException("Invalid quadrant: " + quad);
        };
    }

    // Returns 1, 2, 3, or 4 corresponding to the quadrant the given point is in
    private int getQuadrantIndex(Point2 index) {
        int x = index.x;
        int y = index.y;
        ArrayList<ArrayList<T>> quadrant;
        if (x >= 0) {
            if (y >= 0) {
                return 1;
            } else {
                return 4;
            }
        } else {
            if (y >= 0) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    private Point2 getIndexInQuadrant(Point2 index) {
        int x = index.x;
        int y = index.y;
        if (x < 0) x = -x - 1;
        if (y < 0) y = -y - 1;
        return new Point2(x, y);
    }


    public int getLowerBoundX() {
        return getXBound(false);
    }

    public int getLowerBoundY() {
        return getYBound(false);
    }

    public int getUpperBoundX() {
        return getXBound(true);
    }

    public int getUpperBoundY() {
        return getYBound(true);
    }

    private int getXBound(boolean upper) {
        AtomicReference<Integer> value = new AtomicReference<>();
        iterateColumns((x) -> {
            if (assessColumn(x)) {
                value.set(x);
                return true;
            }
            return false;
        }, upper);
        if (value.get() != null) {
            return value.get();
        }
        throw new ArrayIndexOutOfBoundsException("This ArrayGrid is empty.");
    }

    private int getYBound(boolean upper) {
        Container<Integer> bound;
        if (upper) {
            bound = new Container<>(Integer.MIN_VALUE);
        } else {
            bound = new Container<>(Integer.MAX_VALUE);
        }
        iterateColumns((x) -> {
            int minY = -getQuadrant(getQuadrantIndex(new Point2(x, -1))).size();
            int maxY = getQuadrant(getQuadrantIndex(new Point2(x, 1))).size() - 1;
            for (int y = minY; y <= maxY; y++) {
                if (!isDefault(x, y)) {
                    if (y > bound.get()) {
                        bound.set(y);
                    }
                }
            }
            return false;
        }, upper);
        return bound.get();
    }

    private void iterateColumns(Function<Integer, Boolean> action, boolean rightToLeft) {
        int farthestNegativeX = -Math.max(quadrant2.size()-1, quadrant3.size()-1) - 1;
        int farthestPositiveX = Math.max(quadrant1.size()-1, quadrant4.size()-1);
        if (!rightToLeft) {
            for (int x = farthestNegativeX; x <= farthestPositiveX; x ++) {
                boolean finish = action.apply(x);
                if (finish) {
                    return;
                }
            }
        } else {
            for (int x = farthestPositiveX; x >= farthestNegativeX; x --) {
                boolean finish = action.apply(x);
                if (finish) {
                    return;
                }
            }
        }
    }

    // Finds if any values are in a column, given that column's x value
    private boolean assessColumn(int x) {
        ArrayList<ArrayList<T>> quadrantA;
        ArrayList<ArrayList<T>> quadrantB;
        if (x >= 0) {
            quadrantA = quadrant1;
            quadrantB = quadrant4;
        } else {
            quadrantA = quadrant2;
            quadrantB = quadrant3;
        }

        ArrayList<T> columnA = quadrantA.get(x);
        for (int y = 0; y < columnA.size(); y++) {
            if (!isDefault(x, y)) {
                return true;
            }
        }
        ArrayList<T> columnB = quadrantB.get(x);
        for (int y = 0; y < columnB.size(); y++) {
            if (!isDefault(x, y)) {
                return true;
            }
        }
        return false;
    }
}*/