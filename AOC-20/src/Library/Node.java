package Library;

import java.util.*;

import static Management.IO.println;

public class Node <T> {
    public T value;
    public final int id;
    public HashMap<Node<T>, Long> connections;

    public Node(T value, int id) {
        this.value = value;
        this.id = id;
        this.connections = new HashMap<>();
    }

    public void addConnection(Node<T> node, long distance) {
        connections.put(node, distance);
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
            //println(">  current", currentNode.value, distances.get(currentNode));

            if (visited.contains(currentNode)) {
                //println("dropped, already visited");
                continue;
            }

            visited.add(currentNode);

            for (Node<T> neighbor : currentNode.connections.keySet()) {
                long tentativeDistance = distances.get(currentNode) + currentNode.connections.get(neighbor);

                if (tentativeDistance < distances.get(neighbor)) {
                    //println(neighbor.value, "overwrote", distances.get(neighbor), "with", tentativeDistance);

                    distances.put(neighbor, tentativeDistance);

                    queue.add(neighbor);
                } else {
                    //println(neighbor.value, "ignored with", tentativeDistance);
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
            //println(">  current", currentNode.value, distances.get(currentNode));

            if (visited.contains(currentNode)) {
                //println("dropped, already visited");
                continue;
            }

            visited.add(currentNode);

            for (Node<T> neighbor : currentNode.connections.keySet()) {
                long tentativeDistance = distances.get(currentNode) + currentNode.connections.get(neighbor);

                if (tentativeDistance <= distances.get(neighbor)) {
                    if (tentativeDistance < distances.get(neighbor)) {
                        //println(neighbor.value, "overwrote", distances.get(neighbor), "with", tentativeDistance);
                        prev.put(neighbor, new ArrayList<>());
                        prev.get(neighbor).add(currentNode);
                        distances.put(neighbor, tentativeDistance);
                        queue.add(neighbor);
                    } else {
                        //println(neighbor.value, "equalled", distances.get(neighbor), "with", tentativeDistance);
                        if (!prev.get(neighbor).contains(currentNode)) {
                            prev.get(neighbor).add(currentNode);
                        }
                    }
                } else {
                    //println(neighbor.value, "ignored with", tentativeDistance);
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
