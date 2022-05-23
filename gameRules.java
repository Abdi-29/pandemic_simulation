import java.util.Arrays.*;
import java.util.Arrays;

public class gameRules extends Simulation
{
    int startPosX;
    int startPosY;
    int endPosX;
    int endPosY;
    int numberOfInfected;
    int[][] boardCopy;

    public gameRules(String[] argv)
    {
        super(argv);
        boardCopy = super.initGrid();
        System.out.println("---------------start of simulation------------------");
    }

    public void startSimulation()
    {
        cloneBoard();
        while (round > 0) {
            for (int i = 0; i < grid_size; i++) {
                for (int j = 0; j < grid_size; j++)
                {
                    if (map[i][j] == 0)
                    {
                        if (checkNeighbours(i, j) > infection)
                            boardCopy[i][j] = 1;
                    }
                    else if (map[i][j] == 1)
                    {
                        if (checkNeighbours(i, j) > recover)
                            boardCopy[i][j] = 2;
                    }
                }
            }
//            System.out.println(round);
            round--;
            map = boardCopy;
        }
        printBoard();
    }

    //deep copy of map
    public void cloneBoard()
    {
        for (int i = 0; i < map.length; i++)
        {
            boardCopy[i] = Arrays.copyOf(map[i], map[i].length);
        }
    }

    public int  checkNeighbours(int x, int y)
    {
        startPosX = Math.max(x - 1, 0);
        startPosY = Math.max(y - 1, 0);
        endPosX = Math.min(x + 1, grid_size - 1);
        endPosY = Math.min(y + 1, grid_size - 1);
        numberOfInfected = 0;
        for (int i = startPosX; i <= endPosX; i++)
        {
            for (int j = startPosY; j <= endPosY; j++)
            {
                if (map[i][j] != 2)
                    numberOfInfected += map[i][j];
            }
        }
        return numberOfInfected;
    }

    public void printBoard()
    {
        for (int i = 0; i < grid_size; i++)
        {
            for (int j = 0; j < grid_size; j++)
            {
                System.out.print(boardCopy[i][j] + "  ");
            }
            System.out.println();
        }
    }
}