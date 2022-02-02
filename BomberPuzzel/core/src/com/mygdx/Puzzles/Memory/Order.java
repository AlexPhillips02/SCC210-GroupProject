package com.mygdx.Puzzles.Memory;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;

/**
 * @author Kathryn Hurst
 */
public class Order
{
    Board board;

    ColourButton R;
    ColourButton G;
    ColourButton B;
    ColourButton Y;

    ColourButton[] buttons = new ColourButton[4];
    ColourButton[] sequenceInput = new ColourButton[4];

    public Order(Board board, Squares Square1, Squares Square2, Squares Square3, Squares Square4)
    {
        this.board = board;

        //Translates grid position to coordinate
        int x1 = Square1.getX();
        int y1 = Square1.getY();

        int x2 = Square2.getX();
        int y2 = Square2.getY();

        int x3 = Square3.getX();
        int y3 = Square3.getY();

        int x4 = Square4.getX();
        int y4 = Square4.getY();

        // Create buttons
        R = new ColourButton("Red", "core/assets/Buttons/RedButton.png", board, x1, y1);
        G = new ColourButton("Green", "core/assets/Buttons/GreenButton.png", board, x2, y2);
        B = new ColourButton("Blue", "core/assets/Buttons/BlueButton.png", board, x3, y3);
        Y = new ColourButton("Yellow", "core/assets/Buttons/YellowButton.png", board, x4, y4);

        buttons[0] = R;
        buttons[1] = G;
        buttons[2] = B;
        buttons[3] = Y;
    }

    public void Draw(SpriteBatch batch)
    {
        R.Draw(batch);
        G.Draw(batch);
        B.Draw(batch);
        Y.Draw(batch);
    }

    // Randomly shuffle the order buttons need to be pressed in
    public void shuffleOrder()
    {
        for(int i = 0; i < buttons.length; i++)
        {
            Random rand = new Random();
            int indexSwap = rand.nextInt(buttons.length);
            ColourButton temp = buttons[indexSwap];
            buttons[indexSwap] = buttons[i];
            buttons[i] = temp;
        }
        System.out.println("Sequence: " + buttons[0].name + ", " + buttons[1].name + ", " + buttons[2].name + ", " + buttons[3].name + "\n");
    }

    // Display the button sequence on screen
    public void displayOrder()
    {}

    // Compare the buttons input by the player to the actual sequence
    public void compareInput()
    {
        int i = sequenceInput.length;
        if(buttons[i] != sequenceInput[i])
        {
            sequenceInput = null;
            displayOrder();
        }
    }
}