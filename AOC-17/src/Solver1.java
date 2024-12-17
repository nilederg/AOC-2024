import Management.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Management.IO.println;

public class Solver1 {
    public static Object Evaluate(Input input) {
        long[][] data = input.getInts();

        long registerA = data[0][0];
        long registerB = data[1][0];
        long registerC = data[2][0];

        long[] opcodes = data[4];

        int pointer = 0;

        ArrayList<Integer> output = new ArrayList<>();

        while (pointer < opcodes.length) {
            int literalOperand = (int) opcodes[pointer + 1];
            long comboOperand = switch (literalOperand) {
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
                    registerA = registerA >> comboOperand;
                    break;
                }
                case 1: {
                    // BXL
                    registerB = registerB ^ literalOperand;
                    break;
                }
                case 2: {
                    // BST
                    registerB = comboOperand & 7;
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
                    output.add((int) (comboOperand & 7));
                    break;
                }
                case 6: {
                    // BDV
                    registerB = registerA >> comboOperand;
                    break;
                }
                case 7: {
                    // CDV
                    registerC = registerA >> comboOperand;
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
