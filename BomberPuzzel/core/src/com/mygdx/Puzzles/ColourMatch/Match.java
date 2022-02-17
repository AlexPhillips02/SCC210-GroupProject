package com.mygdx.Puzzles.ColourMatch;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.GUI;
import com.mygdx.Board.*;
import com.mygdx.Player.Player;
import com.mygdx.Puzzles.Puzzle;
import com.mygdx.TileTypes.*;


/*
In the colour coding mini-game, there will be various colours hidden within the walls. 
The player must walk on two of the same colour in order to complete the task. 
When all colour combinations have completed the mini-game will be won. 
If the Player gets into contact with another colour before completing a previous combination, 
the colours will reset and the Player will have to start over.
*/

public class Match extends Puzzle
{
    ColourTiles selected_tile;
    ArrayList<Rectangle> colourtileRectangles = new ArrayList<Rectangle>();

    private static ArrayList<ColourTiles> colourTiles = new ArrayList<ColourTiles>();
    public static int count = 0; 
    public static boolean hasMatched = false;
    public static ColourTiles prev;
    public static ColourTiles current;

    public Match(GUI gui, Board board, ArrayList<Rectangle> colourtileRectangles, ArrayList<ColourTiles> cTiles){
        super(gui, board);
        this.colourtileRectangles = colourtileRectangles;
        this.colourTiles = cTiles;
    }

    public void addColourTiles(Squares pathSquare, int colour){
        int x = pathSquare.getX();
        int y = pathSquare.getY();

        selected_tile = new ColourTiles(colour, board, x, y);
        colourTiles.add(selected_tile);
        colourtileRectangles.add(selected_tile.getCollisionRectangle());
    }

    public void Draw(SpriteBatch batch){
        for(int i = 0; i<colourTiles.size(); i++)
        {
            colourTiles.get(i).Draw(batch);
        }    
    }

    public static void setCurrent(ColourTiles current_tile){
        prev = current;
        current = current_tile;
        
        if(prev != null)
        {
            System.out.println("pevious Coloured Tile number =" + prev.getselectedColourNumber());
            System.out.println("current Coloured TIle number =" + current.getselectedColourNumber());
            if(isMatch(prev, current) == true) 
            {
                colourTiles.remove(prev);
                colourTiles.remove(current);
            } else {
                System.out.println("NO MATCH");
            }
        }     
    }


    public static boolean isMatch(ColourTiles prev, ColourTiles current) 
    {
        if(prev.getselectedColourNumber() == current.getselectedColourNumber())
        {
            return true;
        } else {
            return false; 
        }
    }
}