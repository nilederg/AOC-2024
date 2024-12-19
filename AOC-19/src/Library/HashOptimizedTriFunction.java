package Library;

import java.util.function.BiFunction;

public class HashOptimizedTriFunction <A, B, C, R> implements TriFunction<A, B, C, R> {
    private final HashOptimizedFunction<Triplet<A, B, C>, R> innerFunction;

    public HashOptimizedTriFunction(TriFunction<A, B, C, R> function) {
        innerFunction = new HashOptimizedFunction<>((Triplet<A, B, C> v) -> {
            return function.apply(v.a, v.b, v.c);
        });
    }

    public R apply(A a, B b, C c) {
        return innerFunction.apply(new Triplet<>(a, b, c));
    }
}
