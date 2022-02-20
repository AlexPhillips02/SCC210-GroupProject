package com.mygdx.Puzzles.Object;

import com.mygdx.Board.Board;
import com.mygdx.Player.Entity;


/**@author Victor boateng, Kathryn Hurst 
 * Will create a moveable object that can be moved by the player 
*/
public class MoveableObject extends Entity
{
    public MoveableObject(Board board, float x, float y, float speed){
        super("football (1).png", board, x, y, speed);
    }

}
