package com.mygdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends ApplicationAdapter 
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
}