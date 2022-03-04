package com.mygdx.TileTypes;

/**
 * @author Alex Phillips
 * Like soft wall can be destroyed but has an intial health greater than 1
 */
public class ReinforcedWall extends BreakableWall
{ 
    /**
     * @param health Initial health of wall
     */
    public ReinforcedWall(int health)
    {
        super("Wall/BrownWall.png", health);
    }
}
