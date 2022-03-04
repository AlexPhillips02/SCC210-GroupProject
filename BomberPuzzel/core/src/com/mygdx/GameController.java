package com.mygdx;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Abilities.*;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Enemies.EnemyController;
import com.mygdx.GameScreens.MenuScreen;
import com.mygdx.Player.Player;
import com.mygdx.Puzzles.PuzzleController;
import com.mygdx.Sound.SoundController;

/**
 * @author Alex Phillips, Alex Chalakov, Kathryn Hurst
 * Controls the games, will create the board / player / enemies / puzzels
 * Creates and controls the camera
 */
public class GameController
{
	private GUI gui;
    private Board gameBoard;
	private Player player;
	private EnemyController enemyController;
	private PuzzleController puzzleController;
	private ArrayList<Ability> boardAbilities;	//Abilities on the board
	private ArrayList<Ability> activeAbilities;	//Abilities the player has picked up and currently using
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport gamePort;
	
	private int levelNumber = 0;
	private float timeSinceGameStop = 0;
	private boolean gameLoaded = false;
	private boolean runGame = true;
	private boolean pause = false;

	private Sound playerHit = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/damage.mp3"));
	private SoundController soundController;
	
	/**
	 * Creates the camera, sound controller and gameport
	 * @param batch
	 */
    public GameController(SpriteBatch batch)
    {
        this.batch = batch;
		soundController = new SoundController();
		camera = new OrthographicCamera();
		camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
		gamePort = new FitViewport(1280, 720, camera);
		CreateLevel();
    }

	/**
	 * Creates the level.
	 * Every single part of the level is declared and initialised here.
	 */
	public void CreateLevel()
	{
		gameLoaded = false;
		runGame = false;

		//Level number originally set to 0
		float basePercentageOfDestrctableWalls = 12;
		int baseEnemyAmount = 6;
		int baseAbilitesAmount = 7;

		//Increasing base amounts based on level (Increase difficulty)
		basePercentageOfDestrctableWalls = basePercentageOfDestrctableWalls + (levelNumber * 5);
		baseEnemyAmount = baseEnemyAmount + levelNumber;
	    baseAbilitesAmount = baseAbilitesAmount - levelNumber;

		levelNumber++;

		gameBoard = new Board(27, 15, basePercentageOfDestrctableWalls);
		player = new Player(gameBoard, 90, 70, 175);
		enemyController = new EnemyController(gameBoard, player);
		boardAbilities = new ArrayList<Ability>();
		activeAbilities = new ArrayList<Ability>();
		enemyController.CreateEnemies(baseEnemyAmount, levelNumber);

		gui = new GUI(levelNumber);
		puzzleController = new PuzzleController(gui, gameBoard, player);
		puzzleController.SetPuzzle();

		CreateAbilities(baseAbilitesAmount);
		gameBoard.createStartingPath();
		gameLoaded = true;
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
	 * Continuously sends updates looking for player movement
	 * Also calls the function to draw the player and the board
	 */
	public void Update() 
	{
		if(gameLoaded == true)
		{
			//Pause game
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			{
				if(pause)
				{
					pause = false;
				}
				else
				{
					pause = true;
				}
			}
			
			//Gameboard
			gameBoard.Draw(batch);	//draws gameboard
			ArrayList<Rectangle> deathSquares = gameBoard.getDeathSquares(); //Returns squares that should inflict damage when a bomb explodes
			
			//Puzzle controller
			puzzleController.Update(batch);	
			gameBoard.DrawBombs(batch);

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

			//Enemies and player drawing
			enemyController.draw(batch);
			player.Draw(batch);

			//Camera
			moveCamera();		
			gui.stage.draw();

			//If the game has been won or the player has died
			if (puzzleController.getWinStatus() || !player.isAlive() || !runGame || pause)
			{
				gui.setHealth(player.getHealth()); //Ensures gui is outputting correct health

				if(!pause)
				{
					GamePauseOutput();
				}
				else
				{
					gui.Pause(this);
				}
			}
			else
			{
				//If the game is still running, update the moving entities
				gui.update(player.getHealth(), Gdx.graphics.getDeltaTime(), activeAbilities);
				player.update();
				enemyController.Update();
			}
		}
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
		int randomIndex = rand.nextInt(5);
		Ability newAbility;

		if (randomIndex == 0) 
		{
			newAbility = new BombIncrement(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 1)
		{
			newAbility = new BombRange(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 2)
		{
			newAbility = new SpeedDecrease(gameBoard, xPosition, yPosition, player);
		}
		else if (randomIndex == 3)
		{
			newAbility = new SpeedIncrease(gameBoard, xPosition, yPosition, player);
		}
		else
		{
			newAbility = new HealthIncrease(gameBoard, xPosition, yPosition, player);
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
				soundController.playMusic(playerHit);
				player.reduceHealth();

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
		float maxWidth = gameBoard.getXLength() * gameBoard.getTileSize();
		float maxHeight = gameBoard.getYLength() * gameBoard.getTileSize();

		//Updates camera position with player
		batch.setProjectionMatrix(camera.combined);
		camera.position.set((player.getX() + player.getWidth() / 2), (player.getY() + player.getHeight() / 2), 0);

		//Left wall and bottom wall (Prevents camera from showing past the wall)
		if(camera.position.x < startX)
		{
			camera.position.x = startX;
		}  
		if(camera.position.y < startY)
		{
			camera.position.y = startY;
		}

		//Right wall and top wall
		if(camera.position.x > (maxWidth - startX))
		{
			camera.position.x = (maxWidth - startX);
		} 
		if(camera.position.y > (maxHeight - startY))
		{
			camera.position.y = (maxHeight - startY);
		}

		camera.update();
	}

	/**
	 * What happens when the game is paused
	 */
	public void GamePauseOutput()
	{
		if (puzzleController.getWinStatus())
		{
			//THIS IS WHAT HAPPENS WHEN THE GAME IS WON
			timeSinceGameStop = timeSinceGameStop + Gdx.graphics.getDeltaTime();
			gui.puzzelCompleted();
		
			if(timeSinceGameStop >= 5)
			{
				timeSinceGameStop = 0;
				CreateLevel();
			}
			else if(timeSinceGameStop >= 1.5)
			{
				gui.puzzelCompletedTime();
			}
		}
		else if (!player.isAlive())
		{
			timeSinceGameStop = timeSinceGameStop + Gdx.graphics.getDeltaTime();
			gui.gameOverLabel();
			
			if(timeSinceGameStop >= 5)
			{
				LoadMenu();
			}
			else if(timeSinceGameStop >= 1.5)
			{
				gui.levelCompletionLabel();
			}
		}
		else
		{
			//If the game is yet to start
			int countDown = 4;
			timeSinceGameStop = timeSinceGameStop + Gdx.graphics.getDeltaTime();
			countDown = (int)(countDown - timeSinceGameStop);

			gui.startGame();
			gui.gameCountDown(countDown);
		
			if(countDown == 0)
			{
				gui.removeCountDown();
				timeSinceGameStop = 0;
				runGame = true;
			}
		}
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

	/**
	 * set the poolean paused to true or false
	 * @param b new value of pause
	 */
	public void setPaused(boolean b) {
		pause = b;
	}

	/**
	 * Loads the menu
	 */
	public void LoadMenu()
	{
		dispose();
		((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
	}

	/**
	 * Disposes of objects
	 */
	public void dispose()
	{
		batch.dispose();
	}
}