package com.mygdx.Puzzles.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Sound.SoundController;

/**
 * @author Kathryn Hurst, Alex Phillips
 * ColourButton is the basis for the colour button entities
 */
public abstract class ColourButton
{
    protected String colour;
    protected Texture currentImage;
    protected Texture unclickedImage;
    protected Texture clickedImage;
    protected float x;
    protected float y;

    protected Board board;    
    protected Rectangle collisionRectangle;

    protected boolean active = true;
    protected SoundController soundController;
    protected Sound buttonPress = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/Click (2).mp3"));

    /**
     * Constructor for the button class
     * @param board is the game board
     * @param imageURL is the URL of the image
     * @param x is the x coordinates
     * @param y is the y coordinates
     */
    public ColourButton(Board board, String imageURL, String image2URL, float x, float y)
    {
        this.unclickedImage = new Texture(imageURL);
        this.clickedImage = new Texture(image2URL);
        this.board = board;
        this.x = x;
        this.y = y;

        this.currentImage = unclickedImage;
        soundController = new SoundController();
        collisionRectangle = new Rectangle(this.x, this.y, currentImage.getWidth(), currentImage.getHeight());
    }

    /**
     * Draws the entity on the board.
     * @param batch Spritebatch which displays all of the stuff in the driver
    */
    public void Draw(SpriteBatch batch)
    {
        batch.draw(currentImage , x, y);
    }

    /**
     * Accessor method for the image to be displayed
     * @return currentImage
     */
    public Texture getImage()
    {
        return currentImage;
    }

    /**
     * Gets the height of the current image
     * @return currentImage height
     */
    public int getHeight()
    {
        return currentImage.getHeight();
    }

    /**
     * Gets the width of the current image
     * @return currentImage width
     */
    public int getWidth()
    {
        return currentImage.getWidth();
    }

    /**
     * Accessor method for the buttons's x-position
     * @return x
     */
    public float getX()
    {
        return x;
    }

    /**
     * Accessor method for the buttons's y-position
     * @return y
     */
    public float getY()
    {
        return y;
    }

    /**
     * Accessor method for the buttons collision rectangle
     * @return collisionRectange
     */
    public Rectangle getCollisionRectangle()
    {
        return collisionRectangle;
    }

    /**
     * Mutator method to set active to true or false
     * @param TF the boolen to set active to
     */
    public void setActive(boolean TF)
    {
        active = TF;
    }
    
    /**
     * Accessor method to see if the buton is active or not
     */
    public boolean getActiveStatus()
    {
        return active;
    }

    /**
     * Accessor method to get the colour of the button
     * @return colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets current image to unclickedImage
     */
    public void unclicked()
    {
        currentImage = unclickedImage;
    }

    /**
     * Sets current image to clickedImage
     */
    public void clicked()
    {
        soundController.playMusic(buttonPress);
        currentImage = clickedImage;
    }

    /**
     * Mutator method to set new x-position
     * @param x new position of the button
     */
    public void setX(float x)
    {
        this.x = x;
    }

    /**
     * Mutator method to set new y-position
     * @param y new position of the button
     */
    public void setY(float y)
    {
        this.y = y;
    }
}