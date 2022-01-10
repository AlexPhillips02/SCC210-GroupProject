package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        int movementSpeed = player.getMovementSpeed();
        
        //Use arrow keys for player
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			x = (x - (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
        {
            //Place bomb
        }
    }   

    //Collision detection. cordinates are based of the bottom left corner of the player
    //Gets the position of the corners of the player (/ 32 as that is size of the board squares)
    //Check that the future tile of the player, all 4 corners are still on path tiles
    public void playerMovement(float x, float y)
    {
        int tileWidth = 64;
        int tileHight = 64;

        Squares bottomLeft = player.board.getGameSquare((int)(x / tileWidth), (int)(y / tileHight));
        Squares bottomRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)(y / tileHight));
        Squares topLeft = player.board.getGameSquare((int)(x / tileWidth), (int)((y + playerHeight) / tileHight));
        Squares topRight = player.board.getGameSquare((int)((x + playerWidth) / tileWidth), (int)((y + playerHeight) / tileHight));
        
        if (bottomLeft.getTile() instanceof Path && bottomRight.getTile() instanceof Path && topLeft.getTile() instanceof Path && topRight.getTile() instanceof Path) 
        {
            player.setY(y);
            player.setX(x);
        }
    }
}
