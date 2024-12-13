package Library;

public class FreeDimensionalBiList <T> {
    FreeDimensionalList<T>[] zones;

    public FreeDimensionalBiList(int dimensions) {
        if (dimensions < 1) {
            throw new IllegalArgumentException("Dimensions must be greater than 0");
        }
        FreeDimensionalList<T>[] zones = new FreeDimensionalList[Maths.pow(2, dimensions)];
        for (int i = 0; i < zones.length; i++) {
            zones[i] = new FreeDimensionalList<>(dimensions);
        }
        this.zones = zones;
    }


}
