package com.mygdx.GameScreens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Driver;
import com.mygdx.Sound.SoundController;

/**
 * @author Alex Chalakov, Lincoln Delhomme
 * A class for the menu screen which adds all the options when the program is run.
 */
public class MenuScreen implements Screen 
{
    private int PLAY_BUTTON_WIDTH = 200;
    private int PLAY_BUTTON_HEIGHT = 150;
    private int EXIT_BUTTON_WIDTH = 200;
    private int EXIT_BUTTON_HEIGHT = 150;
    private int OPTION_BUTTON_WIDTH = 200;
    private int OPTION_BUTTON_HEIGHT = 150;
    private int PLAY_BUTTON_Y = 170;
    private int EXIT_BUTTON_Y = 10;
    private int OPTION_BUTTON_Y = 330;
    private int TITLE = 485;
    private int TITLE_HEIGHT = 240;
    private int TITLE_BUTTON_WIDTH = 800;
    private int BUTTON_X = Driver.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
    private int TITLE_X = Driver.WIDTH / 2 - TITLE_BUTTON_WIDTH / 2;

    private SpriteBatch batch;
    private SoundController soundController;

    private Texture inActiveHelp;
    private Texture inActivePlayButton;
    private Texture inActiveExitButton;
    private Texture activeHelp;
    private Texture activePlayButton;
    private Texture activeExitButton;
    private Texture bombTexture;
    private Texture puzzleBomber;

    private Sound buttonClick;

    private float lastDropTime = 1f;
    private ArrayList<Rectangle> bombs = new ArrayList<>();
   
    
    /**
     * Constructor for the main Menu Screen which appears at the start of the game.
     */
    public MenuScreen()
    {
        batch = new SpriteBatch();


        buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/ButtonPress.mp3"));
    

        inActiveHelp = new Texture("Screens/Help(WHITE).png");
        activeHelp = new Texture("Screens/Help(active).png");
        inActivePlayButton = new Texture("Screens/Play(UnactiveWHITE).png");
        inActiveExitButton = new Texture("Screens/Exit(unactiveWHITE).png");
        activePlayButton = new Texture("Screens/Play(Active).png");
        activeExitButton = new Texture("Screens/Exit(active).png");
        bombTexture = new Texture("Bombs/bomb(single).png");
        puzzleBomber = new Texture("Screens/TitleScreen.png");
        

        soundController = new SoundController();
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) 
    {
        batch.begin();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Creates falling bombs on the main menu
        lastDropTime = lastDropTime + Gdx.graphics.getDeltaTime();

        //If no bomb has been created within the last 0.5 seconds, spawn a falling bomb
        if(lastDropTime > 0.5)
        {
            spawnBomb();
        }

        //Loops through falling bombs and adjusts y values
        for(int i = 0; i < bombs.size(); i++ )
        {
            Rectangle bomb = bombs.get(i);
            bomb.y -= 200 * Gdx.graphics.getDeltaTime();
            batch.draw(bombTexture, bomb.x, bomb.y, 100, 100);

            //If the bomb has fallen off the screen, remove
			if(bomb.y + 64 < 0)
            {
                bombs.remove(i);
                i--;
            } 
        }
        
        //Create buttons
        batch.draw(puzzleBomber, TITLE_X, TITLE, TITLE_BUTTON_WIDTH, TITLE_HEIGHT);
        playAndExit();
        batch.end();
    }

    /**
     * Creates a falling bomb
     */
    private void spawnBomb() 
    {
		Rectangle bomb = new Rectangle();
		bomb.x = MathUtils.random(0, Gdx.graphics.getWidth()-64);
		bomb.y = Gdx.graphics.getHeight();

        //Adds to array list
		bombs.add(bomb);
		lastDropTime = 0f;
	}

    /**
     * Called when Window is set to menu Screen. Draws 
     * the components of the menu screen unto the window.
     */
    private void playAndExit()
    {
        if(Gdx.input.getX() < BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            batch.draw(activePlayButton, BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        
            if (Gdx.input.isTouched()){
                    soundController.playMusic(buttonClick);
                    this.dispose();
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen());
                }
            }
        else {
            batch.draw(inActivePlayButton, BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            batch.draw(activeExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
           
            if (Gdx.input.isTouched()){
                soundController.playMusic(buttonClick);
                Gdx.app.exit();
            }
        }
        else {
            batch.draw(inActiveExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < BUTTON_X + OPTION_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < OPTION_BUTTON_Y + OPTION_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > OPTION_BUTTON_Y){
            batch.draw(activeHelp, BUTTON_X, OPTION_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                this.dispose();
                soundController.playMusic(buttonClick);
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TutorialScreen());
            }
        }
        else {
            batch.draw(inActiveHelp, BUTTON_X, OPTION_BUTTON_Y, OPTION_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);
        }

    }

    /**
     * @param width size of width
     * @param height size of height
     */
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() 
    {}

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() 
    {
        batch.dispose();
    }
}
