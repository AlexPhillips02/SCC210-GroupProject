package com.mygdx.TileTypes;

/**
 * @author Alex Phillips
 * Creates a soft wall, initial health of one
 */
public class SoftWall extends BreakableWall
{
    /**
     * Type of breakable wall (only takes 1 hit with bomb)
     */
    public SoftWall()
    {
        super("Wall/Box(better).png", 1);
    }
}
