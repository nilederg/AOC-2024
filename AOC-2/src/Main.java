import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        char[] data = Input.get().toCharArray();
        ArrayList<ArrayList<Integer>> reports = new ArrayList<>();
        reports.add(new ArrayList<>());
        StringBuilder num = new StringBuilder();
        for (char c : data) {
            if (c == ' ') {
                reports.get(reports.size() - 1).add(Integer.parseInt(num.toString()));
                num = new StringBuilder();
            } else if (c == '\n') {
                reports.get(reports.size() - 1).add(Integer.parseInt(num.toString()));
                num = new StringBuilder();
                reports.add(new ArrayList<>());
            } else {
                num.append(c);
            }
        }

        // PART ONE

        int safes = 0;
        safeCount: for (ArrayList<Integer> rawreport : reports) {
            reportTest: for (int omit = 0; omit < rawreport.size(); omit++) {
                ArrayList<Integer> report = new ArrayList<>(rawreport.size() - 1);
                for (int j = 0; j < rawreport.size(); j++) {
                    if (omit == j) {
                        continue;
                    }
                    report.add(rawreport.get(j));
                }


                boolean increasing = report.get(1) > report.get(0);
                if (report.get(1) == report.get(0)) {
                    continue reportTest;
                }
                for (int i = 0; i < report.size() - 1; i++) {
                    int a = report.get(i);
                    int b = report.get(i + 1);
                    if (a == b || b > a && !increasing || b < a && increasing || Math.abs(a - b) > 3) {
                        continue reportTest;
                    }
                }
                safes++;
                break;
            }
        }

        System.out.println("Safe reports");
        System.out.println(safes);
    }
}