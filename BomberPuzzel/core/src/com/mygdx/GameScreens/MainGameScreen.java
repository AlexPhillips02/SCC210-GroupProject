package com.mygdx.GameScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.GameController;

public class MainGameScreen implements Screen {

	private SpriteBatch batch;
	private GameController controller;
	
	public MainGameScreen (SpriteBatch batch)
	{
		this.batch = batch;
		controller = new GameController(batch);
	}

    @Override
	public void render (float delta) {
		
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();
		controller.Update();
		batch.end();
	}

	@Override
	public void resize (int width, int height) 
	{
		controller.resize(width, height);
	}

	@Override
	public void show () {

	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
 