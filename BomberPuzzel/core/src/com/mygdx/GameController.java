package com.mygdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;
import com.mygdx.TileTypes.Path;
import com.mygdx.Board.Squares;

public class GameController
{
	private Boolean winStatus;
    private Board gameBoard;
	private Player player;
	private SpriteBatch batch;

    public GameController(SpriteBatch batch)
    {
        this.batch = batch;
		CreateLevel();
    }

	public void CreateLevel() 
	{
		gameBoard = new Board(29, 15);
		player = new Player(gameBoard, 32, 32, 100);

		//Just creates a bit of room to move around in at start
		Squares temps = gameBoard.getGameSquare(2, 2);
		temps.setTile(new Path());
	}

	public void Update() 
	{	
		player.checkInput();

		gameBoard.Draw(batch);	//draws gameboard
		player.Draw(batch);		//Draws player
	}
}
