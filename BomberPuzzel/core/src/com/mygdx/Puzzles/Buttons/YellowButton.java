package com.mygdx.Puzzles.Buttons;

import com.mygdx.Board.Board;

/**
 * @Author Alex Phillips
 * Represents yellow button for the puzzles
 */
public class YellowButton extends ColourButton
{
    public YellowButton(Board board, float x, float y)
    {
        super(board, "core/assets/Buttons/YellowButton.png" , x, y);   
        colour = "YELLOW";
    }
}
