import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class Visualiser {

    static boolean  test;
    static JPanel   panel;
    static JFrame   frame;
    private int     count;
    private static final int BOARDER = 1;

    GameRules gameRules;

    public Visualiser(GameRules gameRules) {
        frame = new JFrame("pandemic simulation");
        frame.setSize(720,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 700));
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        this.gameRules = gameRules;
//        printingOut(gameRules.simulation.getMap());
//        gameRules.printBoard();
    }

    public void GraphicalInterface(int[][] array, Graphics graphic)
    {
        int BOX_DIM = 700/ array.length;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array.length; j++)
            {
                if(array[i][j] == 0)
                {
                    graphic.setColor(Color.WHITE);
                }
                else if(array[i][j] == 1)
                {
                    graphic.setColor(Color.RED);
                }
                else if(array[i][j] == 2)
                {
                    graphic.setColor(Color.GREEN);
                }
                graphic.fillRect(j  * BOX_DIM, i * BOX_DIM, BOX_DIM - BOARDER, BOX_DIM - BOARDER);
            }
        }
    }

    public void printingOut(int[][] array)
    {
        Graphics graphic = panel.getGraphics();
        GraphicalInterface(array, graphic);
    }
}