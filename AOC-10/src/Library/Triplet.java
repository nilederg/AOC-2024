package Library;

/**
 * An immutable class representing a collection of 3 values of any type.
 */
public class Triplet <A, B, C> {
    protected A a;
    protected B b;
    protected C c;

    public Triplet(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getA() {
        return a;
    }
    public B getB() {
        return b;
    }
    public C getC() {
        return c;
    }

    /**
     * Returns true if the compared tuple's items are both equal to this triplet's items
     *
     * @param other The object to compare. Automatically false if not a tuple.
     * @return Whether this is equal to the given object
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Triplet)) {
            return false;
        }
        return (((Triplet<?, ?, ?>) other).a.equals(this.a) && ((Triplet<?, ?, ?>) other).b.equals(this.b) && ((Triplet<?, ?, ?>) other).c.equals(this.c));
    }

    @Override
    public String toString() {
        return "(" + this.a.toString() + ", " + this.b.toString() + ", " + this.c.toString() + ")";
    }
}
