package com.mygdx.Puzzles;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;
import com.mygdx.Puzzles.Buttons.ColourButton;
import com.mygdx.Puzzles.ColourMatch.Match;
import com.mygdx.Puzzles.Memory.Order;
import com.mygdx.Sound.SoundController;

/**
 * @author Kathryn Hurst
 * Controls the puzzles on the level
 */
public class PuzzleController
{
	private GUI gui;
    private Board board;
    private Player player;
    private int puzzle;
	private Puzzle puzzleGame;
	private SoundController soundController;

	private Sound redButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/RedButtonSound.mp3"));
	private Sound blueButton = Gdx.audio.newSound(Gdx.files.internal("sounds/BlueButtonSound.mp3"));
	private Sound yellowButton = Gdx.audio.newSound(Gdx.files.internal("sounds/YellowButtonSound.mp3"));
	private Sound greenButton = Gdx.audio.newSound(Gdx.files.internal("sounds/GreenButtonSound.mp3"));
    /**
     * Loads board and player
     * @param board used to select locations on board
     * @param player used for collision detection on puzzle interaction
     */
    public PuzzleController(GUI gui, Board board, Player player)
    {
		this.gui = gui;
        this.board = board;
        this.player = player;
		soundController = new SoundController();
    }

    /**
     * Randomly selects which puzzle to run and sets it up
     */
    public void SetPuzzle()
	{
		Random rand = new Random();
		puzzle = rand.nextInt(1);

		if(puzzle == 0)
		{
			// Run colour match puzzle
			gui.setPuzzle("Colour Match");

			puzzleGame = new Match(gui, board);
			puzzleGame.createGame();
		}
		else if(puzzle == 1)
		{
			// Run memory puzzle
			gui.setPuzzle("Button Sequence");
		
			// Puzzle set up
			puzzleGame = new Order(gui, board);
			puzzleGame.createGame();
		}
		else
		{
			// Run object puzzle
			gui.setPuzzle("Object");
		}
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
			puzzleGame.Draw(batch);
			ArrayList<ColourButton> buttons = ((Match)puzzleGame).getButtons();

			for(int i = 0; i < buttons.size(); i++)
			{
				if(buttons.get(i).getCollisionRectangle().overlaps(player.getCollisionRectangle()))
				{
					if(buttons.get(i).getActiveStatus())
					{	
						((Match)puzzleGame).setCurrent(buttons.get(i));
						i = 0;
					}
				}			
			}
		}
		else if(puzzle == 1)
		{
			// Draw buttons
			puzzleGame.Draw(batch);
			ColourButton[] buttons = ((Order) puzzleGame).getButtons();
			
			// Button Collision detection
			for(int i = 0; i < buttons.length; i++)
			{
				if(buttons[i].getCollisionRectangle().overlaps(player.getCollisionRectangle()))
				{
					if(buttons[i].getActiveStatus())
					{
						buttons[i].setActive(false);
						((Order) puzzleGame).Pressed(buttons[i]);
					}
				}
			}
		}
		else
		{
			// Draw Object & endpoint
		}
    }

	public boolean getWinStatus()
	{
		return puzzleGame.winStatus;
	}
}
