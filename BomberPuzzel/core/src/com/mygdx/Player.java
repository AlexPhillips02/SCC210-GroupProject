package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player 
{
    private Texture image;
    private Rectangle player;
    private float x;
    private float y;
    private SpriteBatch batch;

    public Player()
    {
        batch = new SpriteBatch();
        player = new Rectangle();
        image = new Texture("badlogic.jpg");
        this.x = 0;
        this.y = 0;
        setDefaults();
    }

    public void setDefaults()
    {
        player.x = 100;
        player.y = 100;
        x = player.x;
        y = player.y;
        player.width = 32;
        player.height = 64;
    }

    public Texture getImage() {
        return image;
    }

    public Rectangle getPlayer() {
        return player;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        player.x = x;
    }

    public void setY(float y) {
        this.y = y;
        player.y = y;
    }

    public void Draw()
    {
        batch.begin();
		batch.draw(image , x, y);
		batch.end();

    }
}
