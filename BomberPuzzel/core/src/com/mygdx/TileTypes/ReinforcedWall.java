package com.mygdx.TileTypes;

/**
 * @author Alex Phillips
 * Like soft wall can be destroyed but takes more than 1 hit
 */
public class ReinforcedWall extends BreakableWall
{ 
    /**
     * @param health Initial health of wall
     */
    public ReinforcedWall(int health)
    {
        super("", health);
    }
}
