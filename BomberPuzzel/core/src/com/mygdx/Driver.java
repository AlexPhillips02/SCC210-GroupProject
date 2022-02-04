package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.GameScreens.MainGameScreen;
import com.mygdx.GameScreens.MenuScreen;

public class Driver extends Game {

	public static final int WIDTH = 928;
	public static final int HEIGHT = 480;


	private SpriteBatch batch;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		//this.setScreen(new MainGameScreen(batch));
		this.setScreen(new MenuScreen(batch));
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
