package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Abilities.Bomb;
import com.mygdx.Board.Squares;
import com.mygdx.TileTypes.Path;

/**
 * @author Alex Phillips
 * Player controller controls the player (User input, player movement)
 */
public class PlayerController
{
    int playerHeight;
    int playerWidth;
    Player player;
    int currentPlayerImage = 0;

    public PlayerController(Player player)
    {
        this.player = player; 
        playerHeight = player.getImage().getHeight();
        playerWidth = player.getImage().getWidth();
        playerHeight = 60;
        playerWidth = 60;
    }

    /**
     * Called every update, checks for an input
     * Movement, bomb placing etc
     */
	public void checkInput() 
    {
        float x = player.getX();
        float y = player.getY();
        float movementSpeed = player.getMovementSpeed();
        int tileWidth = 64;
        int tileHeight = 64;
        String direction = "STANDING";
        
        //Use arrow keys for player
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
            direction = "UP";
			y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares topLeft = player.board.getGameSquare((int)(x / tileWidth), (int)((y + playerHeight) / tileHeight));
            Squares topRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(topLeft, topRight, x, y))
            {
                possibleCornerCut(topLeft, topRight, player.getX(), player.getY(), direction);
            }
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
            direction = "RIGHT";
			x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)(y / tileHeight));
            Squares topRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(bottomRight, topRight, x, y))
            {
                possibleCornerCut(bottomRight, topRight, player.x, player.y, direction);
            }
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
            direction = "DOWN";
			y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomLeft = player.board.getGameSquare((int)(x / tileWidth), (int)(y / tileHeight));
            Squares bottomRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)(y / tileHeight));

            if(!playerMovement(bottomLeft, bottomRight, x, y))
            {
                possibleCornerCut(bottomLeft, bottomRight, player.getX(), player.getY(), direction);
            }
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
            direction = "LEFT";
			x = (x - (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomLeft = player.board.getGameSquare((int)(x / tileWidth), (int)(y / tileHeight));
            Squares topLeft = player.board.getGameSquare((int)(x / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(bottomLeft, topLeft, x, y))
            {
                possibleCornerCut(bottomLeft, topLeft, player.getX(), player.getY(), direction);
            }
		}
        
        //Sets the animation within the player
        player.setAnimationDirection(direction);

        //Places a bomb
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
        {
            new Bomb(player.board, player.getX(), player.getY(), 5);
        }
    }   

    //Collision detection. Points are inputted from the direction (corners) they are travelling
    //Ie. moving left will pass through the 2 left corners (top and bottom)
    //Returns true if both future postions are paths and moves the player
    //Returns false if one or both is blocked then checks if the corner can be "shortcutted"
    /**
     * @param point1 Square corner of player is in
     * @param point2 Square other corner of player is in (Ie. if moving down, both bottom corners)
     * @param x X coordiante of player
     * @param y Y coordinate of player
     * @return If the player is able to move without adjustement (Corner cutting)
     */
    public boolean playerMovement(Squares point1, Squares point2, float x, float y)
    {        
        if (point1.getTile() instanceof Path && point2.getTile() instanceof Path) 
        {
            player.setY(y);
            player.setX(x);
            return true; 
        }

        return false;
    }

    /**
     * Adjusts for "corner cutting" if the player isnt lined up correctly will adjust the values to allow them
     * to continue moving
     * @param point1 Square corner of player is in
     * @param point2 Square other corner of player is in (Ie. if moving down, both bottom corners)
     * @param x X coordiante of player
     * @param y Y coordinate of player
     * @param direction Direction of movement
     */
    public void possibleCornerCut(Squares point1, Squares point2, float x, float y, String direction)
    {
        float movementSpeed = player.movementSpeed;

        switch (direction) 
        {
            case "UP":
                if (point1.getTile() instanceof Path) 
                {
                    //Top left free (adjust left)
                    x = (x - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2.getTile() instanceof Path)
                {
                    //Top right free (adjust right)
                    x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;
        
            case "RIGHT":
                if (point1.getTile() instanceof Path) 
                {
                    //Bottom right free (adjust downwards)
                    y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2.getTile() instanceof Path)
                {
                    //Top right free (adjust upwards)
                    y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;

            case "DOWN":
                if (point1.getTile() instanceof Path) 
                {
                    //Bottom left free (adjust left)
                    x = (x - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2.getTile() instanceof Path)
                {
                    //Bottom right free (adjust right)
                    x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;

            case "LEFT":
                if (point1.getTile() instanceof Path) 
                {
                    //Bottom left free (adjust down)
                    y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                else if (point2.getTile() instanceof Path)
                {
                    //Top left free (adjust upwards)
                    y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
                }
                break;
        }

        //Sets new x and y coordinate
        player.setY(y);
        player.setX(x);
    }
}
