import Library.Quadruplet;
import Management.Input;

public class Inputs {
    public static final int phase = 1;

    // Sample data
    // A: Sample input
    // B: Phase 1 expected output
    // C: Phase 2 expected output
    // D: To print or not to print
    public static final Quadruplet<String, Object, Object, Boolean>[] samples = new Quadruplet[]{
            new Quadruplet<>(
                    "" +
                            "029A\n" +
                            "980A\n" +
                            "179A\n" +
                            "456A\n" +
                            "379A"
                    , (Object) 126384L
                    , (Object) null
                    , (Boolean) true
            ),
            new Quadruplet<>(
                    ""
                    , (Object) null
                    , (Object) null
                    , (Boolean) false
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
    public static final long[] tooHigh1 = new long[] {190903L};
    public static final long[] tooLow2  = new long[] {};
    public static final long[] tooHigh2 = new long[] {};
    public static final boolean printTrueData = false;
    public static final String trueData = "" +
            "805A\n" +
            "170A\n" +
            "129A\n" +
            "283A\n" +
            "540A";

    public static Input getData() {
        return new Input(trueData);
    }
}
