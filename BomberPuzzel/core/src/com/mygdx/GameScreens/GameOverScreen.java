package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.Driver;

/**
 * @author Alex Chalakov, Lincoln Delhomme, Kathryn Hurst
 * A class for the game over screen for whenever our player dies.
 */
public class GameOverScreen implements Screen {

    private static final int GAMEOVER_WIDTH = 500;
    private static final int GAMEOVER_HEIGHT = 250;
    private static final int PLAY_BUTTON_WIDTH = 200;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int GAMEOVER_Y = 450;
    private static final int PLAY_BUTTON_Y = 170;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int GAMEOVER_X = Driver.WIDTH / 2 - GAMEOVER_WIDTH / 2;
    private static final int BUTTON_X = Driver.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
    
    

    private SpriteBatch batch;

    private Texture gameover;
    private Texture inActivePlayButton;
    private Texture inActiveExitButton;
    private Texture activePlayButton;
    private Texture activeExitButton;
    private Texture backGround;


    /**
     * Constructor for the main Game Over Screen which appears at the end of the game, when the player dies.
     * @param batch SpriteBatch batch
     */
    public GameOverScreen(SpriteBatch batch){
        this.batch = batch;
        
        gameover = new Texture("Screens/PuzzleBomber.png");
        inActivePlayButton = new Texture("Screens/Play(Unactive)-1.png");
        inActiveExitButton = new Texture("Screens/Exit(unactive)-1.png");
        activePlayButton = new Texture("Screens/Play (Active).png");
        activeExitButton = new Texture("Screens/Exit (active).png");
        backGround = new Texture("Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg");
    }


    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show()
    {
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //drawing the background photo
        batch.begin();
        batch.draw(backGround, 0, 0, Driver.WIDTH, Driver.HEIGHT);
        title();
        playAndExit();
        batch.end();
    }

    /**
     * Shows the heading on screen
     */
    private void title() {
        batch.draw(gameover, GAMEOVER_X, GAMEOVER_Y, GAMEOVER_WIDTH, GAMEOVER_HEIGHT);
    }

    /**
     * Shows the buttons on screen and highlight on hover
     */
    private void playAndExit(){
        if(Gdx.input.getX() < BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            batch.draw(activePlayButton, BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                this.dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(batch));
            }
        }
        else {
            batch.draw(inActivePlayButton, BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX() < BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            batch.draw(activeExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else {
            batch.draw(inActiveExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
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
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
