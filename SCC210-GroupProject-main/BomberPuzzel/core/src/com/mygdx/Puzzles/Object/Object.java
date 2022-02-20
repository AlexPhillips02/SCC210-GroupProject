package com.mygdx.Puzzles.Object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Player;
import com.mygdx.Puzzles.Puzzle;

public class Object extends Puzzle {

Player player;
MoveableObject object;
EndPoint target;

    public Object(GUI gui, Board board) {
        super(gui, board);
        
        
    }

    public void createGame(){

        Squares pathSquare = board.getRandomPath();
        Squares pathSquare1 = board.getRandomPath();
        
        int x = pathSquare.getX();
        int y = pathSquare.getY();
        
        int x1 = pathSquare1.getX();
        int y2 = pathSquare1.getY();
        
        object = new MoveableObject(board, x, y, 0);
        target = new EndPoint(board, x1, y2);  

    }
          

    public void Draw(SpriteBatch batch)
    {
        object.Draw(batch);
        target.Draw(batch);
    }

    public void move(){
        object.setX(getX());
    }


}
