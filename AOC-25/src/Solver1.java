import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        String[] data = input.getLines();

        Set<Integer[]> keys = new HashSet<>();
        Set<Integer[]> locks = new HashSet<>();

        {
            char[][] schematic = new char[7][];
            int schematicRow = 0;
            for (int lineIndex = 0; lineIndex < data.length; lineIndex++) {
                String line = data[lineIndex];
                if (!line.isBlank()) {
                    schematic[schematicRow] = line.toCharArray();
                    schematicRow++;
                }
                if (line.isBlank() || lineIndex == data.length - 1) {
                    schematicRow = 0;
                    Integer[] heights = new Integer[5];
                    Arrays.fill(heights, 0);
                    for (int i = 0; i < schematic.length; i++) {
                        for (int j = 0; j < schematic[i].length; j++) {
                            heights[j] += (schematic[i][j] == '#') ? 1 : 0;
                        }
                    }
                    for (int i = 0; i < heights.length; i++) {
                        heights[i] --;
                    }
                    if (schematic[0][0] == '#') {
                        locks.add(heights);
                    } else {
                        keys.add(heights);
                    }
                    schematic = new char[7][];
                }
            }
        }

        // 5 columns, for each column maps desired height to a key
        Map<Integer, Set<Integer[]>>[] heightToKey = new HashMap[5];
        for (int i = 0; i < heightToKey.length; i++) {
            heightToKey[i] = new HashMap<>();
        }
        for (Integer[] key : keys) {
            for (int i = 0; i < 5; i ++) {
                int height = key[i];
                heightToKey[i].putIfAbsent(height, new HashSet<>());
                heightToKey[i].get(height).add(key);
            }
        }

        for (int i = 0; i < heightToKey.length; i++) {
            println(heightToKey[i]);
        }

        int total = 0;
        for (Integer[] lock : locks) {
            Set<Integer[]> matchKeys = new HashSet<>(keys);
            for (int column = 0; column < 5; column ++) {
                int lockHeight = lock[column];
                int maximumKeyHeight = 5 - lockHeight;
                Map<Integer, Set<Integer[]>> heightMap = heightToKey[column];
                for (int discardHeight = maximumKeyHeight + 1; discardHeight <= 5; discardHeight++) {
                    Set<Integer[]> discard = (heightMap.get(discardHeight));
                    if (discard != null) {
                        matchKeys.removeAll(discard);
                    }
                }
            }
            total += matchKeys.size();
        }
        return total;
    }
}
