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
    protected Animation<TextureRegion> bombAnimation;

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

    /**
     * @return Current bomb animation (Either placed bomb or explosion animation)
     */
    public Animation<TextureRegion> getAnimation()
    {
        return bombAnimation;
    }

    /**
     * Sets the current bomb animation
     */
    public void setAnimation(Animation<TextureRegion> animation)
    {
        this.bombAnimation = animation;
    }

    /**
     * Removes the current bomb animation. Sets to null
     */
    public void removeAnimation()
    {
        this.bombAnimation = null;
    }

    /**
     * Gets the height of the image representing the tile
     * @return Image height
     */
    public int getHeight()
    {
        return image.getHeight();
    }

    /**
     * Gets the width of the image representing the tile
     * @return Image width
     */
    public int getWidth()
    {
        return image.getWidth();
    }

    public Texture getTexture() {
        return image;
    }
}