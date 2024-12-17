import Management.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Management.IO.println;

public class Solver1 {
    public static Object Evaluate(Input input) {
        long[][] data = input.getInts();

        int registerA = (int) data[0][0];
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
                    double result = registerA / Library.Maths.powLong(2, comboOperand);
                    if (result > Integer.MAX_VALUE) {
                        registerA = Integer.MAX_VALUE;
                    } else if (result < Integer.MIN_VALUE) {
                        registerA = Integer.MIN_VALUE;
                    } else {
                        registerA = (int) result;
                    }
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
                    double result = registerA / Library.Maths.powLong(2, comboOperand);
                    if (result > Integer.MAX_VALUE) {
                        registerB = Integer.MAX_VALUE;
                    } else if (result < Integer.MIN_VALUE) {
                        registerB = Integer.MIN_VALUE;
                    } else {
                        registerB = (int) result;
                    }
                    break;
                }
                case 7: {
                    // CDV
                    double result = registerA / Library.Maths.powLong(2, comboOperand);
                    if (result > Integer.MAX_VALUE) {
                        registerC = Integer.MAX_VALUE;
                    } else if (result < Integer.MIN_VALUE) {
                        registerC = Integer.MIN_VALUE;
                    } else {
                        registerC = (int) result;
                    }
                    break;
                }
            }

            pointer += 2;
        }

        StringBuilder outputStr = new StringBuilder();
        for (int out : output) {
            outputStr.append(out);
            outputStr.append(",");
        }
        return outputStr.substring(0, outputStr.length() - 1);
    }
}
