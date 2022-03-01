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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
   
  
    private Label information;  
    private Label movement;
    private Label enemy;
    private Label player; 
    private Label puzzles; 
    private Label movementInfo;
    private Label enemyInfo;
    private Label playerInfo; 
    private Label puzzlesInfo; 
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
        
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Text/GUI_Font.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 25;
        BitmapFont font = fontGenerator.generateFont(fontParameters);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);
        information = new Label("Information",labelStyle);        
        information.setPosition(centreLable(Driver.WIDTH, information.getWidth()), 680);
        fontParameters.size = 15;
        movement = new Label("Movement", labelStyle);
        enemy = new Label("Enemy", labelStyle);
        player = new Label("player",labelStyle);
        puzzles = new Label("puzzles",labelStyle);

        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        playerInfo = new Label("Player must avoid contact with enemy to survive that level. In order to complete the level player must complete a randomly selected puzzle. ", labelStyle);
        enemyInfo = new Label("There are two types of enemies. There is a bomb carrier and enemy spawns. Bomb carrier enemy follows player around and drops bomb near player, " 
         + "whilst enemy spawns reduce life of player with each contact with player", labelStyle);
        movement.setPosition(70,600);
        enemy.setPosition(70, 500);
        player.setPosition(70, 300);
        //puzzles.setPosition(70,200);
        playerInfo.setPosition(350,300);
        enemyInfo.setPosition(350,500);

        enemyInfo.setWidth(700);
        enemyInfo.setWrap(true);

        stage.addActor(information);
        stage.addActor(movement);
        stage.addActor(enemy);
        stage.addActor(player);
        stage.addActor(puzzles);

        stage.addActor(information);
        //stage.addActor(movementInfo);
        stage.addActor(enemyInfo);
        stage.addActor(playerInfo);
        //stage.addActor(puzzlesInfo);
    }
    

    public float centreLable(float x, float y){
        float result = (x - y)/2;
        return result;
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
        //int y = EXIT_BUTTON_Y, x = EXIT_BUTTON_Y, z = 180, n = 180;
        batch.begin();
        //x, y, width, length
        //Exit();
        stage.draw();
        batch.end(); 
   }
   /*
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
   */
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
