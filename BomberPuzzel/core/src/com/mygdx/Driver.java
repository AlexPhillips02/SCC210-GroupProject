package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends Game 
{
	private SpriteBatch batch;
	private GameController controller;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		controller = new GameController(batch);
	}

	@Override
	public void render() 
	{
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();
		controller.Update();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();		//Honestly not really sure what this does or if it is ever used
	}

	@Override
	public void resize(int width, int height)
	{
		controller.resize(width, height);
	}
}
