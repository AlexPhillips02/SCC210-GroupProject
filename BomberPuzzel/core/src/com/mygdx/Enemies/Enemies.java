package com.mygdx.Enemies;

import com.mygdx.Board.Board;
import com.mygdx.Player.Entity;

/**
 * @author Alex Phillips
 * Is to be extended by enemy type classes
 */
public abstract class Enemies extends Entity
{
    String[] possibleDirections = {"UP", "DOWN", "LEFT", "RIGHT"};

    /**
     * 
     * @param imageURL URL of standing image
     * @param board Gameboard
     * @param x Starting x position
     * @param y Starting y position
     * @param movementSpeed Speed at which the enemy can move
     */
    public Enemies(String imageURL, Board board, float x, float y, float movementSpeed)
    {
        super(imageURL, board, x, y, movementSpeed);
    }

    /**
     * If the enemy can no longer move (has hit a wall)
     * Choose a new direction to travel in
     */
    public void update()
    {
        if(move() == false)
        {
            chooseNewDirection();
        }
    }

    /**
     * Sets a new movement direction of the enemy
     */
    public void chooseNewDirection()
    {
        int direction = (int)(Math.random() * 4);

        movementDirection = possibleDirections[direction];
        setAnimationDirection(movementDirection);
    }
}
