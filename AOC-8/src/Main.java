import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        char[][] data = Input.getCharGrid();

        boolean[][] antinodes1 = new boolean[data.length][data[0].length];
        boolean[][] antinodes2 = new boolean[data.length][data[0].length];
        HashMap<Character, ArrayList<Point>> antennaMap = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] != '.') {
                    if (antennaMap.get(data[i][j]) == null) {
                        antennaMap.put(data[i][j], new ArrayList<>());
                    }
                    antennaMap.get(data[i][j]).add(new Point(i, j));
                }
            }
        }

        char[] freqValues = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        for (char freq : freqValues) {
            if (!antennaMap.containsKey(freq)) {
                continue;
            }
            ArrayList<Point> antennae = antennaMap.get(freq);
            for (Point antenna : antennae) {
                for (Point antenna2 : antennae) {
                    if (antenna == antenna2) {
                        continue;
                    }
                    Point vector = new Point(antenna2.x - antenna.x, antenna2.y - antenna.y);
                    One:
                    {
                        Point antinode1 = new Point(antenna2.x + vector.x, antenna2.y + vector.y);
                        if (antinode1.x < 0 || antinode1.y < 0 || antinode1.x >= antinodes1[0].length || antinode1.y >= antinodes1.length) {
                            break One;
                        }
                        antinodes1[antinode1.x][antinode1.y] = true;
                    }
                    Two:
                    {
                        int vectorGCD = gcd(Math.abs(vector.x), Math.abs(vector.y));
                        vector = new Point(vector.x / vectorGCD, vector.y / vectorGCD);
                        Point antinode2 = antenna2;
                        while (!(antinode2.x < 0 || antinode2.y < 0 || antinode2.x >= antinodes2[0].length || antinode2.y >= antinodes2.length)) {
                            antinodes2[antinode2.x][antinode2.y] = true;
                            antinode2 = new Point(antinode2.x + vector.x, antinode2.y + vector.y);
                        }
                    }
                }
            }
        }

        int total1 = 0;
        for (boolean[] row : antinodes1) {
            for (boolean b : row) {
                if (b) {
                    total1++;
                }
            }
        }

        int total2 = 0;
        for (boolean[] row : antinodes2) {
            for (boolean b : row) {
                if (b) {
                    total2++;
                }
            }
        }

        System.out.println("Part one: Total number of antinodes");
        System.out.println(total1);

        System.out.println("Part two: Total number of antinodes");
        System.out.println(total2);
    }

    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
}