package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Board.Tile;
import com.mygdx.TileTypes.Path;

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
    protected String movementDirection;
    protected Board board;    
    protected Rectangle collisionRectangle;

    protected Animation<TextureRegion> currentAnimation;
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkRight;
    protected float elapsedTime = 0f;

    protected int health;

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
        collisionRectangle = new Rectangle(this.x, this.y, defaultImage.getWidth(), defaultImage.getHeight());
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
     * Called within player controller, sets the animation if walking in a direction
     * @param direction Direction of the player (If null standing still)
     */
    public void setAnimationDirection(String direction)
    {
        switch (direction) {
            case "UP":
                setAnimation(walkUp);
            break;

            case "DOWN":
                setAnimation(walkDown);
            break;

            case "LEFT":
                setAnimation(walkLeft);
            break;

            case "RIGHT":
                setAnimation(walkRight);
            break;
        
            default:
                setAnimation(null);
            break;
        }
    }

    /**
     * Moves the entity according to the direction of current travel
     * @return Returns false if the entity is unable to move (Used for enemies finding new direction)
     */
    public boolean move()
    {
        float tempX = this.getX();
        float tempY = this.getY();

        int imageHeight = 60;
        int imageWidth = 60;

        int tileHeight = 64;
        int tileWidth = 64;

        //If the entity is standing still
        if(movementDirection == null)
        {
            return false;
        }

        //Calculate the corners of the entity in the new position
        //Get the tile in those corners then check if they are path tiles
        //If both are, move the player
        //If one is but one isn't adjust the player automatically
        switch (movementDirection) 
        {
            case "UP":
                tempY = (tempY + (movementSpeed * Gdx.graphics.getDeltaTime()));
                Tile uTopLeft = (board.getGameSquare((int)(tempX / tileWidth), (int)((tempY + imageHeight) / tileHeight))).getTile();
                Tile uTopRight = (board.getGameSquare((int)((tempX + imageWidth) / tileWidth), (int)((tempY + imageHeight) / tileHeight))).getTile();

                if(!validMove(uTopLeft, uTopRight, tempX, tempY))
                {
                    possibleCornerCut(uTopLeft, uTopRight, this.getX(), this.getY(), movementDirection);
                }
                else 
                {
                    return true;
                }
            break;

            case "DOWN":
                tempY = (tempY - (movementSpeed * Gdx.graphics.getDeltaTime()));
                Tile dBottomLeft = (board.getGameSquare((int)(tempX / tileWidth), (int)(tempY / tileHeight))).getTile();
                Tile dBottomRight = (board.getGameSquare((int)((tempX + imageWidth) / tileWidth), (int)(tempY / tileHeight))).getTile();

                if(!validMove(dBottomLeft, dBottomRight, tempX, tempY))
                {
                    possibleCornerCut(dBottomLeft, dBottomRight, this.getX(), this.getY(), movementDirection);
                }
                else 
                {
                    return true;
                }
            break;

            case "LEFT":
                tempX = (tempX - (movementSpeed * Gdx.graphics.getDeltaTime()));   
                Tile lBottomLeft = (board.getGameSquare((int)(tempX / tileWidth), (int)(tempY / tileHeight))).getTile();
                Tile lTopLeft = (board.getGameSquare((int)(tempX / tileWidth), (int)((tempY + imageHeight) / tileHeight))).getTile(); 

                if(!validMove(lBottomLeft, lTopLeft, tempX, tempY))
                {
                    possibleCornerCut(lBottomLeft, lTopLeft, this.getX(), this.getY(), movementDirection);
                }
                else 
                {
                    return true;
                }
            break;

            case "RIGHT":
                tempX = (tempX + (movementSpeed * Gdx.graphics.getDeltaTime()));
                Tile rBottomRight = (board.getGameSquare((int)((tempX + imageWidth) / tileWidth), (int)(tempY / tileHeight))).getTile();
                Tile rTopRight = (board.getGameSquare((int)((tempX + imageWidth) / tileWidth), (int)((tempY + imageHeight) / tileHeight))).getTile();

                if(!validMove(rBottomRight, rTopRight, tempX, tempY))
                {
                    possibleCornerCut(rBottomRight, rTopRight, this.getX(), this.getY(), movementDirection);
                }
                else 
                {
                    return true;
                }
            break;
        }

        return false;
    }

    //Collision detection. Points are inputted from the direction (corners) they are travelling
    //Ie. moving left will pass through the 2 left corners (top and bottom)
    //Returns true if both future positions are paths and moves the player
    //Returns false if one or both is blocked then checks if the corner can be "shortcut"
    /**
     * @param point1 Square corner of player is in
     * @param point2 Square other corner of player is in (Ie. if moving down, both bottom corners)
     * @param tempX X position the player is moving into
     * @param tempY Y position the player is moving into
     * @return If the player is able to move without adjustement (Corner cutting)
     */
    public boolean validMove(Tile point1, Tile point2, float tempX, float tempY)
    {                
        if (point1 instanceof Path && point2 instanceof Path) 
        {
            this.setY(tempY);
            this.setX(tempX);
            return true; 
        }

        return false;
    }

    /**
     * Adjusts for "corner cutting" if the player isnt lined up correctly will adjust the values to allow them
     * to continue moving (If one point is moving onto a path, but the other is hitting a wall)
     * @param point1 Square corner of player is in
     * @param point2 Square other corner of player is in (Ie. if moving down, both bottom corners)
     * @param tempX X position the player is moving into
     * @param tempY Y position the player is moving into
     * @param direction Direction of movement
     */
    public void possibleCornerCut(Tile point1, Tile point2, float tempX, float tempY, String direction)
    {
        switch (direction) 
        {
            case "UP":
                if (point1 instanceof Path) 
                {
                    //Top left free (adjust left)
                    tempX = (tempX - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2 instanceof Path)
                {
                    //Top right free (adjust right)
                    tempX = (tempX + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;
        
            case "RIGHT":
                if (point1 instanceof Path) 
                {
                    //Bottom right free (adjust downwards)
                    tempY = (tempY - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2 instanceof Path)
                {
                    //Top right free (adjust upwards)
                    tempY = (tempY + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;

            case "DOWN":
                if (point1 instanceof Path) 
                {
                    //Bottom left free (adjust left)
                    tempX = (tempX - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2 instanceof Path)
                {
                    //Bottom right free (adjust right)
                    tempX = (tempX + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;

            case "LEFT":
                if (point1 instanceof Path) 
                {
                    //Bottom left free (adjust down)
                    tempY = (tempY - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2 instanceof Path)
                {
                    //Top left free (adjust upwards)
                    tempY = (tempY + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;
        }

        this.setY(tempY);
        this.setX(tempX);
    }

    /**
     * Draws the entity on the board, either with an animation or an image
     * @param batch Spritebatch which displays all of the stuff in the driver
     */
    public void Draw(SpriteBatch batch)
    {
        if (currentAnimation == null) 
        {
            batch.draw(defaultImage , this.getX(), this.getY());   
        }
        else
        {
            //Draws the animation (gets the frame of the animation (elapsed time kinda like an index | true loops | x / y coordinates))
            batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), this.getX(), this.getY());
            elapsedTime += Gdx.graphics.getDeltaTime();
        }
    }

    /**
     * Reducing the health method
     */
    public void reduceHealth()
    {
        this.health --;
    }

    /**
     * Method to check if the player is alive
     * @return false if dead, true if alive
     */
    public Boolean isAlive()
    {
        if(health <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setHealth (int health) {
        this.health = health;
    }

    public void setMovementDirection(String direction)
    {
        this.movementDirection = direction;
    }

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

    public void decreasePlacedBombs() {
    }
}
