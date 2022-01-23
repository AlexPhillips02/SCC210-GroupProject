package com.mygdx.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Abilities.Bomb;
import com.mygdx.TileTypes.BreakableWall;
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

                    if(current.getBomb() != null)
                    {
                        Bomb bomb = current.getBomb();
                        float elapsedTime = bomb.getElapsedTime();
                        batch.draw(current.getAnimation().getKeyFrame(elapsedTime, false), current.getX(), current.getY());

                        bomb.setElapsedTime(elapsedTime += Gdx.graphics.getDeltaTime()); 

                        if (current.getBomb().getAnimation().isAnimationFinished(elapsedTime)) 
                        {
                            createExplosion(current, bomb);
                            bomb.expload();
                        }

                        bomb.setElapsedTime(elapsedTime += Gdx.graphics.getDeltaTime()); 
                    }
                    else
                    {
                        batch.draw(current.getAnimation().getKeyFrame(0, false), current.getX(), current.getY());
                    }
                }
                else
                {
                    batch.draw(current.getTexture(), current.getX(), current.getY());
                }
            }
        }
    }

    /**
     * Calculates which blocks will be effected by the explosion
     * @param center Center of the explosion (Where the bomb was placed)
     * @param size Size of the explosion (How far in each direction)
     */
    public void createExplosion(Squares center, Bomb bomb)
    {
        Squares current = center;
        int size = bomb.getExplosionRange();
        center.setAnimation(bomb.getCenterAnimation());
        System.out.println("Center X: " + (current.getX() / current.getTile().getWidth()) + " Y: " + (current.getY() / current.getTile().getHeight()));
        System.out.println("Affected blocks");

        if (size > 0) 
        {
            for (int i = 0; i < 4; i++) 
            {
                Squares next = GetNext(current, i);
                SetExplosionPath(next, size - 1, i, bomb);
            }   
        }
    }

    /**
     * Recursive function that calulcates the affected blocks
     * @param current Current square
     * @param size Amount left to travel in that direction
     * @param direction Direction of travel (0 = left, 1 = right and so on)
     */
    public void SetExplosionPath(Squares current, int size, int direction, Bomb bomb)
    {
        if (current.getTile() instanceof Path && size >= 0) 
        {
            System.out.println("X: " + (current.getX() / current.getTile().getWidth()) + " Y: " + (current.getY() / current.getTile().getHeight()));
            if (direction == 0 || direction == 1) 
            {
                current.createExplosion(bomb.getExplosionLinesHorizontalAnimation());
            }
            else
            {
                current.createExplosion(bomb.getExplosionLinesVerticalAnimation());
            }
        
            size--;
            Squares next = GetNext(current, direction);
            SetExplosionPath(next, size, direction, bomb);
        }
        else
        {
            //Either bomb has ran out of range, or has hit something that isnt a path
            if (size >= 0 && current.getTile() instanceof BreakableWall) 
            {
                //If the wall is breakable reduce the health
                ((BreakableWall)current.getTile()).reduceHealth();

                //If the wall is broken, replace with a path block
                if (((BreakableWall)current.getTile()).getHealth() <= 0 ) 
                {
                    current.setTile(new Path());
                }
            }

            return;
        }
    }

    /**
     * Gets the next square in that direction
     */
    public Squares GetNext(Squares current, int n)
    {
        int x = 0;
        int y = 0;

        if (n == 0) {x = -1;} //Left
		if (n == 1) {x = 1;} //Right
		if (n == 2) {y = 1;} //Down
		if (n == 3) {y = -1;} //Up

        Squares next = (Squares)(getGameSquare(((current.getX() / current.getTile().getWidth()) + x), ((current.getY() / current.getTile().getHeight()) + y)));
        return next;
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
