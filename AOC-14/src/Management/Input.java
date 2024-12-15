package Management;

import java.util.ArrayList;
import java.util.Arrays;

public class Input {
    private final String data;

    public Input(String data) {
        this.data = data;
    }

    public String getString() {
        return data;
    }
    public char[] getRaw() {
        return getString().toCharArray();
    }

    /**
     *
     * @return An array of each line in the input from top to bottom, in string format
     */
    public String[] extractLines(char[] data) {
        ArrayList<String> lines = new ArrayList<>();
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
    public String[] getLines() {
        return extractLines(getRaw());
    }

    public char[][] extractCharGrid(char[] data) {
        String[] lines = extractLines(data);
        char[][] grid = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return grid;
    }
    public char[][] getCharGrid() {
        return extractCharGrid(getRaw());
    }

    /**
     * Use for integer inputs
     * @return An array of each line in the input, where each line is an array of the numbers it contains
     */
    public long[][] extractInts(char[] data) {
        String[] lines = getLines();
        ArrayList<long[]> intLines = new ArrayList<>();
        for (String line : lines) {
            char[] chars = line.toCharArray();
            ArrayList<Long> integers = new ArrayList<>();
            StringBuilder num = new StringBuilder();
            boolean negate = false;
            for (char c : chars) {
                if (!isDigit(c) && c != '-') {
                    if (!num.isEmpty()) {
                        long integer = Long.parseLong(num.toString());
                        if (negate) {
                            integer = -integer;
                        }
                        integers.add(integer);
                        num = new StringBuilder();
                        negate = false;
                    }
                    continue;
                }
                if (c == '-') {
                    negate = true;
                } else {
                    num.append(c);
                }
            }
            if (!num.isEmpty()) {
                long integer = Long.parseLong(num.toString());
                if (negate) {
                    integer = -integer;
                }
                integers.add(integer);
            }
            long[] intline = new long[integers.size()];
            for (int i = 0; i < intline.length; i++) {
                intline[i] = integers.get(i);
            }
            intLines.add(intline);
        }
        return intLines.toArray(new long[intLines.size()][]);
    }
    public long[][] getInts() {
        return extractInts(getRaw());
    }

    /**
     * Gets rid of characters within the string
     * @param exclude The characters to remove
     * @return An array of the characters constituting the input, with all undesired characters removed
     */
    public char[] trim(char[] data, char[] exclude) {
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
    public char[] getTrimmed(char[] exclude) {
        return trim(getRaw(), exclude);
    }

    public char[] filter(char[] data, char[] include) {
        ArrayList<Character> filteredList = new ArrayList<>();
        for (char c : data) {
            for (char t : include) {
                if (c == t) {
                    filteredList.add(c);
                }
            }
        }
        char[] filtered = new char[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            filtered[i] = filteredList.get(i);
        }
        return filtered;
    }
    public char[] getFiltered(char[] exclude) {
        return filter(getRaw(), exclude);
    }

    /**
     * Breaks the string up by lines and spaces
     * @return An array of lines where each line is an array of chunks. Chunks within the input string are separated by newlines and spaces
     */
    public String[][] separateBySpaces(char[] data) {
        String[] lines = extractLines(data);
        ArrayList<String[]> separatedLines = new ArrayList<>();
        for (String line : lines) {
            separatedLines.add(line.split("\\s+"));
        }
        return separatedLines.toArray(new String[0][]);
    }
    public String[][] getSeparatedBySpaces() {
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
