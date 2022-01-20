package com.mygdx.Puzzles.Memory;

import java.util.Random;

/**
 * @author Kathryn Hurst
 */
public class Order
{
    ColourButton R = new ColourButton("R");
    ColourButton G = new ColourButton("G");
    ColourButton B = new ColourButton("B");
    ColourButton Y = new ColourButton("Y");

    protected ColourButton[] Buttons = {R,G,B,Y};

    public void ShuffleOrder()
    {
        for(int i = 0; i < Buttons.length; i++)
        {
            Random rand = new Random();
            int indexSwap = rand.nextInt(Buttons.length);
            ColourButton temp = Buttons[indexSwap];
            Buttons[indexSwap] = Buttons[i];
            Buttons[i] = temp;
        }

        for(int i = 0; i < Buttons.length ; i++)
        {
            System.out.println(Buttons[i].name);
        }
        System.out.println("\n");
    }
}