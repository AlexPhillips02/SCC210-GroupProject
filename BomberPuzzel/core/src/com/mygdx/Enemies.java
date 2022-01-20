package com.mygdx;

import com.mygdx.Board.Board;
import com.mygdx.Player.Entity;

public abstract class Enemies extends Entity
{
    String[] possibleDirections = {"UP", "DOWN", "LEFT", "RIGHT"};

    public Enemies(String imageURL, Board board, float x, float y, float movementSpeed)
    {
        super(imageURL, board, x, y, movementSpeed);
    }

    public void update()
    {
        if(move() == false)
        {
            chooseNewDirection();
        }
    }

    public void chooseNewDirection()
    {
        int direction = (int)(Math.random() * 4);

        movementDirection = possibleDirections[direction];
        setAnimationDirection(movementDirection);
    }
}
