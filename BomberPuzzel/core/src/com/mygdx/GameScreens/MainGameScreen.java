package com.mygdx.GameScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MainGameScreen implements Screen {

	private Driver game;
	
	public MainGameScreen (Driver game){
		this.game = game;
	}

    @Override
	public void render (float delta) {
		
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();
		game.controller.Update();
		batch.end();
	}

	@Override
	public void resize (int width, int height) {
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
 