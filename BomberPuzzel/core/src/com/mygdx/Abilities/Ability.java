package com.mygdx.Abilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Player.Entity;

/**
 * @author Alex Chalakov, Alex Phillips
 * Abilities class creates basis for all abilities.
 */
public abstract class Ability 
{
    protected Texture defaultImage;
    protected Board board;
    protected float x;
    protected float y;
    protected Entity entity;
    protected Rectangle collisionRectangle;
    protected boolean deactivated = false;

    protected Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f;
    protected float abilityLength; //In seconds

    protected String name;

    /**
     * Constructor for the abstract class of Abilities, all of them should be extended by this.
     * @param imageURL URL of the image for the ability
     * @param board Instance of the gameboard
     * @param x X position of the ability
     * @param y Y position of the ability
     * @param entity Entity for the ability to take affect on (Usually the player)
     * @param name Name of the ability
     * @param abilityLength Length of time the ability should take affect for
     */
    public Ability(String imageURL, Board board, float x, float y, Entity entity, String name, float abilityLength)
    {
        defaultImage = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;
        this.entity = entity;
        this.name = name;
        this.abilityLength = abilityLength;

        collisionRectangle = new Rectangle(x, y, defaultImage.getWidth(), defaultImage.getHeight());
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

    /**
     * Updates the ability, deactivates when the ability has been active
     */
    public void update() 
    {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (elapsedTime > abilityLength) 
        {
            DeactivateAbility();
            deactivated = true;
        }
    }

    /**
     * Sets the new elapsed time
     * @param newElapsedTime Provides the new elapsed time
     */
    public void setElapsedTime(float newElapsedTime) 
    {
        elapsedTime = newElapsedTime;
    }

    /**
     * Returns the elapsed time of the ability
     * @return elapsedTime
     */
    public float getElapsedTime() 
    {
        return elapsedTime;
    }

    /**
     * Sets the ability as active
     */
    public void setActive() 
    {
        ActivateAbility();
        elapsedTime = 0f;
    }

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

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public void setAnimation(Animation<TextureRegion> animation)
    {
        currentAnimation = animation;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public String getName()
    {
        return name;
    }

    //Method overridden in child classes
    public void ActivateAbility()
    {}   

    public void DeactivateAbility()
    {}

    public void dispose()
    {
        defaultImage.dispose();
    }
}
