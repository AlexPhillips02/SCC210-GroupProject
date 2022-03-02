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
    private String[] possibleDirections = {"LEFT", "DOWN", "RIGHT", "UP"};

    /**
     * 
     * @param imageURL URL of standing image
     * @param board Game board
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
        increaseLastDamaged();
        
        if(!move() || movementDirection == null)
        {
            chooseNewDirection();
        }
    }

    /**
     * Calculates a new direction for the enemy to walk in
     */
    public void chooseNewDirection()
    {
        Squares currentSquare = board.getGameSquare((int)getX() / board.getTileSize(), (int)getY() / board.getTileSize());
        ArrayList<String> possibleMovements = new ArrayList<>();

        //Calculates which directions the enemy can move in
        //Which directions dont have walls
        for (int i = 0; i < possibleDirections.length; i++) 
        {
            Squares next = getNext(i, currentSquare);
            
            if (canMove(next))
            {
                possibleMovements.add(possibleDirections[i]);
            }
        }

        //If the enemy isn't trapped move in one of the possible directions
        //Else the enemy just stands still
        if (possibleMovements.size() > 0) 
        {
            int direction = (int)(Math.random() * possibleMovements.size());
            movementDirection = possibleMovements.get(direction);
        }
        else
        {
            movementDirection = "STANDING";
        }

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
     * @param next checks the next square
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
     * Basic function of enemies, can be overridden within specific enemy classes (e.g. BombCarrier)
     * @param player
     */
    public void Attack(Player player)
    {
        player.reduceHealth();
    }
}
