import Library.*;
import Management.Input;

import java.util.*;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver1 {
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

        HashMap<Node<Point3>, Long> distances = dijkstra(startNode, nodes);

        Point2 point2 = new Point2(3,8);
        for (int z = 0; z < 4; z++) {
            Point3 point3 = new Point3(point2.x, point2.y, z);
            Node<Point3> node = point3.getValue(nodeArray);
            long distance = distances.get(node);
            println("Orientation", node.value.z, "Distance", distance);
        }

        long minCost = Long.MAX_VALUE;
        int z = 0;
        assert endNodes != null;
        for (Node<Point3> endNode : endNodes) {
            long distance = distances.get(endNode);
            if (distance < minCost) {
                minCost = distance;
                z = (int) endNode.value.z;
            }
        }

        forcePrintln("direction", z);

        FreeDimensionalList<Long> distanceVis = new FreeDimensionalList<>(2);
        for (Node<Point3> node : distances.keySet()) {
            Long current = distanceVis.get((int) node.value.x, (int) node.value.y);
            if (current == null || distances.get(node) < current) {
                distanceVis.set(distances.get(node), (int) node.value.x, (int) node.value.y);
            }
        }
        distanceVis.print2D(0, 0, data[0].length - 1, data.length - 1, true, false, (v, l) -> {
            if (v == null) {
                return "-".repeat(l);
            }
            return v.toString();
        }, (s) -> {
            println(s);
        });

        return minCost;
    }

    public static <T> HashMap<Node<T>, Long> dijkstra(Node<T> startNode, Collection<Node<T>> nodes) {
        HashSet<Node<T>> visited = new HashSet<>();
        HashMap<Node<T>, Long> distances = new HashMap<>();
        for (Node<T> node : nodes) {
            distances.put(node, Long.MAX_VALUE);
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

                if (tentativeDistance < distances.get(neighbor)) {
                    println(neighbor.value, "overwrote", distances.get(neighbor), "with", tentativeDistance);

                    distances.put(neighbor, tentativeDistance);

                    queue.add(neighbor);
                } else {
                    println(neighbor.value, "ignored with", tentativeDistance);
                }
            }
        }

        return distances;
    }
}
