package Library;

public class Quadruplet <A,B,C,D> {
    protected A a;
    protected B b;
    protected C c;
    protected D d;

    public Quadruplet(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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
    public D getD() {
        return d;
    }

    /**
     * Returns true if the compared tuple's items are both equal to this triplet's items
     *
     * @param other The object to compare. Automatically false if not a tuple.
     * @return Whether this is equal to the given object
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Quadruplet<?,?,?,?>)) {
            return false;
        }
        return (((Quadruplet<?, ?, ?, ?>) other).a.equals(this.a) && ((Quadruplet<?, ?, ?, ?>) other).b.equals(this.b) && ((Quadruplet<?, ?, ?, ?>) other).c.equals(this.c) && ((Quadruplet<?, ?, ?, ?>) other).d.equals(this.d));
    }

    @Override
    public String toString() {
        return STR."(\{this.a.toString()}, \{this.b.toString()}, \{this.c.toString()}, \{this.d.toString()})";
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = result * 31 + a.hashCode();
        result = result * 31 + b.hashCode();
        result = result * 31 + c.hashCode();
        result = result * 31 + d.hashCode();
        return result;
    }
}
