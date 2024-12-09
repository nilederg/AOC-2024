import Library.FreeList;
import Library.Miscellaneous;
import Management.IO;
import Management.Input;

import java.util.ArrayList;
import java.util.Arrays;

import static Management.IO.println;

public class Solver2 {
    public static long Evaluate(Input input) {
        char[] data = input.getRaw();
        ArrayList<Integer> filesystemCompact = new ArrayList<>();
        for (char c : data) {
            int length = Integer.parseInt(String.valueOf(c));
            filesystemCompact.add(length);
        }

        println(filesystemCompact);

        ArrayList<Integer> fileID = new ArrayList<>((filesystemCompact.size()+1) / 2);
        for (int i = 0; i < (filesystemCompact.size()+1) / 2; i++) {
            fileID.add(i);
        }

        //IO.forcePrintln(deCompactString(filesystemCompact, fileID));

        int line = 1;
        for (int moveID = filesystemCompact.size()/2 + 1; moveID >= 0; moveID--) {
            int moveIndex = 0;
            for (int i = 0; i < fileID.size(); i++) {
                if (fileID.get(i) == moveID) {
                    moveIndex = i;
                    break;
                }
            }
            if (moveIndex == 0) {
                continue;
            }
            int fileSize = filesystemCompact.get(2 * moveIndex);
            for (int destinationIndex = 1; destinationIndex <= moveIndex; destinationIndex++) {
                int fileMoveIndex = moveIndex * 2;
                int fileDestinationIndex = destinationIndex * 2 - 1;
                int destinationSize = filesystemCompact.get(fileDestinationIndex);
                if (destinationSize >= fileSize) {
                    println(deCompactString(filesystemCompact, fileID));
                    line++;

                    fileID.remove(moveIndex);
                    fileID.add(destinationIndex, moveID);

                    if (moveIndex == fileID.size() - 1) {
                        filesystemCompact.remove(fileMoveIndex);
                        filesystemCompact.set(fileMoveIndex - 1, filesystemCompact.get(fileMoveIndex - 1) + fileSize);
                    } else {
                        int freeRemaining = filesystemCompact.get(fileMoveIndex - 1) + filesystemCompact.get(fileMoveIndex) + filesystemCompact.get(fileMoveIndex + 1);
                        filesystemCompact.set(fileMoveIndex - 1, freeRemaining);
                        filesystemCompact.remove(fileMoveIndex);
                        filesystemCompact.remove(fileMoveIndex);
                    }

                    destinationSize = filesystemCompact.get(fileDestinationIndex);
                    int remaining = destinationSize - fileSize;
                    filesystemCompact.set(fileDestinationIndex, remaining);
                    filesystemCompact.add(fileDestinationIndex, fileSize);
                    filesystemCompact.add(fileDestinationIndex, 0);

                    if (filesystemCompact.size() % 2 == 0) {
                        filesystemCompact.remove(filesystemCompact.size() - 1);
                    }

                    break;
                }
            }
        }

        println(deCompactString(filesystemCompact, fileID));

        ArrayList<Integer> filesystem = deCompact(filesystemCompact, fileID);

        long sum = 0;
        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i) == -1) {
                continue;
            }
            sum += filesystem.get(i) * i;
        }

        return sum;
    }

    private static ArrayList<Integer> deCompact(ArrayList<Integer> filesystemCompact, ArrayList<Integer> fileID) {
        ArrayList<Integer> filesystem = new ArrayList<>();
        boolean fileLength = true;
        for (int j = 0; j < filesystemCompact.size(); j++) {
            int length = filesystemCompact.get(j);
            if (fileLength) {
                fileLength = false;
                if (length == 0) {
                    continue;
                }
                int id = fileID.get(j/2);
                for (int i = 0; i < length; i++) {
                    filesystem.add(id);
                }
            } else {
                fileLength = true;
                if (length == 0) {
                    continue;
                }
                for (int i = 0; i < length; i++) {
                    filesystem.add(-1);
                }
            }
        }
        return filesystem;
    }

    private static String deCompactString(ArrayList<Integer> filesystemCompact, ArrayList<Integer> fileID) {
        StringBuilder filesystem = new StringBuilder();
        boolean fileLength = true;
        for (int j = 0; j < filesystemCompact.size(); j++) {
            int length = filesystemCompact.get(j);
            if (fileLength) {
                fileLength = false;
                if (length == 0) {
                    continue;
                }
                int id = fileID.get(j/2);
                for (int i = 0; i < length; i++) {
                    filesystem.append(Library.Miscellaneous.representInt(id));
                }
            } else {
                fileLength = true;
                if (length == 0) {
                    continue;
                }
                for (int i = 0; i < length; i++) {
                    filesystem.append('.');
                }
            }
        }
        return filesystem.toString();
    }
}
