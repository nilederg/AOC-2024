package Library;

import java.util.Arrays;

public class FreeDimensionalList<T> {
    public final int dimensions;
    private FreeList<T> array;

    public FreeDimensionalList(int dimensions) {
        if (dimensions < 1) {
            throw new IllegalArgumentException("Dimensions must be greater than 0");
        }
        this.dimensions = dimensions;
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
}
