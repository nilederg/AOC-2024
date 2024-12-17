import Management.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static Object Evaluate(Input input) {
        long[][] data = input.getInts();

        int testA = 0;

        while (true) {
            testA++;
            int registerA = testA;
            int registerB = (int) data[1][0];
            int registerC = (int) data[2][0];

            long[] opcodes = data[4];

            int pointer = 0;

            ArrayList<Integer> output = new ArrayList<>();

            while (pointer < opcodes.length) {
                int literalOperand = (int) opcodes[pointer + 1];
                int comboOperand = switch (literalOperand) {
                    case 0 -> 0;
                    case 1 -> 1;
                    case 2 -> 2;
                    case 3 -> 3;
                    case 4 -> registerA;
                    case 5 -> registerB;
                    case 6 -> registerC;
                    default -> throw new IllegalStateException("Unexpected value: " + literalOperand);
                };

                int opcode = (int) opcodes[pointer];

                switch (opcode) {
                    case 0: {
                        // ADV
                        int result = registerA / (1 << comboOperand);
                        registerA = result;
                        break;
                    }
                    case 1: {
                        // BXL
                        registerB = registerB ^ literalOperand;
                        break;
                    }
                    case 2: {
                        // BST
                        registerB = comboOperand % 8;
                        break;
                    }
                    case 3: {
                        // JNZ
                        if (registerA != 0) {
                            pointer = literalOperand - 2;
                        }
                        break;
                    }
                    case 4: {
                        // BXC
                        registerB = registerB ^ registerC;
                        break;
                    }
                    case 5: {
                        // OUT
                        output.add(comboOperand % 8);
                        break;
                    }
                    case 6: {
                        // BDV
                        int result = registerA / (1 << comboOperand);
                        registerB = result;
                        break;
                    }
                    case 7: {
                        // CDV
                        int result = registerA / (1 << comboOperand);
                        registerC = result;
                        break;
                    }
                }

                pointer += 2;
            }

            boolean success = true;
            if (output.size() != opcodes.length) {
                success = false;
            }
            for (int i = 0; i < output.size(); i++) {
                if (output.get(i) != opcodes[i]) {
                    success = false;
                    break;
                }
            }

            if (success) {
                forcePrintln(output.toString());
                forcePrintln(Arrays.toString(opcodes));
                break;
            }
        }

        return testA;
    }
}
