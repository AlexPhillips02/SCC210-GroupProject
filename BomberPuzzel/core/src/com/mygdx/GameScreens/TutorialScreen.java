package com.mygdx.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;

public class TutorialScreen implements Screen{

    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAYER_IMAGE_HEIGHT = 150;
    private static final int PLAYER_IMAGE_WIDTH = 100;
    private static final int GAME_IMAGE_WIDTH = 600;
    private static final int GAME_IMAGE_HEIGHT = 400;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int ENEMY_IMAGE_WIDTH = 100;
    private static final int ENEMY_IMAGE_HEIGHT = 100;
    private static final int BUTTON_X = Driver.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
    
    
    private SpriteBatch batch;
    private SoundController soundController;
    private Sound buttonClick = Gdx.audio.newSound(Gdx.files.internal("Sounds/mixkit-interface-click-1126.mp3"));
    
    private final Texture inActiveExitButton;
    private final Texture activeExitButton;
    private final Texture gameImage;
    private final Texture playerImage;
    private final Texture enemyImage;
    
    public Stage stage;
    private FitViewport viewport;
    private Table table;
    private Label helpLabel;
    private OrthographicCamera camera;
    /**
     * Constructor for the Tutorial Screen which introduces players to how the game is played.
     * @param controller
     */
    public TutorialScreen()
    {
        batch = new SpriteBatch();
        soundController = new SoundController();
        inActiveExitButton = new Texture("Screens/Exit(unactive)-1.png");
        activeExitButton = new Texture("Screens/Exit (active).png");
        gameImage = new Texture("Screens/puzzleBombSS.png");
        playerImage = new Texture("Bomberman/BombermanFront.png");
        enemyImage = new Texture("Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f00.png");
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage
        table = new Table();
        
        LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        helpLabel = new Label("In order to complete the game, the player must complete a a randomly selected Puzzle. The colour Match Puzzle the user must step on two of the same coloured button one after the other. ALl button must be Match in order to win. In Colour Sequencing the colour buttons must be stepped on, in the sequence given.",labelStyle); 
            //(float)GAME_IMAGE_WIDTH, 
            //(float)GAME_IMAGE_HEIGHT);
        //helpLabel.setBounds(x, y, width, height);
       // helpLabel.setWrap(true);
        //helpLabel.setAlignment(Align.center);
        
        helpLabel.setPosition(340, 280);
        helpLabel.setWidth(300);
        helpLabel.setWrap(true);
        //table.top(); //qill put it at the top of stage
        stage.addActor(helpLabel);
        //table.setFillParent(true);
        //table.add(helpLabel).width(300).height(300);

        
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
        int y = EXIT_BUTTON_Y, x = EXIT_BUTTON_Y, z = 180, n = 180;
        batch.begin();

                //x, y, width, length
        batch.draw(gameImage, 340, 320, GAME_IMAGE_WIDTH, GAME_IMAGE_HEIGHT);

        for(int i = 0; i<4; i++){
            batch.draw(playerImage, 70, y , PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
            y = y + PLAYER_IMAGE_HEIGHT + 30;
        }

        for(int i = 0; i<4; i++){
            batch.draw(playerImage, 1120, x , PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
            x = x + PLAYER_IMAGE_HEIGHT + 30;
        }

        for(int i = 0; i<4; i++){
            batch.draw(enemyImage, 220, z, ENEMY_IMAGE_WIDTH ,ENEMY_IMAGE_HEIGHT ); 
            z = z + ENEMY_IMAGE_HEIGHT + 30;
        }
  
        for(int i = 0; i<4; i++){
            batch.draw(enemyImage, 980, n , ENEMY_IMAGE_WIDTH ,ENEMY_IMAGE_HEIGHT ); 
            n = n + ENEMY_IMAGE_HEIGHT + 30;
        }
  

        batch.draw(playerImage, (70 + PLAYER_IMAGE_WIDTH + 70), 10, PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
        batch.draw(playerImage, (70 + PLAYER_IMAGE_WIDTH + 70 + PLAYER_IMAGE_WIDTH + 70), 10, PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
        batch.draw(playerImage, (1280 - PLAYER_IMAGE_WIDTH - 70 - PLAYER_IMAGE_WIDTH - 70), 10, PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
        batch.draw(playerImage, (1280 - PLAYER_IMAGE_WIDTH - 70 - PLAYER_IMAGE_WIDTH - 70 - PLAYER_IMAGE_WIDTH - 70), 10, PLAYER_IMAGE_WIDTH ,PLAYER_IMAGE_HEIGHT ); 
            
        

        Exit();
        stage.draw();
        batch.end(); 
   }

    private void Exit(){
        if(Gdx.input.getX() < BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            batch.draw(activeExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                soundController.playMusic(buttonClick);
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
   public void dispose () 
   {

   }

}
