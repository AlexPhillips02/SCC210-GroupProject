package com.mygdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board 
{    
    Squares[][] gameSquares = new Squares[30][16];
    SpriteBatch batch;
    
    //Creates initial board
    public Board()
    {
        batch = new SpriteBatch();

        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                Tile tempTile;

                //Creates boarder around the outside
                if(x == 0 || y == 0 || x == (gameSquares.length - 1) || y == (gameSquares[x].length - 1))
                {
                    tempTile = new Wall();
                }
                else
                {
                    tempTile = new Path();
                }

                //Size of image (32 pixels) --> Amount to skip to draw next image otherwise they overlap
                int xSize = tempTile.getWidth(); 
                int ySize = tempTile.getHeight();

                gameSquares[x][y] = new Squares((x * xSize), (y * ySize), tempTile);
            }
        }
    }

    //Draws the squares
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
