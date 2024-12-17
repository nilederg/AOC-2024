package Library;

import java.util.ArrayList;

public class Maths {
    public static long factorialLong(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static int factorial(int n) {
        return (int) factorialLong(n);
    }

    public static int choose(int n, int k) {
        if (k > n) {
            throw new IllegalArgumentException();
        }
        int denom2 = n - k;
        int divisor;
        int primaryDiv;
        if (k > denom2) {
            divisor = denom2;
            primaryDiv = k;
        } else {
            divisor = k;
            primaryDiv = denom2;
        }
        long dividendFact = 1;
        for (int i = primaryDiv + 1; i <= n; i++) {
            dividendFact *= i;
        }
        long divisorFact = factorialLong(divisor);
        return (int) (dividendFact / divisorFact);
    }
    public static int choosePascal(int n, int k) {
        if (k > n) {
            throw new IllegalArgumentException();
        }
        ArrayList<int[]> rows = new ArrayList<>();
        rows.add(new int[]{1});
        for (int i = 1; i <= n; i++) {
            int[] prevRow = rows.get(rows.size() - 1);
            int[] row = new int[i+1];
            row[0] = 1;
            row[row.length-1] = 1;
            for (int j = 1; j < row.length - 1; j++) {
                row[j] = prevRow[j-1] + prevRow[j];
            }
            rows.add(row);
        }
        return rows.get(n)[k];
    }

    public static long powLong(int n, int k) {
        if (k == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long product = 1;
        for (int i = 1; i <= k; i ++) {
            product *= n;
        }
        return product;
    }
    public static int pow(int n, int k) {
        return (int) powLong(n, k);
    }
}
