package Library;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BoundedDimensionalList<T> implements Iterable<PointN>{
    public FreeDimensionalList<T> internalList;
    private int[] sizes;

    public BoundedDimensionalList(int dimensions, int... sizes) {
        if (sizes.length != dimensions) {
            throw new IllegalArgumentException();
        }
        internalList = new FreeDimensionalList<>(dimensions);
        this.sizes = sizes;
    }

    /**
     * Creates a BoundedDimensionalList from a 2D char array. Supports arbitrary conversion of chars to T, and detection of desired points on creation.
     * Assumes that the array is an array of rows, such that it should be indexed as array[y][x].
     * @param array The 2D char array to build the grid from.
     * @param translator A function that dictates the value of the output list at each given point, based only on the input char.
     * @return A tuple. Its first element is the desired BoundedDimensionalList, while the second is a map, containing sets of points representing all instances found of each character.
     * @param <T> The type to be stored inside the BoundedDimensionalList
     */
    public static <T> Tuple<BoundedDimensionalList<T>, Map<Character, Set<Point2>>> fromCharArray(char[][] array, Function<Character, T> translator) {
        BoundedDimensionalList<T> list = new BoundedDimensionalList<>(2);
        Map<Character, Set<Point2>> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Character c = array[i][j];
                T value = translator.apply(c);
                Point2 point = new Point2(j, i);

                list.set(value, point);
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
                map.get(c).add(point);
            }
        }

        return new Tuple<>(list, map);
    }

    public static Tuple<BoundedDimensionalList<Character>, Map<Character, Set<Point2>>> fromCharArray(char[][] array) {
        return fromCharArray(array, x -> x);
    }

    /**
     * Builds a graph by connecting points in this list's space.
     * @param traversable The values for which points in this list should be able to be traversed.
     * @param neighborDistances A mapping from all possible vectors across which points can connect, to the distance that those connecting points are from one another.
     * @return A tuple containing a new BoundedDimensionalList of the generated nodes of the graph, and a set of all generated nodes.
     */
    public Tuple<BoundedDimensionalList<Node<PointN>>, Set<Node<PointN>>> buildGraph(Set<T> traversable, Map<VectorN, Long> neighborDistances) {
        BoundedDimensionalList<Node<PointN>> nodes = new BoundedDimensionalList<>(2);
        Set<Node<PointN>> nodeSet = new HashSet<>();

        for (PointN point : this) {
            if (traversable.contains(point)) {
                Node<PointN> node = new Node<>(point);
                nodeSet.add(node);
                nodes.set(node, point);
            }
        }

        for (Node<PointN> node : nodeSet) {
            PointN point = node.value;
            for (VectorN dir : neighborDistances.keySet()) {
                PointN neighbor = point.add(dir);
                Node<PointN> neighborNode = new Node<>(neighbor);
                node.addConnection(neighborNode, neighborDistances.get(dir));
            }
        }

        return new Tuple<>(nodes, nodeSet);
    }

    private void verifyIndex(int... indices) {
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] >= sizes[i]) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
        }
    }

    public T get(int... indices) {
        verifyIndex(indices);
        return internalList.get(indices);
    }

    public T get(Point2 index) {
        if (internalList.dimensions != 2) {
            throw new IllegalStateException("Cannot index a non-2D array with a Point2.");
        }
        if (index.x > Integer.MAX_VALUE || index.y > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return get((int) index.x, (int) index.y);
    }

    public T get(PointN index) {
        if (internalList.dimensions != index.coordinates.length) {
            throw new IllegalStateException("Incorrect number of dimensions");
        }
        for (long l : index.coordinates) {
            if (l > Integer.MAX_VALUE || l < 0) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
        }
        int[] coords = new int[index.coordinates.length];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = (int) index.coordinates[i];
        }
        return get(coords);
    }

    public void set(T element, int... indices) {
        verifyIndex(indices);
        internalList.set(element, indices);
    }

    public void set(T element, Point2 index) {
        if (internalList.dimensions != 2) {
            throw new IllegalStateException("Cannot index a non-2D array with a Point2.");
        }
        if (index.x > Integer.MAX_VALUE || index.y > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        set(element, (int) index.x, (int) index.y);
    }

    public void set(T element, PointN index) {
        if (internalList.dimensions != index.coordinates.length) {
            throw new IllegalStateException("Incorrect number of dimensions");
        }
        for (long l : index.coordinates) {
            if (l > Integer.MAX_VALUE || l < 0) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
        }
        int[] coords = new int[index.coordinates.length];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = (int) index.coordinates[i];
        }
        set(element, coords);
    }

    public void print2D(boolean alignRight, boolean spacing, BiFunction<T, Integer, String> stringify, Consumer<String> println) {
        internalList.print2D(0, 0, sizes[0] - 1, sizes[1] - 1, alignRight, spacing, stringify, println);
    }

    public Set<T> toSet() {
        Set<T> set = new HashSet<>();
        for (PointN point : this) {
            T value = get(point);
            if (value != null) {
                set.add(value);
            }
        }
        return set;
    }

    public Collection<T> toCollection() {
        Collection<T> c = new HashSet<>();
        for (PointN point : this) {
            T datum = get(point);
            if (datum != null) {
                c.add(datum);
            }
        }
        return c;
    }

    public Collection<Tuple<T, PointN>> findAll(Predicate<T> predicate) {
        Collection<Tuple<T, PointN>> matches = new HashSet<>();
        for (PointN point : this) {
            T datum = get(point);
            if (datum == null) {
                continue;
            }
            if (predicate.test(datum)) {
                matches.add(new Tuple<>(datum, point));
            }
        }
        return matches;
    }

    public Collection<Tuple<T, PointN>> findAll(Object o) {
        return findAll(x -> x.equals(o));
    }

    @Override
    public Iterator<PointN> iterator() {
        return new Itr(this.internalList.dimensions, sizes);
    }

    private class Itr implements Iterator<PointN> {
        long[] iterCoords;
        int[] sizes;
        boolean done = false;

        public Itr(int dimensions, int... sizes) {
            iterCoords = new long[dimensions];
            this.sizes = sizes;
            this.done = false;
        }

        @Override
        public boolean hasNext() {
            return !done;
        }

        @Override
        public PointN next() {
            boolean hasNext = true;
            PointN preReturn = new PointN(iterCoords.clone());
            int index = 0;
            while (true) {
                iterCoords[index]++;
                if (iterCoords[index] > sizes[index] - 1) {
                    iterCoords[index] = 0;
                    index++;
                } else {
                    break;
                }
                if (index >= iterCoords.length) {
                    hasNext = false;
                    break;
                }
            }
            if (!hasNext) {
                if (done) {
                    throw new NoSuchElementException();
                }
                done = true;
            }
            return preReturn;
        }
    }
}
