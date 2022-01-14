package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Abilities.Bomb;
import com.mygdx.Board.Squares;
import com.mygdx.TileTypes.Path;

public class PlayerController
{
    int playerHeight;
    int playerWidth;
    Player player;

    public PlayerController(Player player)
    {
        this.player = player; 
        playerHeight = player.getImage().getHeight();
        playerWidth = player.getImage().getWidth();
    }

	public void checkInput() 
    {
        float x = player.getX();
        float y = player.getY();
        float movementSpeed = player.getMovementSpeed();
        int tileWidth = 64;
        int tileHeight = 64;
        
        //Use arrow keys for player
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares topLeft = player.board.getGameSquare((int)(x / tileWidth), (int)((y + playerHeight) / tileHeight));
            Squares topRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(topLeft, topRight, x, y))
            {
                possibleCornerCut(topLeft, topRight, player.getX(), player.getY(), "UP");
            }
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)(y / tileHeight));
            Squares topRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(bottomRight, topRight, x, y))
            {
                possibleCornerCut(bottomRight, topRight, player.x, player.y, "RIGHT");
            }
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomLeft = player.board.getGameSquare((int)(x / tileWidth), (int)(y / tileHeight));
            Squares bottomRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)(y / tileHeight));

            if(!playerMovement(bottomLeft, bottomRight, x, y))
            {
                possibleCornerCut(bottomLeft, bottomRight, player.getX(), player.getY(), "DOWN");
            }
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			x = (x - (movementSpeed * Gdx.graphics.getDeltaTime()));
            Squares bottomLeft = player.board.getGameSquare((int)(x / tileWidth), (int)(y / tileHeight));
            Squares topLeft = player.board.getGameSquare((int)(x / tileWidth), (int)((y + playerHeight) / tileHeight));

            if(!playerMovement(bottomLeft, topLeft, x, y))
            {
                possibleCornerCut(bottomLeft, topLeft, player.getX(), player.getY(), "LEFT");
            }
		}

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
        {
            new Bomb(player.board, player.getX(), player.getY(), 5);
        }
    }   

    //Collision detection. Points are inputted from the direction (corners) they are travelling
    //Ie. moving left will pass through the 2 left corners (top and bottom)
    //Returns true if both future postions are paths and moves the player
    //Returns false if one or both is blocked then checks if the corner can be "shortcutted"
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

    public void possibleCornerCut(Squares point1, Squares point2, float x, float y, String direction)
    {
        //Adjusts for "corner cutting" if the player isnt lined up correctly will adjust the values to allow them
        //to continue moving
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

        player.setY(y);
        player.setX(x);
    }
}
