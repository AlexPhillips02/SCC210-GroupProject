package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Alex Phillips
 * Tile holds the Texture for each square
 */
public class Tile 
{
    protected Texture image;
    protected Animation<TextureRegion> tileAnimation;

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

    public Animation<TextureRegion> getAnimation()
    {
        return tileAnimation;
    }

    public void setAnimation(Animation<TextureRegion> animation)
    {
        this.tileAnimation = animation;
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