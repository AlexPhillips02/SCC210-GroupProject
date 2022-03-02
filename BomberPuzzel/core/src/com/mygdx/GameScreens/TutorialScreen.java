package com.mygdx.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.Driver;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;

public class TutorialScreen implements Screen
{
    private SpriteBatch batch;
    private Texture playerImage;
    private Texture enemyImage;
    private Texture enemyImage2;
    private Texture RedButton;
    private Texture BlueButton;
    private Texture YellowButton;
    private Texture GreenButton;
    
    public Stage stage;
    private FitViewport viewport;
  
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
        //soundController = new SoundController();
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
        enemySpawn = new Label("Creeps", labelStyle);
        bombCarrier = new Label("bomb Carrier", labelStyle);
        player = new Label("player",labelStyle);
        character = new Label("character",labelStyle);
        puzzles = new Label("puzzles",labelStyle);
        buttons = new Label("Buttons",labelStyle);
    

        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        playerInfo = new Label("Player must avoid contact with enemy to survive that level. In order to complete the level player must complete a randomly selected puzzle. ", labelStyle);
        enemyInfo = new Label("There are two types of enemies: Creeps will path randomly through the level and inflict damage on the player if touched. Bomb carriers track the player when they approch too close," 
         + "reducing the life of the player and also placing a bomb", labelStyle);
        controlInfo = new Label("Use the arrow keys to move, space key to drop a bomb and the esc key to pause the game. Dropping bombs next to destructable wall will lower its health. Soft walls have a health of 1, reinforced walls have a health of 2.",labelStyle);
        puzzlesInfo = new Label("The player must complete a puzzle in order to complete the level. In pattern match the player must match the buttons by colour, by standing on consequitive colours. The level is completed when all buttons are matched." +
        "In Colour Sequencing, a sequence will appear on the screen. The buttons must be stepped on in the sequence provided. An incorrect button press will cause the sequence to reset.",labelStyle);
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
        playerInfo.setPosition(250,250);
        enemyInfo.setPosition(250,500);
        controlInfo.setPosition(250, 600);
        puzzlesInfo.setPosition(250, 40);
        character.setPosition(625, 100);
        enemySpawn.setPosition(450, 320);
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
   public void hide () 
   {}

   @Override
   public void pause () {
   }

   @Override
   public void resume () {
   }

    @Override
    public void dispose()
    {
        System.out.println("DISPOSING tutorial screen");
        /*
        playerImage.dispose();
        enemyImage.dispose();
        enemyImage2.dispose();
        RedButton.dispose();
        BlueButton.dispose();
        YellowButton.dispose();
        GreenButton.dispose();
    
        stage.dispose();
        */
        batch.dispose();
    }
}