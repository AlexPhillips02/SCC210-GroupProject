package com.mygdx.Enemies;

import java.util.ArrayList;

import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Entity;
import com.mygdx.Player.Player;
import com.mygdx.TileTypes.Path;

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
        if(move() == false || movementDirection == null)
        {
            chooseNewDirection();
        }
    }

    /**
     * Sets a new movement direction of the enemy
     */
    public void chooseNewDirection()
    {
        Squares currentSquare = board.getGameSquare((int)getX() / board.getTileSize(), (int)getY() / board.getTileSize());
        ArrayList<String> possibleMovements = new ArrayList<>();

        for (int i = 0; i < possibleDirections.length; i++) 
        {
            Squares next = getNext(i, currentSquare);
            
            if (canMove(next))
            {
                possibleMovements.add(possibleDirections[i]);
            }
        }

        if (possibleMovements.size() > 0) 
        {
            int direction = (int)(Math.random() * 4);

            movementDirection = possibleDirections[direction];
        }
        else
        {
            movementDirection = null;
            movementDirection = "STANDING";
        }

        setAnimationDirection(movementDirection);

        int direction = (int)(Math.random() * 4);

        movementDirection = possibleDirections[direction];
        setAnimationDirection(movementDirection);
    }

    /**
	 * A method that is invoked when a user clicks on this square.
	 * 
	 * @param n Direction in which to get the next square from
     * @param current The current square the algorithm is using
	 * @return Returns an AlgorithmSquare of the next square in that direction 
	 */	
	public Squares getNext(int n, Squares current)
	{
		int y = 0;
		int x = 0;

        if (n == 0) {x = -1;} //Left
        if (n == 1) {y = -1;} //Bottom
        if (n == 2) {x = 1;} //Right
		if (n == 3) {y = 1;} //Top

		Squares next = ((Squares) board.getGameSquare((current.getXLocation() + x), (current.getYLocation()) + y));
		return next;
	}

    /**
	 * A method that determines if there is a wall on the next possible tile
	 * 
	 * @param Next The next possible block to check
     * @return True if the player can move there (is a path)
	 */	
	public boolean canMove(Squares next)
	{
		if ((next.getTile() instanceof Path)) 
        {
            return true;
        }
		else 
        {
            return false;
        }
	}

    /**
     * Basic function of enemies, can be overidden within specific enemy classes (e.g. BombCarrier)
     * @param player
     */
    public void Attack(Player player)
    {
        player.reduceHealth();
    }
}
