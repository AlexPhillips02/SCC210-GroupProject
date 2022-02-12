package com.mygdx.Puzzles;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Player;
import com.mygdx.Puzzles.Memory.ColourButton;
import com.mygdx.Puzzles.Memory.Order;

/**
 * @author Kathryn Hurst
 * Controls the puzzles on the level
 */
public class PuzzleController
{
    private Board board;
    private Player player;
    private int puzzle;
	private Order buttonGame;

    /**
     * Loads board and player
     * @param board used to select locations on board
     * @param player used for collision detection on puzzle interaction
     */
    public PuzzleController(Board board, Player player)
    {
        this.board = board;
        this.player = player;
    }

    /**
     * Randomly selects which puzzle to run and sets it up
     */
    public void SetPuzzle()
	{
		Random rand = new Random();
		puzzle = rand.nextInt(3);

		if(puzzle == 0)
		{
			// Run colour match puzzle
			System.out.println(puzzle + " ColourMatch");
		}
		else if(puzzle == 1)
		{
			// Run memory puzzle
			System.out.println(puzzle + " Memory");
			
			// Choose 4 Random squares to place buttons on
			Squares pathSquare1 = board.getRandomPath();
			Squares pathSquare2 = board.getRandomPath();
			Squares pathSquare3 = board.getRandomPath();
			Squares pathSquare4 = board.getRandomPath();
			// Puzzle set up
			buttonGame = new Order(board, pathSquare1, pathSquare2, pathSquare3, pathSquare4);
			buttonGame.shuffleOrder();
		}
		else
		{
			// run object puzzle
			System.out.println(puzzle + " Object");
		}
	}

    public int getPuzzle()
    {
        return puzzle;
    }

    /**
     * Called within GameController to update puzzle pieces
     * @param batch Batch used to draw the tiles, buttons, or object for the puzzle
     */
    public void Update(SpriteBatch batch)
    {
        // Draw puzzles on board
		if(puzzle == 0)
		{
			// Draw colour tiles
		}
		else if(puzzle == 1)
		{
			// Draw buttons
			buttonGame.Draw(batch);
			ColourButton[] buttons = buttonGame.getButtons();
			
			// Button Collision detection
			for(int i = 0; i < buttons.length; i++)
			{
				if(buttons[i].getCollisionRectangle().overlaps(player.getCollisionRectangle()))
				{
					if(buttons[i].active())
					{
						buttons[i].setActive(false);
						buttonGame.Pressed(buttons[i]);
					}
				}
			}
		}
		else
		{
			// Draw Object & endpoint
		}
    }
}
