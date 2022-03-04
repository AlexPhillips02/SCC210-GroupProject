package com.mygdx;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Abilities.Ability;
import com.mygdx.GameScreens.MainGameScreen;
import com.mygdx.Sound.SoundController;

/**
 * Displays the GUI with text to the user
 * @author Alex Phillips, Kathryn Hurst
 */
public class GUI 
{
    public Stage stage;
    public Stage pauseStage;
    private FitViewport viewport;
    private Table table;

    private int levelTimer;
    private int levelCount;
    private float timeCount;
    private int playerLives;
    private String puzzleType;
    private float start = 0;
    private String abilites = "";
    private String temp = "";

    private boolean text = false;
    // Now we create our widgets. Our widgets will be labels, essentially text, that allow us to display Game Information
    private Label levelCountLabel;
    private Label timeLabel;
    private Label livesLabel;
    private Label levelLabel;

    private Label puzzelNameLabel;
    private Label puzzelTypeLabel;
    private Label livesCountLabel;
    private Label timeCountLabel;

    private Label puzzelInfoLabel;
    private Label puzzelLabel;

    private Label gameOverLabel;
    private Label levelCompletionLabel;

    private Label activeAbilites;
    private Label Pause;

    private Texture activePlayButton;
    private Texture activeExitButton;
    private Texture activeRestartButton; 
    private Texture UnActivePlayButton;
    private Texture UnActiveExitButton;
    private Texture UnActiveRestartButton;


    private int PLAY_BUTTON_WIDTH = 200;
    private int PLAY_BUTTON_HEIGHT = 150;
    private int EXIT_BUTTON_WIDTH = 200;
    private int EXIT_BUTTON_HEIGHT = 150;
    private int RESTART_BUTTON_WIDTH = 300;
    private int PLAY_BUTTON_Y = 200;
    private int EXIT_BUTTON_Y = 200;
    private int RESTART_BUTTON_X = Driver.WIDTH / 2 - RESTART_BUTTON_WIDTH / 2;
    private int EXIT_BUTTON_X = (Driver.WIDTH / 4) * 3 - EXIT_BUTTON_WIDTH / 2;
    private int PLAY_BUTTON_X = (Driver.WIDTH / 4) - PLAY_BUTTON_WIDTH / 2;

    private SpriteBatch batch;
    private SoundController soundController;
    private Sound buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/Click (2).mp3"));
    /**
     * Constructor for the GUI around the Game Board, which displays all the player and game info.
     * @param levelCount gets the level number.
     */
    public GUI(int levelCount)
    {
        batch = new SpriteBatch();
        soundController = new SoundController();

        //declaring all the buttons with their corresponding pictures
        activePlayButton = new Texture("Screens/Play(Active).png");
        activeExitButton = new Texture("Screens/Exit(active).png");
        activeRestartButton = new Texture("Screens/Restart(active).png");
        UnActivePlayButton = new Texture("Screens/Play(UnactiveWHITE).png");
        UnActiveExitButton = new Texture("Screens/Exit(unactiveWHITE).png");
        UnActiveRestartButton = new Texture("Screens/Restart.png");

        this.levelCount = levelCount;
        levelTimer = 0;
        playerLives = 1;

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage
        pauseStage= new Stage(viewport);
        table = new Table();
        table.top(); // Will put it at the top of our stage
        table.setFillParent(true);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Text/GUI_Font.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 17;

        //Constructing the screen
        BitmapFont font = fontGenerator.generateFont(fontParameters);
        
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);

        fontParameters.size = 30;
        font = fontGenerator.generateFont(fontParameters);
        Label.LabelStyle puzzelStyle = new Label.LabelStyle(font, null);

        fontParameters.size = 50;
        font = fontGenerator.generateFont(fontParameters);
        Label.LabelStyle gameOverStyle = new Label.LabelStyle(font, null);
        
        fontParameters.size = 100;
        font = fontGenerator.generateFont(fontParameters);
        Label.LabelStyle pauseStyle = new Label.LabelStyle(font, null);

        Pause = new Label("paused",pauseStyle);
        Pause.setPosition(((Driver.WIDTH - Pause.getWidth())/2), 450);

