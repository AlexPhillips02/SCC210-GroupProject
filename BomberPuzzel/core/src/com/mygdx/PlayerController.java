package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
            playerMovement(0, 0, 0, playerHeight, x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x = (x + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(playerWidth, 0, playerWidth, playerHeight, x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			y = (y + (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(0, playerHeight, playerWidth, playerHeight, x, y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			y = (y - (movementSpeed * Gdx.graphics.getDeltaTime()));
            playerMovement(0, 0, playerWidth, 0, x, y);
		}
    }   

    //Collision detection. cordinates are based of the bottom left corner of the player
    //Gets the position of the two corners of the direction they are wanting to move in
    //ie. moving up would get the future positions of the top corners (the square they are wanting to move to) and check if its a path (not a wall)
    public void playerMovement(int offsetAX, int offsetAY, int offsetBX, int offsetBY, float x, float y)
    {
        Squares pointA = board.getGameSquare((int) ((x + offsetAX) / 32), (int) ((y + offsetAY) / 32));
        Squares pointB = board.getGameSquare((int) ((x + offsetBX) / 32), (int) ((y + offsetBY) / 32));

        if (pointA.getTile() instanceof Path && pointB.getTile() instanceof Path) 
        {
            player.setY(y);
            player.setX(x);
        }
    }
}
