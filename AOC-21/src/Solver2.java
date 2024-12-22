import Library.*;
import Management.Input;

import java.util.*;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static int robots = 25;

    public static long Evaluate(Input input) {
        String[] lines = input.getLines();

        BoundedDimensionalList<Character> keypadAlpha = new BoundedDimensionalList<>(2, 3, 4);
        keypadAlpha.set('7', 0, 0);
        keypadAlpha.set('8', 1, 0);
        keypadAlpha.set('9', 2, 0);
        keypadAlpha.set('4', 0, 1);
        keypadAlpha.set('5', 1, 1);
        keypadAlpha.set('6', 2, 1);
        keypadAlpha.set('1', 0, 2);
        keypadAlpha.set('2', 1, 2);
        keypadAlpha.set('3', 2, 2);
        keypadAlpha.set('0', 1, 3);
        keypadAlpha.set('A', 2, 3);

        Point2 alphaPosition = new Point2(2, 3);
        long sum = 0;

        for (String line : lines) {
            char[] chars = line.toCharArray();
            long shortestPath = 0L;
            for (char targetCharacter : chars) {
                Point2 targetAlpha = Miscellaneous.getSingularItem(keypadAlpha.findAll(targetCharacter)).getB().toPoint2();
                long shortestPathSegment = shortestInputForKeypadInput(alphaPosition, targetAlpha);
                //forcePrintln(shortestPathSegment);
                shortestPath += shortestPathSegment;
                alphaPosition = targetAlpha;
            }
            long complexity = (long) Integer.parseInt(line.substring(0, 3)) * shortestPath;
            println("\n>>> ", line, " ", shortestPath);
            sum += complexity;
        }

        BoundedDimensionalList<Character> keypad = new BoundedDimensionalList<>(2, 3, 2);
        keypad.set('^', 1, 0);
        keypad.set('A', 2, 0);
        keypad.set('<', 0, 1);
        keypad.set('v', 1, 1);
        keypad.set('>', 2, 1);

        char[] keys = "^A<v>".toCharArray();
        for (char keyA : keys) {
            for (char keyB : keys) {
                println(keyA, keyB, " ", mutatePairs(keyA, keyB));
            }
        }

        return sum;
    }

    public static Map<Tuple<Point2, Point2>, Long> shortestInputForKeypadInputMap = new HashMap<>();

    public static Long shortestInputForKeypadInput(Point2 currentAlphaKey, Point2 nextAlphaKey) {
        Tuple<Point2, Point2> key = new Tuple<>(currentAlphaKey, nextAlphaKey);
        if (shortestInputForKeypadInputMap.containsKey(key)) {
            return shortestInputForKeypadInputMap.get(key);
        }
        String path = robot1FromKeyShift(currentAlphaKey, nextAlphaKey);
        long shortestPath = Long.MAX_VALUE;
        long pathLength = 0;
        char prevChar = 'A';
        for (char curChar : path.toCharArray()) {
            //println("Pair ", prevChar, curChar);
            Map<Tuple<Character, Character>, Long> pathMap = mutatePairsRecursive(prevChar, curChar, robots);
            prevChar = curChar;
            for (Map.Entry<Tuple<Character, Character>, Long> entry : pathMap.entrySet()) {
                pathLength += entry.getValue();
            }
        }
        if (pathLength < shortestPath) {
            shortestPath = pathLength;
        }
        shortestInputForKeypadInputMap.put(key, shortestPath);
        return shortestPath;
    }

    public static String robot1FromKeyShift(Point2 currentAlphaKey, Point2 nextAlphaKey) {
        int horizontal = (int) (nextAlphaKey.x - currentAlphaKey.x);
        int vertical = (int) (nextAlphaKey.y - currentAlphaKey.y);
        Vector2 move = Vector2.fromPoints(currentAlphaKey, nextAlphaKey);
        String horizontalString = null;
        String verticalString = null;
        if (horizontal > 0) {
            horizontalString = ">".repeat(horizontal);
        } else {
            horizontalString = "<".repeat(-horizontal);
        }
        if (vertical > 0) {
            verticalString = "v".repeat(vertical);
        } else {
            verticalString = "^".repeat(-vertical);
        }

        String sH = horizontalString + verticalString + "A";
        String sV = verticalString + horizontalString + "A";

        if (currentAlphaKey.y == 3 && nextAlphaKey.x == 0) {
            return sV;
        }
        if (nextAlphaKey.y == 3 && currentAlphaKey.x == 0) {
            return sH;
        }

        if (move.x == 0) {
            return sV;
        }
        if (move.y == 0) {
            return sH;
        }

        if (move.x > 0) {
            return sV;
        } else {
            return sH;
        }
    }

    public static <T> Map<T, Long> addMaps(Collection<Map<T, Long>> maps) {
        Map<T, Long> result = new HashMap<>();
        for (Map<T, Long> map : maps) {
            for (T key : map.keySet()) {
                if (!result.containsKey(key)) {
                    result.put(key, 0L);
                }
                result.put(key, result.get(key) + map.get(key));
            }
        }
        return result;
    }

    public static Map<Tuple<Character, Character>, Long> mutatePairsRecursive(Character prev, Character next, int recursions) {
        Map<Tuple<Character, Character>, Long> map = new HashMap<>();
        map.put(new Tuple<>(prev, next), 1L);

        for (int i = 0; i < recursions; i++) {
            Map<Tuple<Character, Character>, Long> newMap = new HashMap<>();
            for (Tuple<Character, Character> key : map.keySet()) {
                long count = map.get(key);
                //println("Pair ", key.getA(), key.getB(), " (", count, ")");
                Map<Tuple<Character, Character>, Long> outputMap = mutatePairs(key.getA(), key.getB());
                //println(outputMap.toString());
                for (Tuple<Character, Character> outputKey : outputMap.keySet()) {
                    if (!newMap.containsKey(outputKey)) {
                        newMap.put(outputKey, 0L);
                    }
                    newMap.put(outputKey, newMap.get(outputKey) + count * outputMap.get(outputKey));
                }
            }
            map = newMap;
        }

        return map;
    }

    public static Map<Tuple<Character, Character>, Long> mutatePairs(Character prev, Character next) {
        BoundedDimensionalList<Character> keypad = new BoundedDimensionalList<>(2, 3, 2);
        keypad.set('^', 1, 0);
        keypad.set('A', 2, 0);
        keypad.set('<', 0, 1);
        keypad.set('v', 1, 1);
        keypad.set('>', 2, 1);

        Point2 prevPoint = Miscellaneous.getSingularItem(keypad.findAll(prev)).getB().toPoint2();
        Point2 nextPoint = Miscellaneous.getSingularItem(keypad.findAll(next)).getB().toPoint2();

        String path = robotFromDirKeyShift(prevPoint, nextPoint);
        Character prevChar = 'A';
        Map<Tuple<Character, Character>, Long> map = new HashMap<>();
        for (Character curChar : path.toCharArray()) {
            Tuple<Character, Character> key = new Tuple<>(prevChar, curChar);
            prevChar = curChar;
            if (!map.containsKey(key)) {
                map.put(key, 0L);
            }
            map.put(key, map.get(key) + 1);
        }
        return map;
    }

    public static Map<Tuple<Point2, Point2>, String> robotFromDirKeyShiftMap = new HashMap<>();

    public static String robotFromDirKeyShift(Point2 currentDirKey, Point2 nextDirKey) {
        Tuple<Point2, Point2> key = new Tuple<>(currentDirKey, nextDirKey);
        if (robotFromDirKeyShiftMap.containsKey(key)) {
            return robotFromDirKeyShiftMap.get(key);
        }

        BoundedDimensionalList<Character> keypad = new BoundedDimensionalList<>(2, 3, 2);
        keypad.set('^', 1, 0);
        keypad.set('A', 2, 0);
        keypad.set('<', 0, 1);
        keypad.set('v', 1, 1);
        keypad.set('>', 2, 1);

        Vector2 move = Vector2.fromPoints(currentDirKey, nextDirKey);

        if (move.x == 0 && move.y == 0) {
            String results = "A";
            robotFromDirKeyShiftMap.put(key, results);
            return results;
        }

        String horizontalString = null;
        String verticalString = null;
        if (move.x > 0) {
            horizontalString = ">".repeat((int) move.x);
        } else {
            horizontalString = "<".repeat(-(int) move.x);
        }
        if (move.y > 0) {
            verticalString = "v".repeat((int) move.y);
        } else {
            verticalString = "^".repeat(-(int) move.y);
        }

        String sH = horizontalString + verticalString + "A";
        String sV = verticalString + horizontalString + "A";

        if (currentDirKey.x == 0) {
            robotFromDirKeyShiftMap.put(key, sH);
            return sH;
        }
        if (nextDirKey.x == 0) {
            robotFromDirKeyShiftMap.put(key, sV);
            return sV;
        }

        if (move.x == 0) {
            robotFromDirKeyShiftMap.put(key, sV);
            return sV;
        }
        if (move.y == 0) {
            robotFromDirKeyShiftMap.put(key, sH);
            return sH;
        }

        if (move.x > 0) {
            robotFromDirKeyShiftMap.put(key, sV);
            return sV;
        } else {
            robotFromDirKeyShiftMap.put(key, sH);
            return sH;
        }
    }
}
