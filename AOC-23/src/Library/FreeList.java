package Library;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

public interface FreeList<T> extends Iterable<T> {
    boolean contains(Object o);
    Collection<T> toCollection();
    void clear();
    void set(int index, T o);
    T get(int index);
    void delete(int index);
    Collection<T> findAll(Predicate<T> predicate);
    Collection<T> findAll(T o);
}
