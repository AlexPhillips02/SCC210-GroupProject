package com.mygdx.Puzzles.Memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Puzzles.Buttons.BlueButton;
import com.mygdx.Puzzles.Buttons.ColourButton;
import com.mygdx.Puzzles.Buttons.GreenButton;
import com.mygdx.Puzzles.Buttons.RedButton;
import com.mygdx.Puzzles.Buttons.YellowButton;
import com.mygdx.Puzzles.Puzzle;

/**
 * @author Kathryn Hurst
 * Order creates and shuffles the buttons, it also controls what happens when the buttons are pressed
 */
public class Order extends Puzzle
{
    ColourButton R;
    ColourButton G;
    ColourButton B;
    ColourButton Y;

    ColourButton[] buttons = new ColourButton[4];
    ColourButton[] sequenceInput = new ColourButton[4];

    String sequence;

    /**
     * Constructor for the order class
     * @param gui the GUI of the game
     * @param board is the gameboard
     */
    public Order(GUI gui, Board board)
    {
        super(gui, board);
    }

    public void Draw(SpriteBatch batch)
    {
        R.Draw(batch);
        G.Draw(batch);
        B.Draw(batch);
        Y.Draw(batch);
    }

    /**
     * // Accessor method for the buttons array
     * @return the buttons array
     */
    public ColourButton[] getButtons()
    {
        return buttons;
    }

    /**
     * Randomly shuffle the order buttons need to be pressed in
     */
    public void createGame()
    {
        // Choose 4 Random squares to place buttons on
        ArrayList<Squares> usedSquares = new ArrayList<>();
        for(int i = 0; i< 4; i++)
        {
            boolean r = true;
            while(r)
            {
                Squares pathSquare = board.getRandomPath();
                if(!usedSquares.contains(pathSquare))
                {
                    usedSquares.add(pathSquare);
                    r = false;
                }
            }
        }

        // Translates squares to grid positions
        int x1 = usedSquares.get(0).getX();
        int y1 = usedSquares.get(0).getY();

        int x2 = usedSquares.get(1).getX();
        int y2 = usedSquares.get(1).getY();

        int x3 = usedSquares.get(2).getX();
        int y3 = usedSquares.get(2).getY();

        int x4 = usedSquares.get(3).getX();
        int y4 = usedSquares.get(3).getY();

        // Create buttons
        int tileWidth = square1.getTile().getWidth();
        int tileHeight = square1.getTile().getHeight();

        R = new RedButton(board, x1, y1);
        R.setX(x1 + ((tileWidth - R.getWidth()) / 2));
		R.setY(y1 + ((tileHeight - R.getHeight()) / 2));

        G = new GreenButton(board, x2, y2);
        G.setX(x2 + ((tileWidth - G.getWidth()) / 2));
		G.setY(y2 + ((tileHeight - G.getHeight()) / 2));

        B = new BlueButton(board, x3, y3);
        B.setX(x3 + ((tileWidth - B.getWidth()) / 2));
		B.setY(y3 + ((tileHeight - B.getHeight()) / 2));

        Y = new YellowButton(board, x4, y4);
        Y.setX(x1 + ((tileWidth - Y.getWidth()) / 2));
		Y.setY(y2 + ((tileHeight - Y.getHeight()) / 2));

        buttons[0] = R;
        buttons[1] = G;
        buttons[2] = B;
        buttons[3] = Y;

        for(int i = 0; i < buttons.length; i++)
        {
            Random rand = new Random();
            int indexSwap = rand.nextInt(buttons.length);
            ColourButton temp = buttons[indexSwap];
            buttons[indexSwap] = buttons[i];
            buttons[i] = temp;
        }

        sequence = buttons[0].getColour() + ", " + buttons[1].getColour() + ", " + buttons[2].getColour() + ", " + buttons[3].getColour();
        displayOrder();
    }

    /**
     * Display the correct sequence to press the buttons in
     */
    public void displayOrder()
    {
        gui.addTempLabel("SEQUENCE: " + sequence);
    }

    /**
     * Adds the pressed button to the sequenceInput 
     * @param button is the button pressed
     */
    public void add(ColourButton button)
    {
        for(int i = 0; i < sequenceInput.length; i++)
        {
            if(sequenceInput[i] == null)
            {
                sequenceInput[i] = button;
                break;
            }
        }
    }

    /**
     * Compares the order of buttons pressed to the correct order
     */
    public void compareInput()
    {
        int match = 0;
        for(int i = 0; i < sequenceInput.length; i++)
        {
            if(sequenceInput[i] != buttons[i] && sequenceInput[i] != null)
            {
                gui.setPuzzleInfoBad("Sequence Incorrect");
                for(int j = 0; j < sequenceInput.length; j++)
                {
                    if(sequenceInput[j] != null)
                    {
                        sequenceInput[j].unclicked();
                    }
                }
                Arrays.fill(sequenceInput, null);
                match = 0;
                displayOrder();
                R.setActive(true);
                G.setActive(true);
                B.setActive(true);
                Y.setActive(true);
                break;
            }
            else
            {
                if(sequenceInput[i] == buttons[i])
                {
                    match++;
                    gui.removeTempLabel();
                    gui.addTempLabel(sequenceInput[i].getColour() + " Button Pressed");
                }
                if(match == 4)
                {
                    gui.setPuzzleInfoGood("Sequence Complete");
                    winStatus = true;
                }
            }
        }
    }
    
    /**
     * What happens when the player presses the button:
     * @param button is the button pressed
    */
    public void Pressed(ColourButton button)
    {
        gui.addTempLabel(button.getColour() + " Button Pressed");
        button.setActive(false);
        button.clicked();
        add(button);
        compareInput();
    }
}