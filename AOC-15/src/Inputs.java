import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    public static final String sampleOverride = "";
    public static final String sampleData = "";
    public static final Long sampleResult1 = null;
    public static final Long sampleResult2 = null;
    // Puzzle data
    public static final long[] tooLow  = new long[] {};
    public static final long[] tooHigh = new long[] {};
    public static final String trueData = "";

    public static Input getSample() {
        return new Input(sampleData);
    }
    public static Input getData() {
        return new Input(trueData);
    }
}
