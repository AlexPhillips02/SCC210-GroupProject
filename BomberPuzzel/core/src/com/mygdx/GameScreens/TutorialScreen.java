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
import com.mygdx.Sound.SoundController;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;

/**
 * @author Victor Boateng
 * The help menu screen which supports new players and gives info on the game
 */
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
    private Label walls;
    private Label wallsInfo;
    private Label abilities;
    private Label abilitesInfo;

    private TextButton button; 
    private TextButtonStyle textButtonStyle;
    
    private SoundController soundController;
    private Sound buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/ButtonPress.mp3"));
    private boolean hasDisposed = false;

    /**
     * Constructor for the Tutorial Screen which introduces players to how the game is played.
     */
    public TutorialScreen()
    {
        batch = new SpriteBatch();
        soundController = new SoundController();
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

        //declaring all the labels
        information = new Label("Information",labelStyle);        
        information.setPosition(centreLable(Driver.WIDTH, information.getWidth()), 680);
        fontParameters.size = 15;
        control = new Label("Control", labelStyle);
        enemy = new Label("Enemy", labelStyle);
        enemySpawn = new Label("Creeps", labelStyle);
        bombCarrier = new Label("Bomb\nCarrier", labelStyle);
        player = new Label("player",labelStyle);
        character = new Label("Player",labelStyle);
        puzzles = new Label("puzzles",labelStyle);
        buttons = new Label("Buttons",labelStyle);
        walls = new Label("Walls", labelStyle);
        abilities = new Label("Abilites", labelStyle);
    
        //main text
        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        abilitesInfo = new Label("Thoughout the level random abilities can spawn. These will have immediate positive or negative effects on the player. Speed increase, Speed decrease, Health increase (+1 life), Increase bomb range (+2), Increase bomb inventory (+1).\n" +
        "Once collected, most will be active for a short period of time before exipring.", labelStyle);
        wallsInfo = new Label("There are 3 types of walls: Soft, Reinforced and Indestructable. Soft and reinforced walls can loose health by placing a bomb in range of the wall." + 
        " Reducing a walls health to 0 will destroy it. Soft walls have an initial health of 1, and reinforced walls have an initial health of 2.", labelStyle);
        playerInfo = new Label("The player must avoid contact with enemies to survive that level. In order to progress to the next level the player must complete a randomly selected puzzle. Initially the player will have an inventory size of 2 and bombs will expload 3 tiles in each " +
        "direction (this can be altered with abilities).", labelStyle);
        enemyInfo = new Label("There are two types of enemies: Creeps will path randomly through the level and inflict damage on the player if touched. Bomb carriers track the player when they approch too close, " 
         + "reducing the life of the player and also placing a bomb", labelStyle);
        controlInfo = new Label("Use the arrow keys to move, space key to drop a bomb and the esc key to pause the game.",labelStyle);
        puzzlesInfo = new Label("The player must complete a puzzle in order to complete the level. In pattern match, the player must match the buttons by colour, by standing on the same colour consecutively. The level is completed when all buttons are matched." +
        " In Colour Sequencing, a sequence will appear on the screen. The buttons must be stepped on in the sequence provided. An incorrect button press will cause the sequence to reset.",labelStyle);
        button = new TextButton("Back To Main Screen", textButtonStyle);
        button.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                soundController.playMusic(buttonClick);
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
                return true;
            }
            
        });

        //setting positions
        control.setPosition(70,600);
        enemy.setPosition(70, 500);
        player.setPosition(70, 300);
        walls.setPosition(70, 400);
        wallsInfo.setPosition(250, 400);
        puzzles.setPosition(70,80);
        playerInfo.setPosition(250,300);
        enemyInfo.setPosition(250,500);
        controlInfo.setPosition(250, 600);
        puzzlesInfo.setPosition(250, 80);
        character.setPosition(1125, 200);
        enemySpawn.setPosition(1125, 575);
        bombCarrier.setPosition(1125, 375);
        buttons.setPosition(1115, 60);
        button.setPosition(10, 690);
        abilities.setPosition(70, 180);
        abilitesInfo.setPosition(250, 180);

        //setting parameters
        wallsInfo.setWidth(700);
        enemyInfo.setWidth(700); 
        playerInfo.setWidth(700); 
        controlInfo.setWidth(700);
        puzzlesInfo.setWidth(700);
        abilitesInfo.setWidth(700);

        playerInfo.setWrap(true);
        enemyInfo.setWrap(true);
        controlInfo.setWrap(true);
        puzzlesInfo.setWrap(true);
        wallsInfo.setWrap(true);
        abilitesInfo.setWrap(true);

        //adding them to the stage
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
        stage.addActor(walls);
        stage.addActor(wallsInfo);
        stage.addActor(abilities);
        stage.addActor(abilitesInfo);

        Gdx.input.setInputProcessor(stage);
    }
    
    /**
     * Method for centering labels
     * @param x the width of the window
     * @param y the width of the object to draw
     * @return returns the X position to place a drawing at the centre of screen
     */
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
        batch.draw(enemyImage, 1140, 600, 100, 100);
        batch.draw(enemyImage2, 1140, 425, 100, 100);
        batch.draw(playerImage, 1150, 225, 70, 110);
        batch.draw(RedButton, 1125, 100, 25, 25);
        batch.draw(GreenButton, 1155, 100, 25, 25);
        batch.draw(YellowButton, 1185, 100, 25, 25);
        batch.draw(BlueButton, 1215, 100, 25, 25);
        //batch.draw(texture, x, y);
        stage.draw();
        batch.end(); 
   }
   
   /**
    * @param width size of width
    * @param height size of height
    */
   @Override
   public void resize (int width, int height) 
   {}

   @Override
   public void show () 
   {}

   @Override
   public void hide () 
   {}

   @Override
   public void pause () 
   {}

   @Override
   public void resume () 
   {}

    @Override
    public void dispose()
    {
        if (hasDisposed == false) 
        {
            batch.dispose();   
            hasDisposed = true;
        }
    }
}