package Library;

import java.util.ArrayList;
import java.util.HashMap;

public class FreeList <T> {
    ArrayList<T> data;

    public FreeList(int size) {
        data = new ArrayList<>(size);
    }

    public FreeList(ArrayList<T> data) {
        this.data = (ArrayList<T>) data.clone();
    }

    public FreeList(HashMap<Integer, T> map) {
        this.data = new ArrayList<>();
        for (Integer key : map.keySet()) {
            set(key, map.get(key));
        }
    }

    public FreeList() {
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
}
