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
        Tuple<?, ?> otherTuple = (Tuple<?, ?>) other;
        if (!otherTuple.getA().equals(this.getA())) {
            return false;
        }
        if (!otherTuple.getB().equals(this.getB())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.a.toString() + ", " + this.b.toString() + ")";
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = result * 31 + a.hashCode();
        result = result * 31 + b.hashCode();
        return result;
    }

    @Override
    public Tuple<A, B> clone() {
        return new Tuple<>(a, b);
    }
}
