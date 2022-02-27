package com.mygdx.Puzzles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.Board;

/**
 * @author Alex Phillips
 * Introduces the puzzles.
 */
public class Puzzle 
{
    protected GUI gui;
    protected Board board;
    protected boolean winStatus = false;

    /**
     * Constructor for the puzzles.
     * @param gui the GUI of the game
     * @param board the Game Board
     */
    public Puzzle(GUI gui, Board board)
    {
        this.gui = gui;
        this.board = board;
    }

    //Method overridden on puzzle creation
    public void createGame()
    {}

    //Overridden in type of puzzle
    public void Draw(SpriteBatch batch) 
    {}
}
