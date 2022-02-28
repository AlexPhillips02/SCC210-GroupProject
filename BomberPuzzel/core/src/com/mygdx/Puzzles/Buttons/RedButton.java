package com.mygdx.Puzzles.Buttons;

import com.mygdx.Board.Board;

/**
 * @Author Alex Phillips
 * Represents red button for the puzzles
 */
public class RedButton extends ColourButton
{
    private String unclicked = "Buttons/RedButton.png";
    private String clicked = "";

    public RedButton(Board board, float x, float y)
    {
        super(board, "Buttons/RedButton.png" , x, y);   
        colour = "RED";
    }
}
