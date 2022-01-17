package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Alex Phillips
 * Each square on the board
 */
public class Squares 
{
    private int x;
    private int y; 
    private Tile tile;
    
    /**
     * Creates a square 
     * @param x X position of this square
     * @param y Y position of this square 
     * @param tile Holds the tile for the square (image)
     */
    public Squares(int x, int y, Tile tile)
    {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    //Getters and setters
    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public Tile getTile() 
    {
        return tile;
    }

    public Squares getSquare()
    {
        return this;
    }

    public Texture getTexture()
    {
        return tile.image;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
