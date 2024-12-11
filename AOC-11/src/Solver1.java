import Management.Input;

import java.util.ArrayList;
import java.util.HashMap;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        long[] data = input.getInts()[0];
        ArrayList<Long> stones = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            stones.add((long) data[i]);
        }
        long repetitions = stones.get(stones.size()-1);
        stones.remove(stones.size()-1);

        for (int i = 0; i < repetitions; i ++) {
            println(stones.toString());
            forcePrintln(i, " ", stones.size());
            stones = blink(stones);
        }
        println(stones.toString());

        return stones.size();
    }

    public static ArrayList<Long> blink(ArrayList<Long> stones) {
        ArrayList<Long> blinked = new ArrayList<>();
        for (int i = 0; i < stones.size(); i++) {
            if (stones.get(i) == 0) {
                blinked.add(1L);
            } else if (String.valueOf(stones.get(i)).length() % 2 == 0) {
                String s = String.valueOf(stones.get(i));
                blinked.add(Long.parseLong(s.substring(0, s.length() / 2)));
                blinked.add(Long.parseLong(s.substring(s.length() / 2)));
            } else {
                blinked.add(2024L * stones.get(i));
            }
        }

        return blinked;
    }
}
