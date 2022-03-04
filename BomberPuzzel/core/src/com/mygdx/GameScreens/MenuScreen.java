package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.SortedIntList.Iterator;
import com.mygdx.Driver;
import com.mygdx.Sound.SoundController;

import java.util.Iterator;


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
    private int BUTTON_X = Driver.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;

    private SpriteBatch batch;
    private SoundController soundController;

    private Texture inActiveHelp;
    private Texture inActivePlayButton;
    private Texture inActiveExitButton;
    private Texture activeHelp;
    private Texture activePlayButton;
    private Texture activeExitButton;
    private Texture backGround;
    private Texture bomb;

    private Music introSong;
    private Sound buttonClick;

    private long lastDropTime;
    private Array<Rectangle> raindrops;
   
    
    /**
     * Constructor for the main Menu Screen which appears at the start of the game.
     */
    public MenuScreen()
    {
        batch = new SpriteBatch();

        introSong = Gdx.audio.newMusic(Gdx.files.internal("Sounds/alex-productions-epic-cinematic-gaming-cyberpunk-reset.mp3"));
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("Sounds/mixkit-interface-click-1126.mp3"));

        inActiveHelp = new Texture("Screens/Help(WHITE).png");
        activeHelp = new Texture("Screens/Help(active).png");
        inActivePlayButton = new Texture("Screens/Play(UnactiveWHITE).png");
        inActiveExitButton = new Texture("Screens/Exit(unactiveWHITE).png");
        activePlayButton = new Texture("Screens/Play (Active).png");
        activeExitButton = new Texture("Screens/Exit (active).png");
        bomb = new Texture("Bombs/bomb(single).png");
        

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
        //soundController.playMusic(introSong);
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();
        }

        //drawing the background photo
        batch.begin();
        
        playAndExit();
        batch.end();
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

    private void spawnRaindrop() {
		Rectangle bomb = new Rectangle();
		bomb.x = MathUtils.random(0, 800-64);
		bomb.y = 480;
		bomb.width = 64;
		bomb.height = 64;
		raindrops.add(bomb);
		lastDropTime = TimeUtils.nanoTime();
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
