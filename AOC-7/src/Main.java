import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long[][] data = Input.getInts();

        long total = 0;
        for (long[] line : data) {
            long result = line[0];
            Operator[] operators = new Operator[line.length - 2];
            for (int i = 0; i < operators.length; i++) {
                operators[i] = Operator.Addition;
            }
            boolean solvable = false;
            NextOperation: while (true) {
                /*ArrayList<ArrayList<Long>> addends = new ArrayList<>();
                addends.add(new ArrayList<>());
                addends.get(0).add(line[1]);
                int lineIndex = 2;
                for (boolean multiply : multiplication) {
                    if (multiply) {
                        addends.get(addends.size() - 1).add(line[lineIndex]);
                    } else {
                        addends.add(new ArrayList<>());
                        addends.get(addends.size() - 1).add(line[lineIndex]);
                    }
                    lineIndex++;
                }

                long sum = 0;
                for (ArrayList<Long> addend : addends) {
                    long product = 1;
                    for (Long multiplicand : addend) {
                        product *= multiplicand;
                    }
                    sum += product;
                }

                if (sum == result) {
                    solvable = true;
                    total += sum;
                    break NextOperation;
                }*/

                long value = line[1];
                int lineIndex = 2;
                for (Operator operator : operators) {
                    if (operator == Operator.Addition) {
                        value += line[lineIndex];
                    } else if (operator == Operator.Multiplication) {
                        value *= line[lineIndex];
                    } else {
                        value = Long.parseLong(String.valueOf(value) + String.valueOf(line[lineIndex]));
                    }
                    lineIndex++;
                }

                if (value == result) {
                    solvable = true;
                    total += value;
                    System.out.println("Solved " + result + " " + Arrays.toString(operators));
                    break NextOperation;
                }
                //System.out.println("No hits on " + Arrays.toString(operators));

                int index = 0;
                while (operators[index] == Operator.Concatenation) {
                    operators[index] = Operator.Addition;
                    index++;
                    if (index >= operators.length) break NextOperation;
                }
                if (operators[index] == Operator.Addition) {
                    operators[index] = Operator.Multiplication;
                } else {
                    operators[index] = Operator.Concatenation;
                }
            }
            if (solvable) {
                System.out.println(result + " Solved");
            } else {
                System.out.println(result + " Not Solved");
            }
            System.out.println();
        }

        System.out.println(total);
    }
}