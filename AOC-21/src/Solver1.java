import Library.*;
import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver1 {
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

        BoundedDimensionalList<Character> keypadBeta = new BoundedDimensionalList<>(2, 3, 2);
        keypadBeta.set('^', 1, 0);
        keypadBeta.set('A', 2, 0);
        keypadBeta.set('<', 0, 1);
        keypadBeta.set('v', 1, 1);
        keypadBeta.set('>', 2, 1);

        BoundedDimensionalList<Character> keypadGamma = new BoundedDimensionalList<>(2, 3, 2);
        keypadGamma.set('^', 1, 0);
        keypadGamma.set('A', 2, 0);
        keypadGamma.set('<', 0, 1);
        keypadGamma.set('v', 1, 1);
        keypadGamma.set('>', 2, 1);

        BoundedDimensionalList<Character> keypadLambda = new BoundedDimensionalList<>(2, 3, 2);
        keypadLambda.set('^', 1, 0);
        keypadLambda.set('A', 2, 0);
        keypadLambda.set('<', 0, 1);
        keypadLambda.set('v', 1, 1);
        keypadLambda.set('>', 2, 1);

        Map<Character, Vector2> movement = new HashMap<>();
        movement.put('<', new Vector2(-1, 0));
        movement.put('v', new Vector2(0, -1));
        movement.put('^', new Vector2(0, 1));
        movement.put('>', new Vector2(1, 0));

        Point2 alphaPosition = new Point2(2, 3);
        long sum = 0;

        for (String line : lines) {
            char[] chars = line.toCharArray();
            StringBuilder shortestPath = new StringBuilder();
            for (char targetCharacter : chars) {
                Point2 targetAlpha = Miscellaneous.getSingularItem(keypadAlpha.findAll(targetCharacter)).getB().toPoint2();
                String shortestPathSegment = shortestInputForKeypadInput(alphaPosition, targetAlpha);
                //println(shortestPathSegment);
                shortestPath.append(shortestPathSegment);
                alphaPosition = targetAlpha;
            }
            long complexity = (long) Integer.parseInt(line.substring(0, 3)) * shortestPath.length();
            println("\n>>> ", line, " ", shortestPath.length(), " ", shortestPath.toString());
            sum += complexity;
        }
        return sum;
    }

    public static String shortestInputForKeypadInput(Point2 currentAlphaKey, Point2 nextAlphaKey) {
        Set<String> betaPaths = robot1FromKeyShift(currentAlphaKey, nextAlphaKey);
        Set<String> gammaPaths = increaseDepth(betaPaths);
        Set<String> lambdaPaths = increaseDepth(gammaPaths);
        /*println("A");
        println(betaPaths.size() + " beta paths, " + gammaPaths.size() + " gamma paths, " + lambdaPaths.size() + " lambda paths");
        for (String path : betaPaths) {
            println("  Beta " + path);
        }
        for (String path : gammaPaths) {
            println(" Gamma " + path);
        }
        for (String path : lambdaPaths) {
            println("Lambda " + path);
        }*/
        int shortestPathLength = Integer.MAX_VALUE;
        String shortestPath = "";
        for (String lambdaPath : lambdaPaths) {
            if (lambdaPath.length() < shortestPathLength) {
                shortestPath = lambdaPath;
                shortestPathLength = lambdaPath.length();
            }
            if (shortestPathLength != Integer.MAX_VALUE && lambdaPath.length() != shortestPathLength) {
                println("MISMATCH ", lambdaPath);
                println("         ", shortestPath);
            }
        }
        return shortestPath;
    }

    public static Map<Tuple<Point2, Character>, Set<String>> increaseDepthMap = new HashMap<>();

    public static Set<String> increaseDepth(Set<String> betaPaths) {
        BoundedDimensionalList<Character> keypad = new BoundedDimensionalList<>(2, 3, 2);
        keypad.set('^', 1, 0);
        keypad.set('A', 2, 0);
        keypad.set('<', 0, 1);
        keypad.set('v', 1, 1);
        keypad.set('>', 2, 1);

        Point2 ADir = new Point2(2, 0);
        Set<String> gammaPathsAll = new HashSet<>();
        for (String betaPath : betaPaths) {
            Set<String> gammaPaths = new HashSet<>();
            gammaPaths.add("");
            Point2 betaPoint = ADir.clone();
            for (Character betaChar : betaPath.toCharArray()) {
                Set<String> gammaPathsFromBeta = null;
                Point2 nextBetaPoint = Miscellaneous.getSingularItem(keypad.findAll(betaChar)).getB().toPoint2();
                Tuple<Point2, Character> key = new Tuple<>(betaPoint, betaChar);
                if (increaseDepthMap.containsKey(key)) {
                    gammaPathsFromBeta = increaseDepthMap.get(key);
                } else {
                    gammaPathsFromBeta = robotFromDirKeyShift(betaPoint, nextBetaPoint);
                    increaseDepthMap.put(key, gammaPathsFromBeta);
                }
                betaPoint = nextBetaPoint;
                Set<String> newGammaPaths = new HashSet<>();
                for (String gammaPath : gammaPaths) {
                    for (String gammaPathFromBeta : gammaPathsFromBeta) {
                        newGammaPaths.add(gammaPath + gammaPathFromBeta);
                    }
                }
                gammaPaths = newGammaPaths;
            }
            gammaPathsAll.addAll(gammaPaths);
        }
        /*int shortestPathLength = Integer.MAX_VALUE;
        String shortestPath = "";
        println("-----");
        for (String gammaPath : gammaPathsAll) {
            println("Path " + gammaPath);
            if (gammaPath.length() < shortestPathLength) {
                shortestPath = gammaPath;
                shortestPathLength = gammaPath.length();
            }
        }
        println("-----");
        gammaPaths = new HashSet<>();
        gammaPaths.add(shortestPath);
        return gammaPaths*/
        /*Set<String> gammaPaths = new HashSet<>();
        gammaPaths.add(gammaPathsAll.iterator().next());*/
        return gammaPathsAll;
    }

    public static Set<String> robot1FromKeyShift(Point2 currentAlphaKey, Point2 nextAlphaKey) {
        boolean bifurcateTree = true;
        Boolean preferHorizontal = true;
        if (currentAlphaKey.y == 3 && nextAlphaKey.x == 0) {
            bifurcateTree = false;
            preferHorizontal = false;
        }
        if (nextAlphaKey.y == 3 && currentAlphaKey.x == 0) {
            bifurcateTree = false;
            preferHorizontal = true;
        }
        int horizontal = (int) (nextAlphaKey.x - currentAlphaKey.x);
        int vertical = (int) (nextAlphaKey.y - currentAlphaKey.y);
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

        if (horizontal > 0 && vertical < 0) {
            bifurcateTree = false;
        }

        String sH = horizontalString + verticalString + "A";
        String sV = verticalString + horizontalString + "A";

        if (bifurcateTree && !sH.equals(sV)) {
            Set<String> results = new HashSet<>();
            results.add(sH);
            results.add(sV);
            return results;
        } else {
            Set<String> results = new HashSet<>();
            if (preferHorizontal) {
                results.add(sH);
            } else {
                results.add(sV);
            }
            return results;
        }
    }

    public static Map<Tuple<Point2, Point2>, Set<String>> robotFromDirKeyShiftMap = new HashMap<>();

    public static Set<String> robotFromDirKeyShift(Point2 currentDirKey, Point2 nextDirKey) {
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

        Map<Character, Vector2> movement = new HashMap<>();
        movement.put('<', new Vector2(-1, 0));
        movement.put('v', new Vector2(0, -1));
        movement.put('^', new Vector2(0, 1));
        movement.put('>', new Vector2(1, 0));

        Vector2 move = Vector2.fromPoints(currentDirKey, nextDirKey);

        if (move.x == 0 && move.y == 0) {
            Set<String> results = new HashSet<>();
            results.add("A");
            robotFromDirKeyShiftMap.put(key, results);
            return results;
        }

        boolean bifurcateTree = true;
        Boolean preferHorizontal = true;
        if (currentDirKey.y == 0 && nextDirKey.x == 0) {
            bifurcateTree = false;
            preferHorizontal = false;
        }
        if (nextDirKey.y == 3 && currentDirKey.x == 0) {
            bifurcateTree = false;
            preferHorizontal = true;
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

        if (move.x > 0 && move.y < 0) {
            bifurcateTree = false;
        }

        String sH = horizontalString + verticalString + "A";
        String sV = verticalString + horizontalString + "A";

        if (bifurcateTree && !sH.equals(sV)) {
            Set<String> results = new HashSet<>();
            //results.add(sH);
            results.add(sV);
            robotFromDirKeyShiftMap.put(key, results);
            return results;
        } else {
            Set<String> results = new HashSet<>();
            if (preferHorizontal) {
                results.add(sH);
            } else {
                results.add(sV);
            }
            robotFromDirKeyShiftMap.put(key, results);
            return results;
        }
    }
}
