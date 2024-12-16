import Library.*;
import Management.IO;
import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        char[][] data = input.getCharGrid();
        Set<Node<Point3>> nodes = new HashSet<>();
        Node<Point3>[][][] nodeArray = new Node[data.length][data[0].length][4];
        Node<Point3> startNode = null;
        Node<Point3>[] endNodes = null;

        Queue<Runnable> buildConnections = new LinkedList<>();

        Vector3[] directions = new Vector3[] {
                new Vector3(1, 0, 0),
                new Vector3(0, 1, 0),
                new Vector3(-1, 0, 0),
                new Vector3(0, -1, 0),
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == '#') {
                    continue;
                }
                Node<Point3> nodeEast  = new Node<>(new Point3(j, i, 0), 0);
                Node<Point3> nodeSouth = new Node<>(new Point3(j, i, 1), 0);
                Node<Point3> nodeWest  = new Node<>(new Point3(j, i, 2), 0);
                Node<Point3> nodeNorth = new Node<>(new Point3(j, i, 3), 0);

                nodeEast.value.setValue(nodeArray, nodeEast);
                nodeSouth.value.setValue(nodeArray, nodeSouth);
                nodeWest.value.setValue(nodeArray, nodeWest);
                nodeNorth.value.setValue(nodeArray, nodeNorth);

                if (data[i][j] == 'S') {
                    startNode = nodeEast;
                }
                if (data[i][j] == 'E') {
                    endNodes = new Node[] {
                            nodeEast,
                            nodeSouth,
                            nodeWest,
                            nodeNorth
                    };
                }

                nodeEast.addConnection(nodeNorth, 1000);
                nodeEast.addConnection(nodeSouth, 1000);
                nodeSouth.addConnection(nodeWest, 1000);
                nodeSouth.addConnection(nodeEast, 1000);
                nodeWest.addConnection(nodeNorth, 1000);
                nodeWest.addConnection(nodeSouth, 1000);
                nodeNorth.addConnection(nodeEast, 1000);
                nodeNorth.addConnection(nodeWest, 1000);

                buildConnections.add(() -> {
                    Node<Point3> potentialNeighborEast   = nodeEast.value.add(directions[(int) nodeEast.value.z]).getValue(nodeArray);
                    if (potentialNeighborEast != null) {
                        nodeEast.addConnection(potentialNeighborEast, 1);
                    }
                    Node<Point3> potentialNeighborSouth = nodeSouth.value.add(directions[(int) nodeSouth.value.z]).getValue(nodeArray);
                    if (potentialNeighborSouth != null) {
                        nodeSouth.addConnection(potentialNeighborSouth, 1);
                    }
                    Node<Point3> potentialNeighborWest   = nodeWest.value.add(directions[(int) nodeWest.value.z]).getValue(nodeArray);
                    if (potentialNeighborWest != null) {
                        nodeWest.addConnection(potentialNeighborWest, 1);
                    }
                    Node<Point3> potentialNeighborNorth = nodeNorth.value.add(directions[(int) nodeNorth.value.z]).getValue(nodeArray);
                    if (potentialNeighborNorth != null) {
                        nodeNorth.addConnection(potentialNeighborNorth, 1);
                    }
                });

                nodes.add(nodeEast);
                nodes.add(nodeSouth);
                nodes.add(nodeWest);
                nodes.add(nodeNorth);
            }
        }

        for (Runnable r : buildConnections) {
            r.run();
        }

        assert endNodes != null;
        Collection<Node<Point3>> pathNodes = dijkstraAllPaths(startNode, nodes, List.of(endNodes));

        FreeDimensionalList<Character> pathVis = new FreeDimensionalList<>(2);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == '#') {
                    pathVis.set('#', j, i);
                } else {
                    pathVis.set('.', j, i);
                }
            }
        }
        for (Node<Point3> node : pathNodes) {
            pathVis.set('O', (int) node.value.x, (int) node.value.y);
        }
        pathVis.print2D(0, 0, data[0].length - 1, data.length - 1, false, false, (v, l) -> v.toString(), IO::println);

        int total = 0;
        for (int x = 0; x < data[0].length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (pathVis.get(x, y) == 'O') {
                    total ++;
                }
            }
        }

        return total;
    }

    public static <T> Collection<Node<T>> dijkstraAllPaths(Node<T> startNode, Collection<Node<T>> nodes, Collection<Node<T>> endNodes) {
        HashSet<Node<T>> visited = new HashSet<>();
        HashMap<Node<T>, Long> distances = new HashMap<>();
        HashMap<Node<T>, ArrayList<Node<T>>> prev = new HashMap<>();
        for (Node<T> node : nodes) {
            distances.put(node, Long.MAX_VALUE);
            prev.put(node, new ArrayList<>(1));
        }
        distances.put(startNode, 0L);
        PriorityQueue<Node<T>> queue = new PriorityQueue<>(Comparator.comparingInt((n) -> {
            return Math.toIntExact(distances.get(n));
        }));
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            println(">  current", currentNode.value, distances.get(currentNode));

            if (visited.contains(currentNode)) {
                println("dropped, already visited");
                continue;
            }

            visited.add(currentNode);

            for (Node<T> neighbor : currentNode.connections.keySet()) {
                long tentativeDistance = distances.get(currentNode) + currentNode.connections.get(neighbor);

                if (tentativeDistance <= distances.get(neighbor)) {
                    if (tentativeDistance < distances.get(neighbor)) {
                        println(neighbor.value, "overwrote", distances.get(neighbor), "with", tentativeDistance);
                        prev.put(neighbor, new ArrayList<>());
                        prev.get(neighbor).add(currentNode);
                        distances.put(neighbor, tentativeDistance);
                        queue.add(neighbor);
                    } else {
                        println(neighbor.value, "equalled", distances.get(neighbor), "with", tentativeDistance);
                        if (!prev.get(neighbor).contains(currentNode)) {
                            prev.get(neighbor).add(currentNode);
                        }
                    }
                } else {
                    println(neighbor.value, "ignored with", tentativeDistance);
                }
            }
        }

        long minDistance = Long.MAX_VALUE;
        Node<T> endNode = null;
        for (Node<T> node : endNodes) {
            if (distances.get(node) < minDistance) {
                minDistance = distances.get(node);
                endNode = node;
            }
        }

        ArrayList<Node<T>> pathNodes = new ArrayList<>();
        Queue<Node<T>> pathQueue = new LinkedList<>();
        pathQueue.add(endNode);

        while (!pathQueue.isEmpty()) {
            Node<T> currentNode = pathQueue.poll();
            pathNodes.add(currentNode);
            Collection<Node<T>> sourceNodes = prev.get(currentNode);
            for (Node<T> sourceNode : sourceNodes) {
                if (!pathQueue.contains(sourceNode) && !pathNodes.contains(sourceNode)) {
                    pathQueue.add(sourceNode);
                }
            }
        }

        return pathNodes;
    }
}
