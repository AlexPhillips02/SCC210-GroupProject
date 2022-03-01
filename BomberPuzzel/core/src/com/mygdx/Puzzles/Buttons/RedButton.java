package com.mygdx.Puzzles.Buttons;

import com.mygdx.Board.Board;

/**
 * @Author Alex Phillips
 * Represents red button for the puzzles
 */
public class RedButton extends ColourButton
{
    public RedButton(Board board, float x, float y)
    {
        super(board, "Buttons/RedButton.png", "Buttons/RedButtonPushed.png", x, y);   
        colour = "RED";
    }
}
