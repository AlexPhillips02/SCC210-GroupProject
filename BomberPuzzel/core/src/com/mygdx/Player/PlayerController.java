package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Board.Board;
import com.mygdx.Board.Path;
import com.mygdx.Board.Squares;

public class PlayerController
{
    Player player;
    Board board;
    int playerHeight;
    int playerWidth;

    public PlayerController(Player player, Board board)
    {
        this.player = player;
        this.board = board;
        playerHeight = player.getImage().getHeight();
        playerWidth = player.getImage().getWidth();
    }

	public void update() 
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
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
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
        
        Squares bottomLeft = board.getGameSquare((int)(x / 32), (int)(y / 32));
        Squares bottomRight = board.getGameSquare((int)((x + playerWidth) / 32), (int)(y / 32));
        Squares topLeft = board.getGameSquare((int)(x / 32), (int)((y + playerHeight) / 32));
        Squares topRight = board.getGameSquare((int)((x + playerWidth) / 32), (int)((y + playerHeight) / 32));
        
        if (bottomLeft.getTile() instanceof Path && bottomRight.getTile() instanceof Path && topLeft.getTile() instanceof Path && topRight.getTile() instanceof Path) 
        {
            player.setY(y);
            player.setX(x);
        }
    }
}
