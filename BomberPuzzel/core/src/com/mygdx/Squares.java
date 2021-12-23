package com.mygdx;

import com.badlogic.gdx.graphics.Texture;

public class Squares 
{
    private int x;
    private int y; 
    private Tile tile;
    
    public Squares(int x, int y, Tile tile)
    {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

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
