import Library.FreeDimensionalList;
import Library.Node;
import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.util.*;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();

        int size = (int) data[0][0];
        FreeDimensionalList<Node<Point2>> nodes = new FreeDimensionalList<>(2);
        Collection<Node<Point2>> nodeSet = new HashSet<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Point2 p = new Point2(j, i);
                Node<Point2> n = new Node<>(p, 0);
                nodeSet.add(n);
                nodes.set(n, j, i);
            }
        }

        for (int i = 1; i < data[0][1]+1 && i < data.length; i ++) {
            int x = (int) data[i][0];
            int y = (int) data[i][1];
            Node<Point2> n = nodes.get(x, y);
            nodeSet.remove(n);
            nodes.set(null, x, y);
        }

        for (Node<Point2> n : nodeSet) {
            Point2 p = n.value;
            for (Vector2 dir : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
                Point2 neighborPoint = p.add(dir);
                if (neighborPoint.x < 0 || neighborPoint.y < 0 || neighborPoint.x >= size || neighborPoint.y >= size) {
                    continue;
                }
                Node<Point2> neighbor = nodes.get((int) neighborPoint.x, (int) neighborPoint.y);
                if (neighbor == null) {
                    continue;
                }
                n.addConnection(neighbor, 1);
            }
        }

        Node<Point2> start = nodes.get(0, 0);
        HashSet<Node<Point2>> end = new HashSet<>();
        end.add(nodes.get(size - 1, size - 1));

        HashMap<Node<Point2>, Long> distances = dijkstra(start, nodeSet);

        return distances.get(nodes.get(size - 1, size - 1));
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
