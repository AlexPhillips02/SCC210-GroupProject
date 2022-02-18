package com.mygdx.Puzzles.Buttons;

import com.mygdx.Board.Board;

/**
 * @Author Alex Phillips
 * Represents green button for the puzzles
 */
public class GreenButton extends ColourButton
{
    public GreenButton(Board board, float x, float y)
    {
        super(board, "core/assets/Buttons/GreenButton.png" , x, y);   
        colour = "GREEN";
    }
}
