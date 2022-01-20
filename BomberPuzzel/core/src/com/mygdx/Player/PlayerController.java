package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Abilities.Bomb;

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
    Bomb playerBombs;

    public PlayerController(Player player)
    {
        this.player = player; 
        playerBombs = new Bomb(player.board);
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
        String direction = "";
        
        //Use arrow keys for player
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
            direction = "UP";
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
            direction = "RIGHT";
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
            direction = "DOWN";
		}
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
            direction = "LEFT";
		}
        
        //Sets the animation within the player
        player.setMovementDirection(direction);
        player.setAnimationDirection(direction);
        player.move();

        //Places a bomb
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
        {
            if (player.getBombsNumber() < player.getBombsMax()) {
                player.incrementBombsNumber();
                //System.out.println(player.getBombsNumber());
                playerBombs.drawBomb((player.getX() + (playerWidth / 2)), player.getY(), 5);
            }
        }
    }  
}