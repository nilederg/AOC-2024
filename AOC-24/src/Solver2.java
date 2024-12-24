import Library.Miscellaneous;
import Library.Tuple;
import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver2 {
    public static String Evaluate(Input input) {
        String[][] data = input.getSeparatedBySpaces();

        Map<String, String> swaps = new HashMap<>();
        swaps.put("z18", "qgd");
        swaps.put("qgd", "z18");
        swaps.put("z10", "mwk");
        swaps.put("mwk", "z10");
        swaps.put("z33", "gqp");
        swaps.put("gqp", "z33");
        swaps.put("jmh", "hsw");
        swaps.put("hsw", "jmh");

        // manually solved
        if (true) {
            return "gqp,hsw,jmh,mwk,qgd,z10,z18,z33";
        }

        Map<String, Boolean> wireValues = new HashMap<>();
        Set<String[]> gates = new HashSet<>();
        Map<String, String[]> wireSource = new HashMap<>();
        Map<String, Set<String>> gatesFromInput = new HashMap<>();
        Map<String, String> outputFromFullInput = new HashMap<>();
        Map<String, String> fullInputFromOutput = new HashMap<>();
        Set<String> wires = new HashSet<>();
        Set<String> inputWires = new HashSet<>();
        Set<String> outputWires = new HashSet<>();

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
                    wires.add(name);
                    inputWires.add(name);
                } else {
                    if (swaps.containsKey(line[4])) {
                        line[4] = swaps.get(line[4]);
                    }
                    if (line[4].charAt(0) == 'z') {
                        outputWires.add(line[4]);
                    }
                    gates.add(line);
                    wireSource.put(line[4], line);
                    wires.add(line[4]);
                    gatesFromInput.putIfAbsent(line[0], new HashSet<>());
                    gatesFromInput.get(line[0]).add(line[4]);
                    gatesFromInput.putIfAbsent(line[2], new HashSet<>());
                    gatesFromInput.get(line[2]).add(line[4]);
                    String inputString = line[0] + " " + line[1] + " " + line[2];
                    outputFromFullInput.put(inputString, line[4]);
                    fullInputFromOutput.put(line[4], inputString);
                }
            }
        }

        /*BiConsumer<String, String> swap = (A, B) -> {

        };*/

        // Pre-compute
        /*Map<String, Set<String>> downstreamOutputs = new HashMap<>();
        {
            Set<String> unvisitedWires = new HashSet<>(outputWires);
            for (String outputWire : outputWires) {
                downstreamOutputs.put(outputWire, new HashSet<>());
                downstreamOutputs.get(outputWire).add(outputWire);
            }
            while (!unvisitedWires.isEmpty()) {
                String wireName = unvisitedWires.iterator().next();
                String[] gate = wireSource.get(wireName);
                if (gate != null) {
                    Set<String> thisDownstream = downstreamOutputs.get(wireName);

                    unvisitedWires.add(gate[0]);
                    downstreamOutputs.putIfAbsent(gate[0], new HashSet<>());
                    downstreamOutputs.get(gate[0]).addAll(thisDownstream);

                    unvisitedWires.add(gate[2]);
                    downstreamOutputs.putIfAbsent(gate[2], new HashSet<>());
                    downstreamOutputs.get(gate[2]).addAll(thisDownstream);
                }
                unvisitedWires.remove(wireName);
            }
        }
        Map<String, Set<String>> downstreamWires = new HashMap<>();
        {
            Set<String> unvisitedWires = new HashSet<>(outputWires);
            for (String outputWire : outputWires) {
                downstreamWires.put(outputWire, new HashSet<>());
                downstreamWires.get(outputWire).add(outputWire);
            }
            while (!unvisitedWires.isEmpty()) {
                String wireName = unvisitedWires.iterator().next();
                String[] gate = wireSource.get(wireName);
                downstreamWires.get(wireName).add(wireName);
                if (gate != null) {
                    Set<String> thisDownstream = downstreamWires.get(wireName);

                    unvisitedWires.add(gate[0]);
                    downstreamWires.putIfAbsent(gate[0], new HashSet<>());
                    downstreamWires.get(gate[0]).addAll(thisDownstream);

                    unvisitedWires.add(gate[2]);
                    downstreamWires.putIfAbsent(gate[2], new HashSet<>());
                    downstreamWires.get(gate[2]).addAll(thisDownstream);
                }
                unvisitedWires.remove(wireName);
            }
        }
        Map<String, Set<String>> upstreamWires = new HashMap<>();
        {
            for (String wire : wires) {
                upstreamWires.putIfAbsent(wire, new HashSet<>());
                Set<String> upstream = upstreamWires.get(wire);
                for (String upstreamWire : wires) {
                    if (!wire.equals(upstreamWire) && downstreamWires.get(upstreamWire).contains(wire)) {
                        upstream.add(upstreamWire);
                    }
                }
            }
        }*=
        println("Downstream output wires: " + downstreamOutputs.size() + " - " + downstreamOutputs);
        println("Downstream wires: " + downstreamWires.size() + " - " + downstreamWires);
        println("Upstream wires: " + upstreamWires.size() + " - " + upstreamWires);*/


        long xValue = getLongFromWires(wireValues, 'x');
        long yValue = getLongFromWires(wireValues, 'y');
        long expectedOutput = getLongFromWires(wireValues, 'z');
        long actualOutput = calculateResult(gates, wireValues);

        Set<Tuple<String[], String[]>> wireSwaps = new HashSet<>();
        /*{
            // [x XOR y  -> A,  // Half add x and y
            //  x AND y  -> B,
            //  A XOR c0 -> z,  // Half add carry in
            //  A AND c0 -> C,
            //  C  OR B  -> c1] // Get carry out
            String[][] fullAdders = new String[45][];
            // Inner sets represent ORs wherein at least 1 is misplaced
            Set<Set<String>> faultyWires = new HashSet<>();
            for (int i = 1; i < 45; i++) {
                println(i);
                fullAdders[i] = new String[5];
                String inputX = getWire('x', i);
                String inputY = getWire('y', i);
                String gate1A = inputX + " XOR " + inputY;
                String gate1B = inputY + " XOR " + inputX;
                String gate2A = inputY + " AND " + inputX;
                String gate2B = inputX + " AND " + inputY;
                String wire1A = outputFromFullInput.get(gate1A);
                String wire1B = outputFromFullInput.get(gate1B);
                String wire2A = outputFromFullInput.get(gate2A);
                String wire2B = outputFromFullInput.get(gate2B);
                String wire1 = wire1A == null ? wire1B : wire1A; // x XOR y -> A
                String wire2 = wire2A == null ? wire2B : wire2A; // x AND y -> B
                if (wire1.charAt(0) == 'z') { // Quiet swap

                }
                Set<String> wires34 = gatesFromInput.get(wire1);
                String wire3 = null; // A XOR c0 -> z
                String wire4 = null; // A AND c0 -> C
                if (wires34.size() != 2) {
                    throw new RuntimeException("que");
                }
                for (String wire : wires34) {
                    if (fullInputFromOutput.get(wire).startsWith("XOR", 4)) {
                        wire3 = wire;
                    }
                    if (fullInputFromOutput.get(wire).startsWith("AND", 4)) {
                        wire4 = wire;
                    }
                }
                String gate5A = Miscellaneous.getSingularItem(gatesFromInput.get(wire2));
                String gate5B = Miscellaneous.getSingularItem(gatesFromInput.get(wire4));
                if (!gate5A.equals(gate5B)) {
                    Set<String> faultySet = new HashSet<>();
                    faultySet.add(gate5A);
                    faultySet.add(gate5B);
                    faultyWires.add(faultySet);
                }
            }
        }*/

        Set<String[]> gatesSwapped = new HashSet<>(gates);
        for (Tuple<String[], String[]> swap : wireSwaps) {
            String[] oldA = swap.getA();
            String[] oldB = swap.getB();
            String[] newA = Arrays.copyOf(oldA, oldA.length);
            newA[4] = oldB[4];
            String[] newB = Arrays.copyOf(oldB, oldB.length);
            newB[4] = oldA[4];

            gatesSwapped.remove(oldA);
            gatesSwapped.remove(oldB);
            gatesSwapped.add(newA);
            gatesSwapped.add(newB);
        }
        long actualFinalOutput = calculateResult(gatesSwapped, wireValues);
        if (actualFinalOutput != expectedOutput) {
            throw new IllegalStateException("Calculation failed!");
        }

        Set<String> swappedWireNames = new HashSet<>();
        for (Tuple<String[], String[]> swap : wireSwaps) {
            String nameA = swap.getA()[4];
            String nameB = swap.getB()[4];
            swappedWireNames.add(nameA);
            swappedWireNames.add(nameB);
        }

        List<String> sort = new ArrayList<>(swappedWireNames);
        Collections.sort(sort);
        String[] sortedSwappedWireNames = sort.toArray(new String[0]);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sortedSwappedWireNames.length; i++) {
            String name = sortedSwappedWireNames[i];
            output.append(name);
            if (i < sortedSwappedWireNames.length - 1) {
                output.append(",");
            }
        }

        return output.toString();
    }

    private static long calculateResult(Set<String[]> unresolvedGatesOriginal, Map<String, Boolean> wireValuesOriginal) {
        Set<String[]> unresolvedGates = new HashSet<>(unresolvedGatesOriginal);
        Map<String, Boolean> wireValues = new HashMap<>(wireValuesOriginal);

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

        long output = getLongFromWires(wireValues, 'z');

        return output;
    }

    private static long getLongFromWires(Map<String, Boolean> wireValues, char character) {
        long output = 0;
        for (String wireName : wireValues.keySet()) {
            if (wireName.charAt(0) != character) {
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

    private static String getWire(char character, int index) {
        String wireNumber = String.valueOf(index);
        if (wireNumber.length() != 2) {
            wireNumber = "0".repeat(2 - wireNumber.length()) + wireNumber;
        }
        return character + wireNumber;
    }

}
