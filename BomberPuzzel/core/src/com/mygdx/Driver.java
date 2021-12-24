package com.mygdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;
import com.mygdx.Player.PlayerController;

public class Driver extends ApplicationAdapter 
{
	private Board gameBoard;
	private Player player;
	private PlayerController controller;
	private SpriteBatch batch;
	
	@Override
	public void create () 
	{
		gameBoard = new Board();
		player = new Player();
		batch = new SpriteBatch();
		controller = new PlayerController(player, gameBoard);
	}

	@Override
	public void render() 
	{
		ScreenUtils.clear(0, 0, 0, 0);
		
		controller.update();	//Controller That moves player

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
