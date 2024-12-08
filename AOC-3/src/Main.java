public class Main {
    public static void main(String[] args) {
        char[] data = Input.getRaw().toCharArray();

        final char[] expectedMul = "mul(,)".toCharArray();
        final char[] expectedDo = "do()".toCharArray();
        final char[] expectedDont = "don't()".toCharArray();
        // A at 4, B at 5
        int posMul = 0;
        int posDo = 0;
        int posDont = 0;
        StringBuilder numberA = new StringBuilder();
        StringBuilder numberB = new StringBuilder();

        int sum = 0;
        boolean enabled = true;

        for (char c : data) {
            CheckMul: {
                if (!enabled) {
                    posMul = 0;
                    numberA = new StringBuilder();
                    numberB = new StringBuilder();
                    break CheckMul;
                }
                boolean valid = false;
                if (expectedMul[posMul] == c) {
                    valid = true;
                } else {
                    if (posMul == 4) {
                        if (isDigit(c)) {
                            numberA.append(c);
                            posMul--;
                            valid = true;
                        }
                    } else if (posMul == 5) {
                        if (isDigit(c)) {
                            numberB.append(c);
                            posMul--;
                            valid = true;
                        }
                    }
                }
                if (valid) {
                    posMul++;
                    if (posMul == 6) {
                        int A = Integer.parseInt(numberA.toString());
                        int B = Integer.parseInt(numberB.toString());
                        sum += A * B;
                        valid = false;
                    }
                }
                if (!valid) {
                    posMul = 0;
                    numberA = new StringBuilder();
                    numberB = new StringBuilder();
                }
            }
            {
                if (expectedDo[posDo] == c) {
                    posDo++;
                } else {
                    posDo = 0;
                }
                if (posDo == expectedDo.length) {
                    posDo = 0;
                    enabled = true;
                }
            }
            {
                if (expectedDont[posDont] == c) {
                    posDont++;
                } else {
                    posDont = 0;
                }
                if (posDont == expectedDont.length) {
                    posDont = 0;
                    enabled = false;
                }
            }
        }

        System.out.println(sum);
    }

    public static boolean isDigit(char c) {
        final char[] digits = "0123456789".toCharArray();
        for (char d : digits) {
            if (d == c) {
                return true;
            }
        }
        return false;
    }
}