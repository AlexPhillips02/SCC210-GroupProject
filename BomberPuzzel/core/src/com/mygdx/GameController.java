package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;
import com.mygdx.TileTypes.Path;
import com.mygdx.Board.Squares;

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

	/**
	 * Creates the camera
	 * @param batch
	 */
    public GameController(SpriteBatch batch)
    {
        this.batch = batch;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);
		CreateLevel();
    }

	/**
	 * Creates the level
	 */
	public void CreateLevel() 
	{
		gameBoard = new Board(29, 15);

		player = new Player(gameBoard, 64, 64, 150);

		//Just creates a bit of room to move around in at start
		Squares temps = gameBoard.getGameSquare(2, 2);
		temps.setTile(new Path());
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

		batch.setProjectionMatrix(camera.combined);

		camera.position.set(player.getX(), player.getY(), 0);

		//Left wall and bottom wall (Prevents camera from showing past the wall)
		if(player.getX() < startX)
		{
			camera.position.x = startX;
		}  
		if(player.getY() < startY)
		{
			camera.position.y = startY;
		}

		//Right wall and top wall
		if(player.getX() > (width + startX))
		{
			camera.position.x = (width + startX);
		} 
		if(player.getY() > (height + startY))
		{
			camera.position.y = (height + startY);
		}

		camera.update();
	}
}
