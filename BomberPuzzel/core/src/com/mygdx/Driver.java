package com.mygdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;
import com.mygdx.Walls.UnbreakableWall;
import com.mygdx.Board.Squares;

public class Driver extends ApplicationAdapter 
{
	private Board gameBoard;
	private Player player;
	private SpriteBatch batch;
	
	@Override
	public void create () 
	{
		gameBoard = new Board();
		player = new Player(gameBoard, 150, 100, 100);
		batch = new SpriteBatch();

		//Sets square at 10, 5 to an unbreakable wall (Assigned after board and player created to make sure is working dynamically)
		Squares temps = gameBoard.getGameSquare(10, 5);
		temps.setTile(new UnbreakableWall());
	}


	@Override
	public void render() 
	{
		ScreenUtils.clear(0, 0, 0, 0);
		
		player.checkInput();

		batch.begin();
		gameBoard.Draw(batch);	//draws gameboard
		player.Draw(batch);		//Draws player
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();		//Honestly not really sure what this does or if it is ever used
	}
}
