import Management.Input;

public class Inputs {
    public static final int phase = 2;

    // Sample data
    public static final String sampleOverride = "" +
            "#######\n" +
            "#S...##\n" +
            "#.##.##\n" +
            "#.#..##\n" +
            "#.#.###\n" +
            "#.#..##\n" +
            "#.##.##\n" +
            "#.#..E#\n" +
            "#.#.###\n" +
            "#.#.###\n" +
            "#.#.###\n" +
            "#.#.###\n" +
            "#.#.###\n" +
            "#...###\n" +
            "#######";
    public static final String sampleData = "#################\n" +
            "#...#...#...#..E#\n" +
            "#.#.#.#.#.#.#.#.#\n" +
            "#.#.#.#...#...#.#\n" +
            "#.#.#.#.###.#.#.#\n" +
            "#...#.#.#.....#.#\n" +
            "#.#.#.#.#.#####.#\n" +
            "#.#...#.#.#.....#\n" +
            "#.#.#####.#.###.#\n" +
            "#.#.#.......#...#\n" +
            "#.#.###.#####.###\n" +
            "#.#.#...#.....#.#\n" +
            "#.#.#.#####.###.#\n" +
            "#.#.#.........#.#\n" +
            "#.#.#.#########.#\n" +
            "#S#.............#\n" +
            "#################";
    public static final Long sampleResult1 = 11048L;
    public static final Long sampleResult2 = 64L;
    // Puzzle data
    public static final long[] tooLow  = new long[] {};
    public static final long[] tooHigh = new long[] {};
    public static final String trueData =
            """
                    #############################################################################################################################################
                    #.....#.............#.#.......#.......#.................#.......#...#.......#.........#.....#.......#.......#...............#..............E#
                    #.###.#.###########.#.#.#####.#.#.###.#######.#########.#.#####.#.#.#.#####.#.#####.###.#.#.#.#######.#.###.#.###.#####.###.#.###.#.#.#.#.###
                    #...#...#.....#.....#.#.#...................#.#.......#.................#.#.#...#.#.....#.#...#.......#.#.#.#...#.....#...#.#.#.............#
                    ###.#######.#.#.#####.#.#.#.###.###.#######.#.#####.#.#######.###.#.###.#.#.#.#.#.#######.#####.#######.#.#.#.#.#########.#.#.#.#######.###.#
                    #...#...#...#.#.....#.....#.....#...#.#.....#.....#.#.#...#...#...#.......#...#.#...#...#.....#.#.......#.#.#.#...........#.#.#.#...#.......#
                    #.###.#.#.#.#.#####.#.###########.###.#.#########.#.###.#.#.#.#.#############.#.###.#.#.#####.#.#.#######.#.#.###############.#.#.#.#######.#
                    #.#...#.#.#.#.....#.#.#...........#...#.#.#.....#.#...#.#.#.....#.........#...#.#...#.......................#...#.........#...#.#.#...#.....#
                    #.#.###.#.###.#.###.###.#############.#.#.#.#.###.#.#.#.#.###.###.#######.#.###.#.#.#.#.#.#.###.#####.#.#.#.###.#.#####.#.#.#.#.#.###.#.###.#
                    #.....#.#...#.#...#...#.#.........#...#.#...#.....#.#.#.....#.....#.....#.#...#.#.#.....................#.#...#...#...#.#...................#
                    #.#####.#.#.#.###.###.#.#.#######.#.###.#.#########.#.#.###.#.#####.#####.###.#.#.#.#.#.#.###.###.#.###.#.###.#####.#.#.#####.#.#.#.#.#.#.#.#
                    #...............#.#.....#.....#...#.....#.....#.....#.#.#.#.#.....#.#...#...#.#...#.#.#...#...#...#.#...#...#.......#.#.#.....#.#.#.#...#.#.#
                    ###.#.#.###.#.#.#.#.###.#.#####.###.#########.#######.#.#.#.#.###.#.#.#.###.#.#####.#.#####.###.#.#.###.###########.#.#.#.###.###.#.###.#.#.#
                    #...#...#...#...#.....#...#.....#...........#...#.....#.#.....#...#.#.#.....#.......#.#.....#...#.#...#...........................#.....#.#.#
                    #.#####.#.#.#.#####.#.#.###.###########.###.###.#.###.#.#####.#.###.#.###############.#.#####.###.###.#.###.###.#.#.#########.#.#.#.###.#.###
                    #...#...#...#.#...#...#.#.#.......#...#...#.#.#.#.#.#.#.....#...#.......#.......#.....#.#.....#.....#.#.#...#.....#.....#.....#...#.#.#.#...#
                    ###.#.###.#.#.#.#.#####.#.#######.#.#.###.#.#.#.#.#.#.#####.###########.###.###.#.###.#.#.#.###.#####.#.#.#####.#.###.#.#.###.###.#.#.#.###.#
                    #...#.#...#...#.#.............#...#.#.....#.#.....#.#...#.#.......#...#...#.#.#.#...#.#.#.#.#...#.#...#.#...#...#...#...#.#.#...#.#...#.#...#
                    #.###.#.###.#.#.#########.###.#.###.#####.#.#######.###.#.#######.#.#.#.#.#.#.#.###.#.#.#.#.#.###.#.#######.#.#.#####.###.#.###.#.#####.#.#.#
                    #.#.#.#.#...........#.....#...#.#.....#...#.....#.............#.#...#...#...#.......#...#.#.#.....#...#.....#.#.#...#.....#...#.#.......#.#.#
                    #.#.#.###.#.#.#####.###.#####.#.#.#.#.#########.#############.#.#######.#####.#########.###.#########.#.#.###.#.#.#.###.#.###.#.###.###.#.###
                    #...#...#.#.............#...#.#.#...#.#.......#.......#.....#.#.......#.#...#...#.......#...#.....#...#.#.#.......#...#...#...#.....#...#...#
                    ###.###.#.#.#.#######.###.#.###.#####.#.#.###########.#.###.#.#####.###.#.#.###.#.#.#.###.###.###.#.###.###.#######.#######.#.#####.###.###.#
                    #.#...#...#.........#.....#.....#...#...#.#...........#.#...#.....#.#...#.....#.#.#.#.#.#...#.#.....#...#...#.....#.#.......#.#...#...#.#...#
                    #.###.#.###.#.#####.#.###########.#.###.#.#.###########.#########.#.#.#####.#.#.#.#.#.#.#.#.#.#######.#.#.###.#.#.#.#.#.#.###.#.#####.#.#.#.#
                    #.#...#...#.#.....#.#...#...#.....#...#.....#.........#...........#.#.....#.#...#.#.#.#...#.#...#.#...#...#...#...#.....#.#...#...#...#.#.#.#
                    #.#.###.#.#.###.#.#.###.#.#.#.#######.#.###########.#.#.###########.#####.#######.#.#.#####.###.#.#.#######.#.#.#.#######.#.#####.#.###.#.#.#
                    #...#...#.#.#...#.....#...#.......#...#.#...#.....#.#.#.#.#.......#.#.....#.......#.#.#.......#.#...#.......#...#...#.....#.......#.......#.#
                    #.#####.###.#.#.###.#.#######.#####.###.#.#.#.###.#.#.#.#.#.#.###.#.#.#.#.#.#######.#.#.#####.#.###.#.#######.#.#####.###.###############.#.#
                    #.#.............#...#.#.....#.#...#.#.#.#.#...#...#.#...#...#.#.....#.#.#.#.#.....#...#...#...#...#.#.......#.#.#...#...#.#.............#...#
                    #.#.#.#.#########.#.#.#.#.#.#.#.#.#.#.#.#.#####.#.#####.#####.#.#####.#.#.#.#.#.#######.#.#.#####.#.###.###.#.###.#.#.#.#.#.#######.#####.###
                    #...#.............#.#.#.#.#...#.#...#.#.#.#...........#.......#...#...#.#.#.#.#.......#.#.#...#...#.....#.#.#.....#...#...#...#...#.......#.#
                    #######.###########.#.###.#####.#####.#.#.#.#########.#####.#######.###.#.#.#######.#.###.###.#.#########.#.#.#####.#.#.#.###.#.#########.#.#
                    #.....#.......#.......#...#.....#.......#.#.#.....#...#.....#.....#.#...#.#...#...#.#.#...#.#.#...#.......#.#...#...#.#.#...#.#.............#
                    #.###.#.#####.#.#######.#.#.#########.###.#.#.###.#.###.#####.###.#.#.###.###.#.#.#.#.#.###.#.###.#.#.#####.###.#.###.#.###.#.#.#######.#.#.#
                    #.#...........#.........#.#.........#...#.......#.#.#...#.....#.#...#.#...#...#.#...#...#.....#.#.#.#.....#.#...#...#.#.#.#.#.....#...#.#...#
                    #.#####.###########.###############.###.#.###.###.#.#.###.#####.#####.#.###.###.#########.###.#.#.#######.#.#.#####.###.#.#.#.#####.#.#.###.#
                    #.......#.......#...#...............#...#.......#.#.#...#...#...........#...#.....#.....#...#...#.......#.#.#.....#.#...#...#.....#.#.#...#.#
                    #######.#.###.#.#.###.###############.#########.#.#.###.###.#.#####.###.#.#####.#.#####.###.#.#.#######.#.#.###.###.#.#####.#####.#.#.#.#.#.#
                    #...#...#...#.#.#.#...#.........#...#...........#...#...#...#...#...#...#.....#...#.......#...#.#.....#.#.....#.#...#.....#.....#...........#
                    #.#.#.#.###.#.#.#.#.#####.#.#.#.#.###############.###.###.#####.#.#.#.#######.#.###.#####.#####.###.#.#.#####.###.#######.#.###.#####.###.#.#
                    #.#...#.#...#.#.#.#...#...#.#.#...#.......#.....#.#...#.#...#.#.#.#.#.#.......#...#.#...#.....#.#...#.#.....#...#.....#.#.#.#...#...#.....#.#
                    #.#####.#.###.###.###.#.###.#.#####.#.#####.###.#.#.###.###.#.#.#.###.#.#########.#.#.#####.#.#.#.###.#####.###.#.#.#.#.#.#.#.###.#.#######.#
                    #.....#.#.#.#.#.......#...#.#.#...#.#...........#.#.....#.......#.#...#.#.........#.#.........#...#...#...#.#.#...#.#...#...#.....#...#.....#
                    #.###.###.#.#.#.#####.#.#.#.#.#.#.#.#########.#.#.#######.#######.#.###.###.###.###.#.#############.#.###.#.#.###.#.#####.###########.#.#####
                    #...#...#...#...#.#...#.#...#.......#.#...#...#.#.........#.....#.#.#...#.....#.#...#.....#.....#...#.....#.#.......#...#...#.....#...#.#...#
                    #.###.#.###.#####.#.#######.#.#####.#.#.#.#.###.#.###########.#.#.#.#.###.###.#.#.#.#######.###.#.#.#####.#.#######.#.#.###.###.#.#.###.###.#
                    #.#.......#.#.#...#.......#...#...#.#.#.#...#.....#.........#.#.#.#.#.#...#.#...#.#.......#...#...#.#.....#.........#.#...#...#.#.#.....#...#
                    #.#.#.###.#.#.#.#.#######.#.###.#.#.#.#.#####.#####.#######.#.#.#.#.#.#.#.#.#####.#######.###.#####.#.###.#######.###.###.###.#.#.#.#####.#.#
                    #.#.....#.#.#.#.#.....#.#.#.#...#.#...#.......#.#...#.......#.#...#.#.#.#.#.......#.....#...#...#.#...#.........#.#...#.#...#...#.........#.#
                    #.###.#.#.#.#.#.###.#.#.#.###.###.###########.#.#.#####.###.#.#####.#.###.#.#######.#.#####.###.#.#####.#####.###.#.###.###.#####.#######.###
                    #.#...#.#.#.#...#...#.#.#...#...#...#...#...#...#...#...#...#.......#.#...#.........#.#.......#...#...#.#.....#...#.#.....#.#...#.#...#.....#
                    #.#.#.#.#.#.#####.###.#.###.###.###.#.#.#.#.###.###.#.###.###.#######.#.#############.#.#########.#.###.#.###.#.###.#.###.#.#.#.###.#.#.#.#.#
                    #.#.#...#.#.#.....#.#.....#.....#.#...#...#.#.#...#...#...#...#...#...#.#...........#.#.........#.#.....#.#...#.......#...#...#.....#.....#.#
                    #.#.#.###.#.#.#####.#####.#######.#########.#.#.#####.#.###.###.#.#.###.#####.###.###.#########.#.###.###.#.#####.#####.#################.#.#
                    #.#.#...#.#.#...#.......#.......#.....#.....#.........#...#...#.#.#.....#.........#...#.......#.....#...#.#.....#.......#...#...#.#.........#
                    #.#.#.#.#.#.###.#####.#.#######.#.###.#.#####.###.#######.###.###.#######.#####.###.###.###.#######.#####.#####.#########.#.#.#.#.#.#####.#.#
                    #.#...#.#.#.#...#...#...#.....#...#.#.#.#.....#.....#.....#.#.....#.......#.#...#...#.....#.#.....#.....#...#.#...........#...#...#.#.......#
                    #.#.#.#.#.#.#.###.#.###.#.###.#####.#.#.#######.#####.#####.#####.#.#####.#.#.###.#######.###.#.#.###.#.#.#.#.###################.#.###.###.#
                    #...#.#.#.#...#...#.#...#.#.#...#.....#.........#.....#.#.......#...........#...#.....#...#...#.#.#.....#.#.#.....#.........#.....#.......#.#
                    #.#.#.#.#######.###.#.#.#.#.###.#.###############.#####.#.#.#####.#########.#.#######.#.###.###.#.#####.###.#.###.#.#######.#.#########.#.#.#
                    #.#.#.#.......#...#...#.#...#.....#.............#.....#...#.#.....#.....#.....#...#.....#...#...#.....#.....#...#.#.#.....#...#.....#...#.#.#
                    #.#.#.#####.###.#.#####.###.#.#.#.#.###########.#####.###.#.#.#####.###.###.###.#.#.#####.#.###.#####.#####.#####.#.###.#.#####.###.###.#.###
                    #...#.....#...#.#...#...#.#.#.#...#.#...........#...#...#.....#.#...#.#...#.#...#.#.#...#.#.........#.....#.#...#...#...#.........#.....#...#
                    ###.###.#.###.#.###.#.###.#.#.#####.#.#########.#.#####.#.#####.#.###.###.#.#.###.#.#.#.#.#.#.#.#########.###.#.#.###.#########.#.#.###.###.#
                    #.......#...#.#.#...#.....#...#.....#.#.......#.#.#.....#.....#.#.#.....#.#.#...#.#.#.#...#.#...#.....#...#...#.#...#...#.....#.#.#.#.......#
                    #######.###.#.#.#.#.#####.#.#.###.###.#######.#.#.#.#########.#.#.#.#####.#####.#.#.#.#####.#.###.###.#.###.###.###.###.#.###.#.#.#.#.#.###.#
                    #...#.......#.....#...#.#...#...#...#...#.....#.#.#.#.......#...#...#...#...#...#...#.#...#.#.....#...#.....#...#...#...#.#.#.......#...#...#
                    #.#.###.#########.###.#.#.#.###.#######.#.#####.#.#.###.#.#####.###.#.#.###.#.#######.#.#.#.#######.#########.###.###.#.#.#.#####.#.###.#.###
                    #.#...#...#.....#.#...#.#.#...#.........#.#.......#...#.#.........#.#.#...#.#...#...#...#.#...#...#.#.......#.#...#...#...#.#.....#.#.#.#...#
                    #.###.#.###.###.#.#.###.#.#.#.#.#########.#.#########.#.###########.#.###.#.###.#.#.#####.###.###.#.#.###.###.#.###.#.#.###.#.#####.#.#.#####
                    #...#...........#...#...#.#.#...#.#.......#...#.#...#.#...#...#...#.#...#.#.#...#.#.....#...#.....#.....#.#...#...#.#.#.#...#.#.......#.....#
                    #.#.###.#.###.#.#.#####.#.###.#.#.#.#####.#.#.#.#.#.#.###.#.#.#.#.#.#####.#.#.###.###.#####.#.#.#.#####.#.#.#####.#.###.###.#.#######.#####.#
                    #.#.#.#.#...#.#...#.....#.#...#...#.#...#.#.....#.#...#...#.#.#.#.#.#...#.#...#.....#.#.....#...#.#...#.#...#...#.#.#.......#.#.....#.#.....#
                    #.#.#.#.#.#.#.#####.#.#.#.#.#####.#.###.#.#.#####.#########.#.#.#.#.#.###.###.#####.#.#.###########.#.#.#####.#.#.#.#.#######.#.###.###.###.#
                    #.#.#.#.#...#.#.....#.#.#...#...#.#.....#...#...#.#.........#...#.#.#...#...#.....#.#.#.............#.#.#...#.....#...........#...#.#...#...#
                    #.#.#.#.#.#.#.#.#####.#.#####.#.#.#####.#####.#.#.#.#########.###.#.#.#.#.#.#####.#.#.#########.###.#.#.#.#.#.#########.#.###.#.#.#.#.###.###
                    #.#...#.#.#.#...#.....#.....#.#.#.....#...#...#.#.....#.....#.#.....#.#...#.#.....#.#...#.........#.#.#...#.#...#.....#.#...#.#.#.#...#.....#
                    ###.#.#.#.#.#####.###########.#.#.#######.#.###.#######.###.#.#.#########.#.###.#######.#.#######.###.#####.###.#.###.#####.#.#.#.###.#######
                    #...#...#.#.....#.........#...#...#.......#.#.#.....#...#...#...#.........#...#.......#.#.......#.....#.#...#.......#.#.....#...#...........#
                    #.###.#.#####.#.###.#####.#.#######.#######.#.#####.#.###.#####.#.#.#########.#######.#.#######.#######.#.###.#####.#.#.#.#.###.###########.#
                    #...#.......#.#.......#.#...#.......#.....#.....#.#...#...#...#...#.#.......#.....#.........#...#.....#...#.....#.#...#.#...#.#.......#.#...#
                    #.#.###.#.#.###.#.###.#.#####.###.#.#####.#.###.#.#####.#.#.#.###.###.#.#.#.#####.#.#########.#####.#.#.###.###.#.#.###.#####.#.#####.#.#.#.#
                    #.#...#...#...#.#.#.#.......#.#.#.#.......#.....#.......#...#.#...#...#...#.......#.#.........#.....#.#...#...#.#...#...#.......#...#...#.#.#
                    #.#.#.#.#.###.###.#.###.#.#.#.#.#.###.#####.#.#.#.###.#.#####.#####.#################.###########.###.###.#.#.#.###.#.###.#.#.#.#.#.#####.#.#
                    #...#.#.#...#.....#.....#.......#.....#.....#...#...#.#...#.#.......#...#.............#.....#.....#...#...#.#.#...#...#...#.#.#...#.....#.#.#
                    #.#.#.#.#.#.#############.#########.#.#.###########.#.###.#.#########.###.#####.#######.#.#.#.#.#####.#.#####.###.#####.###.#.#.#######.#.#.#
                    #.#...#.#.#.#.........#...#.........#.#.#.........#.#.#...#.......#.....#.#...#.#.......#.#.#.#.#...#.#.......#...#.......#...............#.#
                    #.#.###.#.#.#.#######.#.###.#######.###.#.#####.#.###.#.#########.#.###.#.#.###.#.###.###.#.###.#.#.###########.###.#####.#.#.#.###.#####.#.#
                    #.#.......#.....#.....#...#...#...#.....#.#...#.#.....#...........#.#.#...#...#.#.....#...#.....#.#...........#.#...#.....#.#.#...#.......#.#
                    #.#.###.#.#######.#####.#.###.###.#######.#.#.#.#################.#.#.#######.#.#####.#.###.#####.#########.###.#.###.#####.#.###.#########.#
                    #.#...#...#.#.....#.....#...#.......#.......#.#.......#...........#...#.....#.#.#...#.#.#.........#.#.....#.....#...#.#.....#...#...#.....#.#
                    #.#.#.#.#.#.#.#.###.#.#####.#######.###########.#####.#.#############.#.###.#.#.#.#.###.#.#.#######.#.###.###########.###.#####.###.#.#.#.#.#
                    #...#...#.#.#.#.#...#...#...#...#.#...#...#...#.....#...........#...#...#...#.#.#.#...#...#.#.....#.#.#...#.........#.............#.#.#.#.#.#
                    #########.#.#.###.#####.#.###.#.#.###.#.#.#.#.#######.#########.#.#######.###.#.#.###.#.###.#.#.#.#.#.#.#.#.#######.#.#############.#.#.###.#
                    #.#.....#.#.#.........#.#.#...#.#...#...#...........#...#.........#...........#.#...#.#...#...#.#.#...#.#.#.#.....#.#.......#.......#.#.....#
                    #.#.#.###.#.###########.#.#.###.###.###############.###.###########.#.#######.#.###.#.###.#####.#.#####.#.#.#.#.###.#######.#.#######.#######
                    #.#.#.....#...#.........#.#.#.#.........#.........#...#.............#.....#...#.#...#...#.....#.#.......#...#.#...#.....#...#.#.............#
                    #.#.#######.###.#.#####.#.#.#.#########.#.#.#########.###############.###.#.###.#.###.#.#####.#.#############.###.#####.#.###.#.###########.#
                    #...#.....#...#.#.#...#.#...#.#...#.....#.#.#.........#...............#.#.#.#...#.#...#...#.....#...........#.#.......#.#...#...............#
                    #.#####.###.#.#.#.#.#.#######.#.#.#.#####.#.#.#######.#.#.#############.#.###.#.#.#.#####.#######.#######.###.#.#######.###.#.###.###.#.#.###
                    #.#.....#...#.#.#.#.#...#.......#.....#...#...#.......#.#.#...#.........#.....#.#.#.....#...#...#.#.....#.....#.......#.#...#.......#.#.#...#
                    #.#.#.###.###.#.###.###.#.#############.#########.###.#.###.#.###.#########.###.#.###.#.###.#.#.#.#.#.###############.#.#########.###.#.###.#
                    #.#.#.....#...#...#...#...#.......#...#.#.#.......#.....#...#...#...........#.....#...#.#...#.#...#.#.........#.....#.....#.......#...#.#...#
                    #.#########.#.###.###.#.###.#.#####.#.#.#.#.###.###.###.#.#####.#.###########.#####.###.#.###.#.#.#.#####.###.#.###.#.###.#.#####.#.###.#.###
                    #...........#.#.#...........#.#.....#...#.....#.....#.#.#.....#.#.........#...#.......#.#.#...#.#.#.....#.#...#...#...#.#...#.....#...#.#.#.#
                    #####.#######.#.###############.#########.#########.#.#.#####.#.#########.#.###.#####.#.#.###.#.#.#####.#.#.#####.###.#.#####.###.#.#.#.#.#.#
                    #.#.....#...#.#.................#...#.......#.......#.#...#.#.#.....#...#.#.#.....#...#.#...#.#...#.....#.#.#.#...........#.#.#.....#.#.....#
                    #.#.#.#.#.###.#.###.#.###########.###.#######.#######.###.#.#.#####.#.#.###.#.###.###.#.###.#.#########.#.#.#.#.#####.#.#.#.#.#.#.###.#####.#
                    #...#.#...#...#.#...#.........#.............#.#...#.......#.#.#.......#.#...#...#...#.#...#.........#.....#.#.#...#.....#.....#.....#...#...#
                    #.###.#.###.###.###.#########.###.#######.#.#.#.#.###.#####.#.#.###.#.#.#.#########.#.#.###.#.#.###.#.#####.#.###.#.###.###.#.###.#.###.#####
                    #...#.#.....#.....#.........#...#...#...#.#...#.#...#...#...#.....#.#.#.............#...#...#...#...#.....#.#...#...#.#...#.#.#...#.#.#.....#
                    ###.#.#.#########.#######.#####.###.#.#.#.#####.###.###.#.#.#.###.###.###############.###.#######.#.###.#.#.#.#.#####.###.#.#.#.#.#.#.#####.#
                    #.#.#.#...#.....#.#...#...#.....#...#.#...#.....#...#.......#.#.#.......#.......#.....#...#...#...#...#.#.#...#.........#.#.#.#.#.....#.....#
                    #.#.#.#.#.###.#.#.#.#.#####.#########.#####.#####.#########.#.#.###.#.#.#.#####.#######.###.#.#.###.#.#.#.#####.#########.###.###.###.#.###.#
                    #.#.#.#...#...#.#.#.#.....#.#.........#.....#...#.......#...#.....#...#.#...#.#.#.......#...#.#.....#.#.#.#.....#.......#...#.......#.#.#.#.#
                    #.#.#.#.###.###.#.#.#####.#.###.#####.#.#####.#.###.###.#.#####.#.#####.###.#.#.#.#######.###.#######.###.#####.#.#####.###.#####.#.###.#.#.#
                    #...#...#...#...#.....#...#.#.......#.#.....#.#...#...#.#.#.............#...#.#.#...#...#...#...#...#...#.....#.#.#...#.#.....#...#.....#.#.#
                    #.#####.#.###.###.#####.#.#.#.#.###.#.###.###.###.#####.###.#.#.#####.###.###.#.#.#.#.#.###.#.#.#.#####.#.#.#.#.#.#.###.#.#####.#.#######.#.#
                    #.#.....#...#...#.#.....#.#.#.#...#.#.#.#.#...#.......#.....#...#...#.#...#...#.#.#...#.#...#.#...#.....#.#...#.#.#.#...#...#...#.......#...#
                    #.#.###.###.###.###.#######.#.#.#.#.#.#.#.#.#.#######.#########.#.#.###.###.#.#.#.###.#.#.###.#####.#.#.#.#.#####.#.#.#####.#.###.#.###.#.###
                    #.#.#...#...#...#...#.......#.#.#...#.#.#.#.....#.............#.#.#.#...#...#...#.....#.#...#.......#...#.#.#...#.#...#...#.#.#...#...#.#...#
                    #.#.#.#.#.###.###.#.#.#######.#.#####.#.#.#####.#.#######.#.#.###.#.#.###.#######.#####.###.#############.#.#.#.#.#.###.#.#.#.#.#.###.#.###.#
                    #...#.#...#.#.....#.#.#.......#.......#.#...#...#...#...#...#...#.#...#...#.......#.#.....#...........#...#.#.#...#.#...#.#.....#...#.#.#...#
                    #####.#.###.#######.#.#######.#.#####.#.###.#.###.###.#.#######.#.#####.#.#.#.###.#.#.#.#.#.#.#####.#.#####.#.#####.#.###.#######.###.#.#.###
                    #...#...#.........#.#.......#.#.....#.....#.#.#...#...#.#.....#.#.#...#.#.#.#.....#...#.#.#.#...#.#.#.....#.#...#.....#...#...#.#.#...#.#...#
                    ###.###.#.#########.#######.#.#####.#.###.#.#.#####.###.#.#####.#.#.#.#.#.#####.#.#.###.#.#.###.#.#.#####.#.###.#.#######.#.#.#.#.#.###.###.#
                    #.....#...........#...#.....#.#...........................................#...#.#.#.#...#.#.#...#.#...#.......#.#.#.....#...#.#...#.#.....#.#
                    #.#####.#########.#.#.#.#####.#######.#####.#.#.#####.###.#.#.#######.#.###.#.###.###.###.#.#.###.###.#######.#.###.###.#####.#.###.#.#####.#
                    #.....#.........#.....#...#.#.......#...#...#.....#...#...#.#.#.....#.#.#...#.....#...#...#.#.......#...#...#.#.....#...#.....#.#...#.#.....#
                    #.###.#########.#####.###.#.#.#####.#.#.#.#########.###.###.#.#.#.#.#.#.###.#######.#####.#.###.#######.#.#.#########.###.#######.###.#.#####
                    #.#...........#.#.#.....#.#.......#...#.........#...#.....#.#.#.#.....#.....#...#.......#.#...#.#.....................#.............#.......#
                    ###.#.#######.#.#.#.#.#.#.###.#########.#########.#########.#.#.###############.#.#####.#.###.#.#.###.#.#.#######.#.###.#######.#.#.#######.#
                    #...#.#...#.....#...#.#.#...#.#.......#.#...#.....#.........#.#.........#.......#...#.#.#.....#.#.#...#.........#.#...#.......#.#.#.......#.#
                    #.###.#.#.#.#.#######.#####.#.#.#####.#.#.#.#.#####.###.#####.#.#.#####.###.#.#####.#.#.#.#######.#.#####.#####.#.###.#.#####.#.#######.###.#
                    #...#.#.#.#.........#...#...#.#...#.#.#...#...#.....#...#...#.#.#.....#...#.#.........#.#.#...#...#.....#.#...#.#.#...#.....#...........#...#
                    ###.###.#.###.#####.###.#.#######.#.#.#.#.#####.#.###.###.#.#.#.#####.###.#.###########.#.#.#.#.###.###.#.#.#.###.#.#.###################.###
                    #...#...#.........#.....#...#.....#...#.#.....#.#.......#.#.#.#.........#...#.....#.....#...#.#...#.#...#.#.#.....#.#.#.....#.......#.....#.#
                    #.###.#######.#.#######.###.#.#####.#########.#.#####.#.#.#.#.###########.#.#####.#.#########.###.#.#.#####.#####.#.###.###.#.#####.#.#####.#
                    #S............#...........#...#...............#.......#...#...............#.......#...............#.........#.....#.......#.......#.........#
                    #############################################################################################################################################""";

    public static Input getSample() {
        return new Input(sampleData);
    }
    public static Input getData() {
        return new Input(trueData);
    }
}
