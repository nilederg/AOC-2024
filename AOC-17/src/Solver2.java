import Management.Input;

import java.util.*;

import static Management.IO.forcePrintln;
import static Management.IO.println;

public class Solver2 {
    public static Object Evaluate(Input input) {
        long[][] data = input.getInts();
        long[] opcodes = data[4];
        long[] mandatoryOutputs = data[5];

        if (data[3].length == 0) {
            //return bruteForce(opcodes, mandatoryOutputs, 0, Long.MAX_VALUE, true);
        }

        ArrayList<Integer> chunks = new ArrayList<>();

        Integer[] knowns = new Integer[mandatoryOutputs.length * 3 + 15];
        for (int i = 0; i < knowns.length; i++) {
            knowns[i] = null;
        }

        Set<Integer[]> possibleSolutions = new HashSet<>();
        int nextMandatoryOutputInt = (int) mandatoryOutputs[0];
        int[] nextFullMandatoryOutput = new int[mandatoryOutputs.length - 1];
        for (int i = 0; i < nextFullMandatoryOutput.length; i++) {
            nextFullMandatoryOutput[i] = (int) mandatoryOutputs[i + 1];
        }
        int[] nextMandatoryOutput = new int[] {
                nextMandatoryOutputInt & 1,
                (nextMandatoryOutputInt >> 1) & 1,
                (nextMandatoryOutputInt >> 2) & 1
        };

        propagate(knowns, nextMandatoryOutput, 0, nextFullMandatoryOutput, possibleSolutions, 0);

        int[] fullMandatoryOutput = new int[mandatoryOutputs.length];
        for (int i = 0; i < fullMandatoryOutput.length; i++) {
            fullMandatoryOutput[i] = (int) mandatoryOutputs[i];
        }
        //possibleSolutions = search(fullMandatoryOutput, opcodes);

        long minimum = Long.MAX_VALUE;
        long lastValue = 0;
        Set<Runnable> prints = new HashSet<>();
        for (Integer[] possibleSolution : possibleSolutions) {
            long value = buildFromBits(possibleSolution);
            if (bruteForce(opcodes, mandatoryOutputs, value, value, false) != -1) {
                if (value < minimum) {
                    minimum = value;
                }
                prints.add(() -> {
                    forcePrintln(value, " --- SUCCESS ");
                });
            } else {
                forcePrintln(value, " FAILURE ");
            }
            lastValue = value;
        }
        for (Runnable r : prints) {
            r.run();
        }

        if (minimum == Long.MAX_VALUE) {
            minimum = lastValue;
        }

        println("Minimum value found: ", minimum);

        long range = 20000000L;
        return bruteForce(opcodes, mandatoryOutputs, minimum - range, minimum + range, true);
    }

    public static Set<Integer[]> search(int[] fullMandatoryOutput, long[] opcodes) {
        Set<Integer[]> possibleSolutions = new HashSet<>();
        int nextMandatoryOutputInt = fullMandatoryOutput[0];
        int[] nextFullMandatoryOutput = new int[fullMandatoryOutput.length - 1];
        for (int i = 0; i < nextFullMandatoryOutput.length; i++) {
            nextFullMandatoryOutput[i] = fullMandatoryOutput[i + 1];
        }
        int[] nextMandatoryOutput = deconstruct(nextMandatoryOutputInt);

        for (int i = 0; i < (1 << 25); i ++) {
            Integer[] knownABits = new Integer[fullMandatoryOutput.length * 3 + 25];
            for (int j = 0; j < 15; j ++) {
                knownABits[j] = (i >> j) & 1;
            }
            searchSub(knownABits, 25, 3, nextFullMandatoryOutput, possibleSolutions, opcodes);
        }

        return possibleSolutions;
    }

