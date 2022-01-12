package com.mygdx.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;

public abstract class Entity 
{
    protected Texture image;
    protected float x;
    protected float y;
    protected float movementSpeed;
    protected Board board;    
    protected Rectangle collisionRectangle;

    public Entity(String imageURL, Board board, float x, float y, float movementSpeed)
    {
        image = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        collisionRectangle = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public Rectangle getCollisionRectangle()
    {
        return collisionRectangle;
    }

    public Texture getImage() {
        return image;
    }

    public float getMovementSpeed() {
        return movementSpeed;
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
