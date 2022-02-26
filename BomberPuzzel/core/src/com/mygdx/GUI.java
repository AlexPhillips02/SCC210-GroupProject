package com.mygdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
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

    private int levelTimer;
    private float timeCount;
    private int playerLives;
    private String puzzleType;
    private float start = 0;
    private String abilites = "";

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

    private Label puzzelLabel;
    private Label activeAbilites;

    public GUI(int levelCount)
    {
        levelTimer = 0;
        playerLives = 1;

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport); // We must create order by creating a table in our stage

        table = new Table();
        table.top(); // Will put it at the top of our stage
        table.setFillParent(true);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Text/GUI_Font.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 17;

        BitmapFont font = fontGenerator.generateFont(fontParameters);
        
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label.LabelStyle puzzelStyle = new Label.LabelStyle(font, Color.RED);
        

        levelLabel = new Label("CURRENT LEVEL:", labelStyle);
        timeLabel = new Label("TIME:", labelStyle);
        puzzelTypeLabel = new Label("PUZZLE TYPE:", labelStyle);
        livesLabel = new Label("LIVES REMAINING:", labelStyle);

        levelCountLabel = new Label(String.format("%d", levelCount), labelStyle);
        timeCountLabel = new Label(String.format("%d", levelTimer), labelStyle);
        puzzelNameLabel = new Label(puzzleType, labelStyle);
        livesCountLabel = new Label(String.format("%d", playerLives), labelStyle);

        puzzelLabel = new Label(String.format(""), puzzelStyle);

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
        table.add(puzzelLabel).colspan(4).padTop(10); //Colspan means it will combine the 4 columns above into one column for this row

        table.row();
        table.add(puzzelLabel).colspan(4).padTop(10);
        
        table.row();
        table.add().fillY().expandY();

        activeAbilites = new Label(String.format("Active Abilites: %s", abilites), labelStyle);
        activeAbilites.setAlignment(Align.left);
        table.row();
        table.add(activeAbilites).colspan(4).padBottom(10).padRight((int)(Gdx.graphics.getWidth() / 1.35)).width(200);

        // add table to our stage
        stage.addActor(table);
    }

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
     * Clear displayLabel
     */
    public void removeTempLabel()
    {
        puzzelLabel.setText("");
        text = false;
        start = 0;
    }
}