    public static void searchSub(Integer[] knownABits, int chunkWriteIndex, int outputNumber, int[] fullMandatoryOutput, Set<Integer[]> possibleSolutions, long[] opcodes) {
        int[] evaluateResult = evaluateFromA(buildFromBits(knownABits), opcodes);
        if (evaluateResult.length < outputNumber) {
            return;
        }
        for (int i = 0; i < outputNumber; i++) {
            if (evaluateResult[i] != fullMandatoryOutput[i]) {
                return;
            }
        }
        if (outputNumber == fullMandatoryOutput.length) {
            if (Arrays.equals(evaluateResult, fullMandatoryOutput)) {
                possibleSolutions.add(knownABits);
            }
            return;
        }
        //println(Arrays.toString(knownABits));
        //println("`` Index ", outputNumber, ", ", Arrays.toString(evaluateResult));

        for (int chunkValue = 0; chunkValue < 8; chunkValue++) {
            int[] chunkBits = deconstruct(chunkValue);
            Integer[] recurseKnownABits = Arrays.copyOf(knownABits, knownABits.length);
            recurseKnownABits[chunkWriteIndex] = chunkBits[0];
            recurseKnownABits[chunkWriteIndex + 1] = chunkBits[1];
            recurseKnownABits[chunkWriteIndex + 2] = chunkBits[2];
            searchSub(recurseKnownABits, chunkWriteIndex + 3, outputNumber + 1, fullMandatoryOutput, possibleSolutions, opcodes);
        }
    }

    public static int[][] chunkKnowledge = {
            new int[] {1, 0, 0, 1},
            new int[] {0, 1, 0, 1},
            new int[] {3, 0, 1, 1},
            new int[] {2, 1, 1, 1},
            new int[] {5, 0, 0, 0},
            new int[] {4, 1, 0, 0},
            new int[] {7, 0, 1, 0},
            new int[] {6, 1, 1, 0}
    };

    /**
     * All values little-endian
     * @param knownABits The bits in register A: 0 or 1 if known, null if unknown.
     * @param mandatoryOutput The output that this loop of the program must produce
     * @param chunkIndex The index of the bits of register A that we are working in - represents A's ones place at the point this loop is executed
     * @param fullMandatoryOutput - The remaining outputs that must be satisfied for the rest of the program
     * @param possibleSolutions - A collector of all solutions found by this function
     */
    public static void propagate(Integer[] knownABits, int[] mandatoryOutput, int chunkIndex, int[] fullMandatoryOutput, Set<Integer[]> possibleSolutions, int depth) {
        String depthString = ". ".repeat(depth) + "| ";
        for (int chunkProposition = 0; chunkProposition < 8; chunkProposition++) {
            println(depthString, "> Proposing chunk ", chunkProposition, ", Mandatory output ", Arrays.toString(mandatoryOutput));
            // index 0 here is index chunkIndex in knownABits
            Integer[] newKnownABits = new Integer[12];
            // For the proposed chunk, A must actually contain the aforementioned chunk
            newKnownABits[0] = chunkProposition & 1;
            newKnownABits[1] = (chunkProposition >> 1) & 1;
            newKnownABits[2] = (chunkProposition >> 2) & 1;
            // Get the values for how parts of A correspond to the output, based on our chunk
            int[] knowledge = chunkKnowledge[chunkProposition];
            int knowledgeIndex = 1;
            int mandatoryOutputIndex = 0;
            boolean conflict = false;
            // Set the known values in A to what they must be to produce our output from this chunk
            // If it is not possible for A to be correctly configured and still contain the chunk, abort the chunk test.
            for (int newKnownABitsIndex = knowledge[0]; newKnownABitsIndex <= knowledge[0] + 2; newKnownABitsIndex++) {
                int invert = knowledge[knowledgeIndex];
                knowledgeIndex ++;
                int mandatoryOutputBit = mandatoryOutput[mandatoryOutputIndex];
                mandatoryOutputIndex ++;
                int knownBit = mandatoryOutputBit ^ invert;
                if (newKnownABits[newKnownABitsIndex] == null) {
                    newKnownABits[newKnownABitsIndex] = knownBit;
                } else if (newKnownABits[newKnownABitsIndex] != knownBit) {
                    conflict = true;
                    break;
                }
            }
            if (conflict) {
                continue;
            }
            println(depthString, "  Known from index ", chunkIndex, ", ", Arrays.toString(newKnownABits));

            Integer[] recurseKnownABits = Arrays.copyOf(knownABits, knownABits.length);
            // Merge the newly found bits of A into the previously known bits. If there is a conflict, abort the chunk test.
            for (int newKnownABitsIndex = 0; newKnownABitsIndex < 12; newKnownABitsIndex++) {
                int knownABitsIndex = newKnownABitsIndex + chunkIndex;
                if (recurseKnownABits[knownABitsIndex] == null) {
                    recurseKnownABits[knownABitsIndex] = newKnownABits[newKnownABitsIndex];
                } else if (newKnownABits[newKnownABitsIndex] != null) {
                    if (!Objects.equals(recurseKnownABits[knownABitsIndex], newKnownABits[newKnownABitsIndex])) {
                        conflict = true;
                        break;
                    }
                }
            }
            if (conflict) {
                continue;
            } else {
                println(depthString, "  No conflict, currently ", Arrays.toString(recurseKnownABits));
            }

            // If there are no more outputs to demand, add the solution.
            if (fullMandatoryOutput.length == 0) {
                println(depthString, "# Added solution");
                possibleSolutions.add(recurseKnownABits);
                return;
            }

            // Grab the next desired output and remove from the queue
            int nextMandatoryOutputInt = fullMandatoryOutput[0];
            int[] nextFullMandatoryOutput = new int[fullMandatoryOutput.length - 1];
            for (int i = 0; i < nextFullMandatoryOutput.length; i++) {
                nextFullMandatoryOutput[i] = fullMandatoryOutput[i + 1];
            }
            int[] nextMandatoryOutput = deconstruct(nextMandatoryOutputInt);

            // Continue down the chain. This so far has produced correct values.
            propagate(recurseKnownABits, nextMandatoryOutput, chunkIndex + 3, nextFullMandatoryOutput, possibleSolutions, depth + 1);
        }
    }

