package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GameController;

public class GameOverScreen extends Game implements Screen {

    private SpriteBatch batch;
    private GameController controller;

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show()
    {

    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        //If N is pressed a new game is started when this one is finished
        if (Gdx.input.isKeyPressed(Input.Keys.N))
        {
            this.setScreen(new MainGameScreen(batch));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) //If the escape button is clicked the game shuts down
        {
            dispose();
            System.exit(0);
        }

    }

    @Override
    public void create()
    {

    }

    /**
     * @param width size of width
     * @param height size of height
     */
    @Override
    public void resize(int width, int height)
    {
        controller.resize(width, height);
    }

    @Override
    public void pause()
    {

    }


    @Override
    public void resume()
    {

    }


    @Override
    public void hide()
    {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose()
    {

    }
}
