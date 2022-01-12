package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;

public class Tile 
{
    protected Texture image;

    public Tile(String imageURL)
    {
        image = new Texture(imageURL);
    }
    
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