import Library.Tuple;
import Management.Input;

import java.util.HashMap;
import java.util.Map;

import static Management.IO.forcePrintln;

public class Solver2 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();

        forcePrintln("Predicted compute time: " + ((1118 * data.length) / 1000) + " seconds");

        Tuple<long[], long[]>[] information = new Tuple[data.length];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            long secret = data[i][0];
            long[] secrets = new long[2001];
            secrets[0] = secret % 10;
            for (int j = 1; j < 2001; j++) {
                secret = evolve(secret);
                secrets[j] = secret % 10;
            }
            secrets[2000] = secret;
            long[] changes = new long[2001];
            for (int j = 1; j < 2001; j++) {
                changes[j] = secrets[j] - secrets[j - 1];
            }
            information[i] = new Tuple<>(secrets, changes);
        }
        forcePrintln("Information generated");

        long mostBananas = Long.MIN_VALUE;
        long[] changesBest = null;
        for (int c1 = -9; c1 <= 9; c1++) {
            for (int c2 = -9; c2 <= 9; c2++) {
                for (int c3 = -9; c3 <= 9; c3++) {
                    for (int c4 = -9; c4 <= 9; c4++) {
                        long[] changes = new long[] {c1, c2, c3, c4};
                        long bananasTotal = 0;
                        for (int i = 0; i < data.length; i++) {
                            long secret = data[i][0];
                            long bananas = bananas(changes, information[i].getA(), information[i].getB());
                            bananasTotal += bananas;
                        }
                        //println(Arrays.toString(changes), " ", bananasTotal);
                        if (bananasTotal > mostBananas) {
                            mostBananas = bananasTotal;
                            changesBest = changes;
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        double timePerLine = (double) (endTime - startTime) / data.length;
        long timePerLineMS = Math.round(timePerLine);
        forcePrintln(timePerLineMS, " ms per line");

        return mostBananas;
    }

    public static long evolve(long secret) {
        secret = secret ^ (secret << 6);
        secret = secret & 16777215L;
        secret = secret ^ (secret >> 5);
        secret = secret & 16777215L;
        secret = secret ^ (secret << 11);
        secret = secret & 16777215L;
        return secret;
    }

    public static Map<Long, long[]> secretSolve = new HashMap<>();
    public static Map<Long, long[]> changesSolve = new HashMap<>();

    public static long bananas(long[] changesSearch, long[] secrets, long[] changes) {
        for (int i = 1; i < changes.length - 3; i++) {
            boolean found = true;
            for (int j = 0; j < changesSearch.length; j++) {
                if (changesSearch[j] != changes[i+j]) {
                    found = false;
                }
            }
            if (found) {
                return secrets[i+3];
            }
        }
        return 0;
    }

    public static long bananas(long[] changesSearch, long secretFirst) {
        long[] secrets = new long[2001];
        if (secretSolve.containsKey(secretFirst)) {
            secrets = secretSolve.get(secretFirst);
        } else {
            long secret = secretFirst;
            secrets[0] = secret % 10;
            for (int j = 1; j < 2001; j++) {
                secret = evolve(secret);
                //println(secret);
                secrets[j] = secret % 10;
            }
            secrets[2000] = secret;
            secretSolve.put(secretFirst, secrets);
        }

        long[] changes = new long[2001];
        if (changesSolve.containsKey(secretFirst)) {
            changes = changesSolve.get(secretFirst);
        } else {
            for (int i = 1; i < 2001; i++) {
                changes[i] = secrets[i] - secrets[i - 1];
            }
            //println(Arrays.toString(changes));
            changesSolve.put(secretFirst, changes);
        }

        return bananas(changesSearch, secrets, changes);
    }
}
