package com.mygdx.Abilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov
 * Abilities class creates basis for all abilities.
 */
public abstract class Ability 
{
    protected Texture defaultImage;
    protected Board board;
    protected float x;
    protected float y;
    protected Player player;
    protected Rectangle collisionRectangle; //will see if its needed

    protected Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f;

    /**
     * Constructor for the abstract class of Abilities, all of them should be extended by this.
     * @param imageURL URL to an image to use for the entity (Stored as a Texture)
     * @param board The game board
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     */
    public Ability(String imageURL, Board board, float x, float y, Player player)
    {
        defaultImage = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        this.player = player;

        collisionRectangle = new Rectangle(x, y, defaultImage.getWidth(), defaultImage.getHeight());
    }

    public void setAnimation(Animation<TextureRegion> animation)
    {
        currentAnimation = animation;
    }

    /**
     * Draws the entity on the board, either with an animation or an image.
     * @param batch Spritebatch which displays all of the stuff in the driver
     */
    public void Draw(SpriteBatch batch)
    {
        if (currentAnimation == null){
            batch.draw(defaultImage , x, y);
        } else {
            batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), x, y);
            elapsedTime += Gdx.graphics.getDeltaTime();
        }
    }

    //Getters and Setters
    public Texture getImage() {
        return defaultImage;
    }

    public int getHeight()
    {
        return defaultImage.getHeight();
    }

    public int getWidth()
    {
        return defaultImage.getWidth();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        collisionRectangle.x = x;
    }

    public void setY(float y) {
        this.y = y;
        collisionRectangle.y = y;
    }
}
