public class Simulation{

    private final int grid_size;
    private int round;
    private final int infection;
    private final int recover;
    private int[][] map;
    GameRules gameRules;

    public Simulation(String[] argv, GameRules gameRules)
    {
        parsing(argv);
        grid_size = Integer.parseInt(argv[0]);
        round = Integer.parseInt(argv[1]);
        infection = Integer.parseInt(argv[2]);
        recover = Integer.parseInt(argv[3]);
        map = initGrid();
        if (!validPoints(argv[argv.length - 1]))
        {
            System.out.println("invalid starting points");
            System.exit(1);
        }
        this.gameRules = gameRules;
    }
    public static void parsing(String[] argv)
    {
        if (argv.length != 5) {
            System.out.println("usage: [grid_size] [rounds] [infections] [recovery] [infected person to start with]");
            System.exit(1);
        }
        if (!isInvalidInput(argv))
        {
            System.out.println("input must be all integer");
            System.exit(1);
        }
    }

    public static boolean   isInvalidInput(String[] argv) {
        int len;
        int number;

        len = argv.length;
        for (int i = 0; i < len - 1; i++)
        {
            if (argv[i] == null)
                return false;
            try {
                number = Integer.parseInt(argv[i]);
                if (number < 0)
                    return false;
            }
            catch (Exception e) {
                return false;
            }
        }
        return !argv[len - 1].isEmpty();
    }
    public int[][]  initGrid()
    {
        int[][] grid;
        int     size;

        size = this.grid_size;
        grid = new int[size][size];
        for (int i = 0; i < grid_size; i++)
        {
            for (int j = 0; j < grid_size; j++)
            {
                grid[i][j] = 0;
            }
        }
        return grid;
    }

    public boolean validPoints(String argv)
    {
        if (argv.charAt(0) != '[' || argv.charAt(argv.length() - 1) != ']') {
            return false;
        }
        argv = argv.substring(1, argv.length() - 1);
        for (String s : argv.split(">,<")) {
            int x;
            int y;
            String[] split = s.split(",");
            if (split.length != 2)
                return false;
            try {
                if (split[0].startsWith("<"))
                    x = Integer.parseInt(split[0].substring(1));
                else
                    x = Integer.parseInt(split[0]);
                if (split[1].endsWith(">"))
                    y = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
                else
                    y = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (x < 0 || y < 0 || x >= grid_size || y >= grid_size)
                return false;
            map[x][y] = 1;
        }
        return true;
    }

    public int getGrid_size() {
        return grid_size;
    }

    public int getRound() {
        return round;
    }

    public int getInfection() {
        return infection;
    }

    public int getRecover() {
        return recover;
    }

    public int[][] getMap() {
        return map;
    }

    public void decreaseRound() {
        round--;
    }

    public void setMap(int[][] boardCopy) {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                map[i][j] = boardCopy[i][j];
            }
        }
    }
}