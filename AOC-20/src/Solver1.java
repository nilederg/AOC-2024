import Library.FreeDimensionalList;
import Library.Node;
import Library.Point2;
import Library.Vector2;
import Management.Input;

import java.util.HashSet;
import java.util.Set;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        char[][] grid = input.getCharGrid();

        long standardSpeed = findFastest(grid);

        long savingsThreshold = 100;
        long total = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                forcePrintln("i " + i);
                char[][] newGrid = new char[grid.length][grid[0].length];
                for (int k = 0; k < grid.length; k++) {
                    for (int l = 0; l < grid[k].length; l++) {
                        newGrid[k][l] = grid[k][l];
                    }
                }
                if (newGrid[i][j] != '#') {
                    continue;
                }
                newGrid[i][j] = '.';
                long newSpeed = findFastest(newGrid);
                long savings = standardSpeed - newSpeed;
                println("Saved ", savings);
                if (savings >= savingsThreshold) {
                    println("Over threshold");
                    total ++;
                }
            }
        }
        return total;
    }

    public static long findFastest(char[][] grid) {
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

        return Node.dijkstra(startNode, nodeSet).get(endNode);
    }
}
