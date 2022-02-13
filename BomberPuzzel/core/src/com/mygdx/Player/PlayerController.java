package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Abilities.Bomb;

/**
 * @author Alex Phillips
 * Player controller controls the player (User input)
 */
public class PlayerController
{
    int playerHeight;
    int playerWidth;
    Player player;

    /**
     * Controller for the Player
     * @param player Player the controller is to control
     */
    public PlayerController(Player player)
    {
        this.player = player; 
        playerWidth = player.getImage().getWidth();
        playerHeight = 60;
    }

    /**
     * Called every update, checks for an input
     * Movement, bomb placing etc
     */
	public void checkInput() 
    {
        String direction = "";
        
        //Use arrow keys for player sets direction of player movement
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
        
        //Sets the animation within the player and movement direction (If "" player is standing still)
        player.setMovementDirection(direction);
        player.setAnimationDirection(direction);
        player.move();

        //Places a bomb
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            if (player.getBombsNumber() <= player.getBombsMax()) {
                player.increasePlacedBombs();
                createBomb();
            }
        }
    }

    /**
     * The sole method that creates and places bombs on the game board.
     */
    public void createBomb()
    {
        new Bomb(player.board, player.getX() + (playerWidth / 2), player.getY(), player, player.getBombsRange());
    }
}