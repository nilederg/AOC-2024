package Library;

import java.util.*;
import java.util.function.Predicate;

public class FreeArrayList<T> implements FreeList<T> {
    ArrayList<T> data;

    public FreeArrayList(int size) {
        data = new ArrayList<>(size);
    }

    public FreeArrayList(ArrayList<T> data) {
        this.data = (ArrayList<T>) data.clone();
    }

    public FreeArrayList(HashMap<Integer, T> map) {
        this.data = new ArrayList<>();
        for (Integer key : map.keySet()) {
            set(key, map.get(key));
        }
    }

    public FreeArrayList() {
        data = new ArrayList<>();
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index >= data.size()) {
                return null;
            }
            return data.get(index);
        }
    }

    public void set(int index, T value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            for (int j = data.size(); j <= index; j ++) {
                data.add(null);
            }
            data.set(index, value);
        }
    }

    public void insert(int index, T value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int j = data.size(); j <= index; j ++) {
            data.add(null);
        }
        data.add(index, value);
    }

    public void remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index >= data.size()) {
            return;
        }
        data.remove(index);
    }

    public void delete(int index) {
        set(index, null);
    }

    public void trim() {
        for (int i = data.size() - 1; i >= 0; i --) {
            if (data.get(i) == null) {
                data.remove(i);
            } else {
                break;
            }
        }
    }

    public int size() {
        for (int i = data.size() - 1; i >= 0; i --) {
            if (data.get(i) != null) {
                return i;
            }
        }
        return 0;
    }

    public Collection<T> toCollection() {
        Collection<T> c = new HashSet<>();
        for (T datum : data) {
            if (datum != null) {
                c.add(datum);
            }
        }
        return c;
    }

    public Collection<T> findAll(Predicate<T> predicate) {
        Collection<T> matches = new HashSet<>();
        Collection<T> allData = toCollection();
        for (T datum : allData) {
            if (predicate.test(datum)) {
                matches.add(datum);
            }
        }
        return matches;
    }

    public Collection<T> findAll(Object o) {
        return findAll(x -> x.equals(o));
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Collection<T> c = toCollection();
        return c.contains(o);
    }

    public void clear() {
        data.clear();
    }

    public String toString() {
        return data.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return toCollection().iterator();
    }
}
