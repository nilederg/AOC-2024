import Library.*;
import Management.Input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        char[][] grid = input.getCharGrid();

        FreeDimensionalList<Node<Point2>> nodes = new FreeDimensionalList<>(2);
        Set<Node<Point2>> nodeSet = new HashSet<>();
        Node<Point2> startNode = null;
        Node<Point2> endNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '#') {
                    Point2 p = new Point2(j, i);
                    Node<Point2> n = new Node<>(p, 0);
                    if (grid[i][j] == 'S') {
                        startNode = n;
                    }
                    if (grid[i][j] == 'E') {
                        endNode = n;
                    }
                    nodes.set(n, j, i);
                    nodeSet.add(n);
                }
            }
        }

        for (Node<Point2> n : nodeSet) {
            Point2 p = n.value;
            for (Vector2 dir : Vector2.VON_NEUMANN_NEIGHBORHOOD) {
                Point2 neighborPoint = p.add(dir);
                if (neighborPoint.x < 0 || neighborPoint.y < 0 || neighborPoint.x >= grid[0].length || neighborPoint.y >= grid.length) {
                    continue;
                }
                Node<Point2> neighbor = nodes.get((int) neighborPoint.x, (int) neighborPoint.y);
                if (neighbor == null) {
                    continue;
                }
                n.addConnection(neighbor, 1);
            }
        }

        HashMap<Node<Point2>, Long> standardDistances =  Node.dijkstra(startNode, nodeSet);
        long standardSpeed = standardDistances.get(startNode);

        long savingsThreshold = 100;
        long total = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Point2 linkEndPoint = new Point2(j, i);
                Node<Point2> linkEndNode = nodes.get((int) linkEndPoint.x, (int) linkEndPoint.y);
                if (linkEndNode == null) {
                    continue;
                }
                for (int k = 0; k < grid.length; k++) {
                    for (int l = 0; l < grid[0].length; l++) {
                        long linkDistance = Math.abs(i - k) + Math.abs(j - l);
                        if (linkDistance > 20) {
                            continue;
                        }
                        Point2 linkStartPoint = new Point2(l, k);
                        Node<Point2> linkStartNode = nodes.get((int) linkStartPoint.x, (int) linkStartPoint.y);
                        if (linkStartNode == null) {
                            continue;
                        }
                        long previousDistance = standardDistances.get(linkStartNode);
                        long newDistance = standardDistances.get(linkEndNode) + linkDistance;
                        long saved = previousDistance - newDistance;
                        if (saved >= savingsThreshold) {
                            total ++;
                        }
                    }
                }
            }
        }
        return total;
    }
}
