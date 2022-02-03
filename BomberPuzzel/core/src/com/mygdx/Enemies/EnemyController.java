package com.mygdx.Enemies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Player;

public class EnemyController 
{
    private ArrayList<Enemies> enemies;
    private Board board;
    private Player player;
    
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
	public void CreateEnemies(int amount)
	{
		for (int i = 0; i < amount; i++) 
		{
			Squares pathSquare = board.getRandomPath();

			//Translates grid position to coordinate
			int xPosition = pathSquare.getX();
			int yPosition = pathSquare.getY();

			//Creates the enemy and adds to list of enemies
			Enemies enemy;
			if (i == 0) 
			{
				enemy = new BombCarrier(board, xPosition, yPosition, 100, player);
				//enemy = new BombCarrier(board, 64, 400, 100, player);	
			}
			else
			{
				enemy = new Creep(board, xPosition, yPosition, 100);
			}

			enemies.add(enemy);
		}
	}

    public void Update(SpriteBatch batch)
    {
        //Draws and updates all enemeies created
		if (enemies != null) 
		{
			//Loops though all of the enemies
			for(int i = 0; i < enemies.size(); i++ )
			{
				Enemies enemy = enemies.get(i);

				enemy.update();
				enemy.Draw(batch);	

				//Player contact with enemy
				if (enemy.getCollisionRectangle().overlaps(player.getCollisionRectangle())) //&& player.isAlive()) 
				{
					enemy.Attack(player);

					System.out.println("Player has had contact with enemy!");
				}

				if (!(enemy.isAlive())) 
				{
					enemies.remove(enemy);
					i--;
					System.out.println("Enemy dead");
				}
			}
		}
    }

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

    
}
