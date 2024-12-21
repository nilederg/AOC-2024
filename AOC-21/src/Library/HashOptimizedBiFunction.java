package Library;

import java.util.function.BiFunction;

public class HashOptimizedBiFunction <T, U, R> implements BiFunction<T, U, R> {
    private final HashOptimizedFunction<Tuple<T, U>, R> innerFunction;

    public HashOptimizedBiFunction(BiFunction<T, U, R> function) {
        innerFunction = new HashOptimizedFunction<>((Tuple<T, U> v) -> {
            return function.apply(v.a, v.b);
        });
    }

    public R apply(T t, U u) {
        return innerFunction.apply(new Tuple<>(t, u));
    }
}