    public static long bruteForce(long[] opcodes, long[] desiredOutput, long start, long end, boolean print) {
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = Long.MAX_VALUE;
        }

        if (print) {
            //println("Checking from ", start, " to ", end);
        }

        long testA = start - 1;
        boolean found = false;

        while (testA < end) {
            testA++;

            long registerA = testA;
            long registerB = 0;
            long registerC = 0;

            int pointer = 0;

            int outputIndex = 0;
            boolean success = true;

            Evaluate: while (pointer < opcodes.length) {
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
                        registerB = (int) (comboOperand % 8);
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
                        int outputValue = (int) (comboOperand & 7);
                        if (outputIndex >= desiredOutput.length || outputValue != desiredOutput[outputIndex]) {
                            success = false;
                            break Evaluate;
                        }
                        outputIndex++;
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

            if (outputIndex != desiredOutput.length) {
                success = false;
            }

            if (print && testA % 10000000L == 0L && testA > 0) {
                //forcePrintln(testA);
            }

            if (success) {
                found = true;
                break;
            }
        }

        if (found) {
            return testA;
        } else {
            return -1;
        }
    }

    public static int[] evaluateFromA(long registerA, long[] opcodes) {
        long registerB = 0;
        long registerC = 0;

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

        int[] outputArray = new int[output.size()];
        for (int i = 0; i < output.size(); i++) {
            outputArray[i] = output.get(i);
        }
        return outputArray;
    }

    public static int[] deconstruct(int value) {
        return new int[] {
                value & 1,
                (value >> 1) & 1,
                (value >> 2) & 1
        };
    }

    public static long buildFromBits(Integer[] bits) {
        long value = 0;
        for (int i = 0; i < bits.length; i++) {
            Integer possibleSolutionInt = bits[i];
            if (possibleSolutionInt == null) {
                possibleSolutionInt = 0;
            }
            value += ((long) possibleSolutionInt) << i;
        }
        return value;
    }
}
