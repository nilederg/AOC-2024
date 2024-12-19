package Library;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FreeDimensionalList<T> {
    public final int dimensions;
    private FreeList<T> array;

    public FreeDimensionalList(int dimensions) {
        if (dimensions < 1) {
            throw new IllegalArgumentException("Dimensions must be greater than 0");
        }
        this.dimensions = dimensions;
        this.array = new FreeList<>();
    }

    public T get(int... coordinates) {
        return array.get(getInternalIndex(coordinates));
    }

    public void set(T element, int... coordinates) {
        array.set(getInternalIndex(coordinates), element);
    }

    private int getInternalIndex(int... coordinates) {
        if (coordinates == null || coordinates.length != dimensions) {
            throw new IllegalArgumentException("Coordinates do not have the correct number of dimensions");
        }
        // Find which outer "shell" on the hypercube our point inhabits
        int shell = -1;
        for (int value : coordinates) {
            if (value > shell) {
                shell = value;
            }
        }

        // Find which of the coordinates have the maximum value - the number of these is the order of the cell in which the point lies
        boolean[] maxima = new boolean[dimensions];
        int order = dimensions;
        for (int i = 0; i < dimensions; i++) {
            if (coordinates[i] == shell) {
                maxima[i] = true;
                order --;
            }
        }

        // Add volume from previous cells
        int internalVolume = 0;
        boolean[] testMaxima = new boolean[dimensions];
        AddMaxima:
        while (!Arrays.equals(testMaxima, maxima)) {
            // Add volume
            int dims = 0;
            for (boolean max : testMaxima) {
                if (!max) {
                    dims++;
                }
            }
            internalVolume += Maths.pow(shell, dims);
            // Increment testMaxima
            int i = 0;
            while (true) {
                if (!testMaxima[i]) {
                    testMaxima[i] = true;
                    continue AddMaxima;
                }
                testMaxima[i] = false;
                i++;
                if (i >= dimensions) {
                    throw new ArrayIndexOutOfBoundsException("Unreachable State");
                }
            }
        }

        // Add volume from final cell
        int[] cellCoords = new int[order];
        int cellI = 0;
        for (int i = 0; i < dimensions; i++) {
            if (!maxima[i]) {
                cellCoords[cellI] = coordinates[i];
                cellI++;
            }
        }
        for (int k = 0; k < order; k++) {
            internalVolume += cellCoords[k] * Maths.pow(shell, order - k - 1);
        }

        return internalVolume;
    }

    public void print2D(int lowerX, int lowerY, int upperX, int upperY, boolean alignRight, boolean spacing, BiFunction<T, Integer, String> stringify, Consumer<String> println) {
        if (this.dimensions != 2) {
            throw new IllegalStateException("Can only be run on a 2D dimensional list");
        }

        int longest = Integer.MIN_VALUE;
        for (int y = lowerY; y <= upperY; y++) {
            for (int x = lowerX; x <= upperX; x++) {
                if (get(x, y) != null) {
                    if (stringify.apply(get(x, y), 1).length() > longest) {
                        longest = stringify.apply(get(x, y), 1).length();
                    }
                }
            }
        }

        for (int y = lowerY; y <= upperY; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = lowerX; x <= upperX; x++) {
                StringBuilder unit = new StringBuilder();
                String str = stringify.apply(get(x, y), longest);
                int missingLength = longest - str.length();
                String spaces = " ".repeat(missingLength);
                if (alignRight) {
                    unit.append(spaces);
                    unit.append(str);
                } else {
                    unit.append(str);
                    unit.append(spaces);
                }
                line.append(unit);
                if (spacing) {
                    line.append(" ");
                }
            }
            println.accept(line.toString());
        }
    }
}