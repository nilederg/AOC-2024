package Management;

public class IO {
    public static int prints = 0;
    public static boolean printable = true;

    public static void extraLineIfNecessary() {
        if (prints != 0) {
            extraLine();
        }
    }

    public static void extraLine() {
        System.out.println("|");
    }

    /**
     * Print for debugging, compatible with standard solver display
     * @param objects The string(s) to print, or object(s) to convert to string(s) and print.
     */
    public static void println(Object... objects) {
        if (!printable) {
            return;
        }
        forcePrintln(objects);
    }

    public static void forcePrintln(Object... objects) {
        if (prints == 0) {
            extraLine();
        }
        StringBuilder builder = new StringBuilder();
        for (Object object : objects) {
            builder.append(object.toString());
        }
        System.out.println("| " + builder);
        prints++;
    }
}
