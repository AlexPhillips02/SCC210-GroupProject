package com.mygdx.Puzzles.Memory;

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
     * @param board is the gameboard
     * @param Square1 is the random square for the red button to appear at
     * @param Square2 is the random square for the green button to appear at
     * @param Square3 is the random square for the blue button to appear at
     * @param Square4 is the random square for the yellow button to appear at
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
        Squares square1 = board.getRandomPath();
        Squares square2 = board.getRandomPath();
        Squares square3 = board.getRandomPath();
        Squares square4 = board.getRandomPath();

        // Translates squares to grid positions
        int x1 = square1.getX();
        int y1 = square1.getY();

        int x2 = square2.getX();
        int y2 = square2.getY();

        int x3 = square3.getX();
        int y3 = square3.getY();

        int x4 = square4.getX();
        int y4 = square4.getY();

        // Create buttons
        R = new RedButton(board, x1, y1);
        G = new GreenButton(board, x2, y2);
        B = new BlueButton(board, x3, y3);
        Y = new YellowButton(board, x4, y4);

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
                gui.addTempLabel("Not match");
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
                }
                if(match == 4)
                {
                    gui.addTempLabel("Sequence complete");
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
        add(button);
        compareInput();
    }
}