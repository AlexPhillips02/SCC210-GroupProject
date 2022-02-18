package com.mygdx.Puzzles.Buttons;

import com.mygdx.Board.Board;

/**
 * @Author Alex Phillips
 * Represents blue button for the puzzles
 */
public class BlueButton extends ColourButton
{
    public BlueButton(Board board, float x, float y)
    {
        super(board, "Buttons/BlueButton.png" , x, y);   
        colour = "BLUE";
    }
}
