package com.mygdx.GameScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.GameController;

/**
 * @author Alex Chalakov, Lincoln Delhomme
 * A class for the main game screen where the actual game is ran.
 */
public class MainGameScreen implements Screen {

	private final SpriteBatch batch;
	private final GameController controller;

	/**
	 * Constructor for the Main Game Screen, where the game takes place.
	 */
	public MainGameScreen(MenuScreen menu)
	{
		batch = new SpriteBatch();
		controller = new GameController(batch);
	}

	/**
	 * Called when the screen should render itself.
	 * @param delta The time in seconds since the last render.
	 */
    @Override
	public void render (float delta)
	{	
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();
		controller.Update();
		batch.end();
	}

	/**
	 * @param width size of width
	 * @param height size of height
	 */
	@Override
	public void resize (int width, int height) 
	{
		controller.resize(width, height);
	}

	/**
	 * Called when this screen becomes the current screen for a Game.
	 */
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
		batch.dispose();
	}
}
 