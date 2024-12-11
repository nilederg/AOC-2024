import Library.Tuple;
import Management.Input;

import java.util.ArrayList;
import java.util.HashMap;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        long[] data = input.getInts()[0];
        ArrayList<Long> stones = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            stones.add((long) data[i]);
        }
        int repetitions = 75;
        if (stones.size() == 3) {
            repetitions = 6;
        }
        stones.remove(stones.size()-1);

        long sum = 0;
        for (long stone : stones) {
            sum += blinkSingle(stone, repetitions);
        }

        return sum;
    }

    public static HashMap<Tuple<Long, Integer>, Long> blinks = new HashMap<>();

    public static long blinkSingle(long stone, int iterations) {
        if (blinks.containsKey(new Tuple<>(stone, iterations))) {
            return blinks.get(new Tuple<>(stone, iterations));
        }

        if (iterations == 1) {
            if (stone == 0) {
                blinks.put(new Tuple<>(stone, iterations), 1L);
                return 1;
            } else if (String.valueOf(stone).length() % 2 == 0) {
                blinks.put(new Tuple<>(stone, iterations), 2L);
                return 2;
            } else {
                blinks.put(new Tuple<>(stone, iterations), 1L);
                return 1;
            }
        } else {
            if (stone == 0) {
                long value = blinkSingle(1, iterations - 1);
                blinks.put(new Tuple<>(stone, iterations), value);
                return value;
            } else if (String.valueOf(stone).length() % 2 == 0) {
                String s = String.valueOf(stone);
                long value = blinkSingle(Long.parseLong(s.substring(0, s.length() / 2)), iterations - 1) + blinkSingle(Long.parseLong(s.substring(s.length() / 2)), iterations - 1);
                blinks.put(new Tuple<>(stone, iterations), value);
                return value;
            } else {
                long value = blinkSingle(2024L * stone, iterations - 1);
                blinks.put(new Tuple<>(stone, iterations), value);
                return value;
            }
        }
    }
}
