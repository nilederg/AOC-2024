import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] strings = Input.getLines();
        char[][] chars = new char[strings.length][];

        for (int i = 0; i < strings.length; i++) {
            chars[i] = strings[i].toCharArray();
        }

        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            char[] row = chars[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j];
                if (c != 'A') {
                    continue;
                }
                if (i < 1 || i >= chars.length - 1 || j < 1 || j >= chars[0].length - 1) {
                    continue;
                }
                char c1 = chars[i-1][j-1];
                char c2 = chars[i+1][j-1];
                char c3 = chars[i-1][j+1];
                char c4 = chars[i+1][j+1];
                if (!((c1 == 'M' && c4 == 'S') || (c4 == 'M' && c1 == 'S'))) {
                    continue;
                }
                if (!((c2 == 'M' && c3 == 'S') || (c3 == 'M' && c2 == 'S'))) {
                    continue;
                }
                count ++;
            }
        }

        System.out.println(count);
    }
}