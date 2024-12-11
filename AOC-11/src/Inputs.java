import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    public static final String sampleOverride = "";
    public static final String sampleData = "125 17 6";
    public static final Long sampleResult1 = 22L;
    public static final Long sampleResult2 = 22L;
    // Puzzle data
    public static final long[] tooLow  = new long[] {};
    public static final long[] tooHigh = new long[] {};
    public static final String trueData = "773 79858 0 71 213357 2937 1 3998391 75";

    public static Input getSample() {
        return new Input(sampleData);
    }
    public static Input getData() {
        return new Input(trueData);
    }
}
