abstract class GameBoard
{
     int        grid_size;
     int        round;
     int        infection;
     int        recover;
     int[][]    map;
}

public class Simulation  extends GameBoard{

    public Simulation(String[] argv)
    {
        parsing(argv);
        super.grid_size = Integer.parseInt(argv[0]);
        super.round = Integer.parseInt(argv[1]);
        super.infection = Integer.parseInt(argv[2]);
        super.recover = Integer.parseInt(argv[3]);
        super.map = initGrid();
        if (validPoints(argv[argv.length - 1]) == false)
        {
            System.out.println("invalid starting points");
            System.exit(1);
        }
    }
    public static void parsing(String[] argv)
    {
        if (argv.length != 5) {
            System.out.println("usage: [grid_size] [rounds] [infections] [recovery] [infected person to start with]");
            System.exit(1);
        }
        if (isInvalidInput(argv) == false)
        {
            System.out.println("input must be all integer");
            System.exit(1);
        }
    }

    public static boolean   isInvalidInput(String[] argv) {
        int len;
        int number;
        String[]  tmp;

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
        if (argv[len - 1].isEmpty())
            return false;
        return true;
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

    public boolean  validPoints(String argv)
    {
        String[]    tmp;
        int         len;
        int         x;
        int         y;

        if (argv.charAt(0) != '[' || argv.charAt(argv.length() - 1) != ']') {
            return false;
        }
        argv = argv.substring(1, argv.length() - 1);
        tmp = argv.split(",");
        len = tmp.length;
        x = 0;
        y = 0;
        if (len % 2 == 1) {
            return false;
        }
        System.out.println("len " + len);
        for (int i = 0; i < len; i++)
        {
            if (i % 2 == 0)
            {
                x = stringToInt(tmp[i], '<', 1, tmp[i].length());
                if (x < 0)
                    return false;
            }
            else
            {
                y = stringToInt(tmp[i], '>', 0, tmp[i].length());
                if (y < 0)
                    return false;
                map[x][y] = 1;
            }
        }
        return true;
    }

    public int  stringToInt(String input, char sign, int start, int end)
    {
        String  tmp;
        int     number;

        if (start != 0)
        {
            if (input.charAt(0) != sign) {
                return -1;
            }
        }
        else
        {
            end -= 1;
            if (input.charAt(end) != sign) {
                return -1;
            }
        }
        tmp = input.substring(start, end);
        try
        {
            number = Integer.parseInt(tmp);
            if (number < 0 || number >= grid_size)
                return -1;
        }
        catch (Exception e)
        {
            return -1;
        }
        return number;
    }
}