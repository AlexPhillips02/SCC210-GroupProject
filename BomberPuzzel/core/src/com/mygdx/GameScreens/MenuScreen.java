package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.GameController;

/**
 * @author Alex Chalakov, Lincoln Delhomme
 * A class for the menu screen which adds all the options when the program is run.
 */
public class MenuScreen implements Screen {

    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 200;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 200;
    private static final int PLAY_BUTTON_Y = 90;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int BUTTON_X = Driver.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
    
    

    private SpriteBatch batch;
    private GameController controller;
   
    private Texture inActivePlayButton;
    private Texture inActiveExitButton;
    private Texture activePlayButton;
    private Texture activeExitButton;
    private Texture backGround;

    /**
     * Constructor for the main Menu Screen which appears at the start of the game.
     * @param batch SpriteBatch batch
     */
    public MenuScreen(SpriteBatch batch){
        this.batch = batch;
        controller = new GameController(batch);
        inActivePlayButton = new Texture("core/assets/Screens/PlayUnActive.png");
        inActiveExitButton = new Texture("core/assets/Screens/ExitUnActive.png");
        activePlayButton = new Texture("core/assets/Screens/PlayActive.png");
        activeExitButton = new Texture("core/assets/Screens/ExitActive.png");
        backGround = new Texture("core/assets/Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg");
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //drawing the background photo
        batch.begin();
        batch.draw(backGround, 0, 0, Driver.WIDTH, Driver.HEIGHT);
        playAndExit();
        batch.end();

        //drawing the stage with the buttons
        //stage.act();
        //stage.draw();
    }

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
     * Main Screen method that contains everything.
     */

     /*
    private void loadScreen() {
        stage = new Stage();
        Table table = new Table(); //introducing the table within the stage
        table.setWidth(stage.getWidth()); //aligning
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()); //setting position

        //introducing the play button
        
        inActivePlayButton.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){ //adding an action when clicked
                System.out.println("CLICKED");
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(batch)); //send us to the game screen
            }
        });

        //introducing the exit button
        inActiveExitButton.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y){ //adding an action when clicked
                System.out.println("Exited");
                dispose();
                System.exit(0); //exits and closes the screen
            }
        });

        //adding buttons to table
        table.add(inActivePlayButton);
        //table.row(); could be added, but not needed for now
        table.add(inActiveExitButton);

        //adding table to the stage
        stage.addActor(table);

        //introducing our background and aligning it
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage);
    }
    */
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
