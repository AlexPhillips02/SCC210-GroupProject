package com.mygdx.Enemies;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Player;

/**
 * @author Alex Phillips, Kathryn Hurst, Alex Chalakov - Sounds only
 * Controlls all of the enemies on the level
 */
public class EnemyController 
{
    private ArrayList<Enemies> enemies;
    private Board board;
    private Player player;

	/**
	 * Loads the board and the player (For player tracking)
	 * @param board Used to determine original spawn points
	 * @param player Player used for certain types to track the player
	 */
    public EnemyController(Board board, Player player)
    {
        this.board = board;
        this.player = player;
        enemies = new ArrayList<>();
    }    

    /**
	 * Creates the enemies and places them randomly around the map
	 * @param amount Amount of enemys to spawn around the map
	 */
	public void CreateEnemies(int amount, int level)
	{
		for (int i = 0; i < amount; i++) 
		{
			Squares pathSquare = board.getRandomPath();

			//Translates grid position to coordinate
			int xPosition = pathSquare.getX();
			int yPosition = pathSquare.getY();

			//Creates the enemy and adds to list of enemies
			Enemies enemy;
			if (i < level) 
			{
				enemy = new BombCarrier(board, xPosition, yPosition, 100 + (5 * level), player);
			}
			else
			{
				enemy = new Creep(board, xPosition, yPosition, 75 + (5 * level));
			}

			enemies.add(enemy);
		}
	}

	/**
	 * Called within GameController to update enemy positions and status
	 */
    public void Update()
    {
        //Draws and updates all enemeies created
		if (enemies != null) 
		{
			//Loops though all of the enemies
			for(int i = 0; i < enemies.size(); i++ )
			{
				Enemies enemy = enemies.get(i);

				enemy.update();

				//Player contact with enemy
				if (enemy.getCollisionRectangle().overlaps(player.getCollisionRectangle()))
				{
					enemy.Attack(player);
				}

				if (!(enemy.isAlive())) 
				{
					enemies.remove(enemy);
					enemy.dispose();
					i--;
				}
			}
		}
    }

	public void draw(SpriteBatch batch)
	{
		//Draws and updates all enemies created
		if (enemies != null) 
		{
			//Loops though all of the enemies
			for(int i = 0; i < enemies.size(); i++ )
			{
				Enemies enemy = enemies.get(i);
				enemy.Draw(batch);	
			}
		}
	}

	/**
	 * Checks for a collision between enemy and death square
	 * @param deathSquare Board square that if touched by an enemy should die
	 */
    public void checkForCollision(Rectangle deathSquare) 
    {
        if (enemies != null) 
        {
            //Loops though all of the enemies
            for( int i = 0; i < enemies.size(); i++ )
            {
                Enemies enemy = enemies.get(i);

                //If the enemy is in contact with the death square
                if (enemy.getCollisionRectangle().overlaps(deathSquare)) 
                {
                    enemy.reduceHealth();
                }
            }
        }
    }

	/**
	 * Set the speed of all enemies
	 * @param speed new speed of all enemies
	 */
	public void setMovementSpeed(int speed)
	{
		if (enemies != null) 
		{
			//Loops though all of the enemies
			for(int i = 0; i < enemies.size(); i++ )
			{
				Enemies enemy = enemies.get(i);
				enemy.setMovementSpeed(speed);
			}
		}				
	}
}