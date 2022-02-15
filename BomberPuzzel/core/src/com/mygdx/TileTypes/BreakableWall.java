package com.mygdx.TileTypes;

import com.mygdx.Board.Tile;

/**
 * @author Alex Phillips, Alex C
 * Breakable wall with a bomb
 */
public abstract class BreakableWall extends Tile
{
    int health;

    /**
     * Creates a breakable wall (soft / reinforced)
     * @param imageURL URL for the Texture
     * @param health Health of the wall (Amount of bomb hits to destroy)
     */
    public BreakableWall(String imageURL, int health)
    {
        super("core/assets/WALL-1.png (1) (1).png");
        this.health = health;
    }

    /**
     * Reduces health
     */
    public void reduceHealth()
    {
        health = health - 1;
    }

    public int getHealth()
    {
        return health;
    }

    /**
     * Checks if the wall should be broken
     * @return True if health less than 1
     */
    public boolean isBroken()
    {
        if(health <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
