package Library;

/**
 * An immutable class representing a collection of any number of values of any type.
 */
public class Nuple {
    private Object[] values;

    public Nuple(Object... values) {
        this.values = values;
    }

    public Object get(int index) {
        return values[index];
    }
}
