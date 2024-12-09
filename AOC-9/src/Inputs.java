import Management.Input;

public class Inputs {
    public static final int phase = 1;

    public static final String sampleData = "";
    public static final Long sampleResult1 = null;
    public static final Long sampleResult2 = null;
    public static final String trueData = "";

    public static Input getSample() {
        return new Input(sampleData);
    }
    public static Input getData() {
        return new Input(trueData);
    }
}
