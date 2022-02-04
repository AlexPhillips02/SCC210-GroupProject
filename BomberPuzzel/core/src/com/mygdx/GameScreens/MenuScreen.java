package com.mygdx.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Driver;
import com.mygdx.GameController;

public class MenuScreen extends Driver implements Screen {

   private SpriteBatch batch;
   private GameController controller;
   private Stage stage;
   private ImageButton playButton;
   private ImageButton exitButton;

    public MenuScreen(SpriteBatch batch){
       this.batch = batch;
       controller = new GameController(batch);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        loadScreen();
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();
    }

    private void loadScreen() {
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        Texture txBackground = new Texture(Gdx.files.internal("core/assets/Bombing_Chap_Sprite_Set/Sprites/Menu/title_background.jpg"));
        Image background = new Image(txBackground);

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("core/assets/Screens/PlayActive.png")))));
        playButton.setWidth(200); //make it measures of the image
        playButton.setHeight(200);
        playButton.setPosition(((1024 - playButton.getWidth()) / 2 - 30) / 2 + 20, 200);
        playButton.addListener(new ClickListener(){

            public void clicked (InputEvent event, float x, float y){
                System.out.println("CLICKED");
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(batch));
            }
        });

        exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("core/assets/Screens/ExitActive.png")))));
        exitButton.setWidth(200); //make it measures of the image
        exitButton.setHeight(200);
        exitButton.setPosition(((1024 - exitButton.getWidth()) / 2 - 30) / 2 + 20, 200);
        exitButton.addListener(new ClickListener(){

            public void clicked (InputEvent event, float x, float y){
                System.out.println("Exited");
                dispose();
                System.exit(0);
            }
        });

        table.add(background);
        table.add(playButton);
        table.add(exitButton);

        stage.addActor(table);
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
