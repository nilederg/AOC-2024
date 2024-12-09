import Management.Input;

import java.util.ArrayList;

import static Management.IO.println;

public class Solver1 {
    public static long Evaluate(Input input) {
        char[] data = input.getRaw();
        ArrayList<Integer> filesystem = new ArrayList<>();
        {
            boolean fileLength = true;
            int id = 0;
            for (char c : data) {
                int length = Integer.parseInt(String.valueOf(c));
                if (fileLength) {
                    for (int i = 0; i < length; i++) {
                        filesystem.add(id);
                    }
                    id++;
                    fileLength = false;
                } else {
                    for (int i = 0; i < length; i++) {
                        filesystem.add(-1);
                    }
                    fileLength = true;
                }
            }
        }

        int index = filesystem.size() - 1;
        Reorder: while (true) {
            int id = filesystem.get(index);
            if (id == -1) {
                filesystem.remove(index);
                index --;
                continue;
            }
            int writeIndex = 0;
            while (true) {
                if (writeIndex == index) {
                    break Reorder;
                }
                if (filesystem.get(writeIndex) == -1) {
                    break;
                }
                writeIndex++;
            }
            filesystem.set(writeIndex, id);
            filesystem.remove(index);
            index --;
        }

        long sum = 0;
        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i) == -1) {
                throw new ArithmeticException("BAD");
            }
            sum += filesystem.get(i) * i;
        }

        return sum;
    }
}
