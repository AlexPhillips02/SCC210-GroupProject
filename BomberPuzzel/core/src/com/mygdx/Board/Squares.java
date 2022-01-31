package com.mygdx.Board;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Abilities.Bomb;

/**
 * @author Alex Phillips
 * Each square on the board
 */
public class Squares 
{
    private int x;
    private int y; 
    private Tile tile;
    private Bomb bomb;
    private float elapsedTime = 0f;
    private Rectangle collisionRectangle;
    
    /**
     * Creates a square 
     * @param x X position of this square
     * @param y Y position of this square 
     * @param tile Holds the tile for the square (image)
     */
    public Squares(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Method for adding a bomb with an animation on the square.
     * @param bomb bomb that is added.
     */
    public void addBomb(Bomb bomb)
    {
        this.bomb = bomb;
        setAnimation(bomb.getAnimation());
    }

    /**
     * Method for removing the bomb which is placed on the square.
     */
    public void removeBomb()
    {
        this.bomb = null;
    }

    /**
     * Method for removing animations.
     */
    public void removeAnimation()
    {
        tile.removeAnimation();
        this.elapsedTime = 0f;
    }

    /**
     * Sets the current tile of the square to the inputted tile
     * @param tile Tile to set the sqaures tile to
     */
    public void setTile(Tile tile)
    {
        this.tile = tile;
        this.collisionRectangle = new Rectangle(this.x, this.y, tile.getWidth(), tile.getHeight());
    }

    /**
     * Returns the current tile of the square
     * @return Current tile
     */
    public Tile getTile() 
    {
        return tile;
    }

    /**
     * Returns the sqaure at that board location
     * @return This square
     */
    public Squares getSquare()
    {
        return this;
    }

    /**
     * Gets the x coordinate of this square
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Gets the y coordinate of this square
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the current squares bomb animation
     * @param bombAnimation Current bomb animation
     */
    public void setAnimation(Animation<TextureRegion> bombAnimation) 
    {
        this.elapsedTime = 0f;
        tile.setAnimation(bombAnimation);
    }

    /**
     * The current bomb on this square (null if a bomb is not on this tile)
     * @return Bomb placed on this square
     */
    public Bomb getBomb() 
    {
        return bomb;
    }

    /**
     * Gets eslapsed time for animations
     * @return elapsedTime
     */
    public float getElapsedTime() 
    {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time for animations
     * @param elapsedTime New elapsed time
     */
    public void setElapsedTime(float elapsedTime) 
    {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Gets collision rectange of this square (Used for bomb explosions)
     * @return collisionRectangle
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }
}
