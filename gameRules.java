import java.util.Arrays.*;
import java.util.Arrays;

public class gameRules
{
    private int startPosX;
    private int startPosY;
    private int endPosX;
    private int endPosY;
    private int numberOfInfected;
    private int[][] boardCopy;
    Simulation  simulation;
    Visualiser  visualiser;

    public gameRules(String[] argv)
    {
        simulation = new Simulation(argv);
        boardCopy = simulation.initGrid();
        System.out.println("---------------start of simulation------------------");
    }

    public void startSimulation()
    {
        cloneBoard();
        while (simulation.getRound() > 0) {
            for (int i = 0; i < simulation.getGrid_size(); i++) {
                for (int j = 0; j < simulation.getGrid_size(); j++)
                {
                    if (simulation.getMap()[i][j] == 0)
                    {
                        if (checkNeighbours(i, j) > simulation.getInfection())
                            boardCopy[i][j] = 1;
                    }
                    else if (simulation.getMap()[i][j] == 1)
                    {
                        if (checkNeighbours(i, j) > simulation.getRecover())
                            boardCopy[i][j] = 2;
                    }
                }
            }
            simulation.decreaseRound();
            simulation.nextMap(boardCopy);
        }
        printBoard();
    }

    //deep copy of map
    public void cloneBoard()
    {
        for (int i = 0; i < simulation.mapLength(); i++)
        {
            boardCopy[i] = Arrays.copyOf(simulation.getMap()[i], simulation.mapLength());
        }
    }

    public int  checkNeighbours(int x, int y)
    {
        startPosX = Math.max(x - 1, 0);
        startPosY = Math.max(y - 1, 0);
        endPosX = Math.min(x + 1, simulation.getGrid_size() - 1);
        endPosY = Math.min(y + 1, simulation.getGrid_size() - 1);
        numberOfInfected = 0;
        for (int i = startPosX; i <= endPosX; i++)
        {
            for (int j = startPosY; j <= endPosY; j++)
            {
                if (simulation.getMap()[i][j] != 2)
                    numberOfInfected += simulation.getMap()[i][j];
            }
        }
        return numberOfInfected;
    }

    public void printBoard()
    {
        for (int i = 0; i < simulation.getGrid_size(); i++)
        {
            for (int j = 0; j < simulation.getGrid_size(); j++)
            {
                System.out.print(boardCopy[i][j] + "  ");
            }
            System.out.println();
        }
    }
}