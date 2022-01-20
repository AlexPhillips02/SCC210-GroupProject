package com.mygdx.Puzzles.Memory;

import java.util.Random;

/**
 * @author Kathryn Hurst
 */
public class Order
{
    // Create button objects
    private ColourButton R = new ColourButton("R");
    private ColourButton G = new ColourButton("G");
    private ColourButton B = new ColourButton("B");
    private ColourButton Y = new ColourButton("Y");

    protected ColourButton[] buttons = {R,G,B,Y};

    // Button sequence entered by player
    public ColourButton[] sequenceInput = {};

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

        for(int i = 0; i < buttons.length ; i++)
        {
            System.out.println(buttons[i].name);
        }
        System.out.println("\n");
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