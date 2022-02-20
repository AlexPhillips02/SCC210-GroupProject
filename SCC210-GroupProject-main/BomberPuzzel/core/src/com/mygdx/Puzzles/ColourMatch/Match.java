package com.mygdx.Puzzles.ColourMatch;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.*;
import com.mygdx.Puzzles.Buttons.BlueButton;
import com.mygdx.Puzzles.Buttons.ColourButton;
import com.mygdx.Puzzles.Buttons.GreenButton;
import com.mygdx.Puzzles.Buttons.RedButton;
import com.mygdx.Puzzles.Buttons.YellowButton;
import com.mygdx.Puzzles.Puzzle;



/**@author VictorBoateng, alex philips 
In the colour coding mini-game, there will be various colours walls. 
The player must walk on two of all the same colour paths in order to complete the task. 
When all colour combinations have completed the mini-game will be won. 
If the Player gets into contact with another colour before completing a previous combination, 
the colours will reset and the Player will have to start over.
*/



public class Match extends Puzzle
{
    private ArrayList<ColourButton> buttons = new ArrayList<>();
    
    public ColourButton prev;
    public ColourButton current;
    private String match;

    public Match(GUI gui, Board board){
        super(gui, board);
    }

    public void createGame()
    {
        for(int i = 0; i< 8; i++)
        {
            Squares pathSquare = board.getRandomPath();
            addColourTiles(pathSquare, i);
        }
    }

    public void addColourTiles(Squares pathSquare, int i)
    {
        int x = pathSquare.getX();
        int y = pathSquare.getY();

        ColourButton temp;

        if(i < 2)
        {
            temp = new YellowButton(board, x, y);
        }
        else if(i < 4)
        {
            temp = new BlueButton(board, x, y);
        }
        else if(i < 6)
        {
            temp = new GreenButton(board, x, y);
        }
        else
        {
            temp = new RedButton(board, x, y);
        }
        
        buttons.add(temp);
    }

    public void Draw(SpriteBatch batch)
    {
        for(int i = 0; i< buttons.size(); i++)
        {
            buttons.get(i).Draw(batch);
        }    
    }

    public void setCurrent(ColourButton newCurrent)
    {
        prev = current;
        current = newCurrent;
        current.setActive(false);
        
        if(prev != null)
        {
            if(prev.getColour().equals(current.getColour())) 
            {
                buttons.remove(prev);
                buttons.remove(current);

                match = prev.getColour() + " Button has matched";
                gui.addTempLabel(match);

                if(buttons.size() <= 0) 
                {
                    winStatus = true;
                    gui.addTempLabel("Game over");
                }
            } 
            else 
            {
                prev.setActive(true);
            }
        }     
    }

    public ArrayList<ColourButton> getButtons()
    {
        return buttons;
    }
}