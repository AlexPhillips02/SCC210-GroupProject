package com.mygdx.TileTypes;

/**
 * @author Alex Phillips
 */
public class SoftWall extends BreakableWall
{
    /**
     * Type of breakable wall (only takes 1 hit with bomb)
     * @param health Amoount of hits
     */
    public SoftWall(int health)
    {
        super("", health);
    }
}
