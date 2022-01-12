package com.mygdx.Abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public abstract class Ability {
    protected Texture image;
    protected Board board;
    protected float x;
    protected float y;
    protected Player player;
    protected Rectangle collisionRectangle;

    public Ability(String imageURL, Board board, float x, float y)
    {
        image = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        collisionRectangle = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public Texture getImage() {
        return image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        collisionRectangle.x = x;
    }

    public void setY(float y) {
        this.y = y;
        collisionRectangle.y = y;
    }

    public void Draw(SpriteBatch batch)
    {
        batch.draw(image , x, y);
    }
}
