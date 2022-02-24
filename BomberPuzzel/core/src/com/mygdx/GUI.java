package com.mygdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Abilities.Ability;

/**
 * Displays the GUI with text to the user
 * @author Alex Phillips, Kathryn Hurst
 */
public class GUI 
{
    public Stage stage;
    private FitViewport viewport;
    private Table table;
    private Label.LabelStyle labelStyle;

    private int levelCount;
    private int levelTimer;
    private float timeCount;
    private int playerLives;
    private String puzzleType;
    private float start = 0;

    boolean text = false;
    // Now we create our widgets. Our widgets will be labels, essentially text, that allow us to display Game Information
    private Label levelCountLabel;
    private Label timeLabel;
    private Label puzzelLabel;
    private Label livesLabel;
    private Label levelLabel;


    private Label displayLabel;
    private Label puzzelTypeLabel;
    private Label livesCountLabel;
    private Label timeCountLabel;

    public GUI()
    {
        levelTimer = 0;
        levelCount = 1;
        playerLives = 1;

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage

        table = new Table();
        table.top(); // Will put it at the top of our stage
        table.setFillParent(true);

        //white = new BitmapFont(Gdx.files.internal("font/white16.fnt"), false);
        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        timeLabel = new Label("TIME:", labelStyle);
        puzzelLabel = new Label("PUZZLE TYPE:", labelStyle);
        livesLabel = new Label("LIVES REMAINING:", labelStyle);
        levelLabel = new Label("CURRENT LEVEL:", labelStyle);

        levelCountLabel = new Label(String.format("%d", levelCount), labelStyle);
        timeCountLabel = new Label(String.format("%d", levelTimer), labelStyle);
        puzzelTypeLabel = new Label(puzzleType, labelStyle);
        livesCountLabel = new Label(String.format("%d", playerLives), labelStyle);
        displayLabel = new Label(String.format(""), labelStyle);

        table.add(levelLabel).expandX().pad(10); 
        table.add(timeLabel).expandX().padTop(10); // This expand X makes everything in the row share the row equally
        table.add(puzzelLabel).expandX().padTop(10);
        table.add(livesLabel).expandX().padTop(10);   

        table.row(); // THIS CREATES A NEW ROW
        table.add(levelCountLabel);
        table.add(timeCountLabel);
        table.add(puzzelTypeLabel);
        table.add(livesCountLabel);
       
        table.row();
        table.add();
        table.add();
        table.add(displayLabel).expandX().padTop(50);

        // add table to our stage
        stage.addActor(table);
    }

    public void update(int playerHealth, float elapsedTime)
    {      
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
        puzzelTypeLabel.setText(puzzle);
    }

    /**
     * Change the displayLabel value
     * @param display the new value
     */
    public void addTempLabel(String display)
    {
        displayLabel.setText("SEQUENCE: " + display);
        text = true;
        start = levelTimer;
    }

    /**
     * Clear displayLabel
     */
    public void removeTempLabel()
    {
        displayLabel.setText("");
        text = false;
        start = 0;
    }
}
