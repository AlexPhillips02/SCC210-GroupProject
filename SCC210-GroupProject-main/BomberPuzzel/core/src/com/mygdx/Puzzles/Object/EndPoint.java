package com.mygdx.Puzzles.Object;

import com.mygdx.Board.Board;
import com.mygdx.Puzzles.Buttons.ColourButton;

/**@author Victor Boateng, kathryn Hort 
 * Moveable object must be push to a target spot in order to win
 */
public class EndPoint extends ColourButton
{

    public EndPoint(Board board,float x,float y){
        super(board, "Buttons/BlueButton.png", x, y);
    }



}
