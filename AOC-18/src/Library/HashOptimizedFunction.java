package Library;

import java.util.HashMap;
import java.util.function.Function;

public class HashOptimizedFunction<T, R> implements Function<T, R> {
    private final HashMap<T, R> map;
    private final Function<T, R> function;

    public HashOptimizedFunction(Function<T, R> function) {
        map = new HashMap<>();
        this.function = function;
    }

    public R apply(T input) {
        if (map.containsKey(input)) {
            return map.get(input);
        } else {
            R value = function.apply(input);
            map.put(input, value);
            return value;
        }
    }
}
