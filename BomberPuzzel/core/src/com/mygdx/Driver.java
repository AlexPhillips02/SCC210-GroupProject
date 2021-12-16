package com.mygdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends ApplicationAdapter 
{
	Board gameBoard;
	Player player;
	
	@Override
	public void create () {
		gameBoard = new Board();
		player = new Player();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		//Use arrow keys for player
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			player.setX(player.getX() - (200 * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			player.setX(player.getX() + (200 * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			player.setY(player.getY() + (200 * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			player.setY(player.getY() - (200 * Gdx.graphics.getDeltaTime()));
		}

		gameBoard.Draw();
		player.Draw();
	}
	
	@Override
	public void dispose () {
		//batch.dispose();
	}
}
