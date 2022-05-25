import java.util.Arrays;

public class Rules{
    private int startPosX;
    private int startPosY;
    private int endPosX;
    private int endPosY;
    private int numberOfInfected;
    private int[][] boardCopy;
    Parsing parsing;
    Visualiser  visualiser;

    public Rules(String[] argv)
    {
        parsing = new Parsing(argv, this);
        boardCopy = parsing.initGrid();
        System.out.println("---------------start of simulation------------------");
    }

    public void startSimulation()
    {
        visualiser = new Visualiser(this);
        while (parsing.getRound() > 0)
        {
            cloneBoard();
//            printBoard();
            for (int i = 0; i < parsing.getGrid_size(); i++)
            {
                for (int j = 0; j < parsing.getGrid_size(); j++)
                {
                    if (parsing.getMap()[i][j] == 0)
                    {
                        if (checkNeighbours(i, j) > parsing.getInfection())
                            boardCopy[i][j] = 1;
                    }
                    else if (parsing.getMap()[i][j] == 1)
                    {
                        if (checkNeighbours(i, j) > parsing.getRecover())
                            boardCopy[i][j] = 2;
                    }
                }
            }
            visualiser.printingOut(boardCopy);
            try {

                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            parsing.decreaseRound();
            parsing.setMap(boardCopy);
        }
//        printBoard();
    }

    public void cloneBoard()
    {
        for (int i = 0; i < parsing.getMap().length; i++)
        {
            for (int j = 0; j < parsing.getMap().length; j++)
            {
                boardCopy[i][j] = parsing.getMap()[i][j];
            }
        }
    }

    public int  checkNeighbours(int x, int y)
    {
        startPosX = Math.max(x - 1, 0);
        startPosY = Math.max(y - 1, 0);
        endPosX = Math.min(x + 1, parsing.getGrid_size() - 1);
        endPosY = Math.min(y + 1, parsing.getGrid_size() - 1);
        numberOfInfected = 0;
        for (int i = startPosX; i <= endPosX; i++)
        {
            for (int j = startPosY; j <= endPosY; j++)
            {
                if (parsing.getMap()[i][j] != 2)
                    numberOfInfected += parsing.getMap()[i][j];
            }
        }
        return numberOfInfected;
    }

    public void printBoard()
    {
        for (int i = 0; i < parsing.getGrid_size(); i++)
        {
            for (int j = 0; j < parsing.getGrid_size(); j++)
            {
                System.out.print(boardCopy[i][j] + "  ");
            }
            System.out.println();
        }
    }
}