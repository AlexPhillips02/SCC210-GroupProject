package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board 
{    
    Squares[][] gameSquares = new Squares[10][10];
    SpriteBatch batch;
    
    public Board()
    {
        batch = new SpriteBatch();
        int xSize = 64; 
        int ySize = 64;

        Texture tempImage = new Texture("Dirt.jpg");
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                gameSquares[x][y] = new Squares((x * xSize), (y * ySize), tempImage);
            }
        }
    }

    public void Draw()
    {
        batch.begin();
		
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                Squares current = gameSquares[x][y].getSquare();
                batch.draw(current.getTexture(), current.getX(), current.getY());
            }
        }

        batch.end();
    }
}
