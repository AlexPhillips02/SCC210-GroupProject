package com.mygdx.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.TileTypes.Path;
import com.mygdx.TileTypes.ReinforcedWall;
import com.mygdx.TileTypes.SoftWall;
import com.mygdx.TileTypes.UnbreakableWall;

/**
 * @author Alex Phillips
 * Holds the squares to create the board
 */
public class Board 
{    
    Squares[][] gameSquares;
    int xLength;
    int yLength;
    private float elapsedTime = 0f;
    
    /**
     * Creates initial board
     * @param xLength How many squares along the x axis
     * @param yLength How many squares along the y axis
     */
    public Board(int xLength, int yLength, float percentageOfDestructableWalls)
    {
        this.xLength = xLength;
        this.yLength = yLength;
        gameSquares = new Squares[xLength][yLength];
        createBasicLevelLayout();
        addDestructableWalls(percentageOfDestructableWalls);
        addCenterUnbreakableWalls();
        createStartingSquaresForPlayer();
    }

    /**
     * Places a boarder around the outside of the board
     * Fills the middle with path blocks
     */
    public void createBasicLevelLayout()
    {
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

                //Size of image (64 pixels) --> Amount to skip to draw next image otherwise they overlap
                int xSize = tempTile.getWidth(); 
                int ySize = tempTile.getHeight();
                //Creates squares initially with coordinates (based on window coords not amount of tiles)
                gameSquares[x][y] = new Squares((x * xSize), (y * ySize), tempTile);
            }
        }
    }

    /**
     * Places destructable walls into the gameboard
     * @param percentage Rough percentage of destructable blocks to be placed (Is rough estimate as random placement)
     */
    public void addDestructableWalls(float percentage)
    {
        //totalUsableBlocks = size of the map - the walls around the outside
        int totalUseableBlocks = (xLength - 2) * (yLength - 2);
        float totalSoftBlocks = (percentage / 100) * totalUseableBlocks;
        
        int xPosition = 0;
        int yPosition = 0;

        for (int i = 0; i < totalSoftBlocks; i++) 
        {
            xPosition = (int)(Math.random() * (xLength - 2)  + 1);
            yPosition = (int)(Math.random() * (yLength - 2)  + 1);

            //33% of breakable walls will be reinforced
            if(i % 3 == 0)
            {
                gameSquares[xPosition][yPosition].setTile(new SoftWall());
            }
            else
            {
                gameSquares[xPosition][yPosition].setTile(new ReinforcedWall(2));
            }
        }
    }

    /**
     * Adds the unbreakable walls into the middle of the map
     */
    public void addCenterUnbreakableWalls()
    {
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                if (x % 2 == 0 && y % 2 == 0) 
                {
                    gameSquares[x][y].setTile(new UnbreakableWall());    
                }
            }
        }
    }

    /**
     * Creates area with no walls around the player spawn so they are guarranteed to be able to move
     */
    private void createStartingSquaresForPlayer() 
    {
        for (int x = 1; x <= 2; x++) 
        {
            for (int y = 1; y <= 2; y++) 
            {
                gameSquares[x][y].setTile(new Path());
            }
        }
    }

    /**
     * Draws the board squares
     * @param batch Spritebatch to output images (Controlled within driver)
     */
    public void Draw(SpriteBatch batch)
    {
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                Squares current = gameSquares[x][y].getSquare();

                if (current.getAnimation() != null) 
                {
                    batch.draw(current.getTexture(), current.getX(), current.getY());
                    batch.draw(current.getAnimation().getKeyFrame(elapsedTime, true), current.getX(), current.getY());
                    elapsedTime += Gdx.graphics.getDeltaTime();
                }
                else
                {
                    batch.draw(current.getTexture(), current.getX(), current.getY());
                }
            }
        }
    }

    /**
     * Gets the gamesquare at position
     * @param x X position
     * @param y Y position
     * @return Gamesquare
     */
    public Squares getGameSquare(int x, int y) 
    {
        return gameSquares[x][y];
    }

    public int getXLength()
    {
        return this.xLength;
    }

    public int getYLength()
    {
        return this.yLength;
    }

    public int getTileSize()
    {
        return gameSquares[0][0].getTile().getHeight();
    }
}
