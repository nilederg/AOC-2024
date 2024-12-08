import java.util.ArrayList;
import java.util.Arrays;

public class Input {
    public static String getString() {
        return getRealString();
    }
    public static String getRealString() {
        return "........5..................................e..3...\n" +
                ".......q...........m................e.............\n" +
                "....m.......................................e.....\n" +
                ".........................................C........\n" +
                ".u.m........................8.....................\n" +
                "...........7......9.......8...........F...s.......\n" +
                "6...q..............................s..............\n" +
                "..................................................\n" +
                "..................................................\n" +
                "..................................................\n" +
                "..........9....................F..................\n" +
                ".................................M....D...........\n" +
                ".........U........................................\n" +
                "..q................................8..............\n" +
                ".......9..........................................\n" +
                "0....6.....................e..Qs...............F..\n" +
                ".................................Q...D............\n" +
                ".0.u....................................2.........\n" +
                "..................................................\n" +
                "........u................Q........................\n" +
                ".....E........1...................................\n" +
                "...n....v....................................3....\n" +
                "......u..0................N.......................\n" +
                "............................................z.....\n" +
                ".........7....U.........4.....Z...Q.....D.....V...\n" +
                "..............n1.........f.................2......\n" +
                "E.............................f..............z....\n" +
                "...E........1.Z.......U......................D....\n" +
                ".......n...v....7Z...N............................\n" +
                "..........7..N.....Zf...........................3.\n" +
                "................................b............V....\n" +
                "............4..................................9..\n" +
                "..n...v........................5................2.\n" +
                ".........v.................5.........S............\n" +
                "..........................s.......................\n" +
                ".....U.........4..C.....................S..V......\n" +
                "..................................................\n" +
                "......................c........b............M.....\n" +
                "...........4.Wc....d.......1.....b.....S..........\n" +
                "..E........c............................5......z..\n" +
                "..............w..C....................SM.2........\n" +
                "........................d.........................\n" +
                "...............c......C3..........................\n" +
                "...............w....W.............................\n" +
                "..................................................\n" +
                ".........d.......B....w...........................\n" +
                "....B.....W.......dw..........................M...\n" +
                "...............W......................N...V.......\n" +
                ".B................................................\n" +
                "....................B...............b.............\n";
    }
    public static String getSampleString() {
        return "............\n" +
                "........0...\n" +
                ".....0......\n" +
                ".......0....\n" +
                "....0.......\n" +
                "......A.....\n" +
                "............\n" +
                "............\n" +
                "........A...\n" +
                ".........A..\n" +
                "............\n" +
                "............";
    }
    public static char[] getRaw() {
        return getString().toCharArray();
    }

    /**
     *
     * @return An array of each line in the input from top to bottom, in string format
     */
    public static String[] extractLines(char[] data) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder line = new StringBuilder();
        for (char c : data) {
            if (c == '\n') {
                lines.add(line.toString());
                line = new StringBuilder();
            } else {
                line.append(c);
            }
        }
        if (!line.isEmpty()) {
            lines.add(line.toString());
        }
        return lines.toArray(new String[0]);
    }
    public static String[] getLines() {
        return extractLines(getRaw());
    }

    public static char[][] extractCharGrid(char[] data) {
        String[] lines = extractLines(data);
        char[][] grid = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return grid;
    }
    public static char[][] getCharGrid() {
        return extractCharGrid(getRaw());
    }

    /**
     * Use for integer inputs
     * @return An array of each line in the input, where each line is an array of the numbers it contains
     */
    public static long[][] extractInts(char[] data) {
        String[] lines = getLines();
        ArrayList<long[]> intLines = new ArrayList<>();
        for (String line : lines) {
            char[] chars = line.toCharArray();
            ArrayList<Long> integers = new ArrayList<>();
            StringBuilder num = new StringBuilder();
            for (char c : chars) {
                if (!isDigit(c)) {
                    if (!num.isEmpty()) {
                        integers.add(Long.parseLong(num.toString()));
                        num = new StringBuilder();
                    }
                    continue;
                }
                num.append(c);
            }
            if (!num.isEmpty()) {
                integers.add(Long.parseLong(num.toString()));
            }
            long[] intline = new long[integers.size()];
            for (int i = 0; i < intline.length; i++) {
                intline[i] = integers.get(i);
            }
            intLines.add(intline);
        }
        return intLines.toArray(new long[intLines.size()][]);
    }
    public static long[][] getInts() {
        return extractInts(getRaw());
    }

    /**
     * Gets rid of characters within the string
     * @param exclude The characters to remove
     * @return An array of the characters constituting the input, with all undesired characters removed
     */
    public static char[] trim(char[] data, char[] exclude) {
        ArrayList<Character> trimmedList = new ArrayList<>();
        for (char c : data) {
            for (char t : exclude) {
                if (c != t) {
                    trimmedList.add(c);
                }
            }
        }
        char[] trimmed = new char[trimmedList.size()];
        for (int i = 0; i < trimmedList.size(); i++) {
            trimmed[i] = trimmedList.get(i);
        }
        return trimmed;
    }
    public static char[] getTrimmed(char[] exclude) {
        return trim(getRaw(), exclude);
    }

    /**
     * Breaks the string up by lines and spaces
     * @return An array of lines where each line is an array of chunks. Chunks within the input string are separated by newlines and spaces
     */
    public static String[][] separateBySpaces(char[] data) {
        String[] lines = extractLines(data);
        ArrayList<String[]> separatedLines = new ArrayList<>();
        for (String line : lines) {
            separatedLines.add(line.split("\\s+"));
        }
        return separatedLines.toArray(new String[0][]);
    }
    public static String[][] getSeparatedBySpaces() {
        return separateBySpaces(getRaw());
    }

    static boolean isDigit(char c) {
        final char[] digits = "0123456789".toCharArray();
        for (char d : digits) {
            if (d == c) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] collapse(T[][] array) {
        ArrayList<T> collapsed = new ArrayList<>();
        for (T[] row : array) {
            collapsed.addAll(Arrays.asList(row));
        }
        return collapsed.toArray((T[]) new Object[collapsed.size()]);
    }
}
