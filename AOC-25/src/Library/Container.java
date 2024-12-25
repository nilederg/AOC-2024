package Library;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Container <T> {
    private Supplier<T> getter;
    private final Consumer<T> setter;

    public Container(Consumer<T> setter, Supplier<T> getter) {
        this.getter = getter;
        this.setter = setter;
    }

    public Container(T value) {
        getter = () -> value;
        setter = (T v) -> getter = () -> v;
    }

    public T get() {
        return getter.get();
    }
    public void set(T value) {
        setter.accept(value);
    }
}
