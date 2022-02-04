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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.Driver;
import com.mygdx.GameController;

public class MenuScreen implements Screen {

   private SpriteBatch batch;
   private Sprite sprite;
   private GameController controller;
   private Stage stage;
   private ImageButton playButton;
   private ImageButton exitButton;

    /**
     * Constructor for the main Menu Screen which appears at the start of the game.
     * @param batch SpriteBatch batch
     */
    public MenuScreen(SpriteBatch batch){
       this.batch = batch;
       controller = new GameController(batch);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        loadScreen(); //calling our function where everything is placed
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
        sprite.draw(batch);
        batch.end();

        //drawing the stage with the buttons
        stage.act();
        stage.draw();
    }

    /**
     * Main Screen method that contains everything.
     */
    private void loadScreen() {
        stage = new Stage();
        Table table = new Table(); //introducing the table within the stage
        table.setWidth(stage.getWidth()); //aligning
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()); //setting position

        //introducing the play button
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("core/assets/Screens/PlayActive.png")))));
        playButton.addListener(new ClickListener(){

            public void clicked (InputEvent event, float x, float y){ //adding an action when clicked
                System.out.println("CLICKED");
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(batch)); //send us to the game screen
            }
        });

        //introducing the exit button
        exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("core/assets/Screens/ExitActive.png")))));
        exitButton.addListener(new ClickListener(){

            public void clicked (InputEvent event, float x, float y){ //adding an action when clicked
                System.out.println("Exited");
                dispose();
                System.exit(0); //exits and closes the screen
            }
        });

        //adding buttons to table
        table.add(playButton);
        //table.row(); could be added, but not needed for now
        table.add(exitButton);

        //adding table to the stage
        stage.addActor(table);

        //introducing our background and aligning it
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage);
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
