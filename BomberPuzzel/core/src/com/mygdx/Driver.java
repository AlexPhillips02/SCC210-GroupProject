package com.mygdx;

import com.badlogic.gdx.Game;
import com.mygdx.GameScreens.MenuScreen;

public class Driver extends Game 
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	@Override
	public void create ()
	{
		this.setScreen(new MenuScreen());
	}

	@Override
	public void render() 
	{
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
