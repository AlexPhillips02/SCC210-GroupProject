package com.mygdx;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Abilities.*;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Enemies.EnemyController;
import com.mygdx.Player.Player;

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
	private EnemyController enemyController;
	private ArrayList<Ability> boardAbilities;	//Abilities on the board
	private ArrayList<Ability> activeAbilities;	//Abilities the player has picked up and currently using
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

		CreateLevel(0, 10, 5);
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
		player = new Player(gameBoard, 65, 65, 150);
		enemyController = new EnemyController(gameBoard, player);
		boardAbilities = new ArrayList<Ability>();
		activeAbilities = new ArrayList<Ability>();
		enemyController.CreateEnemies(enemyAmount);
		CreateAbilities(abilitiesAmount);
	}

	/**
	 * Creates an amount of abilities and spawns them randomly thought the map
	 * @param amount Amount of abilities to spawn
	 */
	public void CreateAbilities(int amount)
	{
		for (int i = 0; i < amount; i++)
		{
			Squares pathSquare = gameBoard.getRandomPath();

			//Translates grid position to coordinate
			int xPosition = pathSquare.getX();
			int yPosition = pathSquare.getY();

			//Creates the abilities and adds them to the list
			Ability newAbility = getRandomAbility(xPosition, yPosition);
			boardAbilities.add(newAbility);
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

		//Draw abilities on the board
		if (boardAbilities != null)
		{
			for(int i = 0; i < boardAbilities.size(); i++ )
			{	
				Ability ability = boardAbilities.get(i);
				ability.Draw(batch);

				//If the player collides with an ability set the ability as active and add to active list
				//Remove from the board abilities (No longer needs to be drawn)
				if (ability.getCollisionRectangle().overlaps(player.getCollisionRectangle()))
				{
					ability.setActive();
					activeAbilities.add(ability);
					boardAbilities.remove(i);
					i--;
				}
			}
		}

		//Loops through active abilites
		if (activeAbilities != null) 
		{
			for(int i = 0; i < activeAbilities.size(); i++ )
			{	
				Ability ability = activeAbilities.get(i);

				ability.update();
					
				//If the ability has deactivated (Remove from active ability list)
				if (ability.isDeactivated()) 
				{
					activeAbilities.remove(i);
					i--;
				}
			}
		}

		enemyController.Update(batch);

		player.checkInput();
		player.Draw(batch);		//Draws player

		if (player.isAlive() == false) 
		{
			//System.out.println("Player is now dead");
		}
		
		moveCamera();
	}

	/**
	 * A method that gets a random ability from the possible powerups and returns the ability
	 * @param yPosition X coordinate of the ability
	 * @param xPosition Y coordinate of the ability
	 * @return The random ability
	 */
	public Ability getRandomAbility(int xPosition, int yPosition)
	{
		Random rand = new Random();
		int randomIndex = rand.nextInt(6);
		Ability newAbility;

		if (randomIndex == 0) {
			newAbility = new BombIncrement(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 1)
		{
			newAbility = new BombRange(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 2)
		{
			newAbility = new SpeedIncrease(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 3)
		{
			newAbility = new SpeedDecrease(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 4)
		{
			newAbility = new HealthIncrease(gameBoard, xPosition, yPosition, player);
		}
		else
		{
			newAbility = new Invincibility(gameBoard, xPosition, yPosition, player);
		}

		newAbility.setX(xPosition + (newAbility.getWidth()) / 2);
		newAbility.setY(yPosition + (newAbility.getHeight() / 2));
		return newAbility;
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

			enemyController.checkForCollision(deathSquare);
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
