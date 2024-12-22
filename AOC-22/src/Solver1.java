import Management.Input;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        long[][] data = input.getInts();

        long bananas = 0;
        for (int i = 0; i < data.length; i++) {
            long secret = data[i][0];
            long[] secrets = new long[2000];
            for (int j = 0; j < 2000; j ++) {
                secrets[j] = secret;
                secret = evolve(secret);
            }
            bananas += secret;
        }
        return bananas;
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
}
