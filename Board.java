import javax.swing.*;
import java.awt.*;

public class Board extends JFrame
{
    JPanel boardPanel = new JPanel();
    BomberSquare[][] squares = new BomberSquare[20][20];

    public Board()
    {
        this.setTitle("Bomber Man");
		this.setSize(400,400);
		this.setContentPane(boardPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boardPanel.setLayout(new GridLayout(20,20));

        for (int x = 0; x < 20; x++) 
        {
            for (int y = 0; y < 20; y++) {
                squares[x][y] = new BomberSquare(x, y);

                boardPanel.add(squares[x][y]);
            }    
        }

        setVisible(true);
    }
}
