package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Alex Phillips
 * Tile holds the Texture for each square
 */
public class Tile 
{
    protected Texture image;

    /**
     * Creates the texture
     * @param imageURL URL for the Texture
     */
    public Tile(String imageURL)
    {
        image = new Texture(imageURL);
    }
    
    //Getters and setters
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