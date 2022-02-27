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
import com.mygdx.Driver;
import com.mygdx.GameController;
import com.mygdx.Sound.SoundController;
import com.mygdx.Sound.Sounds.Click;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;

public class TutorialScreen implements Screen{

    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int BUTTON_X = Driver.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
    
    
    private SpriteBatch batch;
    private GameController controller;
    private SoundController soundController;
    
    private final Texture inActiveExitButton;
    private final Texture activeExitButton;

    public Stage stage;
    private FitViewport viewport;
    private Table table;

    private Label helpLabel;

    /**
     * Constructor for the Tutorial Screen which introduces players to how the game is played.
     */
    public TutorialScreen ()
    {
        batch = new SpriteBatch();
        controller = new GameController(batch);
        soundController = new SoundController();
        inActiveExitButton = new Texture("Screens/Exit(unactive)-1.png");
        activeExitButton = new Texture("Screens/Exit (active).png");
        
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage
        table = new Table();
        
        LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        helpLabel = new Label("In order to complete the game, the player must complete a Puzzle. The puzzle game is randomly selected and can either be colour match or colour sequence ordering. \n\n\n\n\n\n\n\n\n\n Colour Match: A colour Match is when two of the same colour buttons are pressed one after the other. all buttons must be matched\n Colour Ordering: A sequnce will appear on the screen, the buttons must be pressed in that sequence in order to complete the game",labelStyle);
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
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f,0);
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
                soundController.playSound(new Click());
                this.dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
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
