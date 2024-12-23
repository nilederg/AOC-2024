import Library.Node;
import Library.Triplet;
import Management.Input;

import java.util.*;
import java.util.stream.Collectors;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static String Evaluate(Input input) {
        String[] data = input.getLines();

        Map<String, Node<String>> computerNames = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            String name1 = data[i].substring(0, 2);
            String name2 = data[i].substring(3, 5);
            if (!computerNames.containsKey(name1)) {
                computerNames.put(name1, new Node<>(name1));
            }
            if (!computerNames.containsKey(name2)) {
                computerNames.put(name2, new Node<>(name2));
            }
            Node<String> comp1 = computerNames.get(name1);
            Node<String> comp2 = computerNames.get(name2);
            comp1.addConnection(comp2, 1);
            comp2.addConnection(comp1, 1);
        }

        int largestSet = Integer.MIN_VALUE;
        Set<Node<String>> networkComputers = new HashSet<>();
        for (String name : computerNames.keySet()) {
            Node<String> computer = computerNames.get(name);
            int maxPossibleSize = computer.connections.size() + 1;
            if (maxPossibleSize <= largestSet) {
                continue;
            }
            int connections = computer.connections.size();
            boolean[] tryComputers = new boolean[connections];
            Node<String>[] allConnectedComputers = computer.connections.keySet().toArray(new Node[connections]);
            while (true) {
                Set<Node<String>> testingSet = new HashSet<>();
                for (int i = 0; i < allConnectedComputers.length; i++) {
                    if (tryComputers[i]) {
                        testingSet.add(allConnectedComputers[i]);
                    }
                }
                maxPossibleSize = testingSet.size() + 1;
                if (maxPossibleSize > largestSet) {
                    boolean fullyConnected = true;
                    CheckConnectivity:
                    for (Node<String> comp : testingSet) {
                        for (Node<String> neighbor : testingSet) {
                            if (comp.equals(neighbor)) {
                                continue;
                            }
                            if (!comp.connections.containsKey(neighbor)) {
                                fullyConnected = false;
                                break CheckConnectivity;
                            }
                        }
                    }

                    if (fullyConnected) {
                        int connectionSize = testingSet.size() + 1;
                        if (connectionSize > largestSet) {
                            largestSet = connectionSize;
                            networkComputers = new HashSet<>(testingSet);
                            networkComputers.add(computer);
                            println("New largest ", largestSet, ", ", networkComputers.toString());
                        }
                    }

                    println(Arrays.toString(tryComputers));
                }

                boolean AND = true;
                for (boolean bool : tryComputers) {
                    AND = AND && bool;
                }
                if (AND) {
                    break;
                }

                int index = 0;
                while (true) {
                    if (tryComputers[index]) {
                        tryComputers[index] = false;
                        index++;
                    } else {
                        tryComputers[index] = true;
                        break;
                    }
                }
            }
        }

        List<String> names = new ArrayList<>(networkComputers.size());
        for (Node<String> computer : networkComputers) {
            names.add(computer.value);
        }

        String output = names.stream().sorted().collect(Collectors.joining(","));
        return output;
    }
}
