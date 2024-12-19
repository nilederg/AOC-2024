import Library.Quadruplet;
import Library.Test;
import Library.Triplet;
import Management.IO;
import Management.Input;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Main {
    static Function<Input, Object> solve1 = Solver1::Evaluate;
    static Function<Input, Object> solve2 = Solver2::Evaluate;
    static float delay = 0.5f;

    public static void main(String[] args) throws InterruptedException {
        if (Test.DEBUG) {
            Test.main();
            return;
        }

        int delayInt = Math.round(delay * 1000);

        // Determine relevant values based on current problem phase
        Function<Input, Object> solve;
        long[] tooLow = null;
        long[] tooHigh = null;
        if (Inputs.phase == 1) {
            solve = solve1;
            tooLow = Inputs.tooLow1;
            tooHigh = Inputs.tooHigh1;
        } else if (Inputs.phase == 2) {
            solve = solve2;
            tooLow = Inputs.tooLow2;
            tooHigh = Inputs.tooHigh2;
        } else {
            throw new RuntimeException("Invalid phase. Set it in Inputs.java to be 1 or 2.");
        }

        boolean checks = false;

        // Send startup messages
        int datasets = 0;
        if (Inputs.trueData.isEmpty()) {
            System.out.println("# Input data not found.");
        } else {
            System.out.println("# Found input data.");
            datasets ++;
        }
        ArrayList<Triplet<String, Object, Boolean>> samples = new ArrayList<>();
        for (Quadruplet<String, Object, Object, Boolean> sample : Inputs.samples) {
            if (sample.getA() == null || sample.getA().isEmpty()) {
                continue;
            }
            datasets++;

            Object result = null;
            if (Inputs.phase == 1) {
                result = sample.getB();
            } else {
                result = sample.getC();
            }
            samples.add(new Triplet<>(sample.getA(), result, sample.getD()));

            if (result != null) {
                System.out.println("# Found sample data and result.");
                checks = true;
            } else {
                System.out.println("# Found sample data without expected result.");
            }
        }
        if (datasets == 0) {
            return;
        }
        System.out.print("# Running AOC problem phase " + Inputs.phase + " using " + datasets + " dataset");
        if (Inputs.phase == 1) {
            System.out.println(".");
        } else {
            System.out.println("s.");
        }

        boolean allPassed = true;

        // Test from sample data
        int index = 1;
        Sample: for (Triplet<String, Object, Boolean> sample : samples) {
            TimeUnit.MILLISECONDS.sleep(delayInt);
            System.out.println("\nTesting from sample " + index);
            index++;
            Object result = 0;
            IO.printable = sample.getC();
            IO.prints = 0;
            try {
                result = solve.apply(new Input(sample.getA()));
            } catch (Exception e) {
                IO.extraLineIfNecessary();
                System.out.println("| Sample test threw an exception.");
                e.printStackTrace();
                System.out.println("\n\n\n");
                allPassed = false;
                continue Sample;
            }
            IO.extraLineIfNecessary();
            Object expectedResult = sample.getB();
            if (expectedResult != null) {
                if (result.equals(expectedResult)) {
                    System.out.println("| Sample test passed with " + result + ".");
                } else {
                    System.out.println("| Sample test failed. Returned " + result + ".");
                    System.out.println("                      Expected " + expectedResult + ".");
                    if (expectedResult instanceof Integer || expectedResult instanceof Long) {
                        long expectedResultLong = ((Number) expectedResult).longValue();
                        long resultLong = ((Number) result).longValue();
                        if (resultLong > expectedResultLong) {
                            System.out.println("                       Over by " + (resultLong - expectedResultLong) + ".");
                        } else {
                            System.out.println("                      Under by " + (expectedResultLong - resultLong) + ".");
                        }
                    }
                    allPassed = false;
                }
            } else {
                System.out.println("| Sample test returned " + result + ".");
            }
        }

        // Test from actual input data
        IO.printable = Inputs.printTrueData;
        Actual: if (!Inputs.trueData.isEmpty()) {
            TimeUnit.MILLISECONDS.sleep(delayInt);

            System.out.println("\nEvaluating from problem input");
            Object result = 0;
            IO.prints = 0;
            try {
                result = solve.apply(Inputs.getData());
            } catch (Exception e) {
                IO.extraLineIfNecessary();
                System.out.println("| Solver threw an exception.");
                e.printStackTrace();
                System.out.println("\n\n\n");
                break Actual;
            }
            IO.extraLineIfNecessary();
            System.out.println("| Evaluation of problem input: " + result + ".");
            if (result instanceof Integer || result instanceof Long) {
                long resultLong = ((Number) result).longValue();
                if (tooLow.length != 0) {
                    boolean withinTolerance = true;
                    for (long bound : tooLow) {
                        if (resultLong <= bound) {
                            withinTolerance = false;
                            break;
                        }
                    }
                    if (withinTolerance) {
                        System.out.println("| Solution within lower bound");
                    } else {
                        System.out.println("| Solution is too low");
                        allPassed = false;
                    }
                }
                if (tooHigh.length != 0) {
                    boolean withinTolerance = true;
                    for (long bound : tooHigh) {
                        if (resultLong >= bound) {
                            withinTolerance = false;
                            break;
                        }
                    }
                    if (withinTolerance) {
                        System.out.println("| Solution within upper bound");
                    } else {
                        System.out.println("| Solution is too high");
                        allPassed = false;
                    }
                }
            }
            if (checks) {
                if (allPassed) {
                    System.out.println("| All checks passed");
                } else {
                    System.out.println("| Checks failed");
                }
            }
        }
    }
}