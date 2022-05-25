import java.util.Arrays;

public class GameRules{
    private int startPosX;
    private int startPosY;
    private int endPosX;
    private int endPosY;
    private int numberOfInfected;
    private int[][] boardCopy;
    Simulation simulation;
    Visualiser  visualiser;

    public GameRules(String[] argv)
    {
        simulation = new Simulation(argv, this);
        boardCopy = simulation.initGrid();
        System.out.println("---------------start of simulation------------------");
    }

    public void startSimulation()
    {
        visualiser = new Visualiser(this);
        while (simulation.getRound() > 0)
        {
            cloneBoard();
//            printBoard();
            for (int i = 0; i < simulation.getGrid_size(); i++)
            {
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
            visualiser.printingOut(boardCopy);
//            System.out.println("hello there " + simulation.getRound());
            try {

                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            simulation.decreaseRound();
            simulation.setMap(boardCopy);
        }
//        printBoard();
    }

    //deep copy of map
    public void cloneBoard()
    {
        for (int i = 0; i < simulation.getMap().length; i++)
        {
            for (int j = 0; j < simulation.getMap().length; j++)
            {
                boardCopy[i][j] = simulation.getMap()[i][j];
            }
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