        levelLabel = new Label("CURRENT LEVEL:", labelStyle);
        timeLabel = new Label("TIME:", labelStyle);
        puzzelTypeLabel = new Label("PUZZLE TYPE:", labelStyle);
        livesLabel = new Label("LIVES REMAINING:", labelStyle);

        levelCountLabel = new Label(String.format("%d", levelCount), labelStyle);
        timeCountLabel = new Label(String.format("%d", levelTimer), labelStyle);
        puzzelNameLabel = new Label(puzzleType, labelStyle);
        livesCountLabel = new Label(String.format("%d", playerLives), labelStyle);

        puzzelInfoLabel = new Label(String.format(""), puzzelStyle);
        puzzelLabel = new Label(String.format(""), puzzelStyle);

        gameOverLabel = new Label(String.format(""), gameOverStyle);
        levelCompletionLabel = new Label(String.format(""), gameOverStyle);

        table.add(levelLabel).expandX().padTop(10); 
        table.add(timeLabel).expandX().padTop(10); // This expand X makes everything in the row share the row equally
        table.add(puzzelTypeLabel).expandX().padTop(10);
        table.add(livesLabel).expandX().padTop(10);   

        table.row(); // THIS CREATES A NEW ROW
        table.add(levelCountLabel);
        table.add(timeCountLabel);
        table.add(puzzelNameLabel);
        table.add(livesCountLabel);
       
        table.row();
        table.add(puzzelInfoLabel).colspan(4).padTop(10); //Colspan means it will combine the 4 columns above into one column for this row

        table.row();
        table.add(puzzelLabel).colspan(4).padTop(10);

        table.row();
        table.add(gameOverLabel).colspan(4);

        table.row();
        table.add(levelCompletionLabel).colspan(4);
        

        //Fills the rest of the table (Goes to the bottom)
        table.row();
        table.add().fillY().expandY();

        activeAbilites = new Label(String.format("Active Abilites: %s", abilites), labelStyle);
        activeAbilites.setAlignment(Align.left);
        table.row();
        table.add(activeAbilites).colspan(4).padBottom(10).padRight((int)(Gdx.graphics.getWidth() / 1.35)).width(200);

