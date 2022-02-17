package com.mygdx.Puzzles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Board.Board;

/**
 * @author Alex Phillips
 */
public class Puzzle 
{
    protected Board board;
    protected boolean winStatus = false;
    
    public Puzzle(Board board)
    {
        this.board = board;
    }

    //Method overriden on puzzle creation
    public void createGame()
    {}

    //Overriden in type of puzzle
    public void Draw(SpriteBatch batch) 
    {}
}
