package Library;

/**
 * An immutable class representing a collection of 2 values of any type.
 */
public class Tuple <A,B> {
    protected A a;
    protected B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }
    public B getB() {
        return b;
    }

    /**
     * Returns true if the compared tuple's items are both equal to this tuple's items
     *
     * @param other The object to compare. Automatically false if not a tuple.
     * @return Whether this is equal to the given object
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tuple)) {
            return false;
        }
        return (((Tuple<?, ?>) other).a.equals(this.a) && ((Tuple<?, ?>) other).b.equals(this.b));
    }

    @Override
    public String toString() {
        return "(" + this.a.toString() + ", " + this.b.toString() + ")";
    }
}
