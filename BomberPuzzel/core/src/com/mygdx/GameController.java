package com.mygdx;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Abilities.Ability;
import com.mygdx.Abilities.BombIncrement;
import com.mygdx.Abilities.BombRange;
import com.mygdx.Abilities.SpeedIncrease;
import com.mygdx.Board.Board;
import com.mygdx.Enemies.Creep;
import com.mygdx.Enemies.Enemies;
import com.mygdx.Player.Entity;
import com.mygdx.Player.Player;
import com.mygdx.TileTypes.Path;

/**
 * @author Alex Phillips, Alex Chalakov
 * Controls the games will create the board / player / enemies
 * Creates and controls the camera
 */
public class GameController
{
	//private Boolean winStatus;
    private Board gameBoard;
	private Player player;
	private ArrayList<Enemies> enemies;
	private ArrayList<Ability> abilities;
	private ArrayList<Ability> newList;
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

		CreateLevel(20, 10, 5);
    }

	/**
	 * Creates the level
	 * @param percentageOfDestructableWalls Percentage of the map to be filled with walls (0 - 100)
	 * @param enemyAmount amount of enemies that are going to be spawned on the map
	 * @param abilitiesAmount amount of abilities that are going to be spawned on the map
	 */
	public void CreateLevel(float percentageOfDestructableWalls, int enemyAmount, int abilitiesAmount)
	{
		gameBoard = new Board(29, 15, percentageOfDestructableWalls);
		player = new Player(gameBoard, 64, 64, 150);
		enemies = new ArrayList<Enemies>();
		abilities = new ArrayList<Ability>();
		CreateEnemies(10);
		CreateAbilities(5);
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
			} while (!((gameBoard.getGameSquare(xPosition, yPosition).getTile()) instanceof Path));

			//Translates grid position to coordinate
			xPosition = xPosition * gameBoard.getTileSize();
			yPosition = yPosition * gameBoard.getTileSize();

			//Creates the enemy and adds to list of enemies
			Enemies enemy = new Creep(gameBoard, xPosition, yPosition, 100);
			enemies.add(enemy);
		}
	}

	public void CreateAbilities (int amount)
	{
		int xXPosition;
		int yYPosition;

		for (int i = 0; i < amount; i++)
		{
			//Loops until spawn position is a path or a soft wall
			do
			{
				xXPosition = (int)(Math.random() * (gameBoard.getXLength() - 2)  + 1);
				yYPosition = (int)(Math.random() * (gameBoard.getYLength() - 2)  + 1);
			} while (!((gameBoard.getGameSquare(xXPosition, yYPosition).getTile()) instanceof Path));

			//Translates grid position to coordinate
			xXPosition = xXPosition * gameBoard.getTileSize();
			yYPosition = yYPosition * gameBoard.getTileSize();

			//Creates the abilities and adds them to the list
			Ability bombMax = new BombIncrement(gameBoard, xXPosition, yYPosition, player);
			Ability bombRange = new BombRange(gameBoard, xXPosition, yYPosition, player);
			Ability speedPowerup = new SpeedIncrease(gameBoard, xXPosition, yYPosition, player);

			abilities.add(bombMax);
			abilities.add(bombRange);
			abilities.add(speedPowerup);
			System.out.println("Test");
		}
	}

	/**
	 * Continuosly sends updates looking for player movement
	 * Also calls the function to draw the player and the board
	 */
	public void Update() 
	{
		gameBoard.Draw(batch);	//draws gameboard	
		ArrayList<Rectangle> deathSquares = gameBoard.getDeathSquares(); //Returns squares that should inflict damage when a bomb explodes

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

		//draw abilities - loop to print elements at random
		if (abilities != null)
		{
			int numberOfElements = 5;
			getRandomElement(abilities, numberOfElements);

			for (Ability abilities : newList){
				abilities.Draw(batch);
			}

			/* Spawning lines that make the abilities go crazy
			Random random = new Random();
			for (int i = 0; i < abilities.size(); i++)
			{
				int index = random.nextInt(abilities.size());
				abilities.get(index).Draw(batch);
			}*/
		}

		moveCamera();
	}

	/**
	 * A method that gets a random ability from the list of power ups.
	 * @param abilities an arraylist that contains all the abilities
	 * @param totalElements an int variable that specifies about how many elements are we talking
	 * @return the temporary list of abilities after the randomizer
	 */
	public ArrayList<Ability> getRandomElement(ArrayList<Ability> abilities, int totalElements)
	{
		Random rand = new Random();

		//create a temporary list for storing the selected element
		newList = new ArrayList<>();

		for (int i = 0; i< totalElements; i++){

			//take a random index between 0 to size of given list
			int randomIndex = rand.nextInt(abilities.size());
			newList.add(abilities.get(randomIndex));
		}
		return newList;
	}

	/**
	 * Method that ensures that if there is a collision between an entity and a death square, the entity would take damage
	 * @param deathSquares Squares that should inflict damage (E.g when a bomb exploads)
	 */
	public void checkForSquareCollision(ArrayList<Rectangle> deathSquares)
	{
		//Loops through all the death sqaures
		for (Rectangle deathSquare : deathSquares) 
		{
			//If the player is in contact with a death square
			if(deathSquare.overlaps(player.getCollisionRectangle())) 
			{
				player.reduceHealth();
				System.out.println("Player hit by bomb");
			}

			if (enemies != null) 
			{
				//Loops though all of the enemies
				for( int i = 0; i < enemies.size(); i++ )
				{
					Entity enemy = enemies.get(i);

					//If the enemy is in contact with the death square
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

	/**
	 * Window resizing method.
	 * @param screenWidth width of the screen.
	 * @param screenHeight height of the screen.
	 */
	public void resize(int screenWidth, int screenHeight)
	{
		gamePort.update(screenWidth, screenHeight);
	}
}