        // add table to our stage
        stage.addActor(table);
        pauseStage.addActor(Pause);
    }

    /**
     * Updating the board with the active abilities
     * @param playerHealth variable 1 on the GUI
     * @param elapsedTime the elapsed time 
     * @param activeAbilities variable 2 on the GUI
     */
    public void update(int playerHealth, float elapsedTime, ArrayList<Ability> activeAbilities)
    {      
        abilites = "";

        for (int i = 0; i < activeAbilities.size(); i++) 
        {
            String name = activeAbilities.get(i).getName();

            if (!abilites.contains(name)) 
            {
                if (!abilites.equals("")) 
                {
                    name = (", " + name);
                }
                abilites = abilites.concat(name); 
            }
        }

        activeAbilites.setText(String.format("Active Abilites: %s", abilites));


        if (playerHealth != playerLives) 
        {
            playerLives = playerHealth;
            livesCountLabel.setText(String.format("%d", playerLives));
        }

        timeCount += elapsedTime;

        if(timeCount >= 1)
        {
            levelTimer += (int)(timeCount);
            timeCountLabel.setText(String.format("%d", levelTimer));
            timeCount = 0;
        }

        if(levelTimer >= start + 5 && text == true)
        {
            removeTempLabel();
        }
    }

    /**
     * Set the puzzle label
     * @param puzzle is the puzzle type
     */
    public void setPuzzle(String puzzle)
    {
        puzzelNameLabel.setText(puzzle);
    }

    /**
     * Makes it coloured
     * @param puzzle puzzle type
     */
    public void setPuzzleInfoBad(String puzzle)
    {
        puzzelInfoLabel.setColor(Color.RED);
        puzzelInfoLabel.setText(puzzle);
    }

    /**
     * Makes it coloured
     * @param puzzle puzzle type
     */
    public void setPuzzleInfoGood(String puzzle)
    {
        puzzelInfoLabel.setColor(Color.LIME);
        puzzelInfoLabel.setText(puzzle);
    }

    /**
     * Change the displayLabel value
     * @param display the new value
     */
    public void addTempLabel(String display)
    {
        puzzelLabel.setText(display);
        text = true;
        start = levelTimer;   
    }

    /**
     * Remove text from the puzzelLabel
     * @return the text that was in the label
     */
    public String hideTempLabel()
    {
        String temp = (puzzelLabel.getText()).toString();
        puzzelLabel.setText("");
        return temp;
    }
    
    /**
     * Game Over Label
     */
    public void gameOverLabel()
    {
        gameOverLabel.setColor(Color.RED);
        gameOverLabel.setText("GAMEOVER");
    }

    /**
     * Label for completed level
     */
    public void levelCompletionLabel()
    {
        levelCompletionLabel.setColor(Color.RED);
        levelCompletionLabel.setText("Completed " + (levelCount - 1) + " Levels");
    }

    /**
     * Label for every puzzle completed
     */
    public void puzzelCompleted()
    {
        gameOverLabel.setColor(Color.GREEN);
        gameOverLabel.setText("PUZZLE COMPLETED");
    }

    /**
     * Time for puzzle completion
     */
    public void puzzelCompletedTime()
    {
        levelCompletionLabel.setColor(Color.GREEN);
        levelCompletionLabel.setText("Completed In " + levelTimer + " Seconds");
    }

    /**
     * Clear displayLabel
     */
    public void removeTempLabel()
    {
        puzzelLabel.setText("");
        puzzelInfoLabel.setText("");
        text = false;
        start = 0;
    }

    /**
     * Shows the text in the puzzle label without altering the timer
     * @param temp text to set in label
     */
    public void showTempLabel(String temp)
    {
        puzzelLabel.setText(temp);
    }

    /**
     * Label for every level started
     */
    public void startGame() 
    {
        gameOverLabel.setColor(Color.GREEN);
        gameOverLabel.setText("LEVEL START:");
    }

    /**
     * Removing countdown
     */
    public void removeCountDown() 
    {
        gameOverLabel.setText("");
        levelCompletionLabel.setText("");
    }

    /**
     * Game Countdown
     * @param countDown the number that it starts from
     */
    public void gameCountDown(int countDown) 
    {
        levelCompletionLabel.setColor(Color.GREEN);
        levelCompletionLabel.setText("" + countDown);
    }

    /**
     * Setting player health for the label
     * @param playerHealth how many lives does the player have
     */
    public void setHealth(int playerHealth) 
    {
        if (playerHealth != playerLives) 
        {
            playerLives = playerHealth;
            livesCountLabel.setText(String.format("%d", playerLives));
        }
    }

    /**
     * Pause labels
     * @param controller Game controller
     */
    public void Pause(GameController controller)
    {
        if((puzzelLabel.getText()).toString() != "")
        {
            temp = hideTempLabel();
        }

        batch.begin();
        batch.draw(UnActiveExitButton, EXIT_BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        batch.draw(UnActivePlayButton, PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        batch.draw(UnActiveRestartButton, RESTART_BUTTON_X, PLAY_BUTTON_Y, RESTART_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        pauseStage.draw();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            controller.LoadMenu();
        }

        if(Gdx.input.getX() < PLAY_BUTTON_X + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)
        {
            batch.draw(activePlayButton, PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched())
            {
                soundController.playMusic(buttonClick);
                showTempLabel(temp);
                controller.setPaused(false);
            }
        }

        if(Gdx.input.getX() < RESTART_BUTTON_X + RESTART_BUTTON_WIDTH && Gdx.input.getX() > RESTART_BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y)
        {
            batch.draw(activeRestartButton, RESTART_BUTTON_X, PLAY_BUTTON_Y, RESTART_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                    soundController.playMusic(buttonClick);
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new MainGameScreen());
                }
            }

        if(Gdx.input.getX() < EXIT_BUTTON_X + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_X && Driver.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Driver.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y)
        {
            batch.draw(activeExitButton, EXIT_BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            
            if (Gdx.input.isTouched())
            { 
                soundController.playMusic(buttonClick);
                controller.LoadMenu();
            }
        }
        batch.end();
    }

    public void dispose()
    {
        /*
        activePlayButton.dispose();
        activeExitButton.dispose();
        stage.dispose();
        */
        batch.dispose();
    }
  
    
}