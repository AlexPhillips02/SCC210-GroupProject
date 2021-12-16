package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Squares 
{
    private int x;
    private int y; 
    private Texture image;
    public Object getTexture;
    
    public Squares(int x, int y, Texture image)
    {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Squares getSquare()
    {
        return this;
    }

    public Texture getTexture()
    {
        return image;
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
