package com.mygdx.Board;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.TileTypes.Path;
import com.mygdx.TileTypes.UnbreakableWall;

public class Board 
{    
    Squares[][] gameSquares;
    
    //Creates initial board
    public Board(int xLength, int yLength)
    {
        gameSquares = new Squares[xLength][yLength];

        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                Tile tempTile;

                //Creates border around the outside
                if(x == 0 || y == 0 || x == (gameSquares.length - 1) || y == (gameSquares[x].length - 1))
                {
                    tempTile = new UnbreakableWall();
                }
                else
                {
                    tempTile = new Path();
                }

                //Size of image (32 pixels) --> Amount to skip to draw next image otherwise they overlap
                int xSize = tempTile.getWidth(); 
                int ySize = tempTile.getHeight();
                //Creates squares initially with coordinates (based on window coords not amount of tiles)
                gameSquares[x][y] = new Squares((x * xSize), (y * ySize), tempTile);

                if (x % 2 == 0 && y % 2 == 0) 
                {
                    gameSquares[x][y].setTile(new UnbreakableWall());    
                }
            }
        }
    }

    //Draws the squares
    public void Draw(SpriteBatch batch)
    {
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                Squares current = gameSquares[x][y].getSquare();
                batch.draw(current.getTexture(), current.getX(), current.getY());
            }
        }
    }

    public Squares getGameSquare(int x, int y) 
    {
        return gameSquares[x][y];
    }
}
