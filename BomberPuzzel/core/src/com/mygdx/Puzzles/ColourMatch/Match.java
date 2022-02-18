package com.mygdx.Puzzles.ColourMatch;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.GUI;
import com.mygdx.Board.*;
import com.mygdx.Puzzles.ColourButton;
import com.mygdx.Puzzles.Puzzle;


/*
In the colour coding mini-game, there will be various colours hidden within the walls. 
The player must walk on two of the same colour in order to complete the task. 
When all colour combinations have completed the mini-game will be won. 
If the Player gets into contact with another colour before completing a previous combination, 
the colours will reset and the Player will have to start over.
*/

public class Match extends Puzzle
{
    private ArrayList<ColourButton> buttons = new ArrayList<>();
    
    public ColourButton prev;
    public ColourButton current;

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
            temp = new ColourButton("Yellow", "core/assets/Buttons/YellowButton.png", board, x, y);
        }
        else if(i < 4)
        {
            temp = new ColourButton("Blue", "core/assets/Buttons/BlueButton.png", board, x, y);
        }
        else if(i < 6)
        {
            temp = new ColourButton("Green", "core/assets/Buttons/GreenButton.png", board, x, y);
        }
        else
        {
            temp = new ColourButton("Red", "core/assets/Buttons/RedButton.png", board, x, y);
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

                if(buttons.size() <= 0) 
                {
                    winStatus = true;
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