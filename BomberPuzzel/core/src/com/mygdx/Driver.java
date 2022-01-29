package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.GameScreens.MainGameScreen;

public class Driver extends Game {

	private SpriteBatch batch;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(batch));
	}

	@Override
	public void render() 
	{
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();		//Honestly not really sure what this does or if it is ever used
	}
}
