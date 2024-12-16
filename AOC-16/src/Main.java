import Management.IO;
import Management.Input;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Main {
    static Function<Input, Long> solve1 = Solver1::Evaluate;
    static Function<Input, Long> solve2 = Solver2::Evaluate;

    public static void main(String[] args) throws InterruptedException {
        // Determine relevant values based on current problem phase
        Function<Input, Long> solve;
        Long sampleResult = null;
        if (Inputs.phase == 1) {
            solve = solve1;
            sampleResult = Inputs.sampleResult1;
        } else if (Inputs.phase == 2) {
            solve = solve2;
            sampleResult = Inputs.sampleResult2;
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
        Input sampleOverride = null;
        if (Inputs.sampleData.isEmpty()) {
            System.out.println("# No sample data found.");
        } else if (!(sampleResult == null)) {
            System.out.println("# Found sample data and result.");
            datasets++;
            checks = true;
        } else {
            System.out.println("# Found sample data without expected result.");
            datasets++;
        }
        if (!Inputs.sampleOverride.isEmpty()) {
            sampleOverride = new Input(Inputs.sampleOverride);
            System.out.println("# Sample override found. Original sample will run silently.");
            IO.printable = false;
            datasets++;
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
        Sample: if (!Inputs.sampleData.isEmpty()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\nTesting from sample\n| ");
            long result = 0;
            IO.prints = 0;
            try {
                result = solve.apply(Inputs.getSample());
            } catch (Exception e) {
                IO.extraLine();
                System.out.println("| Sample test threw an exception.");
                e.printStackTrace();
                System.out.println("\n\n\n");
                allPassed = false;
                break Sample;
            }
            IO.extraLine();
            if (!(sampleResult == null)) {
                if (result == sampleResult) {
                    System.out.println("| Sample test passes with " + result + ".");
                } else {
                    System.out.println("| Sample test failed. Returned " + result + ".");
                    System.out.println("                      Expected " + sampleResult + ".");
                    if (result > sampleResult) {
                        System.out.println("                       Over by " + (result - sampleResult) + ".");
                    } else {
                        System.out.println("                      Under by " + (sampleResult - result) + ".");
                    }
                    allPassed = false;
                }
            } else {
                System.out.println("| Sample test returned " + result + ".");
            }
        }

        IO.printable = true;
        SampleOverride: if (sampleOverride != null) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\nTesting from override sample\n| ");
            long result = 0;
            IO.prints = 0;
            try {
                result = solve.apply(sampleOverride);
            } catch (Exception e) {
                IO.extraLine();
                System.out.println("| Override sample test threw an exception.");
                e.printStackTrace();
                System.out.println("\n\n\n");
                break SampleOverride;
            }
            IO.extraLine();
            System.out.println("| Evaluation of override sample input: " + result + ".");
        }

        // Test from actual input data
        IO.printable = false;
        Actual: if (!Inputs.trueData.isEmpty()) {
            TimeUnit.SECONDS.sleep(1);

            System.out.println("\nEvaluating from problem input\n| ");
            long result = 0;
            IO.prints = 0;
            try {
                result = solve.apply(Inputs.getData());
            } catch (Exception e) {
                IO.extraLine();
                System.out.println("| Solver threw an exception.");
                e.printStackTrace();
                System.out.println("\n\n\n");
                break Actual;
            }
            IO.extraLine();
            System.out.println("| Evaluation of problem input: " + result + ".");
            if (Inputs.tooLow.length != 0) {
                boolean withinTolerance = true;
                for (long bound : Inputs.tooLow) {
                    if (result <= bound) {
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
            if (Inputs.tooHigh.length != 0) {
                boolean withinTolerance = true;
                for (long bound : Inputs.tooHigh) {
                    if (result >= bound) {
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