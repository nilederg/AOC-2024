package Library;

import java.util.ArrayList;
import java.util.HashMap;

public class FreeBiList<T> {
    ArrayList<T> negative;
    ArrayList<T> nonNegative;

    public FreeBiList(int size) {
        negative = new ArrayList<>(size);
        nonNegative = new ArrayList<>(size);
    }

    public FreeBiList(ArrayList<T> negative, ArrayList<T> nonNegative) {
        this.negative = (ArrayList<T>) negative.clone();
        this.nonNegative = (ArrayList<T>) nonNegative.clone();
    }

    public FreeBiList(ArrayList<T> nonNegative) {
        this.negative = new ArrayList<>();
        this.nonNegative = (ArrayList<T>) nonNegative.clone();
    }

    public FreeBiList(HashMap<Integer, T> map) {
        this.negative = new ArrayList<>();
        this.nonNegative = new ArrayList<>();
        for (Integer key : map.keySet()) {
            set(key, map.get(key));
        }
    }

    public FreeBiList() {
        negative = new ArrayList<>();
        nonNegative = new ArrayList<>();
    }

    public T get(int index) {
        if (index < 0) {
            int i = -index - 1;
            if (i >= negative.size()) {
                return null;
            }
            return negative.get(i);
        } else {
            if (index >= nonNegative.size()) {
                return null;
            }
            return nonNegative.get(index);
        }
    }

    public void set(int index, T value) {
        if (index < 0) {
            index = -index - 1;
            for (int j = negative.size(); j <= index; j ++) {
                negative.add(null);
            }
            negative.set(index, value);
        } else {
            for (int j = nonNegative.size(); j <= index; j ++) {
                nonNegative.add(null);
            }
            nonNegative.set(index, value);
        }
    }

    public void delete(int index) {
        set(index, null);
    }

    public void trim() {
        for (int i = negative.size() - 1; i >= 0; i --) {
            if (negative.get(i) == null) {
                negative.remove(i);
            } else {
                break;
            }
        }
        for (int i = nonNegative.size() - 1; i >= 0; i --) {
            if (nonNegative.get(i) == null) {
                nonNegative.remove(i);
            } else {
                break;
            }
        }
    }
}
