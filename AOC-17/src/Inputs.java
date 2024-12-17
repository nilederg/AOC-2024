import Library.Quadruplet;
import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    // A: Sample input
    // B: Phase 1 expected output
    // C: Phase 2 expected output
    // D: To print or not to print
    public static final Quadruplet<String, Object, Object, Boolean>[] samples = new Quadruplet[]{
            new Quadruplet<>(
                    "Register A: 192\n" +
                            "Register B: 0\n" +
                            "Register C: 0\n" +
                            "\n" +
                            "Program: 2,4,1,1,7,5,1,5,4,1,5,5,0,3,3,0\n" +
                            "4,0,7"
                    , (Object) "4,0,7"
                    , (Object) 192L
                    , (Boolean) true
            ),
            new Quadruplet<>(
                    ""
                    , (Object) null
                    , (Object) null
                    , (Boolean) false
            ),
    };
    // Puzzle data
    public static final long[] tooLow1  = new long[] {};
    public static final long[] tooHigh1 = new long[] {};
    public static final long[] tooLow2  = new long[] {};
    public static final long[] tooHigh2 = new long[] {};
    public static final boolean printTrueData = false;
    public static final String trueData = "Register A: 17323786\n" +
            "Register B: 0\n" +
            "Register C: 0\n" +
            "\n" +
            "Program: 2,4,1,1,7,5,1,5,4,1,5,5,0,3,3,0\n" +
            "2,4,1,1,7,5,1,5,4,1,5,5,0,3,3,0";

    public static Input getData() {
        return new Input(trueData);
    }
}
