package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Displays the GUI with text to the user
 * @author Alex Phillips
 */
public class GUI 
{
    public Stage stage;
    private FitViewport viewport;
    private Table table;
    private Label.LabelStyle labelStyle;

    private int levelTimer;
    private float timeCount;
    private int playerLives;
    private String puzzleType;
    // Now we create our widgets. Our widgets will be labels, essentially text, that allow us to display Game Information
    private Label timeLabel;
    private Label puzzelLabel;
    private Label livesLabel;

    private Label puzzelTypeLabel;
    private Label livesCountLabel;
    private Label timeCountLabel;

    public GUI()
    {
        levelTimer = 0;
        playerLives = 1;
        puzzleType = "BUTTON PRESSY WESSY";

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

        timeCountLabel = new Label(String.format("%d", levelTimer), labelStyle);
        puzzelTypeLabel = new Label(puzzleType, labelStyle);
        livesCountLabel = new Label(String.format("%d", playerLives), labelStyle);

        table.add(timeLabel).expandX().padTop(10); // This expand X makes everything in the row share the row equally
        table.add(puzzelLabel).expandX().padTop(10);
        table.add(livesLabel).expandX().padTop(10);

        table.row(); // THIS CREATES A NEW ROW
        table.add(timeCountLabel).expandX();
        table.add(puzzelTypeLabel).expandX();
        table.add(livesCountLabel).expandX();

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
    }
}
