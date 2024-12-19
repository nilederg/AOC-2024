import Library.HashOptimizedBiFunction;
import Management.Input;

import java.util.HashMap;

public class Solver1 {
    public static long Evaluate(Input input) {
        String[] availableData = input.getSeparatedBySpaces()[0];
        String[] requiredData = input.getLines();

        char[][] patterns = new char[availableData.length][];
        for (int i = 0; i < patterns.length; i++) {
            String pattern = availableData[i];
            int bound = pattern.length();
            if (pattern.charAt(pattern.length() - 1) == ',') {
                bound--;
            }
            patterns[i] = new char[bound];
            for (int j = 0; j < bound; j++) {
                patterns[i][j] = pattern.charAt(j);
            }
        }

        char[][] designs = new char[requiredData.length - 1][];
        for (int i = 1; i < requiredData.length; i++) {
            designs[i - 1] = new char[requiredData[i].length()];
            for (int j = 0; j < requiredData[i].length(); j++) {
                designs[i - 1][j] = requiredData[i].charAt(j);
            }
        }

        buildDesignMap = new HashMap<>();

        long success = 0;
        for (char[] design : designs) {
            if (buildDesign(String.valueOf(design), patterns) > 0) {
                success++;
            }
        }

        return success;
    }

    public static HashMap<String, Long> buildDesignMap = new HashMap<>();

    public static Long buildDesign(String design, char[][] patterns) {
        if (buildDesignMap.containsKey(design)) {
            return buildDesignMap.get(design);
        }

        long total = 0;
        for (char[] pattern : patterns) {
            if (pattern.length > design.length()) {
                continue;
            }
            boolean match = true;
            for (int j = 0; j < pattern.length; j++) {
                if (pattern[j] != design.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (!match) {
                continue;
            }
            if (pattern.length == design.length()) {
                total++;
                break;
            } else {
                char[] remainingDesign = new char[design.length() - pattern.length];
                for (int i = 0; i < remainingDesign.length; i++) {
                    remainingDesign[i] = design.charAt(pattern.length + i);
                }
                total += buildDesign(String.valueOf(remainingDesign), patterns);
                if (total > 0) {
                    break;
                }
            }
        }

        buildDesignMap.put(design, total);
        return total;
    }
}
