package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
 * A class for the win screen whenever the player beats the game.
 */
public class WinScreen implements Screen {

    private SpriteBatch batch;
    private Sprite sprite;
    private GameController controller;
    private Stage stage;
    private ImageButton startOverButton;


    /**
     * Constructor for the main Game Over Screen which appears at the end of the game, when the player dies.
     * @param batch SpriteBatch batch
     */
    public WinScreen(){
        batch = new SpriteBatch();
        controller = new GameController(batch);
    }


    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show()
    {
        loadScreen();
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        //If N is pressed a new game is started when this one is finished
        if (Gdx.input.isKeyPressed(Input.Keys.N))
        {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) //If the escape button is clicked the game shuts down
        {
            dispose();
            System.exit(0);
        }

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
        startOverButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Screens/PuzzleBomber.png")))));
        startOverButton.addListener(new ClickListener(){

            public void clicked (InputEvent event, float x, float y){ //adding an action when clicked
                System.out.println("CLICKED");
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen()); //send us to the game screen
            }
        });

        //adding buttons to table
        table.add(startOverButton);
        //table.row(); could be added, but not needed for now

        //adding table to the stage
        stage.addActor(table);

        //introducing our background and aligning it
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage);
    }


    /**
     * @param width size of width
     * @param height size of height
     */
    @Override
    public void resize(int width, int height)
    {
        controller.resize(width, height);
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

