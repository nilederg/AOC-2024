import Management.IO;
import Management.Input;

import java.util.function.Function;

public class Main {
    static Function<Input, Long> solve1 = Solver1::Evaluate;
    static Function<Input, Long> solve2 = Solver2::Evaluate;

    public static void main(String[] args) {
        // Determine relevant values based on current problem phase
        Function<Input, Long> solve;
        Long sampleResult;
        if (Inputs.phase == 1) {
            solve = solve1;
            sampleResult = Inputs.sampleResult1;
        } else if (Inputs.phase == 2) {
            solve = solve2;
            sampleResult = Inputs.sampleResult2;
        } else {
            throw new RuntimeException("Invalid phase. Set it in Inputs.java to be 1 or 2.");
        }

        // Send startup messages
        int datasets = 0;
        if (Inputs.trueData.isEmpty()) {
            System.out.println("# Input data not found.");
        } else {
            System.out.println("# Found input data.");
            datasets ++;
        }
        if (Inputs.sampleData.isEmpty()) {
            System.out.println("# No sample data found.");
        } else if (!(sampleResult == null)) {
            System.out.println("# Found sample data and result.");
            datasets ++;
        } else {
            System.out.println("# Found sample data without expected result.");
            datasets ++;
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

        // Test from sample data
        Sample: if (!Inputs.sampleData.isEmpty()) {
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
                }
            } else {
                System.out.println("| Sample test returned " + result + ".");
            }
        }

        // Test from actual input data
        Actual: if (!Inputs.trueData.isEmpty()) {
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
        }
    }
}