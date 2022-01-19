package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Board.Board;
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

		CreateLevel(20);
    }

	/**
	 * Creates the level
	 * @param percentageOfDestructableWalls Percentage of the map to be filled with walls (0 - 100)
	 */
	public void CreateLevel(float percentageOfDestructableWalls) 
	{
		gameBoard = new Board(29, 15, percentageOfDestructableWalls);
		player = new Player(gameBoard, 64, 64, 150);
	}

	/**
	 * Continuosly sends updates looking for player movement
	 * Also calls the function to draw the player and the board
	 */
	public void Update() 
	{
		player.checkInput();
		moveCamera();

		gameBoard.Draw(batch);	//draws gameboard
		player.Draw(batch);		//Draws player
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
