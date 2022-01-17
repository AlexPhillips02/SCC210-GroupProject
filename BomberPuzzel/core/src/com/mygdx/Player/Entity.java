package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;

/**
 * @author Alex Phillips
 * Entitiy class creates basis for players and enimies (Entity types)
 */
public abstract class Entity 
{
    protected Texture defaultImage;
    protected float x;
    protected float y;
    protected float movementSpeed;
    protected Board board;    
    protected Rectangle collisionRectangle;

    protected Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f;

    /**
     * @param imageURL URL to an image to use for the entity (Stored as a Texture)
     * @param board The gameboard
     * @param x X coordinate of the entity
     * @param y Y coordimate of the entity
     * @param movementSpeed Movement speed of the entity
     */
    public Entity(String imageURL, Board board, float x, float y, float movementSpeed)
    {
        defaultImage = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        this.movementSpeed = movementSpeed;
        //Collision rectangle to be used for collisions with other entities
        collisionRectangle = new Rectangle(x, y, defaultImage.getWidth(), defaultImage.getHeight());
    }

    /**
     * @param animation Sets the current animation (Ie. walkingLeft)
     * If the current animation is set to null the entity will display the defaultImage (Ie. standing still)
     */
    public void setAnimation(Animation<TextureRegion> animation)
    {
        currentAnimation = animation;
    }

    /**
     * Draws the entity on the board, either with an animation or an image
     * @param batch Spritebatch which displays all of the stuff in the driver
     */
    public void Draw(SpriteBatch batch)
    {
        if (currentAnimation == null) 
        {
            batch.draw(defaultImage , x, y);   
        }
        else
        {
            //Draws the animation (gets the frame of the animation (elapsed time kinda like an index | true loops | x / y coordinates))
            batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), x, y);
            elapsedTime += Gdx.graphics.getDeltaTime();
        }
    }

    //Getters and setters
    public Rectangle getCollisionRectangle()
    {
        return collisionRectangle;
    }

    public Texture getImage() {
        return defaultImage;
    }

    public int getHeight()
    {
        return defaultImage.getHeight();
    }

    public void setImage(String URL)
    {
        defaultImage = new Texture(URL);
    }

    public int getWidth()
    {
        return defaultImage.getWidth();
    }

    public float getMovementSpeed() {
        return movementSpeed;
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

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
