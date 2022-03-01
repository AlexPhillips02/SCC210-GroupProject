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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.mygdx.Driver;
import com.mygdx.GameController;
import com.mygdx.Enemies.BombCarrier;
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
    private final Texture enemyImage2;
    private final Texture RedButton;
    private final Texture BlueButton;
    private final Texture YellowButton;
    private final Texture GreenButton;
    
    public Stage stage;
    private FitViewport viewport;
    private Table table;
    private Label helpLabel;
    private OrthographicCamera camera;
   
  
    private Label information;  
    private Label control;
    private Label enemy;
    private Label player; 
    private Label puzzles; 
    private Label controlInfo;
    private Label enemyInfo;
    private Label playerInfo; 
    private Label puzzlesInfo; 
    private Label enemySpawn;
    private Label bombCarrier;
    private Label character; 
    private Label buttons;


    private TextButton button; 
    private TextButtonStyle textButtonStyle;
    

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
        playerImage = new Texture("Bomberman/BombermanDefault.png");
        enemyImage = new Texture("Alien enemy/Alien(single).png");
        enemyImage2 = new Texture("Enemies/Spider enemy/spider(single).png");
        RedButton  = new Texture("Buttons/RedButton.png") ;
        BlueButton  = new Texture("Buttons/BlueButton.png");
        YellowButton  = new Texture("Buttons/YellowButton.png");
        GreenButton  = new Texture("Buttons/GreenButton.png");
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        
        
        
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Text/GUI_Font.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 25;
        BitmapFont font = fontGenerator.generateFont(fontParameters);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        information = new Label("Information",labelStyle);        
        information.setPosition(centreLable(Driver.WIDTH, information.getWidth()), 680);
        fontParameters.size = 15;
        control = new Label("Control", labelStyle);
        enemy = new Label("Enemy", labelStyle);
        enemySpawn = new Label("Enemy Spawn", labelStyle);
        bombCarrier = new Label("bomb Carrier", labelStyle);
        player = new Label("player",labelStyle);
        character = new Label("character",labelStyle);
        puzzles = new Label("puzzles",labelStyle);
        buttons = new Label("Buttons",labelStyle);
    

        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        playerInfo = new Label("Player must avoid contact with enemy to survive that level. In order to complete the level player must complete a randomly selected puzzle. ", labelStyle);
        enemyInfo = new Label("There are two types of enemies. There is a bomb carrier and enemy spawns. Bomb carrier enemy follows player around and drops bomb near player, " 
         + "whilst enemy spawns reduce life of player with each contact with player", labelStyle);
        controlInfo = new Label("Use up keys to move up, use right keys to move right, left keys to move left, down keys to move down, space key to drop bomb, esc key to pause the game",labelStyle);
        puzzlesInfo = new Label("Player Must complete one of the follwing puzzles in order to complete the game. In Colour Match, there are a set of two coloured buttons that must be step on one " +
        "after the other to achieve a colour match (All buttons must be matched). In Colour Sequencing, a sequence will appear on the screen. The buttons must be stepped on in the sequence provided",labelStyle);
        button = new TextButton("Back To Main Screen", textButtonStyle);
        button.addListener(new InputListener(){
            /*
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Press a Button");
            }     
            */
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
                return true;
            }
            
        });

        control.setPosition(70,600);
        enemy.setPosition(70, 500);
        player.setPosition(70, 250);
        puzzles.setPosition(70,40);
        playerInfo.setPosition(400,250);
        enemyInfo.setPosition(400,500);
        controlInfo.setPosition(400, 600);
        puzzlesInfo.setPosition(400, 40);
        character.setPosition(625, 100);
        enemySpawn.setPosition(425, 320);
        bombCarrier.setPosition(750,320);
        buttons.setPosition(1125, 20);
        button.setPosition(10, 690);

        enemyInfo.setWidth(700); 
        playerInfo.setWidth(700); 
        controlInfo.setWidth(700);
        puzzlesInfo.setWidth(700);

        playerInfo.setWrap(true);
        enemyInfo.setWrap(true);
        controlInfo.setWrap(true);
        puzzlesInfo.setWrap(true);

        stage.addActor(information);
        stage.addActor(control);
        stage.addActor(enemy);
        stage.addActor(player);
        stage.addActor(puzzles);
        stage.addActor(information);
        stage.addActor(character);
        stage.addActor(controlInfo);
        stage.addActor(enemyInfo);
        stage.addActor(playerInfo);
        stage.addActor(enemySpawn);
        stage.addActor(bombCarrier);
        stage.addActor(puzzlesInfo);
        stage.addActor(buttons);
        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
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
        
        batch.begin();
        Exit();
        batch.draw(enemyImage, 500, 370, 100, 100);
        batch.draw(enemyImage2, 800, 370, 100, 100);
        batch.draw(playerImage, 650, 140, 100, 100);
        batch.draw(RedButton, 1135, 55, 25, 25);
        batch.draw(GreenButton, 1165, 55, 25, 25);
        batch.draw(YellowButton, 1195, 55, 25, 25);
        batch.draw(BlueButton, 1225, 55, 25, 25);
        stage.draw();
        batch.end(); 
   }
   
    private void Exit(){
        
        /*
        if(Gdx.input.getX() < BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            batch.draw(activeExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                soundController.playMusic(buttonClick);
               
            }
        }
        else {
            batch.draw(inActiveExitButton, BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        
        //if(Gdx.input.isButtonPressed(button))*/
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
