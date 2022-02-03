package com.mygdx.Enemies;

import com.mygdx.Board.AlgorithmSquares;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Phillips
 * This class allows enemies to track or follow the player
 * Recursively finds the shortest path from their current location to the player
 * Then moves along this path when the player gets within their range
 */
public abstract class PlayerTrackingEnemy extends Enemies
{
    Player player;
    AlgorithmSquares targetSquare;
    AlgorithmSquares currentSquare;
    int shortestRoute = -1;
    int searchDistance;

    /**
     * @param imageURL URL of the image
     * @param board Gameboard
     * @param x X coordinate
     * @param y Y coordinate
     * @param movementSpeed Movement of the enemy
     * @param player Player for the enemy to track
     * @param searchDistance Player must be within this many blocks to start tracking
     */
    public PlayerTrackingEnemy(String imageURL, Board board, float x, float y, float movementSpeed, Player player, int searchDistance)
    {
        super(imageURL, board, x, y, movementSpeed);
        this.player = player;
        this.searchDistance = searchDistance;
    }

    /**
    * Updates the enemy
    * Calculates the target square (the player) and the current squaer
    */
    public void update()
    {
        shortestRoute = -1;
        resetAlgorithm();
        targetSquare = (AlgorithmSquares)board.getGameSquare((int)player.getX() / board.getTileSize(), (int)player.getY() / board.getTileSize());
        currentSquare = (AlgorithmSquares)board.getGameSquare((int)x / board.getTileSize(), (int)y / board.getTileSize());
        findShortestPathToPlayer(0, currentSquare, null);

        //If they have found a route
        if (!(shortestRoute == -1)) 
        {
            calculateMovementDirection();
            move();
        }
    }

    /**
     * Resets all the squares for the algorithm
     */
    public void resetAlgorithm()
    {
        targetSquare = null;
        currentSquare = null;

        for (int x = 0; x < board.getXLength(); x++) 
        {
            for (int y = 0; y < board.getYLength(); y++) 
            {
                AlgorithmSquares temp = (AlgorithmSquares)board.getGameSquare(x, y);
                temp.distance = -1;
                temp.next = null;
                temp.previous = null;
                temp.visited = false;
            }   
        }
    }

    /**
     * Calculates the direction to move in and moves towards that square
     */
    public void calculateMovementDirection()
    {      
        calculateRoute(targetSquare.previous, targetSquare); 

        //Dude is already on the same square as the player if next is null
        if(currentSquare.next != null)
        {
            moveTowardsSquare(currentSquare.next);
        }
    }

    /**
     * The recursive algorithm only provides previous squares linking them together
     * This method loops through adding the next squares so the enemy knows each square to 
     * travel to in order to reach the players position
     * @param previous Square before the target
     * @param target Target square
     */
    public void calculateRoute(AlgorithmSquares previous, AlgorithmSquares target)
    {
        if (previous != null) 
        {
            previous.next = target;
            target = previous;
            calculateRoute(previous.previous, previous);
        }

        return;
    }

    /**
     * Calculates the direction of this square in terms of the current position of the enemy
     * @param target Next square the enemy needs to walk to
     */
    public void moveTowardsSquare(AlgorithmSquares target)
    {
        if (target.getXLocation() > currentSquare.getXLocation()) 
        {
            movementDirection = "RIGHT";
        }
        else if (target.getXLocation() < currentSquare.getXLocation())
        {
            movementDirection = "LEFT";
        }
        else if (target.getYLocation() > currentSquare.getYLocation()) 
        {
            movementDirection = "UP";
        }
        else if (target.getYLocation() < currentSquare.getYLocation())
        {
            movementDirection = "DOWN";
        }
        else
        {
            movementDirection = "STANDING";
        }

    }

    /**
	 * A method that finds the shortest route
	 * 
	 * @param count Distance away from the start square
	 * @param current Algorithm square of the current square
	 * @param previousSquare Algorithm square of the previous square
	 */	
	public void findShortestPathToPlayer(int count, AlgorithmSquares current, AlgorithmSquares previousSquare)
    {
		//For loop for the 4 directions
		for (int i = 0; i < 4; i++) 
		{
			//If the target is found set the distance and return
			if (current.getXLocation() == targetSquare.getXLocation() && current.getYLocation() == targetSquare.getYLocation()) 
			{
				if (count < shortestRoute || shortestRoute == -1) 
				{
					shortestRoute = count;
					current.previous = previousSquare;
				}	
				return;
			}

            if (count >= searchDistance) 
            {
                return;    
            }

			//Set visited to true
			current.visited = true;

			//Set the distance
			if (count < current.distance || current.distance == -1) 
			{
				current.distance = count;
				current.previous = previousSquare;
			}
			else
			{
				count = current.distance;
			}

			//If can move in a direction. Move in that direction

            AlgorithmSquares next = (AlgorithmSquares)getNext(i, current);
			if (canMove(next)) 
			{
				if (next.visited == false) 
				{
					findShortestPathToPlayer(count + 1, next, current);
				}
			}
		}

		//If stuck back out
		current.visited = false;
		return;
    }
}