package com.mygdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Board.Board;
import com.mygdx.Player.Entity;
import com.mygdx.Player.Player;

/**
 * @author Alex Phillips
 * Controls the games will create the board / player / enemies
 * Creates and controls the camera
 */
public class GameController
{
	//private Boolean winStatus;
    private Board gameBoard;
	private Player player;
	private ArrayList<Enemies> enemies;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport gamePort;

	/**
	 * Creates the camera
	 * @param batch
	 */
    public GameController(SpriteBatch batch)
    {
        this.batch = batch;
		//camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
		camera = new OrthographicCamera();
		gamePort = new FitViewport(928, 480, camera);

		CreateLevel(20, 5);
    }

	/**
	 * Creates the level
	 * @param percentageOfDestructableWalls Percentage of the map to be filled with walls (0 - 100)
	 */
	public void CreateLevel(float percentageOfDestructableWalls, int enemyAmount) 
	{
		gameBoard = new Board(29, 15, percentageOfDestructableWalls);
		player = new Player(gameBoard, 64, 64, 150);
		enemies = new ArrayList<Enemies>();
		CreateEnemies(10);
	}

	/**
	 * Creates the enemies and places them randomly around the map
	 * @param amount Amount of enemys to spawn around the map
	 */
	public void CreateEnemies(int amount)
	{
		int xPosition;
		int yPosition;

		for (int i = 0; i < amount; i++) 
		{
			//Loops until spawn position is a path
			do 
			{
				xPosition = (int)(Math.random() * (gameBoard.getXLength() - 2)  + 1);
            	yPosition = (int)(Math.random() * (gameBoard.getYLength() - 2)  + 1);	
			} while ((gameBoard.getGameSquare(xPosition, yPosition).getTile()) instanceof Path);

			//Translates grid position to coordinate
			xPosition = xPosition * gameBoard.getTileSize();
			yPosition = yPosition * gameBoard.getTileSize();

			//Creates the enemy and adds to list of enemies
			Enemies enemy = new Creep(gameBoard, xPosition, yPosition, 100);	
			enemies.add(enemy);
		}
	}

	/**
	 * Continuosly sends updates looking for player movement
	 * Also calls the function to draw the player and the board
	 */
	public void Update() 
	{
		gameBoard.Draw(batch);	//draws gameboard	
		ArrayList<Rectangle> deathSquares = gameBoard.getDeathSquares(); //Returns squares that should inflict damage when a bomb exploads

		//If squares exist where damage should be inflicted
		if (deathSquares.size() > 0) 
		{
			checkForSquareCollision(deathSquares);
			gameBoard.resetDeathSquares();
		}

		player.checkInput();
		player.Draw(batch);		//Draws player

		//Draws and updates all enemeies created
		if (enemies != null) 
		{
			for (Enemies enemies : enemies) 
			{
				enemies.update();
				enemies.Draw(batch);	

				//Player contact with enemy
				if (enemies.getCollisionRectangle().overlaps(player.getCollisionRectangle()) && player.isAlive()) 
				{
					player.reduceHealth();
					System.out.println("Player has had contact with enemy!");
					if (player.isAlive() == false) 
					{
						System.out.println("Player is now dead");
					}
				}
			}
		}

		moveCamera();
	}

	public void checkForSquareCollision(ArrayList<Rectangle> deathSquares)
	{
		for (Rectangle deathSquare : deathSquares) 
		{
			if(deathSquare.overlaps(player.getCollisionRectangle())) 
			{
				player.reduceHealth();
			}

			if (enemies != null) 
			{
				for( int i = 0; i < enemies.size(); i++ )
				{
					Entity enemy = enemies.get(i);

					if (enemy.getCollisionRectangle().overlaps(deathSquare)) 
					{
						enemies.remove(enemy);
						i--;
						System.out.println("Enemy killed");						
					}
				}
			}
		}
	}

	/**
	 * Moves the camera along with the player movement
	 */
	public void moveCamera()
	{
		float startX = camera.viewportWidth / 2;
		float startY = camera.viewportHeight / 2;
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		//Updates camera position with player
		batch.setProjectionMatrix(camera.combined);
		camera.position.set((player.getX() + player.getWidth() / 2), (player.getY() + player.getHeight() / 2), 0);

		//Left wall and bottom wall (Prevents camera from showing past the wall)
		if((player.getX() + player.getWidth() / 2) < startX)
		{
			camera.position.x = startX;
		}  
		if((player.getY() + player.getHeight() / 2) < startY)
		{
			camera.position.y = startY;
		}

		//Right wall and top wall
		if((player.getX() + player.getWidth() / 2) > (width + startX))
		{
			camera.position.x = (width + startX);
		} 
		if((player.getY() + player.getHeight() / 2) > (height + startY))
		{
			camera.position.y = (height + startY);
		}

		camera.update();
	}

	public void resize(int screenWidth, int screenHeight)
	{
		gamePort.update(screenWidth, screenHeight);
	}
}
