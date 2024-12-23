import Library.Node;
import Library.Triplet;
import Management.Input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
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

        Set<Set<Node<String>>> triangles = new HashSet<>();

        for (String compName : computerNames.keySet()) {
            Node<String> computer = computerNames.get(compName);
            for (Node<String> connectionA : computer.connections.keySet()) {
                for (Node<String> connectionB : computer.connections.keySet()) {
                    if (connectionA.equals(connectionB)) {
                        continue;
                    }
                    if (connectionA.connections.containsKey(connectionB)) {
                        Set<Node<String>> triangle = new HashSet<>();
                        triangle.add(computer);
                        triangle.add(connectionA);
                        triangle.add(connectionB);
                        triangles.add(triangle);
                    }
                }
            }
        }

        println(triangles.toString());

        int total = 0;
        Triangles: for (Set<Node<String>> triangle : triangles) {
            for (Node<String> computer : triangle) {
                String name = computer.value;
                if (name.charAt(0) == 't') {
                    total++;
                    continue Triangles;
                }
            }
        }

        return total;
    }
}
