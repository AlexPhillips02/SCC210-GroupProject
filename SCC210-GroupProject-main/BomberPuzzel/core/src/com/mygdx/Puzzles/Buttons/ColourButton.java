package com.mygdx.Puzzles.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;

/**
 * @author Kathryn Hurst, Alex Phillips
 * ColourButton is the basis for the colour button entities
 */
public abstract class ColourButton
{
    protected String colour;
    protected Texture defaultImage;
    protected float x;
    protected float y;

    protected Board board;    
    protected Rectangle collisionRectangle;

    protected boolean active = true;

    /**
     * Constructor for the button class
     * @param board is the game board
     * @param imageURL is the URL of the image
     * @param x is the x coordinates
     * @param y is the y coordinates
     */
    public ColourButton(Board board, String imageURL, float x, float y)
    {
        this.defaultImage = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        
        collisionRectangle = new Rectangle(this.x, this.y, defaultImage.getWidth(), defaultImage.getHeight());
    }

    /**
     * Draws the entity on the board.
     * @param batch Spritebatch which displays all of the stuff in the driver
    */
    public void Draw(SpriteBatch batch)
    {
        batch.draw(defaultImage , x, y);
    }

    public Texture getImage()
    {
        return defaultImage;
    }

    public int getHeight()
    {
        return defaultImage.getHeight();
    }

    public int getWidth()
    {
        return defaultImage.getWidth();
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
        collisionRectangle.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
        collisionRectangle.y = y;
    }

    public Rectangle getCollisionRectangle()
    {
        return collisionRectangle;
    }

    public void setActive(boolean TF)
    {
        active = TF;
    }
    
    public boolean getActiveStatus()
    {
        return active;
    }

    public String getColour() {
        return colour;
    }
}