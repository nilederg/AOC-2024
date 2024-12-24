import Library.Quadruplet;
import Library.Triplet;
import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        String[][] data = input.getSeparatedBySpaces();

        Map<String, Boolean> wireValues = new HashMap<>();
        Set<String[]> unresolvedGates = new HashSet<>();

        {
            boolean finishedWires = false;
            for (String[] line : data) {
                if (line.length == 0 || line[0].isEmpty()) {
                    finishedWires = true;
                    continue;
                }
                if (!finishedWires) {
                    String name = line[0].substring(0, 3);
                    boolean value = Objects.equals(line[1], "1");
                    wireValues.put(name, value);
                } else {
                    unresolvedGates.add(line);
                }
            }
        }

        println(wireValues.toString());

        ResolveGates: while (!unresolvedGates.isEmpty()) {
            for (String[] line : unresolvedGates) {
                String wire1 = line[0];
                String gateType = line[1];
                String wire2 = line[2];
                String outputWire = line[4];

                if (wireValues.containsKey(outputWire)) {
                    unresolvedGates.remove(line);
                    continue ResolveGates;
                }
                if (!wireValues.containsKey(wire1)) {
                    continue;
                }
                if (!wireValues.containsKey(wire2)) {
                    continue;
                }

                boolean value1 = wireValues.get(wire1);
                boolean value2 = wireValues.get(wire2);
                boolean outputValue = switch (gateType) {
                    case "AND": {
                        yield value1 && value2;
                    }
                    case "OR": {
                        yield value1 || value2;
                    }
                    case "XOR": {
                        yield value1 ^ value2;
                    }
                    default: {
                        throw new IllegalStateException("Unexpected value: " + gateType);
                    }
                };

                unresolvedGates.remove(line);
                wireValues.put(outputWire, outputValue);
                continue ResolveGates;
            }
        }

        println(wireValues);

        long output = 0;
        for (String wireName : wireValues.keySet()) {
            if (wireName.charAt(0) != 'z') {
                continue;
            }
            int index = Integer.parseInt(wireName.substring(1, 3));
            long add = 0;
            if (wireValues.get(wireName)) {
                add = 1L << index;
            }
            output += add;
        }

        return output;
    }
}
