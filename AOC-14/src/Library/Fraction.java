package Library;

public class Fraction {
    private final long numerator;
    private final long denominator;

    public Fraction(long numerator, long denominator) {
        long gcd = Miscellaneous.gcd(numerator, denominator);
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    public Fraction(long value) {
        this.numerator = value;
        this.denominator = 1;
    }

    public static Fraction fromDouble(double value, double tolerance) {
        long testDenominator = 1;
        while (true) {
            long testNumerator = (long) (value * testDenominator);
            double approximation = (double) testNumerator / testDenominator;
            double difference = Math.abs(approximation - value);
            if (difference > tolerance) {
                testDenominator ++;
            } else {
                return new Fraction(testNumerator, testDenominator);
            }
        }
    }

    public boolean isInteger() {
        if (denominator == 1) {
            return true;
        }
        return numerator % denominator == 0;
    }
    public int signum() {
        if (numerator == 0) {
            return 0;
        }
        int numSign = numerator < 0 ? -1 : 1;
        int denomSign = denominator < 0 ? -1 : 1;
        return numSign * denomSign;
    }

    public float toFloat() {
        return (float) numerator / denominator;
    }
    public double toDouble() {
        return (double) numerator / denominator;
    }
    public int toInt() {
        long value = toLong();
        if (value >= (long) Integer.MAX_VALUE || value <= (long) Integer.MIN_VALUE) {
            throw new ArithmeticException("Fraction value is out of range for requested type");
        }
        return (int) value;
    }
    public long toLong() {
        double value = toDouble();
        if (value >= (double) Long.MAX_VALUE || value <= (double) Long.MIN_VALUE) {
            throw new ArithmeticException("Fraction value is out of range for requested type");
        }
        return Math.round(value);
    }

    public Fraction reciprocal() {
        return new Fraction(denominator, numerator);
    }

    public static Fraction multiply(Fraction a, Fraction b) {
        return new Fraction(a.numerator * b.numerator, a.denominator * b.denominator);
    }

    public static Fraction divide(Fraction a, Fraction b) {
        return multiply(a, b.reciprocal());
    }

}
