import Library.Tuple;
import Management.Input;

import static Management.IO.forcePrintln;

public class Solver2 {
    public static long sumTime = 0L;
    public static long sessions = 0L;

    public static long Evaluate(Input input) {
        long[][] data = input.getInts();

        if (sessions != 0) {
            long avgTime = Math.round((float) sumTime / sessions);
            forcePrintln("Predicted compute time: " + ((avgTime * data.length) / 1000) + " seconds");
        }

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

        long mostBananas = bruteForceBananas(data, information);

        long endTime = System.currentTimeMillis();
        double timePerLine = (double) (endTime - startTime) / data.length;
        long timePerLineMS = Math.round(timePerLine);
        sumTime += timePerLineMS;
        sessions++;
        forcePrintln(timePerLineMS, " ms per line");

        return mostBananas;
    }

    private static long bruteForceBananas(long[][] data, Tuple<long[], long[]>[] information) {
        long mostBananas = Long.MIN_VALUE;
        for (int c1 = -9; c1 <= 9; c1++) {
            for (int c2 = -9; c2 <= 9; c2++) {
                for (int c3 = -9; c3 <= 9; c3++) {
                    for (int c4 = -9; c4 <= 9; c4++) {
                        if (Math.abs(c1 + c2 + c3 + c4) > 18) {
                            continue;
                        }
                        long[] changes = new long[] {c1, c2, c3, c4};
                        long bananasTotal = 0;
                        for (int i = 0; i < data.length; i++) {
                            long bananas = bananas(changes, information[i].getA(), information[i].getB());
                            bananasTotal += bananas;
                        }
                        if (bananasTotal > mostBananas) {
                            mostBananas = bananasTotal;
                        }
                    }
                }
            }
        }
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

    public static long bananas(long[] changesSearch, long[] secrets, long[] changes) {
        for (int i = 1; i < changes.length - 3; i++) {
            boolean found = true;
            for (int j = 0; j < changesSearch.length; j++) {
                if (changesSearch[j] != changes[i + j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return secrets[i+3];
            }
        }
        return 0;
    }
}
