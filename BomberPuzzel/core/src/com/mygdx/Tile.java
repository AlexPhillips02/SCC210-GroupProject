package com.mygdx;

import com.badlogic.gdx.graphics.Texture;

public class Tile 
{
    protected Texture image;
    protected boolean isWalkable;
    
    public Texture getImage() 
    {
        return image;
    }

    public int getHeight()
    {
        return image.getHeight();
    }

    public int getWidth()
    {
        return image.getWidth();
    }
}
