package com.mygdx.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Driver;
import com.mygdx.GUI;
import com.mygdx.GameController;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;
import com.mygdx.GUI;

public class TutorialScreen implements Screen{

    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int BUTTON_X = Driver.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
    
    
    private SpriteBatch batch;
    private GameController controller;
    
    
    private Texture inActiveExitButton;
    private Texture activeExitButton;


    public Stage stage;
    private FitViewport viewport;
    private Table table;
    

    private Label helpLabel;

    public TutorialScreen (SpriteBatch batch){
        this.batch = batch;
        controller = new GameController(batch);
        inActiveExitButton = new Texture("Screens/Exit(unactive)-1.png");
        activeExitButton = new Texture("Screens/Exit (active).png");
        
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage
        table = new Table();
        
        LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        helpLabel = new Label("help",labelStyle);
        helpLabel.setAlignment(Align.center);
        
        table.top(); //qill put it at the top of stage
        table.setFillParent(true);
        table.add(helpLabel).width(300).height(300);

        stage.addActor(table);
    }

    

	/**
    * Called when the screen should render itself.
    * @param delta The time in seconds since the last render.
    */
   @Override
   public void render (float delta)
   {	
       Gdx.gl.glClearColor(1, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       batch.begin();
       Exit();
       stage.draw();
       batch.end(); 
   }

    private void Exit(){
        if(Gdx.input.getX() < BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            batch.draw(activeExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                this.dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen(batch));
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
   public void resize (int width, int height) 
   {
       
   }

   /**
    * Called when this screen becomes the current screen for a Game.
    */
   @Override
   public void show () {
        
   }

   @Override
   public void hide () {
   }

   @Override
   public void pause () {
   }

   @Override
   public void resume () {
   }

   @Override
   public void dispose () {

   }

}
