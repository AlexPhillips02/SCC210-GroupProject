package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img1;
	Texture img2;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img1 = new Texture("badlogic.jpg");
		img2 = new Texture("Fractalius Light - Wolf #2.jpg");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img1, 0, 0);
		batch.draw(img2, 250, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img1.dispose();
		img2.dispose();
	}
}
