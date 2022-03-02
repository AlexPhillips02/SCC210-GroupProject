package com.mygdx.Board;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Abilities.Bomb;
import com.mygdx.TileTypes.BreakableWall;
import com.mygdx.TileTypes.Path;
import com.mygdx.TileTypes.ReinforcedWall;
import com.mygdx.TileTypes.SoftWall;
import com.mygdx.TileTypes.UnbreakableWall;

/**
 * @author Alex Phillips
 * Responsible for the game board
 * Holds the squares to create the board
 */
public class Board 
{    
    Squares[][] gameSquares;
    ArrayList<Rectangle> deathSquares = new ArrayList<>(); //squares that should inflict damage when a bomb explodes
    int xLength;
    int yLength;
    
    /**
     * Creates initial board
     * @param xLength How many squares along the x axis
     * @param yLength How many squares along the y axis
     * @param percentageOfDestructableWalls Perecntage of the map to be destructable walls
     */
    public Board(int xLength, int yLength, float percentageOfDestructableWalls)
    {
        this.xLength = xLength;
        this.yLength = yLength;
        gameSquares = new Squares[xLength][yLength];
        createBasicLevelLayout();
        addDestructableWalls(percentageOfDestructableWalls);
        addCenterUnbreakableWalls();
        createStartingSquaresWalls();
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
                Squares tempSquare = new AlgorithmSquares((x * xSize), (y * ySize));

                tempSquare.setTile(tempTile);
                gameSquares[x][y] = tempSquare;
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

            //25% of breakable walls will be reinforced
            if(i % 3 == 0)
            {
                gameSquares[xPosition][yPosition].setTile(new ReinforcedWall(2));
            }
            else
            {
                gameSquares[xPosition][yPosition].setTile(new SoftWall());
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
    public void createStartingSquaresWalls() 
    {
        for (int x = 1; x <= 2; x++) 
        {
            for (int y = 1; y <= 2; y++) 
            {
                gameSquares[x][y].setTile(new SoftWall());
            }
        }
    }
    

    public void createStartingPath()
    {
        for (int x = 1; x <= 2; x++) 
        {
            for (int y = 1; y <= 2; y++) 
            {
                gameSquares[x][y].setTile(new Path());
            }
        }
    }

    public Squares getRandomPath()
	{
		int xPosition;
		int yPosition;
		Random rand = new Random();

		//Loops until spawn position is a path or a soft wall
		do
		{
			xPosition = rand.nextInt((this.getXLength() - 2) + 1);
			yPosition = rand.nextInt((this.getYLength() - 2) + 1);	
		} while (!((getGameSquare(xPosition, yPosition).getTile()) instanceof Path));

		Squares pathSquare = getGameSquare(xPosition, yPosition);
		return pathSquare;
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
                //Draws the background tile for each square
                Squares current = gameSquares[x][y].getSquare();
                batch.draw(current.getTile().getTexture(), current.getX(), current.getY());
            }
        }
    }

    public void DrawBombs(SpriteBatch batch )
    {
        for (int x = 0; x < gameSquares.length; x++) 
        {
            for (int y = 0; y < gameSquares[x].length; y++) 
            {
                //Draws the background tile for each square
                Squares current = gameSquares[x][y].getSquare();

                //If the tile also has a animation, draws the animation ontop of the tile
                if (current.getTile().getAnimation() != null) 
                {
                    //If the Tile has a bomb placed ontop of it
                    if(current.getBomb() != null)
                    {
                        bombHandling(batch, current);
                    }
                    else
                    {
                        float elapsedTime = current.getElapsedTime();

                        if (current.getTile().getAnimation().isAnimationFinished(current.getElapsedTime())) 
                        {
                            current.removeAnimation();
                        }
                        else
                        {
                            batch.draw(current.getTile().getAnimation().getKeyFrame(elapsedTime, false), current.getX(), current.getY());
                            current.setElapsedTime(elapsedTime + Gdx.graphics.getDeltaTime());
                        }
                    }
                }
            }
        }
    }

    /**
     * Method that does the bomb handling
     * @param batch spritebatch to output images
     * @param current the current square
     */
    public void bombHandling(SpriteBatch batch, Squares current)
    {
        Bomb bomb = current.getBomb();
        float elapsedTime = bomb.getElapsedTime();
        batch.draw(current.getTile().getAnimation().getKeyFrame(elapsedTime, false), current.getX(), current.getY());

        //If the bomb has finished the animation remove it and explode
        if (bomb.getAnimation().isAnimationFinished(elapsedTime)) 
        {
            createExplosion(current, bomb, deathSquares);
            bomb.explode();
        }
        else
        {
            bomb.setElapsedTime(elapsedTime += Gdx.graphics.getDeltaTime()); 
        }
    }

    /**
     * Calculates which blocks will be effected by the explosion
     * @param center Center of the explosion (Where the bomb was placed)
     * @param bomb Bomb that does the explosion
     * @param deathSquares Squares that should inflict damage when a bomb explodes - size of explosion
     */
    public void createExplosion(Squares center, Bomb bomb, ArrayList<Rectangle> deathSquares)
    {
        int size = bomb.getExplosionRange();

        Squares current = center;
        center.setAnimation(bomb.getCenterAnimation());
        deathSquares.add(current.getCollisionRectangle());

        if (size > 0) 
        {
            for (int i = 0; i < 4; i++) 
            {
                Squares next = GetNext(current, i);
                SetExplosionPath(next, size - 1, i, bomb, deathSquares);
            }   
        }
    }

    /**
     * Recursive function that calculates the affected blocks
     * @param current Current square
     * @param size Amount left to travel in that direction
     * @param direction Direction of travel (0 = left, 1 = right and so on)
     * @param bomb Bomb that does the explosion
     * @param deathSquares Squares that should inflict damage when a bomb explodes - size of explosion
     */
    public void SetExplosionPath(Squares current, int size, int direction, Bomb bomb, ArrayList<Rectangle> deathSquares)
    {
        if (current.getTile() instanceof Path && size >= 0) 
        {
            deathSquares.add(current.getCollisionRectangle());

            //If the bomb explodes on a tile with another bomb. The other bomb breaks
            if (current.getBomb() != null) 
            {
                Bomb bombRemoval = current.getBomb();
                bombRemoval.explode();
            }

            //Draws lines either vertically or horizontally
            if (direction == 0 || direction == 1) 
            {
                current.setAnimation(bomb.getExplosionLinesHorizontalAnimation());
            }
            else
            {
                current.setAnimation(bomb.getExplosionLinesVerticalAnimation());
            }
        
            size--;
            //Gets next square
            Squares next = GetNext(current, direction);
            SetExplosionPath(next, size, direction, bomb, deathSquares);
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

    public ArrayList<Rectangle> getDeathSquares() {
        return deathSquares;
    }

    public void resetDeathSquares() {
        deathSquares = new ArrayList<>();
    }

    public void dispose() 
    {
        for (int i = 0; i < gameSquares.length; i++) 
        {
            for (int j = 0; j < gameSquares[i].length; j++) 
            {
                gameSquares[i][j].getTile().dispose();
            }
        }
    }
}
