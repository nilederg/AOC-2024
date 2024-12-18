package Library;

import java.util.ArrayList;
import java.util.HashMap;

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
}
