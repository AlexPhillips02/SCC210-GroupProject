package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.GameScreens.MainGameScreen;

public class Driver extends Game {
	public SpriteBatch batch;
	public GameController controller;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		controller = new GameController(batch);
		this.setScreen(new MainGameScreen(this));
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

	@Override
	public void resize(int width, int height)
	{
		controller.resize(width, height);
	}
}
