package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends ApplicationAdapter 
{
	Board gameBoard;
	
	@Override
	public void create () {
		gameBoard = new Board();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gameBoard.Draw();
	}
	
	@Override
	public void dispose () {
		//batch.dispose();
	}
}
