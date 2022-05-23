import javax.swing.*;
import javax.awt.*;
import javax.awt.Dimention;
import javax.awt.Graphics;
import javax.awt.ActionEvent;
import javax.awt.ActionListener;
import javax.swing.JFrame;

public class Visualiser
{
    static int      count;
    static boolean  test;
    static JPanel   panel;
    static Jframe   frame;

    public static void GraphicalInterface(int[][] array, Graphics graphic)
    {
        int BOX_DIM = 10;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array.length; j++)
            {
                if(array[i][j] == 0)
                {
                    graphic.setColor(Color.WHITE);
                }
                if(array[i][j] == 1)
                {
                    graphic.setColor(Color.BLACK);
                }
                if(array[i][j] == 2)
                {
                    graphic.setColor(Color.RED);
                }
                graphic.fillRect(i * BOX_DIM, j * BOX_DIM, BOX_DIM, BOX_DIM);
            }
        }
    }

    public static void printingOut(int[][] array)
    {
        test = true;

        frame = new JFrame("pandemic simulation");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(710, 710));
        frame.add(panel, BorderLayout.WEST);
        frame.setVisible(true);


        while(true) {
            while(Gui.test) {
                Graphics graphic = panel.getGraphics();
                GraphicalInterface(array, graphic);
                gameRules.startSimulation();
            }
        }
    }
}
