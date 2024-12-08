import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] ints = Input.getInts();

        ArrayList<int[]> orderRulesRaw = new ArrayList<>();
        ArrayList<int[]> updates = new ArrayList<>();

        int index = 0;
        while (true) {
            int[] line = ints[index];
            index ++;
            if (line.length == 0) {
                break;
            }
            orderRulesRaw.add(line);
        }
        while (index < ints.length) {
            int[] line = ints[index];
            index ++;
            updates.add(line);
        }

        // For each page number, the list of pages that must be before/after it
        ArrayList<Integer>[] beforePagesArrayList = new ArrayList[100];
        ArrayList<Integer>[] afterPagesArrayList = new ArrayList[100];
        for (int i = 0; i < 100; i++) {
            beforePagesArrayList[i] = new ArrayList<>();
            afterPagesArrayList[i] = new ArrayList<>();
        }
        for (int[] rule : orderRulesRaw) {
            beforePagesArrayList[rule[1]].add(rule[0]);
            afterPagesArrayList[rule[0]].add(rule[1]);
        }
        int[][] beforePages = new int[100][];
        int[][] afterPages = new int[100][];
        for (int i = 0; i < 100; i++) {
            beforePages[i] = new int[beforePagesArrayList[i].size()];
            afterPages[i] = new int[afterPagesArrayList[i].size()];
            for (int j = 0; j < beforePagesArrayList[i].size(); j++) {
                beforePages[i][j] = beforePagesArrayList[i].get(j);
                afterPages[i][j] = afterPagesArrayList[i].get(j);
            }
        }

        int preorderedSum = 0;
        int unorderedSum = 0;
        for (int[] update : updates) {
            boolean preordered = true;
            UpdateCheck: for (int i = 0; i < update.length; i++) {
                int curNum = update[i];
                int[] afters = afterPages[curNum];
                for (int j = 0; j < i; j++) {
                    int prevNum = update[j];
                    for (int afterRule : afters) {
                        if (afterRule == prevNum) {
                            preordered = false;
                            break UpdateCheck;
                        }
                    }
                }
            }
            if (preordered) {
                preorderedSum += update[update.length / 2];
            } else {
                boolean ordered = false;
                while (!ordered) {
                    ordered = true;
                    UpdateCheck:
                    for (int i = 0; i < update.length; i++) {
                        int curNum = update[i];
                        int[] afters = afterPages[curNum];
                        for (int j = 0; j < i; j++) {
                            int prevNum = update[j];
                            for (int afterRule : afters) {
                                if (afterRule == prevNum) {
                                    ordered = false;
                                    update[i] = prevNum;
                                    update[j] = curNum;
                                    break UpdateCheck;
                                }
                            }
                        }
                    }
                }
                unorderedSum += update[update.length / 2];
            }
        }

        System.out.println("Part one: Sum of midpoints of pre-ordered updates");
        System.out.println(preorderedSum);
        System.out.println("Part two: Sum of midpoints of unordered updates");
        System.out.println(unorderedSum);
    }
}